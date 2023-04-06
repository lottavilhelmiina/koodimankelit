package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * API response representation of antibiotic treatment
 */
@Schema(description = "API response of antibiotic treatment")
public class AntibioticTreatment {
    private final String format;
    private final String description;
    private final String antibiotic;
    private final Instructions instructions;
    private final DosageFormula dosageFormula;
    private final DosageResult dosageResult;


    /**
     * Default constructor
     * @param format antibiotic physical format
     * @param description info text
     * @param antibiotic name of the antibiotic
     * @param instructions instruction when to take the antibiotic
     * @param formula how the result was calculated
     * @param result calculated dosage result
     */
    public AntibioticTreatment(String format, String description, String antibiotic, Instructions instructions, DosageFormula formula, DosageResult result) {
        this.format = format;
        this.description = description;
        this.antibiotic = antibiotic;
        this.instructions = instructions;
        this.dosageFormula = formula;
        this.dosageResult = result;
    }


    
    /** 
     * Returns format
     * @return String format
     */
    public String getFormat() {
        return this.format;
    }


    
    /** 
     * Return description
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }


    
    /** 
     * Returns name
     * @return String name
     */
    public String getAntibiotic() {
        return this.antibiotic;
    }


    
    /** 
     * Return instrctions
     * @return Instructions
     */
    public Instructions getInstructions() {
        return this.instructions;
    }


    
    /** 
     * Returns formula how the result were calculated
     * @return DosageFormula
     */
    public DosageFormula getDosageFormula() {
        return this.dosageFormula;
    }


    
    /** 
     * Returns the dosage result
     * @return DosageResult
     */
    public DosageResult getDosageResult() {
        return this.dosageResult;
    }


}
