package fi.tuni.koodimankelit.antibiootit.calculator.data;

public class Parameters {
    private final String diagnosisID;
    private final double weight;
    private final boolean penicillinAllergic;
    private final boolean ebv;


    public Parameters(String diagnosisID, double weight, boolean penicillinAllergic, boolean ebv) {
        this.diagnosisID = diagnosisID;
        this.weight = weight;
        this.penicillinAllergic = penicillinAllergic;
        this.ebv = ebv;
    }


    public String getDiagnosisID() {
        return this.diagnosisID;
    }


    public double getWeight() {
        return this.weight;
    }


    public boolean getPenicillinAllergic() {
        return this.penicillinAllergic;
    }


    public boolean getEbv() {
        return this.ebv;
    }



    @Override
    // For testing purposes
    public String toString() {
        return String.format("Parameters{%s %.1f PenicillinAllergic: %s Ebv: %s}", diagnosisID, weight, penicillinAllergic, ebv);
    }
    

}
