package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import nonapi.io.github.classgraph.json.Id;

/**
 * Representation of diagnosis basic info
 */
@Document(collection = "diagnoses")
public class DiagnoseInfo {

    @Id
    private final String id;
    private final String name;
    private final String etiology;
    private final List<CheckBoxInfo> checkBoxes;
    private final boolean needsAntibiotics;

    /**
     * Default constructor
     * @param id ICD-10 identifier
     * @param name name of diagnose
     * @param etiology etiology (common cause) of diagnose
     * @param checkBoxes list of possibly needed checkboxes for diagnose
     * @param needsAntibiotics tells if diagnose needs antibiotics
     */
    public DiagnoseInfo(String id, String name, String etiology, List<CheckBoxInfo> checkBoxes, boolean needsAntibiotics) {
        super();
        this.id = id;
        this.name = name;
        this.etiology = etiology;
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
     * Returns list of checkboxes
     * @return List<CheckBoxInfo> checkBoxes
     */
    public List<CheckBoxInfo> getCheckBoxes() {
        return this.checkBoxes;
    }

    /**
     * Returns if diagnose need antibiotics
     * @return boolean needsAntibiotics
     */
    public boolean getNeedsAntibiotics() {
        return this.needsAntibiotics;
    }
}
