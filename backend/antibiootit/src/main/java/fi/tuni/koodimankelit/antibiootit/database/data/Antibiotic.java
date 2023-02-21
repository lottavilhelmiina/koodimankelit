package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.ArrayList;

public class Antibiotic {
    private String name;
    private String format;
    private String info;
    private String unit;
    private int dosagePerDay;
    private int maxDosePerDay;
    private ArrayList<Strength> strengths = new ArrayList<>();
    private int days;
    private int dosesPerDay;

    public Antibiotic() {}

    public Antibiotic(String name, String format, String info, String unit, int dosagePerDay, 
        int maxDosePerDay, ArrayList<Strength> strengths, int days, int dosesPerDay) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return this.format;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return this.info;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setDosagePerDay(int dosagePerDay) {
        this.dosagePerDay = dosagePerDay;
    }

    public int getDosagePerDay() {
        return this.dosagePerDay;
    }

    public void setMaxDosePerDay(int maxDosePerDay) {
        this.maxDosePerDay = maxDosePerDay;
    }

    public int getMaxDosePerDay() {
        return this.maxDosePerDay;
    }

    public void setStrengths(ArrayList<Strength> strengths) {
        this.strengths = strengths;
    }

    public ArrayList<Strength> getStrengths() {
        return this.strengths;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() {
        return this.days;
    }

    public void setDosesPerDay(int dosesPerDay) {
        this.dosesPerDay = dosesPerDay;
    }

    public int getDosesPerDay() {
        return this.dosesPerDay;
    }
}
