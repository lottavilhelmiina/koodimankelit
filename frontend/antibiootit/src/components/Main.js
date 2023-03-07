import React from "react";
import Antibiotics from "./Antibiotics";

export default function Main() {
    return (
        <main>
            <Antibiotics />
            <aside>
                <p className="aside-text">Antibiootit.fi on terveydenhuollon ammattilaisten käyttöön suunniteltu antibioottilaskuri, joka laskee suositellun antibioottiannostuksen diagnoosin ja lapsen painon perusteella. Suositukset perustuvat Käypähoitosuosituksiin. </p>
                <p className="aside-update">Päivitetty viimeksi: 6.2.2023</p>
            </aside>
        </main>
    )
}