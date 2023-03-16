import React, {useEffect} from "react";

export default function GetDiagnoses() {

    useEffect(() => {
        const fetchData = async () => {
            const apikey = process.env.REACT_APP_API_KEY;
            const data = await fetch("https://backend-production-0993.up.railway.app/api/antibiotics/diagnoses",
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'X-API-KEY': apikey
                }
            })
            .then(res => res.json());
            console.log(data);
        }

        fetchData()
            .catch(console.error)

    }, []);
}
