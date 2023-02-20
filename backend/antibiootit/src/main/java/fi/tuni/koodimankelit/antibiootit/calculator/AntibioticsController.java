package fi.tuni.koodimankelit.antibiootit.calculator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.koodimankelit.antibiootit.calculator.data.Treatments;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Parameters;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/antibiotics")
public class AntibioticsController {

    private final Calculator calculator;


    public AntibioticsController() {
        this.calculator = new Calculator();
    }

    
    @PostMapping("/dose-calculation")
    public Treatments doseCalculation(@RequestBody @Valid Parameters parameters) {

        // TEST ONLY
        System.out.println(parameters);
        return this.calculator.calculateTreatments(parameters);
    }
}
