package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Document;

import nonapi.io.github.classgraph.json.Id;

@Document(collection = "diagnoses")
public class DiagnoseInfo {
    
    @Id
    private final String id;
    private final String name;
    private final String etiology;
    private final boolean ebv;

    public DiagnoseInfo(String id, String name, String etiology, boolean ebv) {
        this.id = id;
        this.name = name;
        this.etiology = etiology;
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

    public boolean getEbv() {
        return this.ebv;
    }

}
