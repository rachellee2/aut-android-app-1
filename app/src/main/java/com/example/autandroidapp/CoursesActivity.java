package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.AdapterView.*;

/**
 * This is the main class for the courses menu.
 * This class contains methods that manage the actions for 2 spinners that each displays list of departments
 * and degrees as well as the button that will direct the user to a new page that displays papers.
 * It also manages data pulled from json files to be send to CoursesPapersActivity.
 */
public class CoursesActivity extends AppCompatActivity {
    Spinner spinnerDepartment, spinnerDegree;
    ArrayAdapter<String> adapterDepartment, adapterDegree;
    ArrayList<String> departmentNames, degreeNames; // list of departments and degrees that will be displayed in spinners
    ArrayList<HashMap<String, String>> papersInDepartment, papersInDegree; // list of paper information offered in chosen department/degree
    String jsonFileName, selectedDepartment, selectedDegree;
    public final String dpmtPrompt = "Select department";
    public final String dgrPrompt = "Select degree";
    Button btnPaperSearch;
    JsonManager courseJsonManager = new JsonManager();

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

    /**
     * This method is called when the user access the courses menu.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        Intent intent = getIntent();

        papersInDegree = new ArrayList<HashMap<String, String>>();
        papersInDepartment = new ArrayList<HashMap<String, String>>();
        btnPaperSearch = (Button) findViewById(R.id.btnPaperSearch);
        // Set department spinner
        setSpinnerDepartment();
        spinnerDepartment.setSelection(0);
        setAdapterDepartment();
        // Set degree spinner
        setSpinnerDegree();
        spinnerDegree.setSelection(0);
        // Calls listener when an item is selected for each spinner
        spinnerDepartment.setOnItemSelectedListener(dpmtListener);
        spinnerDegree.setOnItemSelectedListener(dgrListener);
    }

    /**
     * This method manages actions to be taken, based on the selected item for spinnerDepartment.
     */
    OnItemSelectedListener dpmtListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Gets string for the selected item and store it in String text
            String text = parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

