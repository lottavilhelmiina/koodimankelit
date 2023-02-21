package fi.tuni.koodimankelit.antibiootit.database;
import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.ArrayList;
import java.util.HashMap;

public interface DiagnoseRepository extends MongoRepository<Diagnose, String> {

    ArrayList<Diagnose> getAll();

    @Query("{_id: '?0'}")
    Diagnose getById(String id);

    @Query(value = "{}", fields = "{'_id': 1, 'name': 1}")
    HashMap<String, String> getAllIdsAndNames();

}