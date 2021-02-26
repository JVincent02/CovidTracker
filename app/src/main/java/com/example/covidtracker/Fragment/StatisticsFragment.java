package com.example.covidtracker.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covidtracker.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StatisticsFragment extends Fragment implements View.OnTouchListener {

    WebView webView;
    String TAG = "WEBVIEW";


    public static StatisticsFragment newInstance(){
        StatisticsFragment fragment = new StatisticsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        webView = root.findViewById(R.id.webView);
        //webView.loadUrl("https://www.gstatic.com/covid19/mobility/2021-02-05_PH_Mobility_Report_en.pdf");
        //String pdf = "https://www.gstatic.com/covid19/mobility/2021-02-05_PH_Mobility_Report_en.pdf";
        webView.setWebViewClient(new WebViewClient());
        webView.setOnTouchListener(this);
        WebSettings ws = webView.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setAllowFileAccess(true);
        enableHTML5(ws);
        webView.loadUrl("https://g.co/kgs/uVAko7");
        //webView.loadUrl("https://www.google.com/");
        //1700
        webView.scrollTo(0,1340);
        return root;
    }
    private void enableHTML5(WebSettings ws){
        try {
            Log.d(TAG, "Enabling HTML5-Features");
            Method m1 = WebSettings.class.getMethod("setDomStorageEnabled", new Class[]{Boolean.TYPE});
            m1.invoke(ws, Boolean.TRUE);

            Method m2 = WebSettings.class.getMethod("setDatabaseEnabled", new Class[]{Boolean.TYPE});
            m2.invoke(ws, Boolean.TRUE);

            Method m3 = WebSettings.class.getMethod("setDatabasePath", new Class[]{String.class});
            m3.invoke(ws, "/data/data/" + getActivity().getPackageName() + "/databases/");

            Method m4 = WebSettings.class.getMethod("setAppCacheMaxSize", new Class[]{Long.TYPE});
            m4.invoke(ws, 1024*1024*8);

            Method m5 = WebSettings.class.getMethod("setAppCachePath", new Class[]{String.class});
            m5.invoke(ws, "/data/data/" + getActivity().getPackageName() + "/cache/");

            Method m6 = WebSettings.class.getMethod("setAppCacheEnabled", new Class[]{Boolean.TYPE});
            m6.invoke(ws, Boolean.TRUE);

            Log.d(TAG, "Enabled HTML5-Features");
        }
        catch (NoSuchMethodException e) {
            Log.e(TAG, "Reflection fail", e);
        }
        catch (InvocationTargetException e) {
            Log.e(TAG, "Reflection fail", e);
        }
        catch (IllegalAccessException e) {
            Log.e(TAG, "Reflection fail", e);
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//            if(webView.getScrollY()!=1900){
//                webView.scrollTo(0,1900);
//            }
        //return false;
        return (event.getAction() == MotionEvent.ACTION_MOVE);
    }
}
