import React, { useEffect, useState } from "react";
import { logUserInputData } from "./logUserInputData";

const STEP2 = 8;
const STEP3 = 9;

export default function Form(props) {
    
    let fullInfo = null;
    let diagnosisNames = null;
    if (!!props.diagnoses) {
        fullInfo = props.diagnoses;
        diagnosisNames = fullInfo.map(diagnosisInfo => diagnosisInfo.name);
    }

    const [diagnosis, setDiagnosis] = useState("");
    const [needsAntibiotics, setNeedsAntibiotics] = useState(false);
    const [additionalCheckboxes, setAdditionalCheckboxes] = useState();

    useEffect(() =>{
        if (diagnosis) {
            const chosen = fullInfo.filter(infection => infection.name === diagnosis.name);
            setNeedsAntibiotics(chosen[0].needsAntibiotics);
            if (chosen[0].checkBoxes.length > 0) {
                setAdditionalCheckboxes(chosen[0].checkBoxes)
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
            <span 
                className="diagnosis-menu-name"
                >
                {name}
            </span>
        )
    }

    const DiagnosisMenu = () => {
        if (!diagnosisNames) {
            return (
                <div 
                    className="diagnosis-menu dropdown" >
                    <button 
                        className="dropdown-btn"
                        data-testid="diagnosis-menu-btn"
                        onClick={handleMenuClick}>{diagnosis ? <ShowDiagnosisName  /> : <Choose />}
                        <ion-icon name="chevron-down-outline" size="large"></ion-icon>
                    </button>
                    <div className="dropdown-content">
                        <ul className="menu--items" data-testid="diagnosis-menu">
                            <li 
                                key="1" 
                                onClick={handleMenuSelection}>
                                Ladataan diagnooseja...
                            </li>
                        </ul>
                    </div>
                </div>
            )
        }
        else {
            return (
                <div 
                    className="diagnosis-menu dropdown" >
                    <button 
                        className="dropdown-btn"
                        data-testid="diagnosis-menu-btn"
                        onClick={handleMenuClick}>{diagnosis ? <ShowDiagnosisName  /> : <Choose />}
                        <ion-icon name="chevron-down-outline" size="large"></ion-icon>
                    </button>
                    <div className="dropdown-content">
                        <ul className="menu--items" data-testid="diagnosis-menu">
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
    }

    const handleMenuClick = (e) => {
        e.preventDefault();
    }

    const handleMenuSelection = (e) => {
        e.preventDefault();
        
        setFormatWeight(true);
        const selected = e.target.textContent;
        
        const selectedInfo = fullInfo.filter(d => d.name === selected)[0]
        setDiagnosis(selectedInfo);
        props.setChosenDiagnosis(selected);
        if(!props.formSubmitted || props.formData === null) {
            props.changeInstruction(STEP2);
        }
        else if (props.hasFormData && selectedInfo.id !== "J20.9" && selectedInfo.id !== "J21.9") {
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
                return selectedInfo.checkBoxes.some(c => c.id === cb.id);
            });

            let newData = null;
            
            if (props.formData === null) {
                console.log("no data")
            }
            newData = {
                    ...props.formData,
                diagnosisID: selectedInfo.id,
                checkBoxes: matchingCheckBoxes
            }
            
            props.handleSubmit(newData);
        }

        if (selectedInfo.needsAntibiotics === true) {
            setNeedsAntibiotics(true);
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
    const [formatWeight, setFormatWeight] = useState(true);
    const inputErrorMessage = "Tarkista paino";
    const MIN_WEIGHT = 4;
    const MAX_WEIGHT = 100;
    const VALID_WEIGHT_INPUT = /^\d*([.,])?\d*$/;

    const handleInput = (e) => {
        e.preventDefault();
        const input = e.target.value;
        if (!VALID_WEIGHT_INPUT.test(input)) {
            setIsWeightOk(false);
            setFormatWeight(false);
        }
        else {
            setWeight(input);
            if(!props.formSubmitted) {
                props.changeInstruction(STEP3);
            }

            const roundedWeight = Math.round(parseFloat(input.replace(",", ".")) * 100) / 100;
            const weightForCalculations = roundedWeight.toFixed(2).replace(",", ".");
            if (weightForCalculations >= MIN_WEIGHT && weightForCalculations <= MAX_WEIGHT) {
                setIsWeightOk(true);
                setFormatWeight(true);
                if (props.hasFormData) {
                    const newData = {
                        ...props.formData,
                        weight: weightForCalculations
                    }
                    props.setChosenWeight(weightForCalculations)
                    props.handleSubmit(newData);
                }
            }
            else {
                setIsWeightOk(false);
                if (input.length > 1) {
                    setFormatWeight(false);
                }
                
            }
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
                disabled={!formatWeight || !needsAntibiotics}>
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

            const roundedWeight = Math.round(parseFloat(weight.replace(",", ".")) * 100) / 100;
            const formattedWeight = roundedWeight.toFixed(2).replace(".", ",");
            
            const weightForCalculations = roundedWeight.toFixed(2).replace(",", ".");
            if (weightForCalculations >= MIN_WEIGHT && weightForCalculations <= MAX_WEIGHT) {
                setIsWeightOk(true);
                setWeight(formattedWeight);
                props.setChosenWeight(formattedWeight);
                setFormatWeight(true);

                const data = { 
                                diagnosisID: diagnosis.id,
                                weight: weightForCalculations,
                                penicillinAllergic: penicillinAllergy,
                                checkBoxes: matchingCheckBoxes
                            }

                logUserInputData(
                    diagnosis.name, 
                    weight, 
                    penicillinAllergy, 
                    concurrentEBV, 
                    concurrentMycoplasma
                    );

                props.handleSubmit(data);

            }            
        }
        else if (diagnosis && !isWeightOk && needsAntibiotics) {
            setFormatWeight(false);
        }
    }

    let placeholder = "Syötä paino"

    const emptyPlaceholder = () => {
        placeholder = "";
    }

    const handlePenicillinAllergy = () => {
        const newData = {
            ...props.formData,
            penicillinAllergic: !penicillinAllergy
        }
        if (props.hasFormData) {
            props.handleSubmit(newData);
        }
        setPenicillinAllergy(!penicillinAllergy)
    }

    const handleEBV = () => {
        if (props.hasFormData) {
            const checkBoxes = [
                {
                    id: 'EBV-001',
                    value: !concurrentEBV
                }
            ];
            const newData = {
                ...props.formData,
                checkBoxes: checkBoxes
            }
            props.handleSubmit(newData);
        }
        setConcurrentEBV(!concurrentEBV)
    }

    const handleMycoplasma = () => {
        if (props.hasFormData) {
            const checkBoxes = [
                {
                    id: 'MYK-001',
                    value: !concurrentMycoplasma
                }
            ];
            const newData = {
                ...props.formData,
                checkBoxes: checkBoxes
            }
            props.handleSubmit(newData);
        }
        setConcurrentMycoplasma(!concurrentMycoplasma)
    }

    return (
        <form 
            className="diagnosis-form" 
            autoComplete="off"
            onSubmit={handleClick}>
            <DiagnosisMenu />
            <div className="weight-input">
                    <input
                        id="weight-input"
                        data-testid="weight-input"
                        className={formatWeight ? "form--input" : "form--input-notok" }
                        placeholder={placeholder}
                        onFocus={emptyPlaceholder}
                        name="weight"   
                        value={weight}
                        onChange={handleInput}
                        type="text"
                        inputMode="numeric"
                        disabled={!needsAntibiotics || !diagnosis}
                        required={true}
                    /><span className="kg-text">kg</span>
                    {!formatWeight && <div className="error" id="inputErr">{inputErrorMessage}</div>}
            </div>
            <div className="checkbox-container">
                {diagnosis &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            disabled={!needsAntibiotics}
                            onClick={handlePenicillinAllergy}
                        /> <span className={!needsAntibiotics ? "disabled" : "enabled"}>Penisilliiniallergia</span>
                    </label>} 
                {additionalCheckboxes && additionalCheckboxes.filter(obj => obj.id === 'EBV-001').length > 0 &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            onClick={handleEBV}
                        /> Samanaikainen EBV-infektio
                    </label>}
                {additionalCheckboxes && additionalCheckboxes.filter(obj => obj.id === 'MYK-001').length > 0 &&
                    <label className="form--checkbox">
                        <input 
                            type="checkbox"
                            onClick={handleMycoplasma}
                        /> Samanaikainen mykoplasma
                    </label>}
            </div>
            {diagnosis && <SubmitButton />}
        </form>
    );}