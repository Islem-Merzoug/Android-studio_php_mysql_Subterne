package solution.citec.oxo.i.com.citecsolution.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MyConsommation {

    @SerializedName("SUM(consommation)")
    @Expose
    private String sUMConsommation;
    @SerializedName("month")
    @Expose
    private String month;

    public String getSUMConsommation() {
        return sUMConsommation;
    }

    public void setSUMConsommation(String sUMConsommation) {
        this.sUMConsommation = sUMConsommation;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

}