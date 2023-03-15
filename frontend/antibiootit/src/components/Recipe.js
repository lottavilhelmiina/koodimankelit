import React, { useState, useEffect } from "react";

export default function Recipe(props) {

    const ab = props.ab;

    //const [antibiotic, setAntibiotic] = useState("")
    const dosageInstructions = ab[0].instruction;
    //const [diagnosisCode, setDiagnosisCode] = useState("");
    const [showNotification, setShowNotification] = useState(false);

    const antibiotic = ab[0].name + " " + ab[0].dosage;
    const diagnosisCode = props.ab[0].id;

    //const choice = props.choice;

    // const chosenAb = () => {
    //     if (choice == "Ensimmäisen vaihtoehdon resepti") {
    //         return  ab[0].name + " " + ab[0].dosage;
    //     }
    //     else {
    //         return ab[1].name + " " + ab[1].dosage;;
    //     }
    // }

    // const instr = () => {
    //     if (choice == "Ensimmäisen vaihtoehdon resepti") {
    //         return  ab[0].instruction;
    //     }
    //     else {
    //         return ab[1].instruction;
    //     }
    // }

    const TIMEOUT_DURATION = 1000;

    

    // useEffect(() => {
    //     setAntibiotic(chosenAb);
    // }, [chosenAb]);

    // useEffect(() => {
    //     if (!editedRef.current) {
    //         setDosageInstructions(instr);
    //     }
    // }, [chosenAb, instr]);

    /**
     * Copies the dosage instructions to clipboard when user clicks the copy button.
     * Activates notification.
     */
    const copy = async () => {
        await navigator.clipboard.writeText(dosageInstructions);
        setShowNotification(true);
    }

    const [copyText, setCopyText] = useState("Kopioi resepti")
    const [hasMounted, setHasMounted] = useState(false);

    /**
     * Sets a timeout for notification when user copies the dosage instructions.
     */
    useEffect(() => {
        if(hasMounted) {
            const timeout = setTimeout(() => {
            setCopyText("Kopioi resepti")
            setShowNotification(false);
            }, TIMEOUT_DURATION);
            return () => {
                setCopyText("Resepti kopioitu")
                clearTimeout(timeout);
            };
        } else {
            setHasMounted(true);
        }
    }, [showNotification, hasMounted])

    const CopyButton = () => {
        return (
            <button
                style={styles}
                className="copy-button"
                onClick={copy} 
                disabled={dosageInstructions === ""}>
                <img className="copy--image" src="./copy.png" alt=""/> {copyText}
            </button>
        )
    }

    const styles = {
        fontStyle: copyText==="Resepti kopioitu" ? "italic" : "normal"
    }

    return (
        <div className="recipe-container">
            <h3>Reseptin kirjoittaminen:</h3>
            <h4>{antibiotic}</h4>
            <div className="recipe-text-container">
                <p className="recipe-text">{dosageInstructions}
                </p>
                <div className="recipe-container-bottom">
                    <span>ICD-10 koodi: <span className="bold">{diagnosisCode}</span></span>
                    <CopyButton />
                </div>
            </div>
        </div>
    );

    
};