package com.haram.qhkarimeh;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity {
    private AdvancedWebView webView;
//    LinearLayout linearLayout;
    String url = "https://qhkarimeh.ir/";

//    IUpdateCheckService service;
//    UpdateServiceConnection connection;
//    private static final String TAG = "UpdateCheck";

    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initService();

        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
//        linearLayout = findViewById(R.id.pgdata);
        webView.loadUrl(url);
        websetting();
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
//        load();
    }
//    public void load(){
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                webView.setVisibility(View.GONE);
//                linearLayout.setVisibility(View.VISIBLE);
//                return true;
//            }
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                linearLayout.setVisibility(View.GONE);
//                webView.setVisibility(View.VISIBLE);
//            }
//        });
//    };
    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    public void websetting(){
        WebSettings webSetting = webView.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setPluginState(WebSettings.PluginState.ON);
        webSetting.setLoadsImagesAutomatically(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSetting.setSupportZoom(false);
        webSetting.setSavePassword(true);
        webSetting.setBlockNetworkImage(false);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setAppCacheEnabled(true);
        webSetting.setSupportZoom(false);
        webSetting.setAllowFileAccess(true);
        webSetting.setAllowContentAccess(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webView.setScrollbarFadingEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setScrollContainer(false);
        webView.addJavascriptInterface(this, "jsinterface");
        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);


    }
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed();
                return;
            }
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, R.string.backMsg, Toast.LENGTH_SHORT).show();
        }
    }



//    class UpdateServiceConnection implements ServiceConnection {
//        public void onServiceConnected(ComponentName name, IBinder boundService) {
//            service = (IUpdateCheckService) INotificationSideChannel.Stub
//                    .asInterface((IBinder) boundService);
//            try {
//                long vCode = service.getVersionCode("com.haram.qhkarimeh");
//                Toast.makeText(MainActivity.this, "Version Code:" + vCode,
//                        Toast.LENGTH_LONG).show();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Log.d(TAG, "onServiceConnected(): Connected");
//        }
//
//        public void onServiceDisconnected(ComponentName name) {
//            service = null;
//            Log.d(TAG, "onServiceDisconnected(): Disconnected");
//        }
//    }
//
//    private void initService() {
//        Log.i(TAG, "initService()");
//        connection = new UpdateServiceConnection();
//        Intent i = new Intent(
//                "com.farsitel.bazaar.service.UpdateCheckService.BIND");
//        i.setPackage("com.farsitel.bazaar");
//        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
//        Log.d(TAG, "initService() bound value: " + ret);
//    }
//
//    /** This is our function to un-binds this activity from our service. */
//    private void releaseService() {
//        unbindService(connection);
//        connection = null;
//        Log.d(TAG, "releaseService(): unbound.");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        releaseService();
//    }
}