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
    const diagnosisOptions = ["Streptokokki-tonsilliitti"
                            , "Välikorvatulehdus"
                            , "Sivuontelotulehdus"
                            , "Bronkiitti"
                            , "Obstruktiivinen bronkiitti"
                            , "Avohoitopneumonia"];

    /**
     * The component for diagnose menu.
     * @returns The diagnose menu.
     */
    const DiagnoseMenu = () => {
        return (
            <div 
                className="diagnose-menu" 
                onClick={() =>setShowAll(!showAll)}>
                    
                <span>{diagnose || 'Valitse diagnoosi'}</span>

                {showAll && (
                    <ul className="menu--items">
                        {diagnosisOptions.map((item) => (
                            <li 
                                key={item} 
                                onClick={handleSelection}>
                                {item}
                            </li>
                        ))}
                    </ul>
                )}
            </div>
        )
    }                        

    /**
     * Handle user's diagnose selection from the menu.
     * @param {*} e 
     */
    const handleSelection = (e) => {
        e.preventDefault();
        setDiagnose(e.target.textContent);
        setShowAll(false);
    }

    /**
     * State for child's weight. 
     * Regex for weight input validation.
     * Allows empty string, numbers with decimal separator, or numbers without decimal separator
     * Values for min and max weights.
     */
    const [weight, setWeight] = useState("");
    const VALID_WEIGHT_INPUT = /^$|^(\d+([,.]\d{0,1})?)$/;
    const MIN_WEIGHT = 0.5;
    const MAX_WEIGHT = 120;

    /**
     * The component for weight input.
     * @returns The input field for weight.
     */
    const WeightInput = () => {
        return (
            <input
                id="weight-input"
                className="form--input"
                placeholder="Syötä paino"
                name="weight"
                value={weight}
                onChange={handleInput}
                type="text"
                required={true}
            />
        )
    }

    /**
     * Handle and validate weight input.
     * @param {*} e 
     */
    const handleInput = (e) => {
        e.preventDefault();
        const input = e.target.value;
        if (VALID_WEIGHT_INPUT.test(input)) {
            // Replace comma with decimal point for consistency
            const formattedWeight = input.replace(',', '.');
            // Check that the entered value is within the desired range
            if (formattedWeight === "" 
             || formattedWeight >= MIN_WEIGHT && formattedWeight <= MAX_WEIGHT) {
                setWeight(input);
            }
        }
      }

    const RelatedCheckboxes = () => {
        return (
            <>
            {diagnose==="Streptokokki-tonsilliitti" &&
                <label className="form--checkbox">
                    <input 
                        type="checkbox"
                    /> Samanaikainen EBV-infektio
                </label>}
            {diagnose==="Avohoitopneumonia" &&
                <label className="form--checkbox">
                    <input 
                        type="checkbox"
                    /> Samanaikainen mykoplasma
                </label>}
            {diagnose &&
                <label className="form--checkbox">
                    <input 
                        type="checkbox"
                    /> Penisilliiniallergia
                </label>}
            </>
        )
    }

    /**
     * The component for the form submit button.
     * @returns The submit button for form.
     */
    const SubmitButton = () => {
        return (
            <button 
                className="form--button" 
                type="submit">
                Laske suositus
            </button>
        )
    }

    /**
     * Handle form submission and send diagnose and weight as parameters.
     * @param {*} e 
     */
    const handleClick = (e) => {
        e.preventDefault();
        handleSubmit(diagnose, weight);
    }

    return (
        <form className="diagnose-form" onSubmit={handleClick}>
            <DiagnoseMenu />
            <input
                id="weight-input"
                className="form--input"
                placeholder="Syötä paino"
                name="weight"
                value={weight}
                onChange={handleInput}
                type="text"
                required={true}
            />    
            {diagnose==="Streptokokki-tonsilliitti" &&
                <label className="form--checkbox">
                    <input 
                        type="checkbox"
                    /> Samanaikainen EBV-infektio
                </label>}
            {diagnose==="Avohoitopneumonia" &&
                <label className="form--checkbox">
                    <input 
                        type="checkbox"
                    /> Samanaikainen mykoplasma
                </label>}
            {diagnose &&
                <label className="form--checkbox">
                    <input 
                        type="checkbox"
                    /> Penisilliiniallergia
                </label>}    
            {diagnose && weight && <SubmitButton />}
        </form>
    );
}