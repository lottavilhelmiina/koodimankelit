package fi.tuni.koodimankelit.antibiootit.services;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;
import fi.tuni.koodimankelit.antibiootit.models.Treatments;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;


public interface AntibioticsService {


    public Treatments calculateTreatments(Parameters parameters);

    public Diagnoses getAllDiagnoseInfos();

    public DiagnoseInfo getDiagnoseInfoByID(String id);
}
