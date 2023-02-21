import React from "react";

export default function Header() {
    return (
        <header className="header">
            <h1 className="header--title">Antibiootit.fi</h1>
            <div className="header--links">
                <button className="button-link">
                    Tietoa sivustosta
                </button>
                <button className="button-link">
                    Palautekysely
                </button>          
            </div>
        </header>
    )
};