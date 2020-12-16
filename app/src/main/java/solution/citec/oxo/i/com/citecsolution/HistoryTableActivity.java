package solution.citec.oxo.i.com.citecsolution;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import solution.citec.oxo.i.com.citecsolution.Adapters.ConsommationAdapter;
import solution.citec.oxo.i.com.citecsolution.ApiRest.RetrofitApi;
import solution.citec.oxo.i.com.citecsolution.Models.MyConsommation;

public class HistoryTableActivity extends AppCompatActivity {


    ArrayList<MyConsommation> MyConsommations,FinalMyconsomation;
    private Retrofit retrofit;
    private RetrofitApi dataapi;
    private RecyclerView recyclerView;
    private ImageView btnRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_history_table );

        btnRetour = (ImageView) findViewById( R.id.retour );
        recyclerView = (RecyclerView) findViewById( R.id.recyclerview );
        retrofit = new Retrofit.Builder()
                .baseUrl( "url_here" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        doRequest( new ApiCallback() {
            @Override
            public void onSuccess(List<MyConsommation> result) {

                MyConsommations = (ArrayList<MyConsommation>) result;
                ConsommationAdapter mAdapter = new ConsommationAdapter( MyConsommations, HistoryTableActivity.this );

                recyclerView.setLayoutManager( new LinearLayoutManager( getApplicationContext() ) );
                recyclerView.setHasFixedSize( true );
                recyclerView.setFocusable( false );


                recyclerView.setAdapter( mAdapter );

            }


        } );

        btnRetour.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HistoryTableActivity.this.finish();
            }
        } );


    }


    //Methode to get data from OnRespons an use it any where
    public void doRequest(final ApiCallback apiCallback) {

        dataapi = retrofit.create( RetrofitApi.class );
        Call<List<MyConsommation>> call = dataapi.getHistory();

        call.enqueue( new Callback<List<MyConsommation>>() {
            @Override
            public void onResponse(Call<List<MyConsommation>> call, Response<List<MyConsommation>> response) {

                MyConsommations = (ArrayList<MyConsommation>) response.body();


                for (int i = 0; i < MyConsommations.size(); i++) {




                    Calendar cal=Calendar.getInstance();
                    SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
                    String month = MyConsommations.get( i ).getMonth();
                    cal.set(Calendar.MONTH,Integer.parseInt( month ));
                    String month_name = month_date.format(cal.getTime());
                    MyConsommations.get( i ).setMonth( month_name );

                              FinalMyconsomation = MyConsommations;



                }


                apiCallback.onSuccess( MyConsommations );

            }

            @Override
            public void onFailure(Call<List<MyConsommation>> call, Throwable t) {
                Log.e( "onFailure", "Verify" );
            }
        } );

    }


    //Intrface to get data from OnRespons
    public interface ApiCallback {
        void onSuccess(List<MyConsommation> result);
    }
}
