package com.example.osvaldoairon.suasnotas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by osvaldoAiron on 12/11/2017.
 */

public class SigaaActivity extends AppCompatActivity{

    private WebView sigaa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigaa);

        sigaa = (WebView)findViewById(R.id.sigaaweb);
        WebSettings sw = sigaa.getSettings();
        sigaa.loadUrl("https://sigaa.ufpb.br/sigaa/");
        /*
        sigaa.setWebViewClient(new WebViewClient(){
        Isso aqui garante que continue sempre na mesma URL DO WEBVIEW (NAO É MUITO VIAVEL PARA ESSA APLICAÇÃO!
            @Override
            public boolean shouldOverrideUrlLoading(WebView view , String url){
                view.loadUrl(url);
                return false;

            }
            public void onBackPressed(){
                if(sigaa.canGoBack()){
                    sigaa.canGoBack();
                }
            }

        });
         */



    }

}
