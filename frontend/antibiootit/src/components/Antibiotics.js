import React, {useEffect, useState} from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import NoTreatment from "./NoTreatment";
import Recipe from "./Recipe";
import GetDiagnoses from "./GetDiagnoses";
import GetInfoTexts from "./GetInfoTexts";
import GetRecommendedTreatment from "./GetRecommendedTreatment";

const STEP1 = 7;
const STEP4 = 13;

export default function Antibiotics() {

    const [chosenDiagnosis, setChosenDiagnosis] = useState("");
    const [diagnosisData, setDiagnosisData] = useState("");
    const [chosenWeight, setChosenWeight] = useState(null);
    const [noAntibioticTreatment, setNoAntibioticTreatment] = useState(null);
    
    const [diagnoses, setDiagnoses] = useState(null);
    const [infoTexts, setInfoTexts] = useState(null);

    const [treatments, setTreatments] = useState(null);

    const [instruction, setInstruction] = useState([]);

    const [loading, setLoading] = useState(true);

    async function fetchData() {
        const diagnosesList = await GetDiagnoses();
        setDiagnoses(diagnosesList);

        const infoTextsList = await GetInfoTexts();
        setInfoTexts(infoTextsList);

        setInstruction(infoTextsList[STEP1]);
    }
    useEffect(() => {
        fetchData();
    }, []);

    const [activeRecipe, setActiveRecipe] = useState(null);

    const [formSubmitted, setFormSubmitted] = useState(false);

    const receiveInput = (data) => {
        if (data.diagnosisId !== "") {
            setFormSubmitted(true);
            setInstruction(infoTexts[STEP4]);
        }

        const selected = diagnoses.filter(infection => infection.id === data.diagnosisID)[0];

        if (selected.needsAntibiotics) {
            setLoading(true);
            GetRecommendedTreatment(data)
            .then(response => {
                setTreatments(response.treatments);
                setLoading(false);
                // Also set the first active recipe 
                const dosageValue = response.treatments[0].dosageResult.dose.value;
                const dosageUnit = response.treatments[0].dosageResult.dose.unit;
                const instructionDosesPerDay = response.treatments[0].instructions.dosesPerDay;
                const instructionDays = response.treatments[0].instructions.days;
                const recipe = `${dosageValue} ${dosageUnit} ${instructionDosesPerDay} kertaa/vrk ${instructionDays} vrk:n ajan`;
                const antibiote = response.treatments[0].antibiotic;
                const strength = response.treatments[0].dosageFormula.strength.text;

                const treatment = {
                    text: recipe,
                    antibioteName: antibiote,
                    antibioteStrength: strength
                }
                setActiveRecipe(treatment);
            })
            .catch(error => {
                console.log(error)
                console.log("Tarkista lomakkeen arvot")
            });
                
        }
        else {
            console.log("No need for antibiotic treatment")
        }
    }

    function changeInstruction(index) {
        setInstruction(infoTexts[index]);
    }

    useEffect(() => {   
        if (chosenDiagnosis !== null && diagnoses !== null) {
            setDiagnosisData(diagnoses.filter(infection => infection.name === chosenDiagnosis)[0]);
            if (chosenDiagnosis === 'Bronkiitti') {
                setNoAntibioticTreatment({id: 'J21.9', text: infoTexts[14].text})
                setFormSubmitted(true);
            }
            else if (chosenDiagnosis === 'Obstruktiivinen bronkiitti') {
                setNoAntibioticTreatment({id: 'J20.9', text: infoTexts[15].text})
                setFormSubmitted(true);
            }
            else {
                setNoAntibioticTreatment(null);
                setFormSubmitted(false);
            }
        }

    }, [chosenDiagnosis, diagnoses, infoTexts])

    
    return (
        <div className="antibiotics">
            <section>
                <h1>Lapsen antibioottilaskuri</h1>
                <div className="antibiotic-instructions">
                    <h2>{instruction.header}</h2>
                    <hr className="line"></hr>
                    <p>{instruction.text}</p>
                </div>
            </section>
            <Form 
                diagnoses={diagnoses}
                handleSubmit={receiveInput} 
                changeInstruction={changeInstruction} 
                setChosenDiagnosis={setChosenDiagnosis}
                setChosenWeight={setChosenWeight}
                formSubmitted={formSubmitted} 
            />
            {formSubmitted && !!noAntibioticTreatment && <NoTreatment />}
            {formSubmitted && (treatments && diagnosisData.needsAntibiotics)  && <Treatment 
                loading={loading}
                needsAntibiotics={diagnosisData.needsAntibiotics}
                description={treatments[0].description}
                diagnosis={chosenDiagnosis}
                weight={chosenWeight}
                treatments={treatments}
                setActiveRecipe={setActiveRecipe}
                format={treatments[0].format}
            />}
            {formSubmitted && (treatments || !!noAntibioticTreatment) && <Recipe 
                treatments={treatments} 
                activeRecipe={activeRecipe} 
                diagnosisData={diagnosisData}
                noTreatment={noAntibioticTreatment} />}
        </div>
    );
}