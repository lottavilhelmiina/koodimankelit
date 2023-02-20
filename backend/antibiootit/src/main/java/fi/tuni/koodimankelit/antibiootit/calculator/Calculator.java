package fi.tuni.koodimankelit.antibiootit.calculator;

import fi.tuni.koodimankelit.antibiootit.calculator.data.Treatments;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Parameters;
import fi.tuni.koodimankelit.antibiootit.calculator.data.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.calculator.data.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Instructions;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Measurement;
import fi.tuni.koodimankelit.antibiootit.calculator.data.DosageResult;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Treatment;

public class Calculator {

    public Treatments calculateTreatments(Parameters parameters) {
        return this.mockCalculator();
    }
    
    // Mock calculation of treatments. Might be useful for unit testing also
    private Treatments mockCalculator() {

        Treatments d =  new Treatments("J03.0", "Streptokokki A");
        Treatment t = new Treatment();
        d.addTreatment(t);

        t.addAntibiotic(
            new AntibioticTreatment(
                "Mikstuura",
                "infoteksti...",
                "Amoksisilliini",
                new Instructions(10, 3),
                new DosageFormula(
                    new Measurement("mg/ml", 100),
                    new Measurement("mg/kg/vrk", 50)),
                new DosageResult(
                    new Measurement("ml", 4)
                )
            )
        );

        return d;
    }
}
