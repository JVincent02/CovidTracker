package com.example.covidtracker.data;

import com.google.android.gms.common.util.Strings;

import java.util.ArrayList;
import java.util.List;

public class UserModel{
    String userId;
    String name;
    String picurl;
    String location;
    String email;
    String birthday;
    String gender;
    boolean[] symptoms;

    public boolean[] getUserSymptoms(){
        return symptoms;
    }
    public void setUserSymptom(int which,boolean isChecked){
        symptoms[which]=isChecked;
    }
    public List<String> getSymptoms(){
        List<String> symptoms = new ArrayList<>();
        symptoms.add("Fever");
        symptoms.add("Dry Cough");
        symptoms.add("Tiredness");
        symptoms.add("Aches");
        symptoms.add("Sore Throat");
        symptoms.add("Diarrhea");
        symptoms.add("Headache");
        symptoms.add("Lost of taste and smell");
        symptoms.add("Difficulty in breathing");
        symptoms.add("Chest Pain");
        symptoms.add("Loss of speech");
        return symptoms;
    }

    public UserModel(String id){
        userId=id;
        symptoms = new boolean[getSymptoms().size()];
        for(int i=0;i<getSymptoms().size();i++){
            symptoms[i]=false;
        }
    }
    public String getName(){
        return name;
    }
    public String getPicurl(){
        return picurl;
    }
    public String getLocation(){return location;}
    public String getEmail(){return email;}
    public String getBirthday(){return birthday;}
    public String getGender(){return gender;}

    public void setName(String n){
        name=n;
    }
    public void setPicUrl(String u){
        picurl=u;
    }
    public void setLocation(String l){ location=l; }
    public void setEmail(String e){ email=e; }
    public void setBirthday(String b){ birthday=b; }
    public void setGender(String g){ gender=g; }
}
