package fi.tuni.koodimankelit.antibiootit.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;

import fi.tuni.koodimankelit.antibiootit.models.DiagnosisResponse;
import fi.tuni.koodimankelit.antibiootit.models.InfoTexts;
import fi.tuni.koodimankelit.antibiootit.models.NoAntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import fi.tuni.koodimankelit.antibiootit.exceptions.DiagnosisNotFoundException;
import fi.tuni.koodimankelit.antibiootit.exceptions.InvalidParameterException;
import fi.tuni.koodimankelit.antibiootit.exceptions.NoAntibioticTreatmentException;

/**
 * REST API controller for antibiotics
 */
@Tag(name = "Antibiotics API")
public interface AntibioticsController {
    
    /** 
     * Handle requests for dose calculation
     * @param parameters Request body
     * @return DiagnosisResponse Response body
     */
    @Operation(summary = "Calculate antibiotic recommendation",
        description = "Calculates the recommended dose of antibiotic for a given diagnosis and weight")
    public DiagnosisResponse doseCalculation(@RequestBody @Valid Parameters parameters);
        
    /** 
     * Returns all diagnoses and their basic information: id, name, etiology, checkboxes
     * @return Diagnoses List of diagnosis infos
     */
    @Operation(summary = "Get all diagnoses", 
        description = "Returns a list of all diagnoses and their basic information")
    public Diagnoses getDiagnoses();

    /**
     * Returns all information texts for the web page
     * @return InfoTexts List of information texts
     */
    @Operation(summary = "Get all infromation texts", 
        description = "Returns all information texts for the web page")
    public InfoTexts getInfoTexts();

    
    /** 
     * Handle InvalidParameterException and return HTTP 400
     * @param ex InvalidParameterException
     * @return Map<String, String> Error message and description
     */
    public Map<String, String> handleInvalidParameterException(InvalidParameterException ex);

    /**
     * Handle NoAntibioticTreatmentException and return HTTP 200
     * @param ex NoAntibioticTreatmentException
     * @return NoAntibioticTreatment
     */
    public ResponseEntity<NoAntibioticTreatment> handleNoAntiobiticTreatmentException(NoAntibioticTreatmentException ex);

    /**
     * Handle RuntimeException and return HTTP 500
     * @param ex RuntimeException
     * @return Empty HTTP 500 error
     */
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex);

    /**
     * Handle DiagnosisNotFoundException and return HTTP 404
     * @param ex DiagnosisNotFoundException
     * @return Map<String, String> Error message
     */
    public Map<String, String> handleDiagnosisNotFoundException(DiagnosisNotFoundException ex);
}
