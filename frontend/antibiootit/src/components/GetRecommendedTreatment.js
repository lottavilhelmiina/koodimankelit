export default async function GetRecommendedTreatment(props) {
    const apikey = process.env.REACT_APP_API_KEY;
    const url = "https://backend-production-0993.up.railway.app/api/antibiotics/dose-calculation";
    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-API-KEY': apikey
        },
        body: JSON.stringify(props)
    }
    return await fetch(url, options)
        .then(res => res.json() )
        .then(data => {
            return data;
        })
        .catch(err => {
			console.log(err);
		});
}       