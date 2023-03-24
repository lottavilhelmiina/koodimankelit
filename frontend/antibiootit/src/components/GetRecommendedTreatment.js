export default async function GetRecommendedTreatment(props) {
    console.log("request body")
    console.log(JSON.stringify(props))

    const requestBody = {
        "diagnosisID": "H66.0",
        "weight": 10,
        "penicillinAllergic": true,
        "checkBoxes": [
        ]
    };

    const apikey = process.env.REACT_APP_API_KEY;
    const url = "https://backend-production-0993.up.railway.app/api/antibiotics/dose-calculation";
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-API-KEY': apikey
        },
        body: requestBody
    }
    return await fetch(url, options)
        .then(res => {
            console.log("res")
            console.log(res)
            res.json();
        })
        .catch(err => {
			console.log(err);
		});
}       