            selectedDepartment = text;
            switch (selectedDepartment){
                // Load papers for science department
                case "Science":
                    jsonFileName = "science.json";
                    selectedDepartment = "Science";
                    try {
                        loadDegree(jsonFileName); // load degree into degreeList
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                // Load papers for engineering department
                case "Engineering, computer and mathematical sciences":
                    jsonFileName = "engineering_computer_and_mathematical_sciences.json";
                    selectedDepartment = "Engineering, computer and mathematical sciences";
                    try {
                        loadDegree(jsonFileName); // load degree into degreeList
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    break;
                // If prompt item is selected, disable spinnerDegrees and empty out papers list.
                case dpmtPrompt:
                    jsonFileName = null;
                    selectedDepartment = null;
                    papersInDepartment = null;
                    spinnerDegree.setEnabled(false);
                    setSpinnerDegree();
                    break;
            }
            // If either science or engineering department is selected, get all the papers for that specific department and enable spinnerDegree.
            if(selectedDepartment!=null) {
                degreeNames = getDegrees();
                spinnerDegree.setEnabled(true);
            }
            btnPaperSearch.setEnabled(false);
            setAdapterDegree();
        }
        // If nothing is selected set selections for both spinners to prompt.
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            spinnerDepartment.setSelection(0);
            spinnerDegree.setSelection(0);
        }
    };

    /**
     * This method manages actions to be taken, based on the selected item for spinnerDegree.
     */
    OnItemSelectedListener dgrListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Gets string for the selected item and store it in String text
            String text = parent.getItemAtPosition(position).toString();
            if(selectedDepartment==null){
                Toast.makeText(parent.getContext(), dpmtPrompt, Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
            }
            // If prompt item is selected, disable the view button.
            if(text.equalsIgnoreCase(dgrPrompt)){
                selectedDegree = null;
                btnPaperSearch.setEnabled(false);
            }
            // Only enable view button, when selected item is not prompt.
            else{
                selectedDegree = text;
                btnPaperSearch.setEnabled(true);
            }
        }
        // If nothing is selected set selection for spinnerDegree to prompt.
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            spinnerDegree.setSelection(0);
        }
    };

    /**
     * This method sets spinnerDegree to initial setting where nothing is selected.
     */
    protected void setSpinnerDegree(){
        spinnerDegree = findViewById(R.id.spinnerDegrees);
        selectedDegree = dgrPrompt;
        degreeNames = new ArrayList<>();
        degreeNames.add(selectedDegree);
    }

    /**
     * This method sets spinnerDepartment to initial setting where nothing is selected.
     */
    protected void setSpinnerDepartment(){
        spinnerDepartment = findViewById(R.id.spinnerCourses);
        selectedDepartment = dpmtPrompt;
        departmentNames = new ArrayList<>();
        departmentNames.add(selectedDepartment);
        departmentNames.add("Science");
        departmentNames.add("Engineering, computer and mathematical sciences");
    }

    /**
     * This method sets adapter spinnerDegree
     */
    protected void setAdapterDegree(){
        adapterDegree = new ArrayAdapter<String>(this, R.layout.spinner_layout, degreeNames);
        adapterDegree.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinnerDegree.setAdapter(adapterDegree);
    }

    /**
     * This method sets adapter spinnerDepartment
     */
    protected void setAdapterDepartment(){
        adapterDepartment = new ArrayAdapter<String>(this, R.layout.spinner_layout,departmentNames);
        adapterDepartment.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        spinnerDepartment.setAdapter(adapterDepartment);
    }

    /**
     * This method is called when the view paper button is pressed. It sends intent with the loaded paper information the user wants to view,
     * to the CoursePapersActivity.
     * @param view view with a page that displays chosen degree's paper information
     */
    public void coursePaperActivity(View view){
        papersInDegree = null;
        papersInDegree = loadPaperInformation(selectedDegree);
        Intent intent = new Intent(CoursesActivity.this, CoursePapersActivity.class);
        intent.putExtra("mapList", papersInDegree);
        startActivity(intent);
    }

    /**
     * This method takes in json fileName based on the selected department, pulls data of papers offered in that department from the file
     * and load them into paperInDepartment.
     * @param fileName selected department's json file name
     */
    public void loadDegree(String fileName) throws JSONException {
        courseJsonManager.readJsonFile(CoursesActivity.this, fileName);
        courseJsonManager.createJsonArray(courseJsonManager.getJsonString());
        ArrayList<HashMap<String, String>> degrees = new ArrayList<HashMap<String, String>>();
        JSONObject currentJsonObject;
        if(courseJsonManager.getJsonArray() != null){
            for(int i = 0; i < courseJsonManager.getJsonArray().length() ; i++){
                currentJsonObject = courseJsonManager.getJsonArray().getJSONObject(i);
                HashMap<String, String> paperInformation = new HashMap<String, String>();
                paperInformation.put("qualification_name", currentJsonObject.getString("qualification_name"));
                paperInformation.put("paper_name", currentJsonObject.getString("paper_name"));
                paperInformation.put("paper_code", currentJsonObject.getString("paper_code"));
                paperInformation.put("efts", currentJsonObject.getString("efts"));
                paperInformation.put("points", currentJsonObject.getString("points"));
                paperInformation.put("level", currentJsonObject.getString("level"));
                paperInformation.put("prescriptor", currentJsonObject.getString("prescriptor"));
                degrees.add(paperInformation);
            }
            this.papersInDepartment = degrees;
        }
    }

    /**
     * This method pulls list of degree names that are offered in selected department in papersInDepartment
     * and return the list.
     * @return list of degrees in selected department
     */
    public ArrayList<String> getDegrees(){
        ArrayList<String> list = new ArrayList<>();
        list.add(dgrPrompt);
        if(this.papersInDepartment !=null){
            for (int j = 0; j < this.papersInDepartment.size(); j++) {
                HashMap<String, String> currentDegree = this.papersInDepartment.get(j);
                if (!list.contains(currentDegree.get("qualification_name"))) {
                    list.add(currentDegree.get("qualification_name"));
                }
            }
        }
        return list;
    }

    /**
     * This method takes in the name of selected degree, and load paper information for that degree from papersInDepartment
     * which stores all the papers in a department.
     * @param degreeName name of a selected degree
     * @return list of papers for selected degree
     */
    public ArrayList<HashMap<String, String>> loadPaperInformation(String degreeName){
        ArrayList<HashMap<String, String>> papers = new ArrayList<>();
        if(this.papersInDepartment !=null){
            for(int i = 0; i < this.papersInDepartment.size(); i++){
                HashMap<String, String> currentPaper = this.papersInDepartment.get(i);
                if(currentPaper.get("qualification_name").equalsIgnoreCase(degreeName)){
                    papers.add(currentPaper);
                    papers.remove("qualification_name");
                }
            }
        }
        return papers;
    }
}
