package fi.tuni.koodimankelit.antibiootit.models;

public class MixtureTreatment extends AntibioticTreatment {
    private final MixtureDosageResult dosageResult;
    private final MixtureDosageFormula dosageFormula;

    /**
     * Default constructor
     * @param format antibiotic physical format
     * @param antibiotic name of the antibiotic
     * @param instructions instruction when to take the antibiotic
     * @param formula how the result was calculated
     * @param result calculated dosage result
     */
    public MixtureTreatment(String format, String antibiotic, Instructions instructions, MixtureDosageFormula formula, MixtureDosageResult result) {
        super(format, antibiotic, instructions);
        this.dosageFormula = formula;
        this.dosageResult = result;
    }


    public MixtureDosageResult getDosageResult() {
        return this.dosageResult;
    }


    public MixtureDosageFormula getDosageFormula() {
        return this.dosageFormula;
    }
}
