package solution.citec.oxo.i.com.citecsolution.ApiRest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import solution.citec.oxo.i.com.citecsolution.Models.Consommation;
import solution.citec.oxo.i.com.citecsolution.Models.MyConsommation;
import solution.citec.oxo.i.com.citecsolution.Models.MyData;
import solution.citec.oxo.i.com.citecsolution.Models.Respons;
import solution.citec.oxo.i.com.citecsolution.Models.etatCitec;


public interface RetrofitApi {


    @POST("citec/get_data_android_graph.php")
    Call<List<MyData>> getData();


    @POST("citec/get_data_android_table.php")
    Call<List<MyConsommation>> getHistory();


    @POST("citec/db_consommation_mois.php")
    Call<List<Consommation>> getConsommation();


    @POST("citec/db_state.php")
    Call<List<etatCitec>> getEtat();

    @FormUrlEncoded
    @POST("citec/logreg/login.php")
    Call<Respons> CheckLogin(@Field("email") String email,
                             @Field("password") String password);


    @FormUrlEncoded
    @POST("citec/logreg/register.php")
    Call<Respons> Register(@Field("reference") String reference,
                           @Field("email") String email,
                           @Field("password") String password);


}
