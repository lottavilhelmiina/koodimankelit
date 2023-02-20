package fi.tuni.koodimankelit.antibiootit.calculator.data;

public class AntibioticTreatment {
    private final String format;
    private final String info;
    private final String antibiotic;
    private final Instructions instructions;
    private final DosageFormula formula;
    private final DosageResult result;


    public AntibioticTreatment(String format, String info, String antibiotic, Instructions instructions, DosageFormula formula, DosageResult result) {
        this.format = format;
        this.info = info;
        this.antibiotic = antibiotic;
        this.instructions = instructions;
        this.formula = formula;
        this.result = result;
    }


    public String getFormat() {
        return this.format;
    }


    public String getInfo() {
        return this.info;
    }


    public String getAntibiotic() {
        return this.antibiotic;
    }


    public Instructions getInstructions() {
        return this.instructions;
    }


    public DosageFormula getFormula() {
        return this.formula;
    }


    public DosageResult getResult() {
        return this.result;
    }


}
