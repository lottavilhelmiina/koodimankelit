package fi.tuni.koodimankelit.antibiootit.models;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents all the diagnoses saved in the database
 */
@Schema(description = "Represents all the diagnoses saved in the database")
public class Diagnoses {

    @Schema(description = "Information of each diagnose")
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
    @Schema(description = "Returns the list of all diagnoses")
    public List<DiagnoseInfo> getDiagnoseInfos() {
        return this.diagnoses;
    }
}
