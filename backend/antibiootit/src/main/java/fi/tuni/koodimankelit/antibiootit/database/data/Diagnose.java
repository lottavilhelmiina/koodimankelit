package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diagnoses")
public class Diagnose {

    @Id
    private final String id;

    private final String name;
    private final String etiology;
    private final String infectionType;
    private final boolean ebv;
    private final ArrayList<Treatment> choicesOfTreatment;

    public Diagnose(String id, String name, String etiology, 
        String infectionType, ArrayList<Treatment> treatments, Boolean ebv) {

        super();
        this.id = id;
        this.name = name;
        this.etiology = etiology;
        this.infectionType = infectionType;
        this.choicesOfTreatment = treatments;
        this.ebv = ebv;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEtiology() {
        return this.etiology;
    }

    public String getInfectionType() {
        return this.infectionType;
    }

    public ArrayList<Treatment> getChoicesOftreatment() {
        return this.choicesOfTreatment;
    }

    public boolean getEbv() {
        return this.ebv;
    }

    public ArrayList<Treatment> getChoicesOfTreatment() {
        return this.choicesOfTreatment;
    }
}