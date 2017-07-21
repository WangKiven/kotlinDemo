package com.example.kiven.test302;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 *
 * Created by kiven on 2017/7/21.
 */

public class FLogin extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ah_login, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
