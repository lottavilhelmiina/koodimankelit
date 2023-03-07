package fi.tuni.koodimankelit.antibiootit.models;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;

/**
 * Represents all the diagnoses saved in the database
 */
public class Diagnoses {

    private final List<DiagnoseInfo> diagnoses;
    
    /**
     * Default constructor
     * @param List<DiagnoseInfo> information of each diagnose
     */
    public Diagnoses(List<DiagnoseInfo> diagnoseInfos) {
        this.diagnoses = diagnoseInfos;
    }

    /**
     * Returns the list of all diagnoses
     * @return List<DiagnoseInfo> information of each diagnose
     */
    public List<DiagnoseInfo> getDiagnoseInfos() {
        return this.diagnoses;
    }
}
