package solution.citec.oxo.i.com.citecsolution.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Consommation {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("Mois")
    @Expose
    private String mois;
    @SerializedName("Quntity")
    @Expose
    private String quntity;

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getQuntity() {
        return quntity;
    }

    public void setQuntity(String quntity) {
        this.quntity = quntity;
    }

}


