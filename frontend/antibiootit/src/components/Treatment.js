import React, {useState} from "react"
import Choise from "./Choise"

export default function Treatment(props) {

    const [activeChoice, setActiveChoice] = useState(props.treatments[0]);

    const style = {
        backgroundColor: "white"
    }

    function toggleChoise(name) {
        for(let i = 0; i < props.treatments.length; i++ ) {
            if(props.treatments[i].antibiotic === name) {
                setActiveChoice(props.treatments[i]);

                const dosageValue = props.treatments[i].dosageResult.dose.value;
                const dosageUnit = props.treatments[i].dosageResult.dose.unit;
                const instructionDosesPerDay = props.treatments[i].instructions.dosesPerDay;
                const instructionDays = props.treatments[i].instructions.days;
                const recipe = `${dosageValue} ${dosageUnit} ${instructionDosesPerDay} kertaa/vrk ${instructionDays} vrk:n ajan`;
                console.log(recipe);
                props.setActiveRecipe(recipe);
            }
        }
        console.log(activeChoice);
        console.log(name);

    }

    let AntibioticElements = props.treatments.map((antibiote, index) => 
        <Choise
            key={index}
            index={index}
            name={antibiote.antibiotic}
            dosage={`${antibiote.dosageFormula.strength.text}`}
            dose={`${antibiote.dosageResult.dose.value} ${antibiote.dosageResult.dose.unit}`}
            doseInDay={`${antibiote.dosageResult.dose.value * antibiote.instructions.dosesPerDay} ${antibiote.dosageResult.dose.unit}`}
            instruction={`${antibiote.instructions.dosesPerDay} krt/vrk, yht ${antibiote.instructions.days} vrk ajan`}
            toggleChoise={toggleChoise}
            choise={antibiote.antibiotic === activeChoice.antibiotic ? true : false}
            diagnose={props.diagnose}
            length={props.treatments.length}
        />
    )

    const [openCalculations, setOpenCalculations] = React.useState(false);
    
    function calculate() {
        setOpenCalculations(prevStatus => !prevStatus)
    }

    if(!props.treatments) {
        return <p>Haetaan hoitosuosituksia...</p>
    }

    return (
        <div className="treatment-container">
            <div className="treatment-header">
                {<div className="treatment-icon"></div>}
                <h2>{props.diagnosis==="Bronkiitti" ?
                `Ei antibioottisuositusta` :
                `Hoitosuositus ${props.format.toLowerCase()}na`}</h2>
            </div>
            <div className="treatment-choises">
                <div className="choise-container">
                    {props.diagnosis !== "Bronkiitti" ? AntibioticElements : 
                    <div className="choise" style={style}>
                        <div className="choise-inner">
                            <p>Bronkiitin hoitoon <strong>ei suositella antibioottia.</strong></p>
                        </div>
                    </div>}
                </div>
            </div>
            <div className="treatment-extra">
                <button className="btn-calculate" onClick={calculate} disabled={props.diagnose==="Bronkiitti"}>
                    {openCalculations ?
                    <p><ion-icon name="eye-off-outline"></ion-icon> Piilota kaava</p> :
                    <p><ion-icon name="calculator-outline"></ion-icon> Laskukaava</p>}
                </button>
                {!openCalculations && <div className="test2-container">
                    {props.diagnose==="Välikorvatulehdus" &&
                    <div className="strepto-info">
                        <p><ion-icon name="help-circle-outline"></ion-icon></p>
                        <p>60% Välikorvatulehduksista paranee ilman antibioottia</p>
                    </div>}
                </div>}
            </div>
            {openCalculations && <div className="treatment-calculations">
                Laskukaavat tähän
            </div>}
        </div>
    )
}