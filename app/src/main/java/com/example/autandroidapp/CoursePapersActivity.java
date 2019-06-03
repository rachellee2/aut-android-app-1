package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.autandroidapp.R.layout.row_textview;

/**
 * This activity class for the page that displays papers' information of user's chosen department and degree.
 * This class contains methods that displays the papers data received from CoursesActivity and manages the search bar action.
 */
public class CoursePapersActivity extends AppCompatActivity {
    ArrayList<HashMap<String, String>> papersList;
    ListView listView;
    SimpleAdapter listAdapter;
    // All the keys in the papersList
    public final String[] from = new String[] {"paper_name", "paper_code", "efts", "points", "level", "prescriptor"};
    // TextViews that corresponding to the keys that are displayed in one listView
    public final int[] to = new int[]{R.id.listText1, R.id.listText2, R.id.listText3, R.id.listText4, R.id.listText5, R.id.listText6};

    /**
     * This onCreate method displays all the paper information of selected degree from the CoursesActivity.
     * It creates an new ArrayList of HashMap, pass on the received paper information into it, and displays using a SimpleAdaper.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_paper);
        Intent intent = getIntent();
        // Pass the course paper information received from the CoursesActivity to papersList
        papersList = (ArrayList<HashMap<String, String>>)intent.getSerializableExtra("mapList");
        // Pass the papersList data into the listAdapter
        listAdapter = new SimpleAdapter(this, papersList, row_textview, from, to);
        listView = (ListView) findViewById(R.id.papersListView);
        //set listAdapter to the listView
        listView.setAdapter(listAdapter);
    }

    /**
     * This method manages actionbar menu as well as search bar. If the search icon is pressed, this receives the input text and
     * updates the listView adapter to filter the papers so that it will only displays papers that contains the input text.
     * @param menu
     * @return menu
     */
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        menu.findItem(R.id.app_bar_search).setVisible(true);

        MenuItem searchMenu = menu.findItem(R.id.app_bar_search);
        // create an actionView class for this searchMenu
        SearchView searchView = (SearchView) searchMenu.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            // If any papers contain the input text, they will be added in the new adapter to be displayed.
            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<HashMap<String, String>> results = new ArrayList<HashMap<String, String>>();
                for(HashMap<String, String> map : papersList){
                    for(Map.Entry<String, String> entry : map.entrySet()){
                        String valueLrCase = entry.getValue().toLowerCase();
                        if(valueLrCase.contains(newText.toLowerCase().trim())&&!results.contains(map)){
                            results.add(map);
                        }
                    }
                }
                // New adapter with updated paper list will be set to the listView.
                listAdapter = new SimpleAdapter(CoursePapersActivity.this, results, R.layout.row_textview, from, to);
                listView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();

                if(results.isEmpty()){
                    Toast toast = Toast.makeText(CoursePapersActivity.this, "No Result Found", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                return false;
            }
        });
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
