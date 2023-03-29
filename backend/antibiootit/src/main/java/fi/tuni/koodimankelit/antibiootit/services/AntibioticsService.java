package fi.tuni.koodimankelit.antibiootit.services;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;
import fi.tuni.koodimankelit.antibiootit.models.DiagnosisResponse;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;

/**
 * Antibiotic related services
 */
public interface AntibioticsService {


    /**
     * Calculates and generates treatments for given diagnosis
     * @param parameters Diagnosis generation parameters
     * @return Diagnosis with treatments
     */
    public DiagnosisResponse calculateTreatments(Parameters parameters);

    /** 
     * Returns all diagnoses and their basic information: id, name, etiology, checkboxes
     * @return Diagnoses List of diagnosis infos
     */
    public Diagnoses getAllDiagnosisInfos();

    public DiagnosisInfo getDiagnosisInfoByID(String id);

}
