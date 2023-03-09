import React, { useState, useEffect } from "react";
import CopyNotification from "./CopyNotification";

export default function Recipe(props) {

    const ab = props.ab;
    const choice = props.choice;

    const chosenAb = () => {
        if (choice == "Ensimmäisen vaihtoehdon resepti") {
            return  ab[0].name + " " + ab[0].dosage;
        }
        else {
            return ab[1].name + " " + ab[1].dosage;;
        }
    }

    const instr = () => {
        if (choice == "Ensimmäisen vaihtoehdon resepti") {
            return  ab[0].instruction;
        }
        else {
            return ab[1].instruction;
        }
    }

    const TIMEOUT_DURATION = 1200;

    const [antibiotic, setAntibiotic] = useState(chosenAb)
    const [dosageInstructions, setDosageInstructions] = useState(instr);
    const [diagnosisCode, setDiagnosisCode] = useState("J03.0")

    const [showNotification, setShowNotification] = useState(false);

    useEffect(() => {
        setAntibiotic(chosenAb);
    }, [chosenAb]);

/*     useEffect(() => {
        setDosageInstructions(instr);
    }, [chosenAb]); */

    /**
     * Sets a timeout for notification when user copies the dosage instructions.
     */
/*     useEffect(() => {
        const timeout = setTimeout(() => {
            setShowNotification(false);
        }, TIMEOUT_DURATION);
        return () => {
            clearTimeout(timeout);
        };
    }, [showNotification]) */

    /**
     * Copies the dosage instructions to clipboard when user clicks the copy button.
     * Activates notification.
     */
    const copy = async () => {
        await navigator.clipboard.writeText(dosageInstructions);
        //setShowNotification(true);
    }

    const CopyButton = () => {
        return (
            <button
                className="copy-button"
                onClick={copy} 
                disabled={dosageInstructions === ""}>
                <img src="./copy.png" /> Kopioi resepti
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
                rows={3}
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
                <textarea
                    className="recipe-textfield"
                    rows={3}
                    value={dosageInstructions}
                    onChange={handleInputChange}
                />
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
