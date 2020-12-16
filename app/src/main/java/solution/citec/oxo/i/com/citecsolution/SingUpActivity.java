package solution.citec.oxo.i.com.citecsolution;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import solution.citec.oxo.i.com.citecsolution.ApiRest.RetrofitApi;
import solution.citec.oxo.i.com.citecsolution.Models.Respons;
import solution.citec.oxo.i.com.citecsolution.helper.SessionManager;

public class SingUpActivity extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private EditText RePassword;
    private EditText ReferencDevice;
    private TextView ExistAccount;
    private Button BtnRegister;
    private ImageView Close;

    private ProgressDialog pDialog;
    private SessionManager session;

    private Retrofit retrofit;
    private RetrofitApi dataapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sing_up );

        Email = (EditText)findViewById( R.id.edt_email_register );
        Password = (EditText)findViewById( R.id.edt_passord_register );
        RePassword = (EditText)findViewById( R.id.edt_repassord_register );
        ReferencDevice = (EditText)findViewById( R.id.edt_reference );
        ExistAccount= (TextView) findViewById( R.id.tv_have_account );
        BtnRegister= (Button) findViewById( R.id.btn_register );
        Close= (ImageView) findViewById( R.id.bt_retour_register );

        ExistAccount.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( SingUpActivity.this,LoginActivity.class ) );
                SingUpActivity.this.finish();
            }
        } );

        Close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SingUpActivity.this.finish();


            }
        } );

        retrofit = new Retrofit.Builder()
                .baseUrl( "url_here" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(SingUpActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        BtnRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = Email.getText().toString().trim();
                String ReferenceDevice = ReferencDevice.getText().toString().trim();
                String password = Password.getText().toString().trim();
                String rpassword = RePassword.getText().toString().trim();




                if (!email.isEmpty() && !ReferenceDevice.isEmpty() && !password.isEmpty()&&passwordIsEqual(password,rpassword)) {
                    registerUser(ReferenceDevice,email, password);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }



            }
        } );


    }

    private void registerUser(String referenceDevice, String email, String password) {

        pDialog.setMessage("Registering ...");
        showDialog();

        dataapi = retrofit.create( RetrofitApi.class );
        Call<Respons> call = dataapi.Register( referenceDevice,email,password );

        call.enqueue( new Callback<Respons>() {

            @Override
            public void onResponse(Call<Respons> call, Response<Respons> response) {
                hideDialog();


                if(response.body().getError()==false){

                    session.setLogin(true);

                    startActivity(new Intent(SingUpActivity.this, MainActivity.class));


                    finish();


                }
                else{
                    Toast.makeText(SingUpActivity.this, "" + response.body().getErrorMsg(), Toast.LENGTH_SHORT).show();
                }




            }

            @Override
            public void onFailure(Call<Respons> call, Throwable t) {

            }
        } );



    }










    public boolean passwordIsEqual(String password, String Repassword) {
        if (password.equals( Repassword )) {return true;}
        else {return false;}
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
