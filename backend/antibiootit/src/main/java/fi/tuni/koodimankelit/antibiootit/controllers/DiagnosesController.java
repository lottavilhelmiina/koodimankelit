package fi.tuni.koodimankelit.antibiootit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;
import fi.tuni.koodimankelit.antibiootit.services.DataHandler;
import fi.tuni.koodimankelit.antibiootit.services.DataHandlerImpl;

@RestController
@RequestMapping("/api/diagnoses")
public class DiagnosesController {

    @Autowired
    private final DataHandler dataHandler;

    private final AntibioticsService antibioticsService;

    public DiagnosesController() {
        this.dataHandler = new DataHandlerImpl();
        this.antibioticsService = new AntibioticsService();
    }

    @GetMapping("/all")
    public List<DiagnoseInfo> getDiagnoses() {
        return this.antibioticsService.getAllDiagnoseInfos();
    }
    
}

