import React, {useEffect, useState} from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";
import GetDiagnoses from "./GetDiagnoses";
import GetInfoTexts from "./GetInfoTexts";
import GetRecommendedTreatment from "./GetRecommendedTreatment";

const STEP1 = 7;
const STEP4 = 13;

export default function Antibiotics() {

    const [chosenDiagnosis, setChosenDiagnosis] = useState("");
    
    const [diagnoses, setDiagnoses] = useState(null);
    const [infoTexts, setInfoTexts] = useState(null);

    const [treatments, setTreatments] = useState(null);

    const [instruction, setInstruction] = useState([]);


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

    //console.log(infoTexts);

    const [activeRecipe, setActiveRecipe] = useState("");

    useEffect(() => {
        console.log("Active recipe toggled");
    }, [activeRecipe])

    const [formSubmitted, setFormSubmitted] = useState(false);

    const receiveInput = (data) => {
        if (data.diagnosis !== "") {
            setFormSubmitted(true);
            setInstruction(infoTexts[STEP4]);
        }

        // Case bronchitis not yet implemented
        if (data.diagnosisID !== 'J20.9' && data.diagnosisID !== 'J21.9') {
            GetRecommendedTreatment(data)
            .then(response => {
                setTreatments(response.treatments);
                // Also set the first active recipe
                const dosageValue = response.treatments[0].dosageResult.dose.value;
                const dosageUnit = response.treatments[0].dosageResult.dose.unit;
                const instructionDosesPerDay = response.treatments[0].instructions.dosesPerDay;
                const instructionDays = response.treatments[0].instructions.days;
                const recipe = `${dosageValue} ${dosageUnit} ${instructionDosesPerDay} kertaa/vrk ${instructionDays} vrk:n ajan`;
                setActiveRecipe(recipe);
                
                console.log(treatments.length);
            })
            .catch(error => {
                console.log(error)
            });
            
        }
    }

    function changeInstruction(index) {
        setInstruction(infoTexts[index]);
    }
    console.log({chosenDiagnosis})

    if(!diagnoses || !infoTexts) {
        return <p className="loading-text">Loading...</p>
    }
    
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
                formSubmitted={formSubmitted} 
            />
            {formSubmitted && treatments && <Treatment 
                diagnosis={chosenDiagnosis}
                treatments={treatments}
                setActiveRecipe={setActiveRecipe}
                format={treatments[0].format}
            />}
            {formSubmitted && treatments && <Recipe 
                treatments={treatments} 
                activeRecipe={activeRecipe} 
                diagnosis={chosenDiagnosis} />}
        </div>
    );
}