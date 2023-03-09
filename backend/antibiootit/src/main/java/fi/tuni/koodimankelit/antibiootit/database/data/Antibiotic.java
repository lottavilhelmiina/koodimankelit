package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

public abstract class Antibiotic {
    private final String antibiotic;
    private final String format;
    private final String info;
    private final int maxDosePerDay;
    private final List<Strength> strength;
    private final String weightUnit;
    private final int days;
    private final int dosesPerDay;

    public Antibiotic(String antibiotic, String format, String info, 
        int maxDosePerDay, List<Strength> strength, String weightUnit, int days,
        int dosesPerDay) {
            this.antibiotic = antibiotic;
            this.format = format;
            this.info = info;
            this.maxDosePerDay = maxDosePerDay;
            this.strength = strength;
            this.days = days;
            this.dosesPerDay = dosesPerDay;
            this.weightUnit = weightUnit;
    }

    public String getAntibiotic() {
        return this.antibiotic;
    }

    public String getFormat() {
        return this.format;
    }

    public String getInfo() {
        return this.info;
    }

    public int getMaxDosePerDay() {
        return this.maxDosePerDay;
    }

    public List<Strength> getStrength() {
        return this.strength;
    }

    public String getWeightUnit() {
        return this.weightUnit;
    }

    public int getDays() {
        return this.days;
    }

    public int getDosesPerDay() {
        return this.dosesPerDay;
    }
}
