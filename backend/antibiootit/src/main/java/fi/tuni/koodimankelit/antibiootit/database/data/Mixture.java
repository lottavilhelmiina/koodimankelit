package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Database representation of antibiotic in a form of mixture
 */
public class Mixture extends Antibiotic {
    @Field("resultUnit")
    private final String resultUnit;
    @Field("dosagePerWeightPerDay")
    private final int dosagePerWeightPerDay;
    @Field("dosagePerWeightPerDayUnit")
    private final String dosagePerWeightPerDayUnit;

    /**
     * Default constructor
     * @param antibiotic antibiotic's name
     * @param format antibiotic's format
     * @param info extra info about antibiotic
     * @param maxDosePerDay max dose per day
     * @param strength list of different strengths of the antibiotic
     * @param weightUnit used weight unit
     * @param days how many days antibiotic needs to be taken
     * @param dosesPerDay how many times a day antibiotic needs to be taken
     * @param resultUnit mixtures result unit
     * @param dosagePerWeightPerDay mixtures dosage per day per weight
     * @param dosagePerWeightPerDayUnit mixtures unit dor dosage per day per weight
     * @param doseMultipliers info if dose needs to be multiplied on some days
     */
    public Mixture(String antibiotic, String format, String info, 
    int maxDosePerDay, List<Strength> strength, String weightUnit, int days,
    int dosesPerDay, String resultUnit, int dosagePerWeightPerDay, String dosagePerWeightPerDayUnit, List<DoseMultiplier> doseMultipliers) {
        super(antibiotic, format, info, maxDosePerDay, strength, weightUnit, days, dosesPerDay, doseMultipliers);
        this.resultUnit = resultUnit;
        this.dosagePerWeightPerDay = dosagePerWeightPerDay;
        this.dosagePerWeightPerDayUnit = dosagePerWeightPerDayUnit;
    }

    /**
     * Returns mixture's result unit
     * @return String resultUnit
     */
    public String getResultUnit() {
        return this.resultUnit;
    }

    /**
     * Returns mixture's dosage per day per weight
     * @return int dosagePerWeightPerDay
     */
    public int getDosagePerWeightPerDay() {
        return this.dosagePerWeightPerDay;
    }

    /**
     * Returns unit of mixture's dosage per day per weight
     * @return String dosagePerWeightPerDayUnit
     */
    public String getDosagePerWeightPerDayUnit() {
        return this.dosagePerWeightPerDayUnit;
    }
}
