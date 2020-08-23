package com.shashi.webviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.web_view);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl("https://shashiofficial.github.io/");

        //A link in website is opened not in the app, but in browesr, to avoid this, we have to use webview client
        //To do that we create our own public class, WebController

        webView.setWebViewClient(new WebColtroller());

    }

    public class WebColtroller extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            //Whenever we recieve a url, we simply show that in the current webview
            view.loadUrl(url);
            return true;
        }
    }
}