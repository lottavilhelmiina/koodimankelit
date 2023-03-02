package fi.tuni.koodimankelit.antibiootit.builder;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;

public class AntibioticTreatmentBuilder {

    private final Antibiotic antibiotic;
    private final double weight;
    private final Integer strength;

    public AntibioticTreatmentBuilder(Antibiotic antibiotic, double weight) {


        this.antibiotic = antibiotic;
        this.weight = weight;
        this.strength = getStrength(antibiotic, weight);

    }

    public AntibioticTreatment build() {

        // Does not exceed minimum weight on any strength
        if(this.strength == null) {
            return null;
        }

        Instructions instructions = new Instructions(antibiotic.getDays(), antibiotic.getDosesPerDay());
        DosageFormula dosageFormula = new DosageFormula(
            new Measurement(antibiotic.getUnit(), this.strength),
            new Measurement(antibiotic.getUnit(), antibiotic.getDosagePerDay())
        );
        DosageResult dosageResult = new DosageResult(
            new Measurement("Tähän pitäisi saada yksiköt tietokannasta", calculateDosageResult(antibiotic, weight))
        );

        return new AntibioticTreatment(
            antibiotic.getFormat(),
            antibiotic.getInfo(),
            antibiotic.getName(),
            instructions,
            dosageFormula,
            dosageResult
        );
        
    }

    private static Integer getStrength(Antibiotic antibiotic, double weight) {

        // Sort by minimum weight, highest first
        antibiotic.getStrengths().sort((a, b) -> Integer.valueOf(b.getWeight()).compareTo(Integer.valueOf(a.getWeight())));
        for(Strength strength : antibiotic.getStrengths()) {
            if(weight <= strength.getWeight()) {
                return strength.getValue();
            }
        }
        return null;
    }

    private static Double calculateDosageResult(Antibiotic antibiotic, double weight) {
        double dosagePerDay = antibiotic.getDosagePerDay() * weight;
        Integer strength = getStrength(antibiotic, weight);
        if(strength == null) {
            throw new RuntimeException("Can not calculate dosage because strength is null");
        }
        double totalDosageInDay = dosagePerDay / strength;
        return totalDosageInDay / antibiotic.getDosesPerDay();
    }

}
