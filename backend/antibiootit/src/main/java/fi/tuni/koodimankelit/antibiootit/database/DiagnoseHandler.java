package fi.tuni.koodimankelit.antibiootit.database;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.tuni.koodimankelit.antibiootit.database.data.Diagnose;

@Service
public class DiagnoseHandler {

    @Autowired
    private final DiagnoseRepository repository;

    public DiagnoseHandler(DiagnoseRepository repository) {
        this.repository = repository;
    }

    public ArrayList<Diagnose> getAll() {
        return repository.getAll();
    }

    public Diagnose findById(String id) {
        return repository.getById(id);
    }

    public HashMap<String, String> findAllIdsAndNames() {
        return repository.getAllIdsAndNames();
    }
}