package fi.tuni.koodimankelit.antibiootit.builder;

import java.util.ArrayList;
import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.Treatment;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DiagnoseResponse;

public class DiagnoseResponseBuilder {
    private final Diagnose diagnose;
    private final double weight;
    private final boolean usePenicillinAllergic;



    public DiagnoseResponseBuilder(Diagnose diagnose, double weight, boolean usePenicillinAllergic) {
        this.diagnose = diagnose;
        this.weight = weight;
        this.usePenicillinAllergic = usePenicillinAllergic;
    }

    public DiagnoseResponse build() {

        DiagnoseResponse diagnoseResponse = new DiagnoseResponse(diagnose.getId(), diagnose.getEtiology());
        List<Treatment> treatments = getTreatments();

        for(Treatment treatment : treatments) {
            Antibiotic antibiotic = getSuitableAntibiotic(treatment);

            AntibioticTreatmentBuilder builder = new AntibioticTreatmentBuilder(antibiotic, this.weight);
            AntibioticTreatment antibioticTreatment = builder.build();

            diagnoseResponse.addTreatment(antibioticTreatment);
        }

        return diagnoseResponse;
        
    }

    private List<Treatment> getTreatments() {

        List<Treatment> treatments = new ArrayList<>();
        for(Treatment treatment : this.diagnose.getTreatments()) {
            if(isSuitableTreatment(treatment)) {
                treatments.add(treatment);
            }
        }
        return treatments;
        
    }

    private boolean isSuitableTreatment(Treatment treatment) {
        // CHANGE IMPLEMENTATION WHEN DATABASE USES CHOICE AS NUMBER
        if(this.usePenicillinAllergic) {
            return "penisillinAllergic".equals(treatment.getChoice());
        }
        return !"penisillinAllergic".equals(treatment.getChoice());
    }

    private Antibiotic getSuitableAntibiotic(Treatment treatment) {
        return null;
        
    }

}
