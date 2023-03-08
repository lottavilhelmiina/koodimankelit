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
    const disease = "Streptokokki-tonsilliitti" /** käyttäjän valinta oikeestis */

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

    const [activeRecipe, setActiveRecipe] = React.useState(antibiotic[0].recipe);

    const [formSubmitted, setFormSubmitted] = useState(false);

    const receiveWeight = (diagnose, weight) => {
        if (diagnose !== "") {
            setFormSubmitted(true);
        }
        
        console.log("Lapsen paino: " + weight + " kg ja diagnoosi: " + diagnose);
    }

    
    return (
        <div className="antibiotics">
            <section>
                <h1>Antibioottilaskuri</h1>
                <h2>{instruction.state}</h2>
                <hr className="line"></hr>
                <p>{instruction.text}</p>
            </section>
            <Form handleSubmit={receiveWeight} />
            {formSubmitted && <Treatment 
                disease={disease}
                antibiotic={antibiotic}
                setAntibiotic={setAntibiotic}
                activeRecipe={activeRecipe}
                setActiveRecipe={setActiveRecipe}
            />}
            {formSubmitted && <Recipe />}
    );
}