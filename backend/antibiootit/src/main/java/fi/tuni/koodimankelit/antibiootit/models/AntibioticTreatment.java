package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * API response representation of antibiotic treatment
 */
@Schema(description = "API response of antibiotic treatment")
public class AntibioticTreatment {
    private final String format;
    private final String antibiotic;
    private final Instructions instructions;

    /**
     * Default constructor
     * @param format antibiotic physical format
     * @param antibiotic name of the antibiotic
     * @param instructions instruction when to take the antibiotic
     */
    public AntibioticTreatment(String format, String antibiotic, Instructions instructions) {
        this.format = format;
        this.antibiotic = antibiotic;
        this.instructions = instructions;
    }
   
    /** 
     * Returns format
     * @return String format
     */
    public String getFormat() {
        return this.format;
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
}
