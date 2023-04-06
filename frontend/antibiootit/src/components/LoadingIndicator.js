import React from 'react';

export default function LoadingIndicator(props) {
    
    if(props.loading === "treatments") {
        return (
            <div className="treatment-choises">
                <div className="choise-container">
                    <div className="loading-container">
                        <div className="loading-animation"></div>
                        <p>Haetaan uusia suosituksia...</p>
                    </div>
                </div>
            </div>
        );
    }

    if(props.loading === "calculations") {
        return (
            <div className="treatment-calculations">
                <div className="calculations-container">
                    <div className="loading-container">
                        <div className="loading-animation-calculations"></div>
                        <p>Suoritetaan uusia laskuja...</p>
                    </div>
                </div>
            </div>
        )
    }
}