package solution.citec.oxo.i.com.citecsolution;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import solution.citec.oxo.i.com.citecsolution.ApiRest.RetrofitApi;
import solution.citec.oxo.i.com.citecsolution.Models.etatCitec;
import solution.citec.oxo.i.com.citecsolution.Tools.DrawerUtil;
import solution.citec.oxo.i.com.citecsolution.Tools.Tools;

public class MainActivity extends AppCompatActivity implements DrawerUtil.clickOpenDrawer {

    static List<etatCitec> EtatCitec;
    AnimationDrawable animationDrawable;
    TextView tvPourcenntage, Consommation, Estimation;
    ImageView reservoire;
    ProgressBar progressBar;
    int pourcentage;
    //Drawer Menu
    private Drawer result = null;
    private Retrofit retrofit;
    private RetrofitApi dataapi;
    private ImageView refresh;
    ImageView imageView;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        Tools.setSystemBarColor( this, R.color.blue_500 );


        ImageView iconMenuDrawer = (ImageView) findViewById( R.id.menu_drawer );
        reservoire = (ImageView) findViewById( R.id.imageView );

        refresh = (ImageView) findViewById( R.id.imageView2 );
        tvPourcenntage = (TextView) findViewById( R.id.tv_porcountage );
        Consommation = (TextView) findViewById( R.id.tv_consommationh );
        Estimation = (TextView) findViewById( R.id.tv_estimationh );

        progressBar = (ProgressBar) findViewById( R.id.progressBar );

        retrofit = new Retrofit.Builder()
                .baseUrl( "url_here" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        imageView= (ImageView) findViewById( R.id.imageView5 );
        imageView.setBackgroundResource( R.drawable.animation );
        animationDrawable = (AnimationDrawable) imageView.getBackground();


        imageView.setVisibility( View.GONE );

        //Creer le Drawer
        result = DrawerUtil.getDrawer( this );

        progressBar.setVisibility( View.VISIBLE );
        refresh.setVisibility( View.GONE );

        doRequest( new ApiCallback() {
            @Override
            public void onSuccess(List<etatCitec> result) {

                pourcentage = Integer.parseInt( result.get( 0 ).getPourcentage() );
                int remplire = Integer.parseInt( result.get( 0 ).getRemplire() );

                tvPourcenntage.setText( result.get( 0 ).getPourcentage() + " %" );
                Consommation.setText( result.get( 0 ).getConsommation() + " L/Day" );
                Estimation.setText( result.get( 0 ).getEstimation() + " Days" );

                progressBar.setVisibility( View.GONE );
                refresh.setVisibility( View.VISIBLE );


                if(remplire==0){
                    animationDrawable.stop();
                    imageView.setVisibility( View.GONE );

                } if(remplire==1){
                    imageView.setVisibility( View.VISIBLE );
                    animationDrawable.start();

                }

                if (pourcentage > 0 && pourcentage <= 25) {

                    reservoire.setImageResource( R.mipmap.one );
                    dialog  = new Dialog(MainActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.setCancelable( false );
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                    lp.copyFrom(dialog.getWindow().getAttributes());
                    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                    lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    lp.gravity = Gravity.CENTER;
                    dialog.getWindow().setAttributes(lp);

                    TextView TvSannuler = (TextView)dialog.findViewById( R.id.dg_annuler ) ;

                    dialog.show();

                    TvSannuler.setOnClickListener( new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();

                        }
                    } );

                }


                if (pourcentage > 25 && pourcentage <= 35) {

                    reservoire.setImageResource( R.mipmap.tow );


                }
                if (pourcentage > 35 && pourcentage <= 55) {

                    reservoire.setImageResource( R.mipmap.three );
                }
                if (pourcentage > 55 && pourcentage <= 75) {

                    reservoire.setImageResource( R.mipmap.four );
                }
                if (pourcentage > 75 && pourcentage <= 85) {

                    reservoire.setImageResource( R.mipmap.five );
                }
                if (pourcentage > 85 && pourcentage <= 100) {
                    reservoire.setImageResource( R.mipmap.six );

                }

            }
        } );

