import React from "react"
import Choise from "./Choise"

export default function Treatment() {
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
            firstChoise: true
        },
        {
            id: "J03.1",
            format: "Tabletti",
            name: "Amoksisilliini2",
            dosage: "100mg/ml2",
            dose: "2ml2",
            doseInDay: "4ml2",
            instruction: "2 krt/vrk, yht 5rk ajan2",
            firstChoise: false
        }
    ]);


    function toggleChoise(name) {
        setAntibiotic(prevAntibiotic => {
            return prevAntibiotic.map((antibiote) => {
                return antibiote.name === name ? 
                {...antibiote, firstChoise: true} : {...antibiote, firstChoise: false}
            })
        })
    }

    let AntibioticElements

    if(antibiotic.length > 1) {
        AntibioticElements = antibiotic.map((antibiote, index) => 
            <Choise
                key={antibiote.id}
                index={index}
                name={antibiote.name}
                dosage={antibiote.dosage}
                dose={antibiote.dose}
                doseInDay={antibiote.doseInDay}
                instruction={antibiote.instruction}
                choise = {antibiote.firstChoise}
                toggleChoise={toggleChoise}
            />
        )
    } else {
        AntibioticElements = antibiotic.map((antibiote, index) => 
            <Choise
                key={antibiote.id}
                index={index}
                name={antibiote.name}
                dosage={antibiote.dosage}
                dose={antibiote.dose}
                doseInDay={antibiote.doseInDay}
                instruction={antibiote.instruction}
                choise={antibiote.firstChoise}
            />
        )
    }

    return (
        <div className="treatment-container">
            <h2>{`Hoitosuositus ${antibiotic[0].format.toLowerCase()}na`}</h2>
            <div>
                <div className="choise-container">
                    {AntibioticElements}
                </div>
            </div>
        </div>
    )
}