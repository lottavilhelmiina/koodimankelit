package fi.tuni.koodimankelit.antibiootit.models;

public class AntibioticTreatment {
    private final String format;
    private final String description;
    private final String antibiotic;
    private final Instructions instructions;
    private final DosageFormula dosageFormula;
    private final DosageResult dosageResult;


    public AntibioticTreatment(String format, String description, String antibiotic, Instructions instructions, DosageFormula formula, DosageResult result) {
        this.format = format;
        this.description = description;
        this.antibiotic = antibiotic;
        this.instructions = instructions;
        this.dosageFormula = formula;
        this.dosageResult = result;
    }


    public String getFormat() {
        return this.format;
    }


    public String getDescription() {
        return this.description;
    }


    public String getAntibiotic() {
        return this.antibiotic;
    }


    public Instructions getInstructions() {
        return this.instructions;
    }


    public DosageFormula getDosageFormula() {
        return this.dosageFormula;
    }


    public DosageResult getDosageResult() {
        return this.dosageResult;
    }


}
