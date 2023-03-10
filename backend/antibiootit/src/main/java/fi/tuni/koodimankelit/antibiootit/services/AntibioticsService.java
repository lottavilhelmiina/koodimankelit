package fi.tuni.koodimankelit.antibiootit.services;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;
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

    /** 
     * Returns all diagnoses and their basic information: id, name, etiology, checkboxes
     * @return Diagnoses List of diagnose infos
     */
    public Diagnoses getAllDiagnoseInfos();

    public DiagnoseInfo getDiagnoseInfoByID(String id);
}