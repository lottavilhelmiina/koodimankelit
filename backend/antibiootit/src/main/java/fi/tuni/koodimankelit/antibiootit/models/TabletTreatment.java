package fi.tuni.koodimankelit.antibiootit.models;

public class TabletTreatment extends AntibioticTreatment {
    private final TabletDosageResult dosageResult;
    private final TabletDosageFormula dosageFormula;
    /**
     * Default constructor
     * @param format antibiotic physical format
     * @param antibiotic name of the antibiotic
     * @param instructions instruction when to take the antibiotic
     * @param formula how the result was calculated
     * @param result calculated dosage result
     */
    public TabletTreatment(String format, String antibiotic, Instructions instructions, TabletDosageFormula dosageFormula, TabletDosageResult dosageResult) {
        super(format, antibiotic, instructions);
        this.dosageFormula = dosageFormula;
        this.dosageResult = dosageResult;
    }


    public TabletDosageResult getDosageResult() {
        return this.dosageResult;
    }


    public TabletDosageFormula getDosageFormula() {
        return this.dosageFormula;
    }
}
