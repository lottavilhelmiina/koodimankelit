package fi.tuni.koodimankelit.antibiootit.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;

import fi.tuni.koodimankelit.antibiootit.models.DiagnoseResponse;
import fi.tuni.koodimankelit.antibiootit.models.NoAntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.request.InfectionSelection;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;
import fi.tuni.koodimankelit.antibiootit.validator.CheckBoxValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import fi.tuni.koodimankelit.antibiootit.database.data.CheckBoxInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.exceptions.InvalidParameterException;
import fi.tuni.koodimankelit.antibiootit.exceptions.NoAntibioticTreatmentException;

/**
 * REST API controller for antibiotics
 */
@RestController
@RequestMapping("/api/antibiotics")
@Tag(name = "Antibiotics API", description = "description")
public class AntibioticsController {

    private final AntibioticsService antibioticsService;
    private final static String NO_ANTIBIOTIC_HEADER = "No-Antibiotic-Treatment";

    @Autowired
    private CheckBoxValidator checkBoxValidator;


    /**
     * Default constructor
     * @param antibioticsService antibiotic treatment related service
     */
    public AntibioticsController(AntibioticsService antibioticsService) {
        this.antibioticsService = antibioticsService;
    }

    
    
    /** 
     * Handle requests for dose calculation
     * @param parameters Request body
     * @return DiagnoseResponse Response body
     */
    @PostMapping("/dose-calculation")
    public DiagnoseResponse doseCalculation(@RequestBody @Valid Parameters parameters) {
        
        String diagnosisID = parameters.getDiagnosisID();
        DiagnoseInfo diagnoseInfo = antibioticsService.getDiagnoseInfoByID(diagnosisID);

        List<CheckBoxInfo> checkBoxInfos = diagnoseInfo.getCheckBoxes();
        List<InfectionSelection> infectionSelections = parameters.getCheckBoxes();

        // Check that all required checkBoxes for the diagnosis are included in the request
        checkBoxValidator.validate(checkBoxInfos, infectionSelections);

        return this.antibioticsService.calculateTreatments(parameters);
    }

    /** 
     * Returns all diagnoses and their basic information: id, name, etiology, checkboxes
     * @return Diagnoses List of diagnose infos
     */
    @GetMapping("/diagnoses")
    @Operation(summary = "Get all diagnoses", 
    description = "Returns a list of all diagnoses and their basic information")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Diagnoses getDiagnoses() {
        return this.antibioticsService.getAllDiagnoseInfos();
    }

    
    /** 
     * Handle InvalidParameterException and return HTTP 400
     * @param ex InvalidParameterException
     * @return Map<String, String> Error message and description
     */
    @ExceptionHandler(InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleInvalidParameterException(InvalidParameterException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("Error", ex.getMessage());
        return errorMap;
    }

    /**
     * Handle NoAntibioticTreatmentException and return HTTP 200
     * @param ex NoAntibioticTreatmentException
     * @return NoAntibioticTreatment
     */
    @ExceptionHandler(NoAntibioticTreatmentException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<NoAntibioticTreatment> handleNoAntiobiticTreatmentException(NoAntibioticTreatmentException ex) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.add(NO_ANTIBIOTIC_HEADER, "true");

        return ResponseEntity
            .ok()
            .headers(headers)
            .body(new NoAntibioticTreatment(ex.getDiagnose()));
    }


    /**
     * Handle RuntimeException and return HTTP 500
     * @param ex RuntimeException
     * @return Empty HTTP 500 error
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.internalServerError().build();

    }



}
