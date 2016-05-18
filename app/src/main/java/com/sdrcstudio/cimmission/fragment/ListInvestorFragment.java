package com.sdrcstudio.cimmission.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sdrcstudio.cimmission.R;
import com.sdrcstudio.cimmission.inc.CustomList;

/**
 * Created by ErfranRplB on 15/05/2016.
 */
public class ListInvestorFragment extends ListFragment {

    ListView list1;
    String[] title = {
            "Surat Izin Usaha Perdagangan",
            "Izin Mendirikan Bangunan",
            "Analisis Mengenai Dampak Lingkungan",
            "Surat Izin Usaha Perdagangan",
            "Izin Mendirikan Bangunan",
            "Analisis Mengenai Dampak Lingkungan",
            "Surat Izin Usaha Perdagangan",
            "Izin Mendirikan Bangunan",
            "Analisis Mengenai Dampak Lingkungan"
    };
    String[] sub = {
            "agit.docx",
            "be.docx",
            "mochi.docx",
            "agit.docx",
            "be.docx",
            "mochi.docx",
            "agit.docx",
            "be.docx",
            "mochi.docx"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_dokumen, container, false);

        list1 = (ListView) rootView.findViewById(android.R.id.list);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        CustomList adapter = new CustomList(getActivity(), title, sub);
        list1.setAdapter(adapter);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "You Clicked at " + title[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
