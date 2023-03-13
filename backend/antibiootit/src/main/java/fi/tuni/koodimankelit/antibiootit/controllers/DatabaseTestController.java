package fi.tuni.koodimankelit.antibiootit.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;
import fi.tuni.koodimankelit.antibiootit.services.DataHandler;

@RestController
@RequestMapping("/api/databaseTest")
public class DatabaseTestController {

    private final DataHandler dataHandler;

    public DatabaseTestController(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
    
    // TEST ONLY
    @PostMapping("/database-search")
    public Diagnosis getDiagnosisByID() {
        return dataHandler.getDiagnosisById("J03.0");
    }
    
    // TEST ONLY
    @PostMapping("/get-diagnosisinfos")
    public List<DiagnosisInfo> getDiagnosisInfos() {
        return dataHandler.getAllDiagnosisInfos();
    }

    // TEST ONLY
    @PostMapping("/get-diagnosisinfo")
    public DiagnosisInfo getDiagnosisInfo() {
        return dataHandler.getDiagnosisInfoById("J03.0");
    }

    @PostMapping("/get-infoTexts")
    public List<InfoText> getInfoTexts() {
        return dataHandler.getAllInfoTexts();
    }
}
