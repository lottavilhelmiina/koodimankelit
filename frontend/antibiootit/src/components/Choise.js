import React from "react";

export default function Choise(props) {
    const styles = {
        backgroundColor: props.choise ? "#D7E2F2" : "#FFFFFF",
        color: props.choise ? "black" : "#8F8F8F",
    };

    const choiseNbr = props.index === 0 ? "Ensimmäinen" : "Toinen"

    return (
        <div className="choise" style={styles} onClick={() => props.toggleChoise(props.name)}>
            <div className="choise-inner">
                <h4>{`${choiseNbr} valinta`}</h4>
                <p>Antibiootti: {props.name} {props.dosage}</p>
                <p>Kerta-annos: {props.dose}</p>
                <p>Vuorokausiannos: {props.doseInDay}</p>
                <p>Lääkkeenotto: {props.instruction}</p>
            </div>
        </div>
    );
}