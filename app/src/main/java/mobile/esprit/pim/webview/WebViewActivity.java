package mobile.esprit.pim.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import mobile.esprit.pim.R;

public class WebViewActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = (WebView) findViewById(R.id.webView1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://youtu.be/GjI92HvFaG8");

    }
}