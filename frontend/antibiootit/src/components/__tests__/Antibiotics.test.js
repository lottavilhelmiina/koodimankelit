/** @format */

import { render } from '@testing-library/react';
import Antibiotics from '../Antibiotics';

test('Should render Antibiotics without crash', async () => {
    render(<Antibiotics />);
});
