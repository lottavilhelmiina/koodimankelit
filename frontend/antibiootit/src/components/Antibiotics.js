import React, { useState } from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";


export default function Antibiotics() {

    const [formSubmitted, setFormSubmitted] = useState(false);

    const receiveWeight = (diagnose, weight) => {
        if (diagnose !== "") {
            setFormSubmitted(true);
        }
        
        console.log("Lapsen paino: " + weight + " kg ja diagnoosi: " + diagnose);
    }

    return (
        <div className="antibiotics-content">
            <section></section>
            <Form handleSubmit={receiveWeight} />
            <Treatment />
            {formSubmitted && <Recipe />}
        </div>
    );
}