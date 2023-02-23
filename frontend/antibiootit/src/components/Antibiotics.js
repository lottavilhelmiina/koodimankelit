import React from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";

export default function Antibiotics() {

    const receiveWeight = (diagnose, weight) => {
        console.log("Lapsen paino: " + weight + " kg ja diagnoosi: " + diagnose);
    }

    return (
        <>
        <section></section>
        <Form handleSubmit={receiveWeight} />
        <Treatment />
        <Recipe />
        </>
    );
}