        iconMenuDrawer.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pour ovrire le drawer lign 206-215 j'ai implemente une interface depuis DrawerUtile
                onClicks();

            }
        } );

        refresh.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                refresh.setVisibility( View.GONE );
                progressBar.setVisibility( View.VISIBLE );



                doRequest( new ApiCallback() {
                    @Override
                    public void onSuccess(List<etatCitec> result) {

                        pourcentage = Integer.parseInt( result.get( 0 ).getPourcentage() );
                        int rempliree = Integer.parseInt( result.get( 0 ).getRemplire() );

                        tvPourcenntage.setText( result.get( 0 ).getPourcentage() + " %" );
                        Consommation.setText( result.get( 0 ).getConsommation() + " L/Day" );
                        Estimation.setText( result.get( 0 ).getEstimation() + " Days" );
                        progressBar.setVisibility( View.GONE );
                        refresh.setVisibility( View.VISIBLE );

                        if (pourcentage > 0 && pourcentage <= 25) {

                            reservoire.setImageResource( R.mipmap.one );

                            dialog  = new Dialog(MainActivity.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.custom_dialog);
                            dialog.setCancelable( false );
                            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                            lp.copyFrom(dialog.getWindow().getAttributes());
                            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                            lp.gravity = Gravity.CENTER;
                            dialog.getWindow().setAttributes(lp);

                            TextView TvSannuler = (TextView)dialog.findViewById( R.id.dg_annuler ) ;

                            dialog.show();

                            TvSannuler.setOnClickListener( new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();

                                }
                            } );

                        }
                        if (pourcentage > 25 && pourcentage <= 35) {

                            reservoire.setImageResource( R.mipmap.tow );


                        }
                        if (pourcentage > 35 && pourcentage <= 55) {

                            reservoire.setImageResource( R.mipmap.three );
                        }
                        if (pourcentage > 55 && pourcentage <= 75) {

                            reservoire.setImageResource( R.mipmap.four );
                        }
                        if (pourcentage > 75 && pourcentage <= 85) {

                            reservoire.setImageResource( R.mipmap.five );
                        }
                        if (pourcentage > 85 && pourcentage <= 100) {
                            reservoire.setImageResource( R.mipmap.six );

                        }
                        if(rempliree==0){
                            imageView.setVisibility( View.GONE );
                            animationDrawable.stop();


                        } if(rempliree==1){
                            imageView.setVisibility( View.VISIBLE );
                            animationDrawable.start();

                        }

                    }
                } );


            }
        } );




      /*  new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {
                animationDrawable.stop();

            }
        }, 530);*/
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged( hasFocus );
        //animationDrawable.start();
//        animationDrawable.setAlpha( 100 );

    }

    // Interface pour ouvrire Drawer Location in DrawerUtil
    @Override
    public void onClicks() {


        if (result != null && !result.isDrawerOpen()) {
            result.openDrawer();
        }
    }

    //Methode to get data from OnRespons an use it any where
    public void doRequest(final ApiCallback apiCallback) {

        dataapi = retrofit.create( RetrofitApi.class );
        Call<List<etatCitec>> call = dataapi.getEtat();

        call.enqueue( new Callback<List<etatCitec>>() {
            @Override
            public void onResponse(Call<List<etatCitec>> call, Response<List<etatCitec>> response) {
                EtatCitec = response.body();
                apiCallback.onSuccess( EtatCitec );
            }

            @Override
            public void onFailure(Call<List<etatCitec>> call, Throwable t) {
Log.e("EEEEEEEEE","Error");
            }
        } );


    }

    //Intrface to get data from OnRespons
    public interface ApiCallback {
        void onSuccess(List<etatCitec> result);
    }

}

