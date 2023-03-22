export default async function GetInfoTexts() {
    const apikey = process.env.REACT_APP_API_KEY;
    const url = "https://backend-production-0993.up.railway.app/api/antibiotics/info-texts";
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
            return data.infoTexts;
        })
        .catch(console.error)
}       