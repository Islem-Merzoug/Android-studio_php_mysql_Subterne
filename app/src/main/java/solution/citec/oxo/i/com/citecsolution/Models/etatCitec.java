package solution.citec.oxo.i.com.citecsolution.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class etatCitec {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("pourcentage")
    @Expose
    private String pourcentage;
    @SerializedName("remplire")
    @Expose
    private String remplire;
    @SerializedName("consommation")
    @Expose
    private String consommation;
    @SerializedName("estimation")
    @Expose
    private String estimation;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getRemplire() {
        return remplire;
    }

    public void setRemplire(String remplire) {
        this.remplire = remplire;
    }

    public String getConsommation() {
        return consommation;
    }

    public void setConsommation(String consommation) {
        this.consommation = consommation;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

}