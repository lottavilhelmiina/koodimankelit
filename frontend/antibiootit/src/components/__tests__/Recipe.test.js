import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import Recipe from '../Recipe';

const diagnosisData = {
    id: 'H66.0',
    name: 'Välikorvatulehdus'
}

const treatments = [
    {format: 'mikstuura',
    antibiotic: 'Amoksisilliini jauhe'
    }]

const activeRecipe = {
    text: '3 ml kahdesti päivässä 10 vrk ajan',
    antibioteName: 'Amoksisilliini jauhe',
    antibioteStrength: '100 mg/ml',
    dosage: 
        {days: 10, dosesPerDay: 2, recipeText: 'kahdesti päivässä', doseMultipliers: 
        [{id: 0, multiplier: 1}]}
}

test('Should render Recipe', async () => {
    render(<Recipe 
                treatments={treatments}
                activeRecipe={activeRecipe} 
                diagnosisData={diagnosisData} 
                noTreatment={null}/>);
});

test('Should show the name and strength of the antibiotic', async () => {
    render(<Recipe 
                treatments={treatments}
                activeRecipe={activeRecipe} 
                diagnosisData={diagnosisData} 
                noTreatment={null}/>);

    expect(screen.queryByText('Amoksisilliini jauhe 100 mg/ml')).toBeInTheDocument();
});

test('Should show dosage instructions', async () => {
    render(<Recipe 
                treatments={treatments}
                activeRecipe={activeRecipe} 
                diagnosisData={diagnosisData} 
                noTreatment={null}/>);

    expect(screen.queryByText('3 ml kahdesti päivässä 10 vrk ajan. Äkillisen välikorvatulehduksen hoitoon.')).toBeInTheDocument();
});

test('Should show the ICD-10 code', async () => {
    render(<Recipe 
                treatments={treatments}
                activeRecipe={activeRecipe} 
                diagnosisData={diagnosisData} 
                noTreatment={null}/>);

    await waitFor(() => {
        expect(screen.queryByText('ICD-10 koodi:')).toBeInTheDocument();
        expect(screen.queryByText('H66.0')).toBeInTheDocument();
    });
});

test('Should render the copy button', async () => {
    render(<Recipe 
                treatments={treatments}
                activeRecipe={activeRecipe} 
                diagnosisData={diagnosisData} 
                noTreatment={null}/>);

    await waitFor(() => {
        expect(screen.queryByText('Kopioi resepti')).toBeInTheDocument();
    });
});