package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This activity class is for services menu.
 * This class contains methods that displays services information received from json file,
 * on textViews when corresponding buttons are clicked.
 */
public class ServicesActivity extends AppCompatActivity {
    // Id of buttons that display service titles
    public static final int[] btnID = {
            R.id.btnServiceOption1,
            R.id.btnServiceOption2,
            R.id.btnServiceOption3,
            R.id.btnServiceOption4,
            R.id.btnServiceOption5,
            R.id.btnServiceOption6,
            R.id.btnServiceOption7,
            R.id.btnServiceOption8,
            R.id.btnServiceOption9,
            R.id.btnServiceOption10,
            R.id.btnServiceOption11,
            R.id.btnServiceOption12
    };
    // Id of textViews that display service descriptions
    public static final int[] txtViewID = {
            R.id.txtViewServiceOption1,
            R.id.txtViewServiceOption2,
            R.id.txtViewServiceOption3,
            R.id.txtViewServiceOption4,
            R.id.txtViewServiceOption5,
            R.id.txtViewServiceOption6,
            R.id.txtViewServiceOption7,
            R.id.txtViewServiceOption8,
            R.id.txtViewServiceOption9,
            R.id.txtViewServiceOption10,
            R.id.txtViewServiceOption11,
            R.id.txtViewServiceOption12
    };
    private static final String jsonFileName = "services.json";
    JsonManager servicesJsonManager;
    ArrayList<HashMap<String, String>> servicesList;
    List<Button> btn; // list of buttons
    List<TextView> txtView; // list of textViews

    /**
     * This method is called when the services menu is clicked. It initializes buttons and textViews, set text,
     * and display the description whenever the button is clicked.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        Intent intent = getIntent();

        servicesJsonManager = new JsonManager();
        servicesList = new ArrayList<>();
        btn = new ArrayList<>();
        txtView = new ArrayList<>();

        try {
            loadServicesInformation(jsonFileName);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        find_ButtonTextview();
        setText_ButtonTextview();
        onClickButton();
    }

    /**
     * This method takes in the json fileName, reads and loads services information (service option, link, and description)
     * from the file, into the servicesList.
     * @param fileName json fileName
     * @throws JSONException
     */
    public void loadServicesInformation(String fileName) throws JSONException {
        servicesJsonManager.readJsonFile(ServicesActivity.this, fileName);
        servicesJsonManager.createJsonArray(servicesJsonManager.getJsonString());
        ArrayList<HashMap<String, String>> services = new ArrayList<HashMap<String, String>>();
        JSONObject currentJsonObject;

        if(servicesJsonManager.getJsonArray() != null){
            for(int i = 0; i < servicesJsonManager.getJsonArray().length() ; i++){
                currentJsonObject = servicesJsonManager.getJsonArray().getJSONObject(i);
                HashMap<String, String> serviceContent = new HashMap<String, String>();
                serviceContent.put("link", currentJsonObject.getString("link"));
                serviceContent.put("option", currentJsonObject.getString("option"));
                serviceContent.put("description", currentJsonObject.getString("description"));
                services.add(serviceContent);
            }
            this.servicesList = services;
        }
    }

    /**
     * This method initializes all the buttons and textViews by their IDs.
     */
    public void find_ButtonTextview(){
        for(int id : btnID) {
            Button button = findViewById(id);
            btn.add(button);
        }

        for(int id2 : txtViewID){
            TextView textView = findViewById(id2);
            txtView.add(textView);
        }
    }

    /**
     * This method sets text of all the buttons (service title) and textViews (service description, link)
     * that are in the servicesList
     */
    public void setText_ButtonTextview(){
        for(int i = 0; i < this.btn.size() ; i++){
            String serviceDescription = servicesList.get(i).get("description") + servicesList.get(i).get("link");
            String serviceTitle = servicesList.get(i).get("option");
            btn.get(i).setText(serviceTitle);
            txtView.get(i).setText(serviceDescription);
        }
    }

    /**
     * This method manages button click events of all the buttons.
     */
    public void onClickButton() {
        for(int i = 0;i < btn.size(); i++){
            final int finalI = i;
            btn.get(i).setOnClickListener(new View.OnClickListener() {
                // If a button is clicked, corresponding textView will open
                // and if the button is clicked again, the textView will close
                @Override
                public void onClick(View v) {
                    txtView.get(finalI).setVisibility((txtView.get(finalI).getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //when home button (action_home) is pressed, the page is redirected to the homepage (MainActivity)
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_home:
                Intent homePageIntent= new Intent(this, MainActivity.class);
                this.startActivity(homePageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
