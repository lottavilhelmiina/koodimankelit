import React from "react";

export default function Header() {
    return (
        <header className="header">
            <button 
                className="header--title-link"
                onClick={() => window.location.reload()}>
                    Antibiootit.fi</button>
            <div className="header--links">
                <button className="header-link">
                    Tietoa sivustosta
                </button>
                <button className="header-link">
                    Palautekysely
                </button>          
            </div>
        </header>
    )
};