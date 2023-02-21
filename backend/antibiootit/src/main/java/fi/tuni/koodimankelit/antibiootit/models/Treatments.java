package fi.tuni.koodimankelit.antibiootit.models;

import java.util.ArrayList;
import java.util.List;

public class Treatments {
    private final String id;
    private final String etiology;
    private final ArrayList<Treatment> choisesOfTreatments;


    public Treatments(String id, String etiology) {
        this.id = id;
        this.etiology = etiology;
        this.choisesOfTreatments = new ArrayList<>();
    }

    public void addTreatment(Treatment treatment) {
        this.choisesOfTreatments.add(treatment);
    }


    public String getId() {
        return this.id;
    }


    public String getEtiology() {
        return this.etiology;
    }


    public List<Treatment> getChoisesOfTreatments() {
        return this.choisesOfTreatments;
    }



}
