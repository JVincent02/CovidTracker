package com.example.covidtracker.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.covidtracker.R;
import com.google.android.gms.maps.SupportMapFragment;

public class OverviewFragment extends Fragment implements View.OnClickListener {
    View historyCon;
    View symptomsCon;
    View preventionCon;
    View treatmentCon;
    View historyContent;
    View symptomsContent;
    View preventionContent;
    View treatmentContent;
    View historyIV;
    View symptomsIV;
    View preventionIV;
    View treatmentIV;
    public static OverviewFragment newInstance(){
        OverviewFragment fragment = new OverviewFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_overview, container, false);
        historyCon = root.findViewById(R.id.overviewHistoryCon);
        symptomsCon = root.findViewById(R.id.overviewSymptomsCon);
        preventionCon = root.findViewById(R.id.overviewPreventionCon);
        treatmentCon = root.findViewById(R.id.overviewTreatmentCon);
        historyCon.setOnClickListener(this);
        symptomsCon.setOnClickListener(this);
        preventionCon.setOnClickListener(this);
        treatmentCon.setOnClickListener(this);
        historyContent = root.findViewById(R.id.overviewHistoryContent);
        symptomsContent = root.findViewById(R.id.overviewSymptomsContent);
        preventionContent = root.findViewById(R.id.overviewPreventionContent);
        treatmentContent = root.findViewById(R.id.overviewTreatmentContent);
        historyIV = root.findViewById(R.id.historyIV);
        symptomsIV = root.findViewById(R.id.symptomsIV);
        preventionIV = root.findViewById(R.id.preventionIV);
        treatmentIV = root.findViewById(R.id.treatmentIV);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.overviewHistoryCon:
                if(historyContent.getVisibility()==View.GONE){
                    historyContent.setVisibility(View.VISIBLE);
                    historyIV.setRotation(90);
                }else{
                    historyContent.setVisibility(View.GONE);
                    historyIV.setRotation(0);
                }
                break;
            case R.id.overviewSymptomsCon:
                if(symptomsContent.getVisibility()==View.GONE){
                    symptomsContent.setVisibility(View.VISIBLE);
                    symptomsIV.setRotation(90);
                }else{
                    symptomsContent.setVisibility(View.GONE);
                    symptomsIV.setRotation(0);
                }
                break;
            case R.id.overviewPreventionCon:
                if(preventionContent.getVisibility()==View.GONE){
                    preventionContent.setVisibility(View.VISIBLE);
                    preventionIV.setRotation(90);
                }else{
                    preventionContent.setVisibility(View.GONE);
                    preventionIV.setRotation(0);
                }
                break;
            case R.id.overviewTreatmentCon:
                if(treatmentContent.getVisibility()==View.GONE){
                    treatmentContent.setVisibility(View.VISIBLE);
                    treatmentIV.setRotation(90);
                }else{
                    treatmentContent.setVisibility(View.GONE);
                    treatmentIV.setRotation(0);
                }
                break;
        }
    }
}
