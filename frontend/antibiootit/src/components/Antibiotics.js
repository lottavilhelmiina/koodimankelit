import React from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";

export default function Antibiotics() {

    const receiveWeight = (diagnose, weight) => {
        console.log("Diagnoosi: " + diagnose);
        console.log("Lapsen paino: " + weight + " kg");
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