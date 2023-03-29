package fi.tuni.koodimankelit.antibiootit.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.exceptions.DiagnosisNotFoundException;
import fi.tuni.koodimankelit.antibiootit.services.DataHandler;
import io.swagger.v3.oas.annotations.Hidden;

@Hidden
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

    /**
     * Handle DiagnosisNotFoundException and return HTTP 404
     * @param ex DiagnosisNotFoundException
     * @return Map<String, String> Error message
     */
    @ExceptionHandler(DiagnosisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleDiagnosisNotFoundException(DiagnosisNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }
}
