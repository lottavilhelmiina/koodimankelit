package fi.tuni.koodimankelit.antibiootit.calculator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.koodimankelit.antibiootit.calculator.data.AntibioticChoise;
import fi.tuni.koodimankelit.antibiootit.calculator.data.DiagnosisTreatments;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Formula;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Instructions;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Measurement;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Result;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Treatment;

@RestController
public class CalculatorController {
    
    @PostMapping("calculator")
    public DiagnosisTreatments calculateTreatments() {

        // TEST ONLY
        return mockCalculator();
    }

    // Mock calculation of treatments. Might be useful for unit testing also
    private DiagnosisTreatments mockCalculator() {

        DiagnosisTreatments d =  new DiagnosisTreatments("J03.0", "Streptokokki A");
        Treatment t = new Treatment();
        d.addTreatment(t);

        t.addAntibiotic(
            new AntibioticChoise(
                "Mikstuura",
                "infoteksti...",
                "Amoksisilliini",
                new Instructions(10, 3),
                new Formula(
                    new Measurement("mg/ml", 100),
                    new Measurement("mg/kg/vrk", 50)),
                new Result(
                    new Measurement("ml", 4)
                )
            )
        );

        return d;
    }
}
