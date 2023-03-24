import React from "react";
import { Link } from "react-router-dom";

export default function Header() {
    return (
        <header className="header">
            <Link to="/" 
                className="header--title-link"
                onClick={() => window.location.href = "/"}>
                    Antibiootit.fi</Link>
            <div className="header--links">
                <Link to="/tietoa" className="header-link">
                    Tietoa sivustosta
                </Link>
                <Link to="/palaute" className="header-link">
                    Palautekysely
                </Link>          
            </div>
        </header>
    )
};