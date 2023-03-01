package fi.tuni.koodimankelit.antibiootit.services;

import java.util.List;

import org.springframework.stereotype.Service;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.Parameters;
import fi.tuni.koodimankelit.antibiootit.models.Treatment;
import fi.tuni.koodimankelit.antibiootit.models.Treatments;

@Service
public class AntibioticsServiceImpl implements AntibioticsService {

    private final DataHandler dataHandler;

    public AntibioticsServiceImpl(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }

    public Treatments calculateTreatments(Parameters parameters) {
        return this.mockCalculator();
    }

    public List<DiagnoseInfo> getAllDiagnoseInfos() {
        return this.dataHandler.getAllDiagnoseInfos();
    }
    
    // Mock calculation of treatments. Might be useful for unit testing also
    private Treatments mockCalculator() {

        Treatments d =  new Treatments("J03.0", "Streptokokki A");
        Treatment t = new Treatment();
        d.addTreatment(t);

        t.addAntibiotic(
            new AntibioticTreatment(
                "Mikstuura",
                "infoteksti...",
                "Amoksisilliini",
                new Instructions(10, 3),
                new DosageFormula(
                    new Measurement("mg/ml", 100),
                    new Measurement("mg/kg/vrk", 50)),
                new DosageResult(
                    new Measurement("ml", 4)
                )
            )
        );

        return d;
    }

}
