package fi.tuni.koodimankelit.antibiootit.database;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;
import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;

@Repository
public interface DiagnoseRepository extends MongoRepository<Diagnose, String> {

    @Query(value = "{}", fields = "{'_id': 1, 'name': 1, 'etiology': 1, 'checkBoxes': 1}")
    List<DiagnoseInfo> getAllDiagnoseInfos();

    @Query("{_id: '?0'}")
    DiagnoseInfo getDiagnosisInfoById(String id);
}