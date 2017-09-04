package com.edudev.proyectomatriculas.ui.fragment;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    public PieChartFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_pie_chart, container, false);
        conte=(WebView)view.findViewById(R.id.content);
        conte.setWebViewClient(new WebViewClient());
        conte.loadUrl("http://www.uap.edu.pe/Esp/ProgramacionAcademica/Pregrado/03/syllabus/030203218.pdf");
        //conte.loadUrl("http://www.google.com.pe");
        return view ;
    }
}