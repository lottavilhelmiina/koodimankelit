package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Database representation of diagnose
 */
@Document(collection = "diagnoses")
public class Diagnose {

    @Id
    private final String id;

    private final String name;
    private final String etiology;
    private final String infectionType;
    private final List<Treatment> treatments;
    private final List<CheckBoxInfo> checkBoxes;
    private final boolean needsAntibiotics;

    /**
     * Default constructor
     * @param id ICD-10 identifier
     * @param name diagnosis name
     * @param etiology diagnosis etiology (common cause)
     * @param infectionType infection type of the diagnose
     * @param checkBoxes list of possibly needed checkboxes for diagnose
     * @param treatments List of all treatment options 
     * @param needsAntibiotics tells if diagnose needs antibiotics
     */
    public Diagnose(String id, String name, String etiology, String infectionType, 
        List<CheckBoxInfo> checkBoxes, List<Treatment> treatments, boolean needsAntibiotics) {

        super();
        this.id = id;
        this.name = name;
        this.etiology = etiology;
        this.infectionType = infectionType;
        this.treatments = treatments;
        this.checkBoxes = checkBoxes;
        this.needsAntibiotics = needsAntibiotics;
    }

    /**
     * Returns diagnosis ICD-10 identifier
     * @return String id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Returns diagnosis name
     * @return String name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns diagnosis etiology
     * @return String etiology
     */
    public String getEtiology() {
        return this.etiology;
    }

    /**
     * Returns diagnosis infection type
     * @return String infectionType
     */
    public String getInfectionType() {
        return this.infectionType;
    }

    /**
     * Returns list of diagnosis treatment options 
     * @return List<Treatment> treatments
     */
    public List<Treatment> getTreatments() {
        return this.treatments;
    }

    /**
     * Returns list of checkboxes
     * @return List<CheckBoxInfo> checkBoxes
     */
    public List<CheckBoxInfo> getCheckBoxes() {
        return this.checkBoxes;
    }

    /**
     * Returns if diagnose needs antibiotics
     * @return boolean needsAntibiotics
     */
    public boolean getNeedsAntibiotics() {
        return this.needsAntibiotics;
    }
}