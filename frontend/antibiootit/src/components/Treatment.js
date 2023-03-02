import React from "react"
import Choise from "./Choise"

export default function Treatment(props) {

    let AntibioticElements

    if(props.antibiotic.length > 1) {
        AntibioticElements = props.antibiotic.map((antibiote, index) => 
            <Choise
                key={antibiote.id}
                index={index}
                name={antibiote.name}
                dosage={antibiote.dosage}
                dose={antibiote.dose}
                doseInDay={antibiote.doseInDay}
                instruction={antibiote.instruction}
                choise = {antibiote.firstChoise}
                toggleChoise={props.toggleChoise}
            />
        )
    } else {
        AntibioticElements = props.antibiotic.map((antibiote, index) => 
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
            <h2>{`Hoitosuositus ${props.antibiotic[0].format.toLowerCase()}na`}</h2>
            <div>
                <div className="choise-container">
                    {AntibioticElements}
                </div>
            </div>
        </div>
    )
}