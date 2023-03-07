package fi.tuni.koodimankelit.antibiootit.services;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;
import fi.tuni.koodimankelit.antibiootit.models.Treatments;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;


public interface AntibioticsService {


    public Treatments calculateTreatments(Parameters parameters);

    /** 
     * Returns all diagnoses and their basic information: id, name, etiology, checkboxes
     * @return Diagnoses List of diagnose infos
     */
    public Diagnoses getAllDiagnoseInfos();

    public DiagnoseInfo getDiagnoseInfoByID(String id);
}
