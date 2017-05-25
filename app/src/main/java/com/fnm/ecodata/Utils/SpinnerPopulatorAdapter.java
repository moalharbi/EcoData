package com.fnm.ecodata.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.fnm.ecodata.R;

/**
 * Created by Muhammad on 19/11/2015.
 */
public class SpinnerPopulatorAdapter extends ArrayAdapter<String> {
    LayoutInflater inflater;
    String[] mItems = null;

    public SpinnerPopulatorAdapter(Context context, String[] _values) {
        super(context, R.layout.adapter_spinner_item, _values);
        mItems = _values;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int _position, View convertView, ViewGroup parent) {
        return getCustomViewSelected(_position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomViewSelected(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.adapter_spinner_item, parent, false);

        TextView name = (TextView) convertView.findViewById(R.id.itemlabel);
        name.setText(mItems[position]);
        if (position == 0) {
            name.setTextColor(((EditText) convertView.findViewById(R.id.dummyedittext)).getCurrentHintTextColor());
        }

        return convertView;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.adapter_spinner_item, parent, false);

        TextView name = (TextView) convertView.findViewById(R.id.itemlabel);
        name.setText(mItems[position]);
        if (position == 0) {
            name.setTextColor(Color.GRAY);
        }

        return convertView;
    }
}
