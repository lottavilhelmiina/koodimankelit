import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import Form from '../Form';

const menuItems = [
    {name: "Streptokokkitonsilliitti"},
    {name: "Bronkiitti"},
    {name: "Third item"}
]

test('Should render Form', async () => {
    render(<Form diagnoses={menuItems} />);
});

test('Should not render checkboxes initially', async () => {
    render(<Form diagnoses={menuItems} />);
    expect(screen.queryByText('Penisilliiniallergia')).toBeNull();
    expect(screen.queryByText('Samanaikainen EBV-infektio')).toBeNull();
    expect(screen.queryByText('Samanaikainen mykoplasma')).toBeNull();
});

test('Should have the correct number of diagnoses in the menu', async () => {
    render(<Form diagnoses={menuItems} />);
    fireEvent.click(screen.getByText('Valitse diagnoosi'))
    await waitFor(() => screen.getAllByTestId('diagnosis-menu'))

    const diagnosisMenu = screen.getByTestId('diagnosis-menu');
    const childCount = diagnosisMenu.children.length;
  
    expect(childCount).toBe(3);

});
