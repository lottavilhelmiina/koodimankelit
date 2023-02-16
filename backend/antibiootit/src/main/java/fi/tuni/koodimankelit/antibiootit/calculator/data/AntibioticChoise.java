package fi.tuni.koodimankelit.antibiootit.calculator.data;

public class AntibioticChoise {
    private final String format;
    private final String info;
    private final String antibiotic;
    private final Instructions instructions;
    private final Formula formula;
    private final Result result;


    public AntibioticChoise(String format, String info, String antibiotic, Instructions instructions, Formula formula, Result result) {
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


    public Formula getFormula() {
        return this.formula;
    }


    public Result getResult() {
        return this.result;
    }


}
