package com.edudev.proyectomatriculas.ui.fragment;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.edudev.proyectomatriculas.R;

import java.util.ArrayList;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;


/**
 * Created by EDUARDO_RIVERA on 3/02/2017.
 */

public class PieChartFragment  extends Fragment{

    View view;
    WebView conte;
    FloatingActionButton flo;
    DownloadManager downloadManager;
    public PieChartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_pie_chart, container, false);
        conte=(WebView)view.findViewById(R.id.content);
        conte.getSettings().setJavaScriptEnabled(true);
        conte.getSettings().setLoadWithOverviewMode(true);
        conte.getSettings().setUseWideViewPort(true);
        conte.getSettings().setSupportZoom(true);
        conte.getSettings().setBuiltInZoomControls(true);

        //conte.setWebViewClient(new WebViewClient());
        conte.loadUrl("https://docs.google.com/viewer?url=http://www.uap.edu.pe/Esp/ProgramacionAcademica/Pregrado/03/syllabus/030203218.pdf");
        //conte.loadUrl("http://www.google.com.pe");

        flo=(FloatingActionButton)view.findViewById(R.id.desc);
        flo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadManager=(DownloadManager)getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri=Uri.parse("http://www.uap.edu.pe/Esp/ProgramacionAcademica/Pregrado/03/syllabus/030203218.pdf");
                DownloadManager.Request request=new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference=downloadManager.enqueue(request);
            }
        });
        return view ;
    }
}