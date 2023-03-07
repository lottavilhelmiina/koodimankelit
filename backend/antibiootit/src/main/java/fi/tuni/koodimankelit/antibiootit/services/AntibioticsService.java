package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.DiagnoseResponse;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;

/**
 * Antibiotic related services
 */
public interface AntibioticsService {


    /**
     * Calculates and generates treatments for given diagnose
     * @param parameters Diagnose generation parameters
     * @return Diagnose with treatments
     */
    public DiagnoseResponse calculateTreatments(Parameters parameters);

    public List<DiagnoseInfo> getAllDiagnoseInfos();

    public DiagnoseInfo getDiagnoseInfoByID(String id);
}
