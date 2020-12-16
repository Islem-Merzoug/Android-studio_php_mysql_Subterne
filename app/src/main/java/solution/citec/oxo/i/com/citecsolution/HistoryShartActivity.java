package solution.citec.oxo.i.com.citecsolution;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import solution.citec.oxo.i.com.citecsolution.ApiRest.RetrofitApi;
import solution.citec.oxo.i.com.citecsolution.Models.MyData;
import solution.citec.oxo.i.com.citecsolution.helper.HourAxisValueFormatter;

public class HistoryShartActivity extends AppCompatActivity {


    static List<MyData> Mydata;
    LineChart lineChart;
    int DateConvertedZero;
    int DateConverted;
    int XNewdateConverted;
    Long dateZero;
    private Retrofit retrofit;
    private RetrofitApi dataapi;

    Long reference_timestape;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_history_shart );
        final Button buttonQuantity = (Button) findViewById( R.id.button_graph_parse );
        lineChart = (LineChart) findViewById( R.id.chart );

        retrofit = new Retrofit.Builder()
                .baseUrl("url_here" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        buttonQuantity.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doRequest( new ApiCallback() {
                    @Override
                    public void onSuccess(List<MyData> result) {

                        ArrayList<Entry> entries1 = new ArrayList<Entry>();


                        int id, qtn;
                        String DateZero;




                        Date datee = null;

                        DateFormat formattert = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                        String DateReference = null;
                        if(result.size()>0){
                            DateReference = result.get( 0 ).getTimestamp();
                            try {
                                datee = (Date) formattert.parse( DateReference );
                                dateZero = datee.getTime()/1000L;
                                String str=Long.toString(dateZero);
                                reference_timestape = Long.parseLong(str)*1000;


                                for (int i = 0; i < result.size(); i++) {

                                    id = Integer.parseInt( result.get( i ).getID() );
                                    qtn = Integer.parseInt( result.get( i ).getConsommation());
                                    DateZero = result.get( i ).getTimestamp();
                                    Log.e( "DDDDDAAAAA",DateZero );

                                    try {


                                        DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                                        datee = (Date) formatter.parse( DateZero );
                                        dateZero = datee.getTime()/1000L;

                                        String stre=Long.toString(dateZero);
                                        long timestamp = Long.parseLong(stre)*1000;
                                        long newtimpstmp = timestamp-reference_timestape;




                                        //HourAxisValueFormatter xAxisFormatter = new HourAxisValueFormatter( timestamp );
                                        XAxis xAxis = lineChart.getXAxis();
                                        //xAxis.setGranularity(2f);
                                        xAxis.setValueFormatter( new HourAxisValueFormatter( reference_timestape ) );


                                        entries1.add( new Entry( newtimpstmp, qtn ) );
                                        Log.e( "Resulteeeeeeee", newtimpstmp + "" );


                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }


                                    //Log.e( "My DATA", result.get( i ).getID() + "  + " + result.get( i ).getConsommation() + " + " + result.get( i ).getTimestamp() );

                                }


                                //Here you use Chart

                                LineDataSet dataSet = new LineDataSet( entries1, "Label" );
                                ArrayList<ILineDataSet> datasets = new ArrayList<>();
                                datasets.add( dataSet );

                                //lineChart.setBackgroundColor( Color.BLUE );
                                lineChart.setDrawGridBackground( true );

                                Description description = new Description();
                                description.setText( "Water consumption L/Day" );
                                description.setTextSize( 10 );
                                description.setTextColor( Color.BLUE );

                                lineChart.setDescription( description );
                                dataSet.setLineWidth( 3 );
                                dataSet.setColor( Color.MAGENTA );
                                dataSet.setDrawCircleHole( true );
                                dataSet.setCircleColor( Color.BLUE );
                                dataSet.setCircleRadius( 4 );
                                dataSet.setCircleHoleRadius( 3 );
                                dataSet.setValueTextSize( 10 );
                                //dataSet.enableDashedLine( 5,10,0 );
                                Legend legend;
                                legend = lineChart.getLegend();
                                legend.setEnabled( false );

                                LineData lineData = new LineData( datasets );
                                lineChart.setData( lineData );
                                lineChart.invalidate();


                                lineChart.animateX( 2000 );


                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Toast.makeText( HistoryShartActivity.this,"Any Data from server ! ",Toast.LENGTH_SHORT ).show();
                        }














                    }
                } );

            }
        } );

                doRequest( new ApiCallback() {
                    @Override
                    public void onSuccess(List<MyData> result) {

                        ArrayList<Entry> entries1 = new ArrayList<Entry>();


                        int id, qtn;
                        String DateZero;




                        Date datee = null;

                        DateFormat formattert = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

                        String DateReference = null;
                        if(result.size()>0){
                             DateReference = result.get( 0 ).getTimestamp();

                            try {
                                datee = (Date) formattert.parse( DateReference );
                                dateZero = datee.getTime()/1000L;
                                String strr=Long.toString(dateZero);
                                reference_timestape = Long.parseLong(strr)*1000;

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            for (int i = 0; i < result.size(); i++) {

                                id = Integer.parseInt( result.get( i ).getID() );
                                qtn = Integer.parseInt( result.get( i ).getConsommation() );
                                DateZero = result.get( i ).getTimestamp();


                                try {


                                    DateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
                                    datee = (Date) formatter.parse( DateZero );
                                    dateZero = datee.getTime()/1000L;

                                    String stry=Long.toString(dateZero);
                                    long timestamp = Long.parseLong(stry)*1000;
                                    long newtimpstmp = timestamp-reference_timestape;




                                    //HourAxisValueFormatter xAxisFormatter = new HourAxisValueFormatter( timestamp );
                                    XAxis xAxis = lineChart.getXAxis();
                                    //xAxis.setGranularity(2f);
                                    xAxis.setValueFormatter( new HourAxisValueFormatter( reference_timestape ) );


                                    entries1.add( new Entry( newtimpstmp, qtn ) );
                                    Log.e( "Resulteeeeeeee", newtimpstmp + "" );


                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }


                                //Log.e( "My DATA", result.get( i ).getID() + "  + " + result.get( i ).getConsommation() + " + " + result.get( i ).getTimestamp() );

                            }


                            //Here you use Chart

                            LineDataSet dataSet = new LineDataSet( entries1, "Label" );
                            ArrayList<ILineDataSet> datasets = new ArrayList<>();
                            datasets.add( dataSet );

                            //lineChart.setBackgroundColor( Color.BLUE );
                            lineChart.setDrawGridBackground( true );

                            Description description = new Description();
                            description.setText( "Water consumption L/Day" );
                            description.setTextSize( 10 );
                            description.setTextColor( Color.BLUE );

                            lineChart.setDescription( description );
                            dataSet.setLineWidth( 3 );
                            dataSet.setColor( Color.MAGENTA );
                            dataSet.setDrawCircleHole( true );
                            dataSet.setCircleColor( Color.BLUE );
                            dataSet.setCircleRadius( 4 );
                            dataSet.setCircleHoleRadius( 3 );
                            dataSet.setValueTextSize( 10 );
                            //dataSet.enableDashedLine( 5,10,0 );
                            Legend legend;
                            legend = lineChart.getLegend();
                            legend.setEnabled( false );

                            LineData lineData = new LineData( datasets );
                            lineChart.setData( lineData );
                            lineChart.invalidate();


                            lineChart.animateX( 2000 );


                        }else{
                            Toast.makeText( HistoryShartActivity.this,"Any Data from server ! ",Toast.LENGTH_SHORT ).show();
                        }






                       /*
                       dataSet.setColor(Color.BLUE);
                        dataSet.setValueTextColor(Color.BLUE);
                       LineData lineData = new LineData(dataSet);
                        lineChart.setData(lineData);
                        lineChart.invalidate(); //

                        ArrayList<ILineDataSet> lineDataSets = new ArrayList<>();


                        LineDataSet lineDataSet1 = new LineDataSet(entries,"cos");
                        lineDataSet1.setDrawCircles(false);
                        lineDataSet1.setColor(Color.BLUE);

                        lineDataSets.add(lineDataSet1);

                        lineChart.setData(new LineData(lineDataSets));
                        lineChart.setVisibleXRangeMaximum(65f);*/

                    }
                } );



    }

    //Methode to get data from OnRespons an use it any where
    public void doRequest(final ApiCallback apiCallback) {

        dataapi = retrofit.create( RetrofitApi.class );
        Call<List<MyData>> call = dataapi.getData();

        call.enqueue( new Callback<List<MyData>>() {
            @Override
            public void onResponse(Call<List<MyData>> call, Response<List<MyData>> response) {

                if(response.body()!=null){

                    Mydata = response.body();

                    apiCallback.onSuccess( Mydata );

                }




            }

            @Override
            public void onFailure(Call<List<MyData>> call, Throwable t) {
                Log.e( "onFailure", "Verify" );
            }
        } );

    }

    //Intrface to get data from OnRespons
    public interface ApiCallback {
        void onSuccess(List<MyData> result);
    }


}
