import React from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";

export default function Antibiotics() {
    /** Conditional rendering!!
     *  Sit ku painaa Laske suositus niin kokonaan pois
     * eli joku arvo muuttuu falseksi?
     *  Muuten ekaks on vaihe yks ja sit Vaihe kaks ku on valinnu
     *  sairauden. Eli ternaryllä? Vai conditional?
     */
    const [instruction, setInstruction] = React.useState({
        state: "Vaihe 1",
        text: "Valitse ensin potilaan diagnoosi"
    });

    /** State muuttuu ku valitaan tauti ja vastaavan taudin tiedot
     * tulee bäkistä? Nyt vaan kovakoodattu. Alle 40kg = mikstuura ja yli tabletti?
     * Bäkistä tulee json/ array mitä tulikaan, niin otetaan indexin mukaan
     * arvo objectiin onko toissijainen vai ensisijainen?
     * {index === 0 ? true : false} esimerkiksi, mutta vaikee tehdä esim vasta
     * propsina, ksoka aktiivisen vaihdossa käytän sitä tuolta? tota
     * "firstChoise"a pitää käyttää myös siihen mihin näytetään reseptinä.
     * Mieti Miian kanssa mikä olisi hyvä.
    */

    const [antibiotic, setAntibiotic] = React.useState([
        {
            id: "J03.0",
            format: "Tabletti",
            name: "Amoksisilliini",
            dosage: "100mg/ml",
            dose: "2ml",
            doseInDay: "4ml",
            instruction: "2 krt/vrk, yht 5rk ajan",
            firstChoise: true,
            recipe: "Ensimmäisen vaihtoehdon resepti"
        },
        {
            id: "J03.1",
            format: "Tabletti",
            name: "Amoksisilliini2",
            dosage: "100mg/ml2",
            dose: "2ml2",
            doseInDay: "4ml2",
            instruction: "2 krt/vrk, yht 5rk ajan2",
            firstChoise: false,
            recipe: "Toisen vaihtoehdon resepti"
        }
    ]);

    const [activeRecipe, setActiveRecipe] = React.useState(antibiotic[0].recipe);
    console.log(activeRecipe); /** Miks tulostaa kaks kertaa :( */

    function toggleChoise(name) {
        for(let i = 0; i < antibiotic.length; i++) {
            if(antibiotic[i].name === name) {
                setActiveRecipe(antibiotic[i].recipe)
            }
        }

        setAntibiotic(prevAntibiotic => {
            return prevAntibiotic.map((antibiote) => {
                return antibiote.name === name ? 
                {...antibiote, firstChoise: true} : {...antibiote, firstChoise: false}
            })
        })
    }

    
    return (
        <div className="antibiotics">
            <section>
                <h1>Antibioottilaskuri</h1>
                <h2>{instruction.state}</h2>
                <hr className="line"></hr>
                <p>{instruction.text}</p>
            </section>
            <Form />
            <Treatment 
                antibiotic={antibiotic}
                toggleChoise={toggleChoise}
            />
            <Recipe />
        </div>
    );
}