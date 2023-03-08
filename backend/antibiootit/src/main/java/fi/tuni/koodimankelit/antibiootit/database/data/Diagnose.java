package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "diagnoses")
public class Diagnose {

    @Id
    private final String id;

    private final String name;
    private final String etiology;
    private final String infectionType;
    private final List<Treatment> treatments;
    private final List<CheckBoxInfo> checkBoxes;

    public Diagnose(String id, String name, String etiology, 
        String infectionType, List<CheckBoxInfo> checkBoxes, List<Treatment> treatments) {

        super();
        this.id = id;
        this.name = name;
        this.etiology = etiology;
        this.infectionType = infectionType;
        this.treatments = treatments;
        this.checkBoxes = checkBoxes;
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

    public List<Treatment> getTreatments() {
        return this.treatments;
    }

    public List<CheckBoxInfo> getCheckBoxes() {
        return this.checkBoxes;
    }
}