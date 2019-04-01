package com.smartify.htasirans;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class InsuranceAdapter extends ArrayAdapter {


    public InsuranceAdapter(Context context, ArrayList<Insurance> insurance) {
        super(context, android.R.layout.simple_list_item_1, insurance);
    }


}
