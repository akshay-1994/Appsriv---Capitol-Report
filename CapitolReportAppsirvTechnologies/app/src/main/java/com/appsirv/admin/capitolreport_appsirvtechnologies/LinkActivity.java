package com.appsirv.admin.capitolreport_appsirvtechnologies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class LinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_link_view);
        WebView webView = findViewById(R.id.webView);

        Intent link = getIntent();
        String linkOpen = link.getStringExtra(Configurations.KEY_LINK);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(linkOpen);

        final ProgressDialog progressDialog = new ProgressDialog(LinkActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                progressDialog.dismiss();
            }
        }, 6000);


    }
}
