import React from 'react';

export default function LoadingIndicator() {

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