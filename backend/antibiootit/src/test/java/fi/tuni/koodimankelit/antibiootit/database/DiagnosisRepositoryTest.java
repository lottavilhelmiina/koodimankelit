package fi.tuni.koodimankelit.antibiootit.database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.CheckBoxInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnosis;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.database.data.DoseMultiplier;
import fi.tuni.koodimankelit.antibiootit.database.data.Instructions;
import fi.tuni.koodimankelit.antibiootit.database.data.Mixture;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.database.data.Treatment;

/**
 * Tests DiagnosisRepository and that integration with MongoDB and Spring Boot works
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DiagnosisRepositoryTest {

    private final String id = "J03.0";

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    private void testInstructions(Instructions instructions) {
        assertNotNull(instructions.getDays());
        assertNotNull(instructions.getDosesPerDay());
        assertNotNull(instructions.getRecipeText());
        assertNotNull(instructions.getDoseMultipliers());

        DoseMultiplier doseMultiplier = instructions.getDoseMultipliers().get(0);

        assertNotNull(doseMultiplier.getId());
        assertNotNull(doseMultiplier.getMultiplier());
    }

    private void testStrength(Strength strength) {
        assertNotNull(strength.getMinWeight());
        assertNotNull(strength.getUnit());
        assertNotNull(strength.getText());
        assertNotNull(strength.getValue());
    }

    @Test
    public void testFindDiagnosisByID() {
        Optional<Diagnosis> findResult = diagnosisRepository.findById(id);
        assertTrue(findResult.isPresent());

        // Test diagnosis attributes
        Diagnosis testDiagnosis = findResult.get();

        assertNotNull(testDiagnosis.getId());
        assertEquals(id, testDiagnosis.getId());
        assertNotNull(testDiagnosis.getName());
        assertNotNull(testDiagnosis.getEtiology());
        assertNotNull(testDiagnosis.getNeedsAntibiotics());
        assertNotNull(testDiagnosis.getCheckBoxes());

        CheckBoxInfo checkBoxInfo = testDiagnosis.getCheckBoxes().get(0);
        assertNotNull(checkBoxInfo.getId());
        assertNotNull(checkBoxInfo.getName());

        assertNotNull(testDiagnosis.getTreatments());

        // Test treatment's attributes
        Treatment testTreatment = testDiagnosis.getTreatments().get(0);

        assertNotNull(testTreatment.getChoice());
        assertNotNull(testTreatment.getAntibiotics());

        // Test mixture's attributes 
        Antibiotic antibioticMixture = testTreatment.getAntibiotics().get(0);
        assertTrue(antibioticMixture instanceof Mixture);

        Mixture mixture = (Mixture) antibioticMixture;
        
        assertNotNull(mixture.getAntibiotic());
        assertNotNull(mixture.getFormat());
        assertNotNull(mixture.getDosage());
        assertNotNull(mixture.getResultUnit());
        assertNotNull(mixture.getInstructions());
        testInstructions(mixture.getInstructions());
        assertNotNull(mixture.getStrength());
        testStrength(mixture.getStrength().get(0));

        // Test tablet's attributes
        Antibiotic antibioticTablet = testTreatment.getAntibiotics().get(1);
        assertTrue(antibioticTablet instanceof Tablet);

        Tablet tablet = (Tablet) antibioticTablet;

        assertNotNull(tablet.getAntibiotic());
        assertNotNull(tablet.getFormat());
        assertNotNull(tablet.getTabletsPerDose());
        assertNotNull(tablet.getInstructions());
        testInstructions(tablet.getInstructions());
        assertNotNull(tablet.getStrength().get(0));
        testStrength(tablet.getStrength().get(0));
    }

    @Test
    public void testFindAllDiagnosis() {
        List<Diagnosis> diagnoses = diagnosisRepository.findAll();
        assertFalse(diagnoses.isEmpty());
    }

    @Test
    public void testFindDiagnosisInfoByID() {
        Optional<DiagnosisInfo> findResult = diagnosisRepository.getDiagnosisInfoById(id);
        assertTrue(findResult.isPresent());

        DiagnosisInfo testDiagnosis = findResult.get();

        assertNotNull(testDiagnosis.getId());
        assertEquals(id, testDiagnosis.getId());
        assertNotNull(testDiagnosis.getName());
        assertNotNull(testDiagnosis.getEtiology());
        assertNotNull(testDiagnosis.getNeedsAntibiotics());
        assertNotNull(testDiagnosis.getCheckBoxes());
    }

    @Test 
    public void testFindAllDiagnosisInfos() {
        List<DiagnosisInfo> diagnosisInfos = diagnosisRepository.getAllDiagnosisInfos();
        assertFalse(diagnosisInfos.isEmpty());
    }
}
