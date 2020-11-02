package com.vadim.kalkkolory;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebviewFragment extends Fragment {

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_webview, container, false);
        webView = (WebView) v.findViewById(R.id.webview);

        Bundle bundle = getArguments();

        String url = bundle.get("url_link").toString();

        if(url!=null){
            webView.setWebViewClient(new WebViewClient());
            webView.loadUrl(url);
        }

        return v;
    }
}