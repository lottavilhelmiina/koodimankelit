package fi.tuni.koodimankelit.antibiootit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.services.DataHandler;
import fi.tuni.koodimankelit.antibiootit.services.DataHandlerImpl;

@RestController
@RequestMapping("/api/databaseTest")
public class DatabaseTestController {

    @Autowired
    private final DataHandler dataHandler;

    public DatabaseTestController() {
        this.dataHandler = new DataHandlerImpl();
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
}
