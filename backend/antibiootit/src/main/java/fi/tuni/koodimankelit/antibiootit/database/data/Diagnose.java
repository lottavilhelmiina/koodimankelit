package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diagnoses")
public class Diagnose {

    @Id
    public String id;

    public String name;
    public String etiology;
    public String infectionType;

    public ArrayList<Treatment> choicesOfTreatment = new ArrayList<>();

    public Diagnose() {super();}

    public Diagnose(String id, String name, String etiology, 
        String infectionType, ArrayList<Treatment> treatments) {

        super();
        this.id = id;
        this.name = name;
        this.etiology = etiology;
        this.infectionType = infectionType;
        this.choicesOfTreatment = treatments;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setEtiology(String etiology) {
        this.etiology = etiology;
    }

    public String getEtiology() {
        return this.etiology;
    }

    public void setInfectionType(String infectionType) {
        this.infectionType = infectionType;
    }

    public String getInfectionType() {
        return this.infectionType;
    }

    public void setChoicesOfTreatment(ArrayList<Treatment> treatments) {
        this.choicesOfTreatment = treatments;
    }

    public ArrayList<Treatment> getChoicesOftreatment() {
        return this.choicesOfTreatment;
    }
}