import React from "react";
import Form from "./Form";
import Treatment from "./Treatment";
import Recipe from "./Recipe";

export default function Antibiotics() {
    /** Conditional rendering!!
     *  Sit ku painaa Laske suositus niin kokonaan pois
     * eli joku arvo muuttuu falseksi?
     *  Muuten ekaks on vaihe yks ja sit Vaihe kaks ku on valinnu
     *  sairauden. Eli ternaryllä? Vai conditional?
     */
    const [instruction, setInstruction] = React.useState({
        state: "Vaihe 1",
        text: "Valitse ensin potilaan diagnoosi"
    });
    
    return (
        <div className="antibiotics">
            <section>
                <h1>Antibioottilaskuri</h1>
                <h2>{instruction.state}</h2>
                <hr className="line"></hr>
                <p>{instruction.text}</p>
            </section>
            <Form />
            <Treatment />
            <Recipe />
        </div>
    );
}