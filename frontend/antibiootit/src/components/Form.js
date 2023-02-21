import React, { useState } from "react";

export default function Form({ handleSubmit }) {
    
    const [weightInput, setWeightInput] = useState("");
    const [selectedDiagnose, setSelectedDiagnose] = useState("");

    const handleInput = (e) => {
        e.preventDefault();
		setWeightInput(e.target.value);
	}

    const handleSelection = (e) => {
        e.preventDefault();
        setSelectedDiagnose(e.target.textContent);
    }

    const handleClick = (e) => {
        e.preventDefault();
        handleSubmit(selectedDiagnose, weightInput);
        //setWeightInput("");
    }

    return (
        <form className="diagnose-form" onSubmit={handleClick}>
            <div className="diagnose-menu">
                <a href="#" className="menu--top">Valitse diagnoosi</a>
                <a href="#" onClick={handleSelection}>Streptokokki-tonsilliitti</a>
                <a href="#" onClick={handleSelection}>Välikorvatulehdus</a>
                <a href="#" onClick={handleSelection}>Sivuontelotulehdus</a>
                <a href="#" onClick={handleSelection}>Bronkiitti</a>
                <a href="#" onClick={handleSelection}>Obstruktiivinen bronkiitti</a>
                <a href="#" onClick={handleSelection} className="menu--bottom">Avohoitopneumonia</a>
            </div>
            <input
                id="weight-input"
                className="form--weight"
                placeholder="Syötä paino"
                name="weight"
                value={weightInput}
                onChange={handleInput}
                type="text"
                required={true}
                maxLength="4"
            />
            <button className="form--button" type="submit">Laske suositus</button>
        </form>
    );
}