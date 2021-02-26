package com.example.covidtracker.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.covidtracker.MainActivity;
import com.example.covidtracker.R;
import com.example.covidtracker.SplashActivity;
import com.example.covidtracker.data.UserModel;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    ImageView profileIV;
    TextView nameTxt;
    TextView emailTxt;
    TextView addressTxt;
    TextView birthdayTxt;
    TextView genderTxt;
    TextView symptomsTxt;
    UserModel userModel;
    View signoutCon;
    View symptomsCon;

    public static ProfileFragment newInstance(){
        ProfileFragment fragment = new ProfileFragment();
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
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        profileIV = root.findViewById(R.id.profileIV);
        nameTxt = root.findViewById(R.id.nameTxt);
        emailTxt = root.findViewById(R.id.emailTxt);
        birthdayTxt = root.findViewById(R.id.birthdayTxt);
        genderTxt = root.findViewById(R.id.genderTxt);
        signoutCon = root.findViewById(R.id.signOutCon);
        symptomsCon = root.findViewById(R.id.symptomsCon);
        addressTxt = root.findViewById(R.id.addressTxt);
        symptomsTxt = root.findViewById(R.id.symptomsTxt);
        signoutCon.setOnClickListener(this);
        symptomsCon.setOnClickListener(this);
        userModel = ((MainActivity)getActivity()).getUserModel();
        //Toast.makeText(getActivity(),mainActivity.getUserId(),Toast.LENGTH_LONG);
        Picasso.get()
                .load(userModel.getPicurl())
                .placeholder(R.drawable.profile_user)
                .into(profileIV);
        nameTxt.setText(userModel.getName());
        emailTxt.setText(userModel.getEmail());
        addressTxt.setText(userModel.getLocation());
        birthdayTxt.setText(userModel.getBirthday());
        genderTxt.setText(userModel.getGender());
        return root;
    }

    private void showRadioButtonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Select your symptoms");
        String[] symptoms = userModel.getSymptoms().toArray(new String[0]);
        builder.setMultiChoiceItems(symptoms, userModel.getUserSymptoms(), new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                userModel.setUserSymptom(which,isChecked);
            }
        });

// add OK and Cancel buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String body = "Symptoms:\n";
                String[] symptoms = userModel.getSymptoms().toArray(new String[0]);
                Boolean none=true;
                for (int i=0;i<userModel.getUserSymptoms().length;i++){
                    if (userModel.getUserSymptoms()[i]){
                        body+="    • "+symptoms[i]+"\n";
                        none=false;
                    }
                }
                if(none) body+="    • None";
                symptomsTxt.setText(body);
                ((MainActivity)getActivity()).hideSystemUI();
            }
        });
        builder.setNegativeButton("Cancel", null);

// create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signOutCon:
                LoginManager.getInstance().logOut();
                Intent intent = new Intent(getActivity().getBaseContext(), SplashActivity.class);
                getActivity().startActivity(intent);
                getActivity().finish();
                break;
            case R.id.symptomsCon:
                showRadioButtonDialog();
                break;
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).hideSystemUI();
    }
}
