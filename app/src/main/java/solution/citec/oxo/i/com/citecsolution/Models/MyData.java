package solution.citec.oxo.i.com.citecsolution.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MyData {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("quantite")
    @Expose
    private String quantite;
    @SerializedName("pourcentage")
    @Expose
    private String pourcentage;
    @SerializedName("consommation")
    @Expose
    private String consommation;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getQuantite() {
        return quantite;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public String getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(String pourcentage) {
        this.pourcentage = pourcentage;
    }

    public String getConsommation() {
        return consommation;
    }

    public void setConsommation(String consommation) {
        this.consommation = consommation;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}