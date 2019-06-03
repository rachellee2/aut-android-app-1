package com.example.autandroidapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class JsonManager {
    private String jsonString;
    private JSONArray jsonArray;

    public JsonManager() {
    }

    public JSONArray createJsonArray(String json) throws JSONException {
        this.jsonArray = new JSONArray(json);
        return jsonArray;
    }

    public String readJsonFile(Context mContext, String fileName){
        try{
            InputStream is = mContext.getAssets().open(fileName);
            int size = is.available();
            byte[] data = new byte[size];
            is.read(data);
            is.close();
            String json = new String(data, "UTF-8");
            this.jsonString = json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return jsonString;
    }

    public void setJsonString(String json){
        this.jsonString = json;
    }

    public String getJsonString(){
        return this.jsonString;
    }

    public void setJsonArray(JSONArray jsonArray){
        this.jsonArray = jsonArray;
    }

    public JSONArray getJsonArray(){
        return this.jsonArray;
    }
}
