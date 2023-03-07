import React, { useState, useEffect } from "react";
import CopyNotification from "./CopyNotification";

export default function Recipe() {

    const [textForRecipe, setTextForRecipe] = useState("2,5 ml noin 12 tunnin välein 10 vrk:n ajan. Streptokokkinielutulehduksen hoitoon.");

    const [showNotification, setShowNotification] = useState(false);

    useEffect(() => {
        const timeout = setTimeout(() => {
            setShowNotification(false);
        }, 1000);
        return () => {
            clearTimeout(timeout);
        };
    }, [showNotification])

    const copy = async () => {
        await navigator.clipboard.writeText(textForRecipe);
        setShowNotification(true);
    }

    const handleInputChange = (e) => {
        e.preventDefault();
        setTextForRecipe(e.target.value);
      };

    const EditableRecipeText = () => {
        return (
            <textarea 
                className="recipe-textfield"
                rows={4}
                value={textForRecipe}
                onInput={handleInputChange}
                
                />
        )
    }

    return (
        <div className="recipe-container">
            <h3>Reseptin kirjoittaminen:</h3>
            <h4>Kefaleksiini jauhe 100 mg/ml</h4>
            <div className="recipe-text-container">
                <EditableRecipeText />
                <p className="notification-container">
                    {showNotification && <CopyNotification />}
                </p>
                <button
                    className="copy-button"
                    onClick={copy} 
                    disabled={!textForRecipe}>
                    Kopioi teksti
                </button>
            </div>
        </div>
    );
};