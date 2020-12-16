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

public class LoginActivity extends AppCompatActivity {


    private EditText Email;
    private EditText Password;
    private TextView ForgotPassword;
    private TextView NotYetAccount;
    private Button BtnLogin;
    private ImageView Close;

    private ProgressDialog pDialog;
    private SessionManager session;

    private Retrofit retrofit;
    private RetrofitApi dataapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

         Email = (EditText)findViewById( R.id.edt_repassord_register );
         Password = (EditText)findViewById( R.id.edt_email_register );
         ForgotPassword= (TextView) findViewById( R.id.tv_forgot_password );
         NotYetAccount= (TextView) findViewById( R.id.tv_not_yet_account );
         BtnLogin= (Button) findViewById( R.id.btn_register );
         Close= (ImageView) findViewById( R.id.bt_retour_login );

        retrofit = new Retrofit.Builder()
                .baseUrl( "url_here" )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();


        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

         NotYetAccount.setOnClickListener( new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 startActivity( new Intent( LoginActivity.this,SingUpActivity.class ) );
                 LoginActivity.this.finish();
             }
         } );

        Close.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();

            }
        } );

        BtnLogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                String password = Password.getText().toString().trim();

                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        } );




    }

    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String email, final String password) {

        pDialog.setMessage("Logging in ...");
        showDialog();

        dataapi = retrofit.create( RetrofitApi.class );
        Call<Respons> call = dataapi.CheckLogin( email,password );

        call.enqueue( new Callback<Respons>() {
            @Override
            public void onResponse(Call<Respons> call, Response<Respons> response) {

                hideDialog();

                if(response.body().getError()==false){

                    session.setLogin(true);

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));


                    finish();

                }else{
                    Toast.makeText(LoginActivity.this, "" + response.body().getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Respons> call, Throwable t) {

            }
        } );



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
