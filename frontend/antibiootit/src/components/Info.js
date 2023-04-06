import React, { useState, useEffect } from "react";
import GetInfoTexts from "./GetInfoTexts";
import getReferences from "./GetReferences";

export default function Info() {

    const [content, setContent] = useState("background");
    const [infoTexts, setInfoTexts] = useState(null);
    const [references, setReferences] = useState(null)

    async function fetchData() {
        const infoTextsList = await GetInfoTexts();
        setInfoTexts(infoTextsList);
        const referencesList = await getReferences();
        setReferences(referencesList);
    }
    useEffect(() => {
        fetchData();
    }, []);

    if (!!references) {
        console.log(references)
    }

    const Background = () => {
        if (!!infoTexts && !!references) {
            const backgroundInfo = infoTexts[1].text;
            const paragraphs = backgroundInfo.split("\n\n");
            return (
                <>
                    <div>{paragraphs.map((paragraph, index) => (
                        <p className="info-paragraph" key={index}>{paragraph.split("\n").join("<br>")}</p>
                    ))}</div>
                    <ul className="info-references">{references.map((item) => (
                        <li key={item.id}>
                            [{item.id}] {item.text}
                        </li>
                    ))}</ul>
                </>
                
            )
        }
        else {
            return <p>Haetaan tietoja...</p>
        }
    
    }
    const Makers = () => {
        if (!!infoTexts) {
            const leaderInfo = infoTexts[3]
            const paragraphs1 = leaderInfo.text.split("\n\n");
            const medicalProfessionalsInfo = infoTexts[4];
            const paragraphs2 = medicalProfessionalsInfo.text.split("\n\n");
            const itProfessionalsInfo = infoTexts[5];
            const paragraphs3 = itProfessionalsInfo.text.split("\n\n");

            return (
                <>
                <h3>{leaderInfo.header}</h3>
                    <div>
                        {paragraphs1.map((paragraph, index) => (
                        <p className="info-paragraph" key={index}>{paragraph.split("\n").join("<br>")}</p>
                    ))}</div>

                <h3>{medicalProfessionalsInfo.header}</h3>
                    <div>
                        {paragraphs2.map((paragraph, index) => (
                        <p className="info-paragraph" key={index}>{paragraph.split("\n").join("<br>")}</p>
                    ))}</div>

                <h3>{itProfessionalsInfo.header}</h3>
                    <div>
                        {paragraphs3.map((paragraph, index) => (
                        <p className="info-it" key={index}>{paragraph.split("\n").join("<br>")}</p>
                    ))}</div>
                </>
                
            )
        }
        else {
            return <p>Haetaan tietoja...</p>
        }
    }

    const Disclaimer = () => {
        if (!!infoTexts) {
            const disclaimerInfo = infoTexts[2].text;
            const paragraphs = disclaimerInfo.split("\n\n");
            return (
                <>
                    <p>{paragraphs.map((paragraph, index) => (
                        <p className="info-paragraph" key={index}>{paragraph.split("\n").join("<br>")}</p>
                    ))}</p>
                </>
                
            )
        }
        else {
            return <p>Haetaan tietoja...</p>
        }
    
    }

    const Privacy = () => {
        return (
            <p>Sivulla ei vielä ole sisältöä</p>
        )   
    }


    return (
        <div className="info-container">
            <h2>Tietoa sivustosta</h2>
            <div className="info-links">
                <button
                    onClick={() => setContent("background")}>Tausta ja tavoitteet</button>
                <button
                    onClick={() => setContent("makers")}>Tekijät</button>
                <button
                    onClick={() => setContent("disclaimer")}>Vastuuvapauslauseke</button>
                <button
                    onClick={() => setContent("privacy")}>Tietosuojaseloste</button>
            </div>
            <hr className="info-line" />
            {content === "background" && <Background />}
            {content === "makers" && <Makers />}
            {content === "disclaimer" && <Disclaimer />}
            {content === "privacy" && <Privacy />}
        </div>
        
    )
}