package fi.tuni.koodimankelit.antibiootit.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import fi.tuni.koodimankelit.antibiootit.models.Parameters;
import fi.tuni.koodimankelit.antibiootit.models.Treatments;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;


@RestController
@RequestMapping("/api/antibiotics")
public class AntibioticsController {

    private final AntibioticsService antibioticsService;


    public AntibioticsController(AntibioticsService antibioticsService) {
        this.antibioticsService = antibioticsService;
    }

    
    @PostMapping("/dose-calculation")
    public Treatments doseCalculation(@RequestBody @Valid Parameters parameters) {
        // TEST ONLY
        System.out.println(parameters);
        return this.antibioticsService.calculateTreatments(parameters);
    }

    @GetMapping("/diagnoses")
    public List<DiagnoseInfo> getDiagnoses() {
        return this.antibioticsService.getAllDiagnoseInfos();
    }



}
