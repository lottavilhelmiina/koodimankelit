import React, {useEffect, useState} from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";
import GetDiagnoses from "./GetDiagnoses";
import GetInfoTexts from "./GetInfoTexts";
import GetRecommendedTreatment from "./GetRecommendedTreatment";

export default function Antibiotics() {

    const [chosenDiagnosis, setChosenDiagnosis] = useState("");
    
    const [diagnoses, setDiagnoses] = useState([]);
    const [infoTexts, setInfoTexts] = useState([]);


    async function fetchData() {
        const diagnosesList = await GetDiagnoses();
        setDiagnoses(diagnosesList);

        const infoTextsList = await GetInfoTexts();
        setInfoTexts(infoTextsList);
    }
    useEffect(() => {
        fetchData();
    }, []);

    console.log(infoTexts);

    const instructions = [
        {
            state: "Vaihe 1",
            text: "Valitse ensin potilaan diagnoosi"
        },
        {
            state: "Vaihe 2",
            text: "Syötä sitten potilaan paino.",
            textCheck: "Tarkista vielä, onko potilaalla samansaikaista EBV-infektiota tai penisilliiniallergiaa."
        },
        {
            state: "Vaihe 3",
            text: "Laske sitten antibioottisuositus painamalla Laske suositus -painiketta."
        }
    ]

    const [instruction, setInstruction] = useState(instructions[0]);


    /** State muuttuu ku valitaan tauti ja vastaavan taudin tiedot
     * tulee bäkistä? Nyt vaan kovakoodattu. Alle 40kg = mikstuura ja yli tabletti?
     * Bäkistä tulee json/ array mitä tulikaan, niin otetaan indexin mukaan
     * arvo objectiin onko toissijainen vai ensisijainen?
     * {index === 0 ? true : false} esimerkiksi, mutta vaikee tehdä esim vasta
     * propsina, ksoka aktiivisen vaihdossa käytän sitä tuolta? tota
     * "firstChoise"a pitää käyttää myös siihen mihin näytetään reseptinä.
    */

    const [antibiotic, setAntibiotic] = useState([
        {
            id: "J03.0",
            format: "Tabletti",
            name: "Amoksisilliini",
            dosage: "100 mg/ml",
            dose: "2 ml",
            doseInDay: "4 ml",
            instruction: "2 krt/vrk, yht 5 vrk ajan",
            recipe: "Ensimmäisen vaihtoehdon resepti",
            choise: true
        },
        {
            id: "J03.1",
            format: "Tabletti",
            name: "Kefaleksiini",
            dosage: "100 mg/ml",
            dose: "2 ml",
            doseInDay: "4 ml",
            instruction: "2 krt/vrk, yht 6 vrk ajan",
            recipe: "Toisen vaihtoehdon resepti",
            choise: false
        }
    ]);

    const [activeRecipe, setActiveRecipe] = useState(antibiotic[0].recipe);

    const [formSubmitted, setFormSubmitted] = useState(false);

    const receiveInput = (data) => {
        if (data.diagnosis !== "") {
            setFormSubmitted(true);
        }

        console.log(data);
        const ebv = data.concurrentEBV;
        const mp = data.concurrentMycoplasma;
              
        console.log("Lapsen paino: " + data.weight + " kg ja diagnoosi: " + data.diagnosis);
        console.log(ebv ? "Lapsella on samanaikainen ebv" : "Lapsella ei ole samanaikaista ebv:tä");
        console.log(mp ? "Lapsella on samanaikainen mykoplasma" : "Lapsella ei ole samanaikaista mykoplasmaa");

       GetRecommendedTreatment(data);
    }

    function changeInstruction(index) {
        setInstruction(instructions[index]);
    }
    console.log({chosenDiagnosis})
    
    return (
        <div className="antibiotics">
            <section>
                <h1>Lapsen antibioottilaskuri</h1>
                {!formSubmitted && <div className="antibiotic-instructions">
                    <h2>{instruction.state}</h2>
                    <hr className="line"></hr>
                    <p>{instruction.text}</p>
                    {instruction.textCheck && <p>{instruction.textCheck}</p>}
                </div>}
            </section>
            <Form 
                diagnoses={diagnoses}
                handleSubmit={receiveInput} 
                changeInstruction={changeInstruction} 
                setChosenDiagnosis={setChosenDiagnosis} 
            />

            {formSubmitted && <Treatment 
                diagnosis={chosenDiagnosis}
                antibiotic={antibiotic}
                setAntibiotic={setAntibiotic}
                activeRecipe={activeRecipe}
                setActiveRecipe={setActiveRecipe}
            />}
            {formSubmitted && <Recipe abChoices={antibiotic} activeRecipe={activeRecipe} diagnosis={chosenDiagnosis} />}
        </div>
    );
}