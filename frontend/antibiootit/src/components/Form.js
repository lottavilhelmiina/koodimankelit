import React, { useState } from "react";
import ABCheckbox from "./ABCheckbox";

export default function Form({ handleSubmit }) {
    
    /**
     * States and items for the drop-down menu for selecting the diagnose.
     * All items are shown by default.
     * After clicking an item, only the selected item is visible.
     */
    const [diagnose, setDiagnose] = useState("");
    const [showAll, setShowAll] = useState(true);
    const menuItems = ["Streptokokki-tonsilliitti"
                     , "Välikorvatulehdus"
                     , "Sivuontelotulehdus"
                     , "Bronkiitti"
                     , "Obstruktiivinen bronkiitti"
                     , "Avohoitopneumonia"];

    /**
     * State for child's weight.
     */
    const [weight, setWeight] = useState("");

    const handleInput = (e) => {
        e.preventDefault();
		setWeight(e.target.value);
	}

    const handleSelection = (e) => {
        e.preventDefault();
        setDiagnose(e.target.textContent);
        setShowAll(false);
    }

    const handleClick = (e) => {
        e.preventDefault();
        handleSubmit(diagnose, weight);
        //setWeight("");
    }

    return (
        <form className="diagnose-form" onSubmit={handleClick}>
            <div 
                className="diagnose-menu menu--selected" 
                onClick={() =>setShowAll(!showAll)}>
                    
                <span>{diagnose || 'Valitse diagnoosi'}</span>

                {showAll && (
                    <ul className="menu--items">
                        {menuItems.map((item) => (
                            <li 
                                key={item} 
                                onClick={handleSelection}>
                                {item}
                            </li>
                        ))}
                    </ul>
                )}
            </div>

            <input
                id="weight-input"
                className="form--input"
                placeholder="Syötä paino"
                name="weight"
                value={weight}
                onChange={handleInput}
                type="text"
                required={true}
                maxLength="4"
            />

            
            {diagnose && weight && 
            <button 
                className="form--button" 
                type="submit">
                Laske suositus
            </button>}

            {diagnose &&
            <label className="form--checkbox">
                <input 
                    type="checkbox"
                /> Penisilliiniallergia
            </label>}

            {diagnose==="Avohoitopneumonia" &&
            <label className="form--checkbox">
                <input 
                    type="checkbox"
                /> Samanaikainen mykoplasma
            </label>}

            {diagnose==="Streptokokki-tonsilliitti" &&
            <label className="form--checkbox">
                <input 
                    type="checkbox"
                /> Samanaikainen EBV-infektio
            </label>}

        </form>
    );
}