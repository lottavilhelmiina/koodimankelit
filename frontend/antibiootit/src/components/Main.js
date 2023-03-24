import React from "react";
import Antibiotics from "./Antibiotics";
import Info from "./Info";
import Feedback from "./Feedback";

export default function Main(props) {
    let path = props.path;
    return (
        <>
            {path === "/tietoa" && <Info />}
            {path === "/palaute" && <Feedback />}
            <main>
                {path === "/" && <Antibiotics />}
                {path === "/" && <aside>
                    <p className="aside-text">Antibiootit.fi on terveydenhuollon ammattilaisten käyttöön suunniteltu antibioottilaskuri, joka laskee suositellun antibioottiannostuksen diagnoosin ja lapsen painon perusteella. Suositukset perustuvat Käypähoitosuosituksiin. </p>
                    <p className="aside-update">Päivitetty viimeksi: 6.2.2023</p>
                </aside>}
            </main>

        </>

    )
}