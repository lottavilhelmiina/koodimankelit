package fi.tuni.koodimankelit.antibiootit.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.koodimankelit.antibiootit.models.Parameters;
import fi.tuni.koodimankelit.antibiootit.models.Treatments;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/antibiotics")
public class AntibioticsController {

    private final AntibioticsService calculator;


    public AntibioticsController() {
        this.calculator = new AntibioticsService();
    }

    
    @PostMapping("/dose-calculation")
    public Treatments doseCalculation(@RequestBody @Valid Parameters parameters) {

        // TEST ONLY
        System.out.println(parameters);
        return this.calculator.calculateTreatments(parameters);
    }
}
