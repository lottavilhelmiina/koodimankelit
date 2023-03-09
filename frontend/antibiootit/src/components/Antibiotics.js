import React, {useState} from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";


export default function Antibiotics() {

    const [chosenDiagnose, setChosenDiagnose] = useState("");
    
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
            dosage: "100mg/ml",
            dose: "2ml",
            doseInDay: "4ml",
            instruction: "2 krt/vrk, yht 5vrk ajan",
            recipe: "Ensimmäisen vaihtoehdon resepti",
            choise: true
        },
        {
            id: "J03.1",
            format: "Tabletti",
            name: "Amoksisilliini2",
            dosage: "100mg/ml2",
            dose: "2ml2",
            doseInDay: "4ml2",
            instruction: "2 krt/vrk, yht 5vrk ajan2",
            recipe: "Toisen vaihtoehdon resepti",
            choise: false
        }
    ]);

    const [activeRecipe, setActiveRecipe] = useState(antibiotic[0].recipe);

    const [formSubmitted, setFormSubmitted] = useState(false);

    const receiveWeight = (diagnose, weight) => {
        if (diagnose !== "") {
            setFormSubmitted(true);
        }
        console.log("Lapsen paino: " + weight + " kg ja diagnoosi: " + diagnose);
    }

    function changeInstruction(index) {
        setInstruction(instructions[index]);
    }

    
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
                handleSubmit={receiveWeight} 
                changeInstruction={changeInstruction} 
                setChosenDiagnose={setChosenDiagnose} 
            />
            {formSubmitted && <Treatment 
                diagnose={chosenDiagnose}
                antibiotic={antibiotic}
                setAntibiotic={setAntibiotic}
                activeRecipe={activeRecipe}
                setActiveRecipe={setActiveRecipe}
            />}
            {formSubmitted && <Recipe />}
        </div>
    );
}