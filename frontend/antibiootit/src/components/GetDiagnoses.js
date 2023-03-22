export default async function GetDiagnoses() {
    const apikey = process.env.REACT_APP_API_KEY;
    const url = "https://backend-production-0993.up.railway.app/api/antibiotics/diagnoses";
    const options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'X-API-KEY': apikey
        }
    }
    return await fetch(url, options)
        .then(res => res.json())
        .then(data => {
            return data.diagnosisInfos;
        })
        .catch(console.error)
}       