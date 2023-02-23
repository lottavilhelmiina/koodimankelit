import React from "react"

export default function Treatment() {
    /** State muuttuu ku valitaan tauti ja vastaavan taudin tiedot
     * tulee bäkistä? Nyt vaan kovakoodattu. Alle 40kg = mikstuura ja yli tabletti?
     * Bäkistä tulee json/ array mitä tulikaan, niin otetaan indexin mukaan
     * arvo objectiin onko toissijainen vai ensisijainen? Myös ensisijaiselle
     * ja toissijaiselle täytyy antaa eri värit taustaksi. Klikkaamalla vaihtaa
     * aktiivista. State joo, onClick joo, mutta miten alkutilanne määrätään?
    */

    const [antibiotic, setAntibiotic] = React.useState([
        {
            format: "tablettina",
            name: "Amoksisilliini",
            dosage: "100mg/ml",
            dose: "2ml",
            doseInDay: "4ml",
            instruction: "2 krt/vrk, yht 5rk ajan",
            choise: "Ensisijainen"
        },
        {
            format: "tablettina2",
            name: "Amoksisilliini2",
            dosage: "100mg/ml2",
            dose: "2ml2",
            doseInDay: "4ml2",
            instruction: "2 krt/vrk, yht 5rk ajan2",
            choise: "Toissijainen"
        }
    ]);

    const AntibioticElements = () => (
        <>
            {antibiotic.map((antibiote, index) => (
                <div key={index}>
                    <h4>{antibiote.choise} valinta</h4>
                    <p>Antibiootti: {antibiote.name} {antibiote.dosage}</p>
                    <p>Kerta-annos: {antibiote.dose}</p>
                    <p>Vuorokausiannos: {antibiote.doseInDay}</p>
                    <p>Lääkkeenotto: {antibiote.instruction}</p>
                </div>
            ))}
        </>
    );

    return (
        /**if lause enssijaisesta ja toissijaisesta? Miten?
         * Ja miten eri väreiksi?
         */
        <div className="treatment-container">
            <h2>Hoitosuositus {antibiotic.format}</h2>
            <div className="choise-container">
                <AntibioticElements />
            </div>
        </div>
    )
}

/**
 * <h4>Ensisijainen valinta:</h4>
 * <p>Antibiootti: {antibiotic.name} {antibiotic.dosage}</p>
    <p>Kerta-annos: {antibiotic.dose}</p>
    <p>Vuorokausiannos: {antibiotic.doseInDay}</p>
    <p>Lääkkeenotto: {antibiotic.instruction}</p>
 */