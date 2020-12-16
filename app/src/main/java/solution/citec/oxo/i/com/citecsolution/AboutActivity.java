package solution.citec.oxo.i.com.citecsolution;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;
import solution.citec.oxo.i.com.citecsolution.Tools.Tools;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_about );

        Tools.setSystemBarColor( this, R.color.blue_500 );

        WebView browser = (WebView) findViewById(R.id.webview);

        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.loadUrl("url_here");



    }



}
