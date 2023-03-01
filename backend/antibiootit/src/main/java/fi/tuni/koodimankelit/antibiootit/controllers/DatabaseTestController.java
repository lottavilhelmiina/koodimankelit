package fi.tuni.koodimankelit.antibiootit.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
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
    public Diagnose getDiagnoseByID() {
        return dataHandler.getDiagnoseById("J21.9");
    }
    
    // TEST ONLY
    @PostMapping("/get-diagnoseinfos")
    public List<DiagnoseInfo> getDiagnoseInfos() {
        return dataHandler.getAllDiagnoseInfos();
    }

    // TEST ONLY
    @PostMapping("/get-infoTexts")
    public List<InfoText> getInfoTexts() {
        return dataHandler.getAllInfoTexts();
    }
}
