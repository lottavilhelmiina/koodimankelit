package fi.tuni.koodimankelit.antibiootit.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.koodimankelit.antibiootit.database.DiagnoseRepository;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosesController {

    private final AntibioticsService antibioticsService;

    public DiagnosesController() {
        this.antibioticsService = new AntibioticsService();
    }

    @GetMapping("/all")
    public Diagnoses getDiagnoses() {
        return this.antibioticsService.getAllDiagnoses();
    }
    
}

