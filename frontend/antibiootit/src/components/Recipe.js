import React, { useState, useEffect } from "react";
import CopyNotification from "./CopyNotification";

export default function Recipe() {

    const TIMEOUT_DURATION = 1200;

    const [antibiotic, setAntibiotic] = useState("Kefaleksiini jauhe 100 mg/ml")
    const [dosageInstructions, setDosageInstructions] = useState("2,5 ml noin 12 tunnin välein 10 vrk:n ajan. Streptokokkinielutulehduksen hoitoon.");
    const [diagnosisCode, setDiagnosisCode] = useState("J03.0")

    const [showNotification, setShowNotification] = useState(false);

    /**
     * Sets a timeout for notification when user copies the dosage instructions.
     */
    useEffect(() => {
        const timeout = setTimeout(() => {
            setShowNotification(false);
        }, TIMEOUT_DURATION);
        return () => {
            clearTimeout(timeout);
        };
    }, [showNotification])

    /**
     * Copies the dosage instructions to clipboard when user clicks the copy button.
     * Activates notification.
     */
    const copy = async () => {
        await navigator.clipboard.writeText(dosageInstructions);
        setShowNotification(true);
    }

    const CopyButton = () => {
        return (
            <button
                className="copy-button"
                onClick={copy} 
                disabled={dosageInstructions === ""}>
                Kopioi resepti
            </button>
        )
    }

    /**
     * Handles input change if user changes the dosage instructions.
     * 
     * NB! Function malfunction.
     * 
     * @param {*} e 
     */
    const handleInputChange = (e) => {
        const newValue = e.target.value;
        setDosageInstructions(newValue);
      };

    /**
     * An editable text area with dosage instructions from the backend as default.
     * @returns A text area with dosage instructions.
     */
    const EditableDosageInstructions = () => {
        return (
            <textarea
                className="recipe-textfield"
                rows={2}
                value={dosageInstructions}
                onChange={handleInputChange}
                />
        )
    }

    return (
        <div className="recipe-container">
            <h3>Reseptin kirjoittaminen:</h3>
            <h4>{antibiotic}</h4>
            <div className="recipe-text-container">
                <EditableDosageInstructions />
                <div className="recipe-container-bottom">
                    <span>ICD-10 koodi: <span className="bold">{diagnosisCode}</span></span>
                    <span className="notification-container">
                    {showNotification && <CopyNotification />}
                    </span>
                    <CopyButton />
                </div>
            </div>
        </div>
    );
};
