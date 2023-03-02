package fi.tuni.koodimankelit.antibiootit.builder;

import java.util.ArrayList;
import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.Treatment;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DiagnoseResponse;

/**
 * Builder for diagnose response. Includes only suitable treatments 
 */
public class DiagnoseResponseBuilder {
    private final Diagnose diagnose;
    private final double weight;
    private final boolean usePenicillinAllergic;


    /**
     * Default constructor
     * @param diagnose Database entity instance
     * @param weight weight in kilograms
     * @param usePenicillinAllergic True, if penicillin allergic option should be used
     */
    public DiagnoseResponseBuilder(Diagnose diagnose, double weight, boolean usePenicillinAllergic) {
        this.diagnose = diagnose;
        this.weight = weight;
        this.usePenicillinAllergic = usePenicillinAllergic;
    }

    
    /** 
     * Build diagnose response object
     * @return DiagnoseResponse generated instance
     */
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

    
    /** 
     * Returns all suitable treatments based on penicillin allergy
     * @return List<Treatment> suitable treatments
     */
    private List<Treatment> getTreatments() {

        List<Treatment> treatments = new ArrayList<>();
        for(Treatment treatment : this.diagnose.getTreatments()) {
            if(isSuitableTreatment(treatment)) {
                treatments.add(treatment);
            }
        }
        return treatments;
        
    }

    
    /** 
     * Return True if treatment is suitable based on penicillin allergy
     * @param treatment specific treatment
     * @return boolean True, if treatment is suitable
     */
    private boolean isSuitableTreatment(Treatment treatment) {
        // CHANGE IMPLEMENTATION WHEN DATABASE USES CHOICE AS NUMBER
        if(this.usePenicillinAllergic) {
            return "penisillinAllergic".equals(treatment.getChoice());
        }
        return !"penisillinAllergic".equals(treatment.getChoice());
    }

    
    /** 
     * Returns suitable antibiotic choise from given treatment based on weight
     * @param treatment specific treatment
     * @return Antibiotic preferred antibiotic
     */
    private Antibiotic getSuitableAntibiotic(Treatment treatment) {
        return null;
        
    }

}
