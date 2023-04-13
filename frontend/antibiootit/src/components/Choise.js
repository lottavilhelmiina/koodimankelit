import React from "react";

export default function Choise(props) {
    
    const styles = {
        backgroundColor: props.choise ? "white" : "#D7E2F2",
        cursor: props.choise ? "auto" : "pointer"
    };

    const choiseNbr = props.index === 0 ? "Ensisijainen" : "Toissijainen"

    const ChoiseTxt = () => {
        const txt =
        <>
            {props.length > 1 && <div className="choise-header">
                <h4>{`${choiseNbr} valinta: ${props.name} ${props.dosage}`}</h4>
                {!props.choise && <ion-icon name="chevron-down-outline" size="large"></ion-icon>}
            </div>}
            {props.choise && <div className="choise-inner">
                {props.length === 1 && <h4>{props.name} {props.dosage}</h4>}
                <p>Kerta-annos: {props.dose}</p>
                <p>Vuorokausiannos: {props.doseInDay}</p>
                <p>Annostelu: {props.instruction}</p>
            </div>}
        </>
        
        return txt;
    }

    return (
        <div className="choise" style={styles} onClick={() => props.toggleChoise(props.name)}>
            <ChoiseTxt />
        </div>
    );
}