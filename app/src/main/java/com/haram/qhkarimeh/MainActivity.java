package com.haram.qhkarimeh;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.Objects;

import im.delight.android.webview.AdvancedWebView;

public class MainActivity extends AppCompatActivity  //implements AdvancedWebView.Listener
         {
    private AdvancedWebView webView;
    LinearLayout linearLayout;
    String url = "https://qhkarimeh.ir/";
    boolean doubleBackToExitPressedOnce = false;
    boolean i = false;
    //    IUpdateCheckService service;
    //    UpdateServiceConnection connection;
    //    private static final String TAG = "UpdateCheck";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initService();
        setContentView(R.layout.activity_main);
        findID();
        websetting();
        webView.loadUrl(url);
        load();

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (!isNetworkConnected()) {
            Toast.makeText(this, "if!isnetwork", Toast.LENGTH_SHORT).show();
            bottomsheet();
        }
    }

    public void  findID(){
        webView = findViewById(R.id.webview);
        linearLayout = findViewById(R.id.pgData);
    }

    public void bottomsheet () {
        SplashScreenActivity.getInstance().haveNetwork();
        SplashScreenActivity.getInstance().Refresh();
    }


             public void load(){
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                if (!isNetworkConnected()){
                    showBottomSheetDialogFragment();
                }
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap facIcon) {
                linearLayout.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                linearLayout.setVisibility(View.GONE);
            }
        });
    };

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

    private boolean isNetworkConnected() {
        boolean i = false;
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo =
                Objects.requireNonNull(cm).getActiveNetworkInfo();
        if (activeNetworkInfo!=null && activeNetworkInfo.isConnected()){
            Toast.makeText(this, "if", Toast.LENGTH_SHORT).show();
            isOnline();
        }else {
            Toast.makeText(this, "else", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(() -> {
            CheckInternetUtil.Listener listener = null;
            if (i){
                listener.onReceived(true);
                Toast.makeText(this, "listener true", Toast.LENGTH_SHORT).show();
            }else {
                listener.onReceived(false);
                Toast.makeText(this, "listener false", Toast.LENGTH_SHORT).show();
            }
        },1500);
        Toast.makeText(this, "return i", Toast.LENGTH_SHORT).show();
        return i;
    }

    private boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
         Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
         int     exitValue = ipProcess.waitFor();
         i = true;
         return (exitValue == 0);
        }
        catch (IOException | InterruptedException e)          { e.printStackTrace(); }
        return false;
    }

    public void showBottomSheetDialogFragment() {
        BottomSheetFragment bottomSheetDialog = BottomSheetFragment.newInstance();
        bottomSheetDialog.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.setCancelable(false);
        bottomSheetFragment.setListener(() -> {
            bottomSheetFragment.dismiss();
            finish();
            startActivity(getIntent());
        });
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
//
//    @Override
//    public void onPageStarted(String url, Bitmap favicon) {
//
//    }
//
//    @Override
//    public void onPageFinished(String url) {
//
//    }
//
//    @Override
//    public void onPageError(int errorCode, String description, String failingUrl) {
//
//    }
//
//    @Override
//    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {
//
//    }
//
//    @Override
//    public void onExternalPageRequest(String url) {
//
//    }


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