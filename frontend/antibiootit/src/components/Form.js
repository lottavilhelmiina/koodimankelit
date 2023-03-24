import React, { useEffect, useState } from "react";

export default function Form(props) {
    
    const fullInfo = props.diagnoses;
    const diagnosisNames = fullInfo.map(diagnosisInfo => diagnosisInfo.name);

    // Store the entire diagnosis data here, not just the name!
    const [diagnosis, setDiagnosis] = useState("");
    
    // This will be replaced by handling the 'needsAntibiotics' attribute of the diagnosis.
    const [isBronchitis, setIsBronchitis] = useState(false);

    const [additionalCheckboxes, setAdditionalCheckboxes] = useState();

    useEffect(() =>{
        if (diagnosis) {
            const chosen = fullInfo.filter(infection => infection.name === diagnosis.name);
            console.log(diagnosis)
            if (chosen[0].checkBoxes.length > 0) {
                setAdditionalCheckboxes(chosen[0].checkBoxes)
                console.log(chosen[0].checkBoxes)
            }
        }
    }, [diagnosis, fullInfo])
    
    const Choose = () => {
        return (
            <span className="diagnosis-menu-choose">
                Valitse diagnoosi
             </span>

        )
    }

    const ShowDiagnosisName = () => {
        const name = diagnosis.name;
        return (
            <span className="diagnosis-menu-name">
                {name}
            </span>
        )
    }

    const DiagnosisMenu = () => {
        return (
            <div 
                className="diagnosis-menu dropdown" >
                <button className="dropdown-btn">{diagnosis ? <ShowDiagnosisName  /> : <Choose />}</button>
                <div className="dropdown-content">
                    <ul className="menu--items">
                        {diagnosisNames
                            .filter((item) => item !== diagnosis.name)
                            .map((item) => (
                            <li 
                                key={item} 
                                onClick={handleMenuSelection}>
                                {item}
                            </li>
                        ))}
                    </ul>
                </div>
                
            </div>
        )
    }

    const handleMenuSelection = (e) => {
        e.preventDefault();
        const selected = e.target.textContent;
        const selectedInfo = fullInfo.filter(d => d.name === selected)[0]
        setDiagnosis(selectedInfo);
        props.setChosenDiagnosis(selected);
        props.changeInstruction(1);
        
        if (selectedInfo.id === 'J20.9') {
            setIsBronchitis(true);
            const data = {
                diagnosisID: selectedInfo.id
            }
            props.handleSubmit(data);
        }
        else if (selectedInfo.id !== 'J20.9') {
            setIsBronchitis(false);
        }
        if (selected !== "Streptokokki-tonsilliitti") {
            resetCheckboxes();
        }
        if (selected !== "Avohoitopneumonia") {
            resetCheckboxes();
        }        
    }

    const resetCheckboxes = () => {
        setConcurrentEBV(false);
        setConcurrentMycoplasma(false);
        setAdditionalCheckboxes([]);
    }

    const [weight, setWeight] = useState("");
    const [isWeightOk, setIsWeightOk] = useState(false);
    const MIN_WEIGHT = 4;
    const MAX_WEIGHT = 100;

    const handleInput = (e) => {
        e.preventDefault();
        const input = e.target.value;
        setWeight(input);
        props.changeInstruction(2);

        const formattedWeight = input.replace(',', '.');
        
        if (formattedWeight >= MIN_WEIGHT && formattedWeight <= MAX_WEIGHT) {
            console.log("paino ok")
            setIsWeightOk(true);
        }
        else {
            // User must be notified, notification not yet implemented
            console.log("Tarkista paino")
            setIsWeightOk(false);
        }
    }

    const [penicillinAllergy, setPenicillinAllergy] = useState(false);
    const [concurrentEBV, setConcurrentEBV] = useState(false);
    const [concurrentMycoplasma, setConcurrentMycoplasma] = useState(false);

    const SubmitButton = () => {
        return (
            <button 
                className="form--button" 
                type="submit"
                disabled={!weight || isBronchitis}>
                Laske suositus
            </button>
        )
    }

    const handleClick = (e) => {
        e.preventDefault();
        if (isWeightOk) {
            const checkBoxes = [
                {
                    id: 'EBV-001',
                    value: concurrentEBV
                },
                {
                    id: 'MYK-001',
                    value: concurrentMycoplasma
                  }
            ];
            const matchingCheckBoxes = checkBoxes.filter(cb => {
                return diagnosis.checkBoxes.some(c => c.id === cb.id);
            });
            const data = { 
                            diagnosisID: diagnosis.id,
                            weight: weight,
                            penicillinAllergic: penicillinAllergy,
                            checkBoxes: matchingCheckBoxes
                        }
            props.handleSubmit(data);
        }
        else {
            console.log("Painon pitää olla 4-100 kg")
        }
        
    }

    let placeholder = "Syötä paino"

    const emptyPlaceholder = () => {
        placeholder = "";
    }

    return (
        <form className="diagnosis-form" onSubmit={handleClick}>
            <DiagnosisMenu />
            <div className="weight-input">
                <span><img className="weight-icon" src="../icons/weight-icon.svg" alt="" />
                
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
                    /><span className="kg-text">kg</span></span>
            </div>
            <div className="checkbox-container">
                {diagnosis &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            disabled={isBronchitis}
                            onClick={() => setPenicillinAllergy(!penicillinAllergy)}
                        /> Penisilliiniallergia
                    </label>} 
                {additionalCheckboxes && additionalCheckboxes.filter(obj => obj.id === 'EBV-001').length > 0 &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            onClick={() => setConcurrentEBV(!concurrentEBV)}
                        /> Samanaikainen EBV-infektio
                    </label>}
                {additionalCheckboxes && additionalCheckboxes.filter(obj => obj.id === 'MYK-001').length > 0 &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            onClick={() => setConcurrentMycoplasma(!concurrentMycoplasma)}
                        /> Samanaikainen mykoplasma
                    </label>}
            </div>
            {diagnosis && <SubmitButton />}
        </form>
    );}