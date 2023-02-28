package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import nonapi.io.github.classgraph.json.Id;

@Document(collection = "diagnoses")
public class DiagnoseInfo {

    @Id
    private final String id;
    private final String name;
    private final String etiology;
    private final List<CheckBoxInfo> checkBoxes;

    public DiagnoseInfo(String id, String name, String etiology, List<CheckBoxInfo> checkBoxes) {
        super();
        this.id = id;
        this.name = name;
        this.etiology = etiology;
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

    public List<CheckBoxInfo> getCheckBoxes() {
        return this.checkBoxes;
    }
}
