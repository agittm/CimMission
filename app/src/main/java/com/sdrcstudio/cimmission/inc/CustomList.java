package com.sdrcstudio.cimmission.inc;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.sdrcstudio.cimmission.R;

/**
 * Created by ErfranRplB on 13/05/2016.
 */

public class CustomList extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] title;
    private final String[] sub;

    public CustomList(Activity context, String[] title, String[] sub) {
        super(context, R.layout.list_item, title);
        this.context = context;
        this.title = title;
        this.sub = sub;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txtTitle);
        TextView txtSub = (TextView) rowView.findViewById(R.id.txtSub);
        txtTitle.setText(title[position]);
        txtSub.setText(sub[position]);
        return rowView;
    }
}