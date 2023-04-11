package fi.tuni.koodimankelit.antibiootit.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.CheckBoxInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;
import fi.tuni.koodimankelit.antibiootit.database.data.Dosage;
import fi.tuni.koodimankelit.antibiootit.database.data.Instructions;
import fi.tuni.koodimankelit.antibiootit.database.data.Mixture;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.database.data.Treatment;
import fi.tuni.koodimankelit.antibiootit.models.DiagnosisResponse;

public class DiagnosisResponseBuilderTest {
    
    private final String id = "id";
    private final String name = "name";
    private final String etiology = "etiology";
    private final String info = "info";
    private final String infectionType = "infectionType";
    private final boolean needsAntibiotics = false;

    private final List<CheckBoxInfo> checkBoxInfos = new ArrayList<>();
    private final List<Treatment> treatments = new ArrayList<>();

    private final Diagnosis diagnosis = new Diagnosis(
        id, name, etiology, info, infectionType, checkBoxInfos, treatments, needsAntibiotics);


    @BeforeEach
    public void populateCheckBoxInfos() {
        checkBoxInfos.clear();
        checkBoxInfos.add(new CheckBoxInfo("id-001", "infection1"));
        checkBoxInfos.add(new CheckBoxInfo("id-002", "infection2"));
    }

    @BeforeEach
    public void populateTreatments() {
        treatments.clear();
        List<Antibiotic> antibiotics1 = new ArrayList<>(
            Arrays.asList(
                generateMixture(
                    "antibiotic1-1", new ArrayList<Strength>(
                        Arrays.asList(
                            new Strength(100, 0, null, null),
                            new Strength(200, 20, null, null)
                        )
                    )
                ),
                generateTablet(
                    "antibiotic1-2", new ArrayList<Strength>(
                        Arrays.asList(
                            new Strength(1000000, 40, null, null),
                            new Strength(1500000, 50, null, null),
                            new Strength(2000000, 60, null, null)
                        )
                    )
                )
            )
        );
        treatments.add(new Treatment(1, antibiotics1));

        List<Antibiotic> antibiotics2 = new ArrayList<>(
            Arrays.asList(
                generateMixture(
                    "antibiotic2-1", new ArrayList<Strength>(
                        Arrays.asList(
                            new Strength(50, 0, null, null),
                            new Strength(100, 10, null, null)
                        )
                    )
                ),
                generateTablet(
                    "antibiotic2-2", new ArrayList<Strength>(
                        Arrays.asList(
                            new Strength(500, 40, null, null),
                            new Strength(750, 60, null, null)
                        )
                    )
                )
            )
        );
        treatments.add(new Treatment(2, antibiotics2));

        List<Antibiotic> antibiotics3 = new ArrayList<>(
            Arrays.asList(
                generateMixture(
                    "antibiotic3-1", new ArrayList<Strength>(
                        Arrays.asList(
                            new Strength(50, 0, null, null),
                            new Strength(100, 10, null, null)
                        )
                    )
                ),
                generateTablet(
                    "antibiotic3-2", new ArrayList<Strength>(
                        Arrays.asList(
                            new Strength(500, 40, null, null),
                            new Strength(750, 60, null, null)
                        )
                    )
                )
            )
        );
        treatments.add(new Treatment(3, antibiotics3));
        
    }

    @Test
    public void allergyIsTreatment3() {
        DiagnosisResponseBuilder builder = new DiagnosisResponseBuilder(diagnosis, 10, true);
        DiagnosisResponse response = builder.build();

        // Choice 3 because penicillin allergy
        // Mixture (antibiotic3-1) because weight 10 kg
        assertEquals(1, response.getTreatments().size());
        assertEquals("antibiotic3-1", response.getTreatments().get(0).getAntibiotic());
    }

    private Antibiotic generateMixture(String id, List<Strength> strengths) {
        Instructions instructions = new Instructions(10, 2, null, null);
        Dosage dosage = new Dosage(3000, 40, null);
        return new Mixture(id, null, strengths, instructions, null, dosage);
    }

    private Antibiotic generateTablet(String id, List<Strength> strengths) {
        Instructions instructions = new Instructions(5, 1, null, null);
        return new Tablet(id, null, strengths, instructions, 1);
    }
}
