package fi.tuni.koodimankelit.antibiootit.calculator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.koodimankelit.antibiootit.calculator.data.DiagnosisTreatments;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Parameters;
import jakarta.validation.Valid;

@RestController
public class CalculatorController {

    private final Calculator calculator;


    public CalculatorController() {
        this.calculator = new Calculator();
    }

    
    @PostMapping("calculator")
    public DiagnosisTreatments calculateTreatments(@RequestBody @Valid Parameters parameters) {

        // TEST ONLY
        System.out.println(parameters);
        return this.calculator.calculateTreatments(parameters);
    }
}
