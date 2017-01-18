package com.edudev.proyectomatriculas.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.edudev.proyectomatriculas.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MatriculaFragment extends Fragment {

    View view;
    TableLayout tableLayout;
    public MatriculaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_matricula, container, false);

        tableLayout = (TableLayout) view.findViewById(R.id.tableLayout);

        TableRow cabecera = (TableRow) LayoutInflater.from(getContext()).inflate(R.layout.encabezado_cursos, null);
        tableLayout.addView(cabecera);
        tableLayout.requestLayout();
        return view;
    }

}
