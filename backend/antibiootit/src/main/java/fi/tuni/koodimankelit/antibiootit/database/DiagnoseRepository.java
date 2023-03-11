package fi.tuni.koodimankelit.antibiootit.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;

/**
 * MongoDB database repository for diagnoses
 */
@Repository
public interface DiagnoseRepository extends MongoRepository<Diagnose, String> {

    /**
     * Returns all diagnosis basic information from database: id, name, etiology, checkBoxes and if it needs antibiotics
     * @return List<DiagnoseInfo> 
     */
    @Query(value = "{}", fields = "{'_id': 1, 'name': 1, 'etiology': 1, 'checkBoxes': 1, 'needsAntibiotics': 1}")
    List<DiagnoseInfo> getAllDiagnoseInfos();

    /**
     * Finds and returns diagnosis info from database by given id
     * @param id diagnosis ICD-10 identifier
     * @return Optional<DiagnoseInfo>
     */
    @Query("{_id: '?0'}")
    Optional<DiagnoseInfo> getDiagnosisInfoById(String id);
}