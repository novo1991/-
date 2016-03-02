package com.czyapp.drex.find.drink;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czyapp.drex.R;

/**
 * Created by Pssskrl1991 on 2016/2/24.
 */
public class DrinkFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_find_drink,null);
    }
}
