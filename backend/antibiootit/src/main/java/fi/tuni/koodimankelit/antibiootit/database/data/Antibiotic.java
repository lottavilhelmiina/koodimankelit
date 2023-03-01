package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

public class Antibiotic {
    private final String name;
    private final String format;
    private final String info;
    private final String unit;
    private final int dosagePerDay;
    private final int maxDosePerDay;
    private final List<Strength> strengths;
    private final int days;
    private final int dosesPerDay;

    public Antibiotic(String name, String format, String info, String unit, int dosagePerDay, 
        int maxDosePerDay, List<Strength> strengths, int days, int dosesPerDay) {
            this.name = name;
            this.format = format;
            this.info = info;
            this.unit = unit;
            this.dosagePerDay = dosagePerDay;
            this.maxDosePerDay = maxDosePerDay;
            this.strengths = strengths;
            this.days = days;
            this.dosesPerDay = dosesPerDay;
    }

    public String getName() {
        return this.name;
    }

    public String getFormat() {
        return this.format;
    }

    public String getInfo() {
        return this.info;
    }

    public String getUnit() {
        return this.unit;
    }

    public int getDosagePerDay() {
        return this.dosagePerDay;
    }

    public int getMaxDosePerDay() {
        return this.maxDosePerDay;
    }

    public List<Strength> getStrengths() {
        return this.strengths;
    }

    public int getDays() {
        return this.days;
    }

    public int getDosesPerDay() {
        return this.dosesPerDay;
    }
}
