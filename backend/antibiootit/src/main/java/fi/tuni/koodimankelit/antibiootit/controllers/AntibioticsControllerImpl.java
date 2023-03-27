package fi.tuni.koodimankelit.antibiootit.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;

import fi.tuni.koodimankelit.antibiootit.models.DiagnosisResponse;
import fi.tuni.koodimankelit.antibiootit.models.InfoTexts;
import fi.tuni.koodimankelit.antibiootit.models.NoAntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.request.InfectionSelection;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;
import fi.tuni.koodimankelit.antibiootit.validator.CheckBoxValidator;
import fi.tuni.koodimankelit.antibiootit.database.data.CheckBoxInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.exceptions.DiagnosisNotFoundException;
import fi.tuni.koodimankelit.antibiootit.exceptions.InvalidParameterException;
import fi.tuni.koodimankelit.antibiootit.exceptions.NoAntibioticTreatmentException;

@RestController
@RequestMapping("/api/antibiotics")
public class AntibioticsControllerImpl implements AntibioticsController {

    private final AntibioticsService antibioticsService;
    private final static String NO_ANTIBIOTIC_HEADER = "No-Antibiotic-Treatment";

    @Autowired
    private CheckBoxValidator checkBoxValidator;

    public AntibioticsControllerImpl(AntibioticsService antibioticsService) {
        this.antibioticsService = antibioticsService;
    }

    @PostMapping("/dose-calculation")
    public DiagnosisResponse doseCalculation(@RequestBody @Valid Parameters parameters) {
        
        String diagnosisID = parameters.getDiagnosisID();
        DiagnosisInfo diagnosisInfo = antibioticsService.getDiagnosisInfoByID(diagnosisID);

        List<CheckBoxInfo> checkBoxInfos = diagnosisInfo.getCheckBoxes();
        List<InfectionSelection> infectionSelections = parameters.getCheckBoxes();

        // Check that all required checkBoxes for the diagnosis are included in the request
        checkBoxValidator.validate(checkBoxInfos, infectionSelections);

        return this.antibioticsService.calculateTreatments(parameters);
    }

    @GetMapping("/diagnoses")
    public Diagnoses getDiagnoses() {
        return this.antibioticsService.getAllDiagnosisInfos();
    }

    @GetMapping("/info-texts")
    public InfoTexts getInfoTexts() {
        return this.antibioticsService.getAllInfoTexts();
    }

    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidParameterException(InvalidParameterException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(NoAntibioticTreatmentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NoAntibioticTreatment> handleNoAntiobiticTreatmentException(NoAntibioticTreatmentException ex) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(NO_ANTIBIOTIC_HEADER, "true");

        return ResponseEntity
            .ok()
            .headers(headers)
            .body(new NoAntibioticTreatment(ex.getDiagnosis()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.internalServerError().build();

    }

    @ExceptionHandler(DiagnosisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleDiagnosisNotFoundException(DiagnosisNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    public Map<String, String> handleNotValidMessageException(HttpMessageNotReadableException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }
}
