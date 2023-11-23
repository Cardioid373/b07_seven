package com.example.demoapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class admission_check extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admission_check, container, false);

        String questionText = getArguments().getString("questionText", "");

        TextView csAdmissionCheck = view.findViewById(R.id.csAdmissionCheck);
        csAdmissionCheck.setText(questionText);

        return view;
    }

}