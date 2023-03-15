import React, {useEffect} from "react";

export default function GetDiagnoses() {

    useEffect(() => {
        const fetchData = async () => {
            const apikey = process.env.REACT_APP_API_KEY;
            console.log(apikey);
            const data = await fetch("localhost:8080/api/antibiotics/diagnoses",
            {
                method: 'GET',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json',
                    'X-API-KEY': apikey
                }
            });
            console.log(data);
        }

        fetchData()
            .catch(console.error)

    }, []);
}