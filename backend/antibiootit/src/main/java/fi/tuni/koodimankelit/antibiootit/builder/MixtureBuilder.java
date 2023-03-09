package fi.tuni.koodimankelit.antibiootit.builder;

import fi.tuni.koodimankelit.antibiootit.database.data.Mixture;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;

public class MixtureBuilder extends AntibioticTreatmentBuilder {

    private final Mixture antibiotic;

    public MixtureBuilder(Mixture antibiotic, double weight) {
        super(antibiotic, weight);
        
        this.antibiotic = antibiotic;
    }

    @Override
    public AntibioticTreatment build() {

        // Does not exceed minimum weight on any strength
        if(this.strength == null) {
            // TODO change to custom exception
            throw new RuntimeException("Selected antibiotic has no suitable strength");
        }

        Instructions instructions = new Instructions(antibiotic.getDays(), antibiotic.getDosesPerDay());
        DosageFormula dosageFormula = new DosageFormula(
            new Measurement(strength.getUnit(), strength.getValue()),
            new Measurement(antibiotic.getDosagePerWeightPerDayUnit(), antibiotic.getDosagePerWeightPerDay())
        );
        DosageResult dosageResult = new DosageResult(
            new Measurement(antibiotic.getResultUnit(), calculateDosageResult())
        );

        return new AntibioticTreatment(
            antibiotic.getFormat(),
            antibiotic.getInfo(),
            antibiotic.getAntibiotic(),
            instructions,
            dosageFormula,
            dosageResult
        );
            
    }

    /** 
     * Calculates one-time antibiotic dosage based on weight
     * @return Double one-time dosage. Unit depends on antibiotic
     */
    private Double calculateDosageResult() {
        double dosagePerDay = antibiotic.getDosagePerWeightPerDay() * weight;

        double totalDosageInDay = dosagePerDay / strength.getValue();
        return totalDosageInDay / antibiotic.getDosesPerDay();
    }
    

    
}
