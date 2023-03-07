package fi.tuni.koodimankelit.antibiootit.models;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;

public class Diagnoses {

    private final List<DiagnoseInfo> diagnoses;
    
    public Diagnoses(List<DiagnoseInfo> diagnoseInfos) {
        this.diagnoses = diagnoseInfos;
    }

    public List<DiagnoseInfo> getDiagnoseInfos() {
        return this.diagnoses;
    }
}
