package fi.tuni.koodimankelit.antibiootit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Parameters;
import fi.tuni.koodimankelit.antibiootit.models.Treatments;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;
import fi.tuni.koodimankelit.antibiootit.services.DataHandler;
import fi.tuni.koodimankelit.antibiootit.services.DataHandlerImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/antibiotics")
public class AntibioticsController {

    @Autowired
    private final DataHandler dataHandler;

    private final AntibioticsService calculator;


    public AntibioticsController() {
        this.calculator = new AntibioticsService();
        this.dataHandler = new DataHandlerImpl();
    }

    
    @PostMapping("/dose-calculation")
    public Treatments doseCalculation(@RequestBody @Valid Parameters parameters) {
        // TEST ONLY
        System.out.println(parameters);
        return this.calculator.calculateTreatments(parameters);
    }


    // TEST ONLY
    @PostMapping("/database-search")
    public Diagnose getDiagnoseByID() {

        return dataHandler.getDiagnoseById("J21.9");
    }

    // TEST ONLY
    @PostMapping("/database-save")
    public boolean savDiagnose(@RequestBody @Valid Diagnose parameters) {
        dataHandler.addDiagnose(parameters);
        boolean toreturn = true;
        if (dataHandler.getDiagnoseById(parameters.getId()) == null) {
            toreturn = false;
        }        
        return toreturn;
    }

    // TEST ONLY
    @PostMapping("/get-diagnoseinfos")
    public List<DiagnoseInfo> getDiagnoseInfos() {
        return dataHandler.getAllDiagnoseInfos();
    }
    
}
