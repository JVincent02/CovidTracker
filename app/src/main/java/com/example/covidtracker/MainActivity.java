package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.covidtracker.Fragment.OverviewFragment;
import com.example.covidtracker.data.UserModel;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView overviewIV;
    ImageView mapsIV;
    ImageView helpIV;
    ImageView profileIV;
    ImageView statsIV;
    SectionsAdapter sectionsAdapter;
    ViewPager viewPager;
    UserModel userModel;
    //String userId;
    //String picUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sectionsAdapter = new SectionsAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.sectionVP);
        viewPager.setAdapter(sectionsAdapter);
        hideSystemUI();
        overviewIV = findViewById(R.id.overviewTab);
        overviewIV.setOnClickListener(this);
        mapsIV = findViewById(R.id.mapTab);
        mapsIV.setOnClickListener(this);
        helpIV = findViewById(R.id.helpTab);
        helpIV.setOnClickListener(this);
        profileIV = findViewById(R.id.profileTab);
        profileIV.setOnClickListener(this);
        statsIV = findViewById(R.id.statTab);
        statsIV.setOnClickListener(this);
        userModel = new UserModel(getIntent().getStringExtra("usedId"));
        //Log.e("MainActivity", userId);
        viewPager.setCurrentItem(2);
        sectionsAdapter.notifyDataSetChanged();
        getProfilePic();
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void getProfilePic() {
        Bundle params = new Bundle();
        params.putString("fields", "id,name,location,birthday,email,gender,picture.width(600).height(600)");
        new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        if (response != null) {
                            try {
                                JSONObject data = response.getJSONObject();
                                if (data.has("picture")) {
                                    String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                    userModel.setPicUrl(profilePicUrl);
                                    userModel.setName(data.getString("name"));
                                    userModel.setLocation(data.getJSONObject("location").getString("name"));
                                    userModel.setEmail(data.getString("email"));
                                    userModel.setGender(data.getString("gender"));
                                    userModel.setBirthday(data.getString("birthday"));
                                    //Log.e("Url",data.getString("birthday"));
                                    //Log.e("loc",data.getString("location"));
                                    //Bitmap profilePic= BitmapFactory.decodeStream(profilePicUrl .openConnection().getInputStream());
                                    //mImageView.setBitmap(profilePic);
                                }
                            } catch (Exception e) {
                                Log.e("error",e.getMessage());
                            }
                        }
                    }
                }).executeAsync();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.helpTab:
                viewPager.setCurrentItem(0);
                sectionsAdapter.notifyDataSetChanged();
                break;
            case R.id.overviewTab:
                viewPager.setCurrentItem(1);
                sectionsAdapter.notifyDataSetChanged();
                break;
            case R.id.mapTab:
                viewPager.setCurrentItem(2);
                sectionsAdapter.notifyDataSetChanged();
                break;
            case R.id.statTab:
                viewPager.setCurrentItem(3);
                sectionsAdapter.notifyDataSetChanged();
                break;
            case R.id.profileTab:
                viewPager.setCurrentItem(4);
                sectionsAdapter.notifyDataSetChanged();
                break;
        }
    }
}