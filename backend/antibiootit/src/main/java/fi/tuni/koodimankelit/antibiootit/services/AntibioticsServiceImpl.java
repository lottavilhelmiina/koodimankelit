package fi.tuni.koodimankelit.antibiootit.services;

import org.springframework.stereotype.Service;

import fi.tuni.koodimankelit.antibiootit.builder.DiagnoseResponseBuilder;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;
import fi.tuni.koodimankelit.antibiootit.models.DiagnoseResponse;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;

@Service
public class AntibioticsServiceImpl implements AntibioticsService {

    private final DataHandler dataHandler;

    public AntibioticsServiceImpl(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public DiagnoseResponse calculateTreatments(Parameters parameters) {

        Diagnose diagnose = dataHandler.getDiagnoseById(parameters.getDiagnosisID());

        // If penicillinAllergic or any infection (checkBox is True)
        boolean usePenicillinAllergic = parameters.getPenicillinAllergic();
        usePenicillinAllergic = usePenicillinAllergic || parameters.getCheckBoxes().stream().anyMatch(c -> c.getValue());

        // Build response
        DiagnoseResponseBuilder builder = new DiagnoseResponseBuilder(diagnose, parameters.getWeight(), usePenicillinAllergic);
        return builder.build();
        
    }

    public Diagnoses getAllDiagnoseInfos() {
        Diagnoses allDiagnoses = new Diagnoses(this.dataHandler.getAllDiagnoseInfos());
        return allDiagnoses;
    }

    @Override
    public DiagnoseInfo getDiagnoseInfoByID(String id) {
        return dataHandler.getDiagnosisInfoById(id);
    }

}
