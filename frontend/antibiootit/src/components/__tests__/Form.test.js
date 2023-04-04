import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import Form from '../Form';

const diagnoses = [
    {name: "Streptokokkitonsilliitti",
     needsAntibiotics: "true",
     checkBoxes: [
        {id: 'EBV-001', name: 'EBV-infektio'}]},
    {name: "Bronkiitti",
     needsAntibiotics: "false",
     checkBoxes: []},
    {name: "Välikorvatulehdus",
     needsAntibiotics: "true",
     checkBoxes: []},
    {name: "Avohoitopneumonia",
     needsAntibiotics: "true",
     checkBoxes: [
        {id: 'MYK-001', name: 'Mykoplasmainfektio'}]}
]

const setChosenDiagnosis = () => {
    console.log("Checkbox test")
}

const changeInstruction = () => {
    console.log("Checkbox test cont.")
}

test('Should render Form', async () => {
    render(<Form diagnoses={diagnoses} />);
});

test('Should not render checkboxes initially', async () => {
    render(<Form diagnoses={diagnoses} />);
    expect(screen.queryByText('Penisilliiniallergia')).toBeNull();
    expect(screen.queryByText('Samanaikainen EBV-infektio')).toBeNull();
    expect(screen.queryByText('Samanaikainen mykoplasma')).toBeNull();
});

test('Should have the correct number of diagnoses in the menu', async () => {
    render(<Form diagnoses={diagnoses} />);
    fireEvent.click(screen.getByText('Valitse diagnoosi'))
    await waitFor(() => screen.getAllByTestId('diagnosis-menu'))

    const diagnosisMenu = screen.getByTestId('diagnosis-menu');
    const childCount = diagnosisMenu.children.length;
  
    expect(childCount).toBe(4);
});

test('Should render the correct checkboxes for streptokokkitonsilliitti', async () => {
    render(<Form 
                diagnoses={diagnoses} 
                setChosenDiagnosis={setChosenDiagnosis} 
                changeInstruction={changeInstruction}/>);
    
    fireEvent.click(screen.getByText('Valitse diagnoosi'))
    await waitFor(() => screen.getAllByTestId('diagnosis-menu'))

    const diagnosisMenu = screen.getByTestId('diagnosis-menu');
    fireEvent.click(screen.getByText('Streptokokkitonsilliitti'))
    await waitFor(() => {
        expect(screen.queryByText('Penisilliiniallergia')).toBeInTheDocument();
        expect(screen.queryByText('Samanaikainen EBV-infektio')).toBeInTheDocument();
        expect(screen.queryByText('Samanaikainen mykoplasma')).toBeNull();
      });
})

test('Should render the correct checkboxes for avohoitopneumonia', async () => {
    render(<Form 
                diagnoses={diagnoses} 
                setChosenDiagnosis={setChosenDiagnosis} 
                changeInstruction={changeInstruction}/>);
    
    fireEvent.click(screen.getByText('Valitse diagnoosi'))
    await waitFor(() => screen.getAllByTestId('diagnosis-menu'))

    const diagnosisMenu = screen.getByTestId('diagnosis-menu');
    fireEvent.click(screen.getByText('Avohoitopneumonia'))
    await waitFor(() => {
        expect(screen.queryByText('Penisilliiniallergia')).toBeInTheDocument();
        expect(screen.queryByText('Samanaikainen EBV-infektio')).toBeNull();
        expect(screen.queryByText('Samanaikainen mykoplasma')).toBeInTheDocument();
      });
})

test('Should render the correct checkbox for bronkiitti', async () => {
    render(<Form 
                diagnoses={diagnoses} 
                setChosenDiagnosis={setChosenDiagnosis} 
                changeInstruction={changeInstruction}/>);
    
    fireEvent.click(screen.getByText('Valitse diagnoosi'))
    await waitFor(() => screen.getAllByTestId('diagnosis-menu'))

    const diagnosisMenu = screen.getByTestId('diagnosis-menu');
    fireEvent.click(screen.getByText('Bronkiitti'))
    await waitFor(() => {
        expect(screen.queryByText('Penisilliiniallergia')).toBeInTheDocument();
        expect(screen.queryByText('Samanaikainen EBV-infektio')).toBeNull();
        expect(screen.queryByText('Samanaikainen mykoplasma')).toBeNull();
      });
})