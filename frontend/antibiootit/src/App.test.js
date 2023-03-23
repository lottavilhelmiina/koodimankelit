import '@testing-library/jest-dom';
import { render, screen } from '@testing-library/react';
import App from './App';
import Footer from './components/Footer';
import Header from './components/Header';

test('Should render App', async () => {
    render(<App />);
});

test('Should render Header', async () => {
    render(<Header />);
});

test('Header includes a text with the name of the website', () => {
	render(<Header />);
	expect(screen.getByText('Antibiootit.fi')).toBeInTheDocument();
});

test('Should render Footer', async () => {
  render(<Footer />);
});

