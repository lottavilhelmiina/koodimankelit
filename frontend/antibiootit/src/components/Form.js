import React, { useState } from "react";

export default function Form(props) {
    
    /**
     * States and items for the drop-down menu for selecting the diagnose.
     * All items are shown by default.
     * After clicking an item, only the selected item is visible.
     */
    const [diagnosis, setDiagnosis] = useState("");
    //const [showAll, setShowAll] = useState(true);
    const diagnosisOptions = ["Streptokokki-tonsilliitti"
                            , "Välikorvatulehdus"
                            , "Sivuontelotulehdus"
                            , "Bronkiitti"
                            , "Obstruktiivinen bronkiitti"
                            , "Avohoitopneumonia"];
    const [isBronchitis, setIsBronchitis] = useState(false);
    
    const Choose = () => {
        return (
            <><img className="choose" src="./heart.png" />
            <span className="bold">Valitse diagnoosi</span>
            </>
        )
    }

    /**
     * The component for diagnose menu.
     * @returns The diagnose menu.
     */
    const DiagnosisMenu = () => {
        return (
            <div 
                className="diagnosis-menu dropdown" >
                <button className="dropdown-btn">{diagnosis || <Choose />}</button>
                <div className="dropdown-content">
                    <ul className="menu--items">
                        {diagnosisOptions
                            .filter((item) => item !== diagnosis)
                            .map((item) => (
                            <li 
                                key={item} 
                                onClick={handleSelection}>
                                {item}
                            </li>
                        ))}
                    </ul>
                </div>
                
            </div>
        )
    }

    /**
     * Handle user's diagnose selection from the menu.
     * @param {*} e 
     */
    const handleSelection = (e) => {
        e.preventDefault();
        setDiagnosis(e.target.textContent);
        props.setChosenDiagnose(e.target.textContent);
        props.changeInstruction(1);

        console.log("Uusi diagnoosivalinta tehty")
        const selected = e.target.textContent;
        setDiagnosis(selected);
        
        if (selected === "Bronkiitti") {
            setIsBronchitis(true);
            props.handleSubmit(selected, null);
        }
        else if (selected !== "Bronkiitti") {
            setIsBronchitis(false);
        }
        if (selected !== "Streptokokki-tonsilliitti") {
            resetCheckboxes();
        }
        if (selected !== "Avohoitopneumonia") {
            resetCheckboxes();
        }        
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
     * Handle and validate weight input.
     * @param {*} e 
     */
    const handleInput = (e) => {
        e.preventDefault();
        props.changeInstruction(2);
        const input = e.target.value;
        if (VALID_WEIGHT_INPUT.test(input)) {
            // Replace comma with decimal point for consistency
            const formattedWeight = input.replace(',', '.');
            // Check that the entered value is within the desired range
            if ((formattedWeight === "")
             || (formattedWeight >= MIN_WEIGHT && formattedWeight <= MAX_WEIGHT)) {
                setWeight(input);
                
            }
        }
    }

    const [penisillinAllergy, setPenisillinAllergy] = useState(false);
    const [concurrentEBV, setConcurrentEBV] = useState(false);
    const [concurrentMycoplasma, setConcurrentMycoplasma] = useState(false);

    /**
     * The component for the form submit button.
     * @returns The submit button for form.
     */
    const SubmitButton = () => {
        return (
            <button 
                className="form--button" 
                type="submit"
                disabled={!weight}>
                Laske suositus
            </button>
        )
    }

    /**
     * Handle form submission and send form data as object parameter.
     * @param {*} e 
     */
    const handleClick = (e) => {
        e.preventDefault();
        const data = { diagnosis: diagnosis,
                       weight: weight,
                       allergy: penisillinAllergy,
                       concurrentEBV: concurrentEBV,
                       concurrentMycoplasma: concurrentMycoplasma }
        props.handleSubmit(data);
    }

    const resetCheckboxes = () => {
        setConcurrentEBV(false);
        setConcurrentMycoplasma(false);
    }

    let placeholder = "Syötä paino"

    const emptyPlaceholder = () => {
        placeholder = "";
    }

    return (
        <form className="diagnosis-form" onSubmit={handleClick}>
            <DiagnosisMenu />
            <div className="weight-input">
                <input
                    id="weight-input"
                    className="form--input"
                    placeholder={placeholder}
                    onFocus={emptyPlaceholder}
                    name="weight"
                    value={weight}
                    onChange={handleInput}
                    type="text"
                    disabled={isBronchitis || !diagnosis}
                    required={true}
                />
                <span>kg</span>
            </div>
            <div className="checkbox-container">
                {diagnosis==="Streptokokki-tonsilliitti" &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            onClick={() => setConcurrentEBV(!concurrentEBV)}
                        /> Samanaikainen EBV-infektio
                    </label>}
                {diagnosis==="Avohoitopneumonia" &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            onClick={() => setConcurrentMycoplasma(!concurrentMycoplasma)}
                        /> Samanaikainen mykoplasma
                    </label>}
                {diagnosis && !isBronchitis &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            onClick={() => setPenisillinAllergy(!penisillinAllergy)}
                        /> Penisilliiniallergia
                    </label>} 
            </div>
            {diagnosis && !isBronchitis && <SubmitButton />}
        </form>
    );}