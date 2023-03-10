import React from "react";

export default function Choise(props) {
    const styles = {
        backgroundColor: props.choise ? "#FFFFFF" : "#D7E2F2"
    };



    const directionUp = "chevron-up-outline";
    const directionDown = "chevron-down-outline"

    const choiseNbr = props.index === 0 ? "Ensisijainen" : "Toissijainen"

    const ChoiseTxt = () => {
        const txt = props.diagnose !== "Bronkiitti" ?
        <>
            {props.length > 1 && <div className="choise-header">
                <h4>{`${choiseNbr} valinta`}</h4>
                <ion-icon name={props.choise ? directionUp : directionDown} size="large"></ion-icon>
            </div>}
            {props.choise && <div className="choise-inner">
                <p>Antibiootti: {props.name} {props.dosage}</p>
                <p>Kerta-annos: {props.dose}</p>
                <p>Vuorokausiannos: {props.doseInDay}</p>
                <p>Lääkkeenotto: {props.instruction}</p>
            </div>}
        </> :
        <div className="choise-inner">
            <p>Bronkiitin hoitoon <strong>ei suositella antibioottia.</strong></p>
        </div>
        
        return txt;
    }

    return (
        <div className="choise" style={styles} onClick={() => props.toggleChoise(props.name)}>
            <ChoiseTxt />
        </div>
    );
}