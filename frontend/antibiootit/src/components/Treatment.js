import React, {useState} from "react"
import Choise from "./Choise"
import 'katex/dist/katex.min.css';
import { InlineMath } from 'react-katex';

export default function Treatment(props) {

    const [activeChoice, setActiveChoice] = useState(props.treatments[0]);
    const [activeVariables, setActiveVariables] = useState({
        weight: `${props.weight}kg`,
        doseInDay: `${props.treatments[0].dosageFormula.dosage.value} ${props.treatments[0].dosageFormula.dosage.unit}`,
        dosesPerDay: `${props.treatments[0].instructions.dosesPerDay} krt/vrk`,
        strength: `${props.treatments[0].dosageFormula.strength.text}`,
        result: `${props.treatments[0].dosageResult.dose.value} ${props.treatments[0].dosageResult.dose.unit}`,
        accResult: `${props.treatments[0].dosageResult.accurateDose.value} ${props.treatments[0].dosageResult.accurateDose.unit}`
    });

    const style = {
        backgroundColor: "white"
    }

    function toggleChoise(name) {
        for(let i = 0; i < props.treatments.length; i++ ) {
            if(props.treatments[i].antibiotic === name) {
                setActiveChoice(props.treatments[i]);

                setActiveVariables({
                    weight: `${props.weight}kg`,
                    doseInDay: `${props.treatments[i].dosageFormula.dosage.value} ${props.treatments[i].dosageFormula.dosage.unit}`,
                    dosesPerDay: `${props.treatments[i].instructions.dosesPerDay} krt/vrk`,
                    strength: `${props.treatments[i].dosageFormula.strength.text}`,
                    result: `${props.treatments[i].dosageResult.dose.value} ${props.treatments[i].dosageResult.dose.unit}`,
                    accResult: `${props.treatments[i].dosageResult.accurateDose.value} ${props.treatments[i].dosageResult.accurateDose.unit}`
                });

                const dosageValue = props.treatments[i].dosageResult.dose.value;
                const dosageUnit = props.treatments[i].dosageResult.dose.unit;
                const instructionDosesPerDay = props.treatments[i].instructions.dosesPerDay;
                const instructionDays = props.treatments[i].instructions.days;
                const recipe = `${dosageValue} ${dosageUnit} ${instructionDosesPerDay} kertaa/vrk ${instructionDays} vrk:n ajan`;
                const antibiote = props.treatments[i].antibiotic;
                const strength = props.treatments[i].dosageFormula.strength.text;

                const treatment = {
                    text: recipe,
                    antibioteName: antibiote,
                    antibioteStrength: strength
                }

                props.setActiveRecipe(treatment);
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

    function MathFormula(mathprops) {
        return (
            <div className="calculations-container">
                <InlineMath math={`\\frac{\\frac{${mathprops.weight} \\cdot 
                ${mathprops.doseInDay}}{${mathprops.dosesPerDay}}}{${mathprops.strength}}
                =${mathprops.accResult} \\approx ${mathprops.result}`}/>
            </div>
        )
    }

    if(!props.treatments) {
        return <p>Haetaan hoitosuosituksia...</p>
    }

    console.log(props.needsAntibiotics);

    return (
        <div className="treatment-container">
            <div className="treatment-header">
                {<div className="treatment-icon"></div>}
                <h2>{!props.needsAntibiotics ?
                `Ei antibioottisuositusta` :
                `Hoitosuositus ${props.format.toLowerCase()}na`}</h2>
            </div>
            <div className="treatment-choises">
                <div className="choise-container">
                    {props.needsAntibiotics ? AntibioticElements : 
                    <div className="choise" style={style}>
                        <div className="choise-inner">
                            <p>Tämän diagnoosin hoitoon <strong>ei suositella antibioottia.</strong></p>
                        </div>
                    </div>}
                </div>
            </div>
            <div className="treatment-extra">
                <button className="btn-calculate" onClick={calculate} disabled={props.diagnose==="Bronkiitti"}>
                    {openCalculations ?
                    <div className="btn-elements">
                        <img className="func-icon-closed" src="./icons/function-icon-closed.svg" alt="icon-closed"/>
                        <p> Piilota kaava</p> 
                    </div> :
                    <div className="btn-elements">
                        <img className="func-icon-open" src="./icons/function-icon-open.svg" alt="icon-open"/>
                        <p> Laskukaava</p>
                    </div>}
                </button>
                {!openCalculations && <div className="description-container">
                    {props.description!=="" &&
                    <div className="strepto-info">
                        <p><ion-icon name="help-circle-outline"></ion-icon></p>
                        <p>{props.description}</p>
                    </div>}
                </div>}
            </div>
            {openCalculations && <div className="treatment-calculations">
                <MathFormula
                    weight={activeVariables.weight}
                    doseInDay={activeVariables.doseInDay}
                    dosesPerDay={activeVariables.dosesPerDay}
                    strength={activeVariables.strength}
                    result={activeVariables.result}
                    accResult={activeVariables.accResult}
                />
            </div>}
        </div>
    )
}