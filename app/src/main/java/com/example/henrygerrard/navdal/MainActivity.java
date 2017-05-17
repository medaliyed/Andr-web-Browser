package com.example.henrygerrard.navdal;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView brow;
    EditText urledit;
    Button go, forward,backward, clear, reld;
    ProgressBar pgsBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        pgsBar = (ProgressBar)findViewById(R.id.pgsbar);

        brow = (WebView)findViewById(R.id.wv_br);
        urledit = (EditText)findViewById(R.id.et_url);
        go = (Button)findViewById(R.id.bntgo);
        forward = (Button)findViewById(R.id.frd);
        backward = (Button)findViewById(R.id.bkw);
        clear = (Button)findViewById(R.id.clr);
        reld = (Button)findViewById(R.id.reload);


        brow.setWebViewClient(new ourviewclt());
        brow.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                pgsBar.setProgress(newProgress);
                if (newProgress == 100){
                    pgsBar.setVisibility(View.GONE);

                }else {
                    pgsBar.setVisibility(View.VISIBLE);

                }

            }
        });

        WebSettings webst = brow.getSettings();
        webst.setJavaScriptEnabled(true);
        brow.loadUrl("https://duckduckgo.com/");




        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edittextval = urledit.getText().toString();
                if(!edittextval.startsWith("http://"))
                    edittextval = "http://" + edittextval;


                String url = edittextval ;
                brow.loadUrl(url);
                InputMethodManager inm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inm.hideSoftInputFromInputMethod(urledit.getWindowToken(),0);




                forward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (brow.canGoForward())
                            brow.goForward();

                    }
                });

                backward.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (brow.canGoBack())
                            brow.goBack();

                    }
                });


                reld.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        brow.reload();

                    }
                });

                clear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        brow.clearHistory();

                    }
                });


            }
        });


    }
}
