package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * This activity class is for the map menu.
 * It gets the google map ready and set the markers as well as initial camera position for the users to view.
 */
public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    // location displayed when the menu is opened
    public static final LatLng latLngStartLocation = new LatLng(-36.8976878, 174.8212133);
    public static final LatLng latLngCityCampus = new LatLng(-36.8533104, 174.7666552);
    public static final LatLng latLngSouthCampus = new LatLng(-36.9853711, 174.8819945);
    public static final LatLng latLngNorthCampus = new LatLng(-36.7979382, 174.7582189);

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_home:
                Intent homePageIntent = new Intent(this, MainActivity.class);
                this.startActivity(homePageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // get the map fragment ready for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFrag);
        mapFragment.getMapAsync(this);
    }

    /**
     * This method takes in the googleMap and initialize as mMap. It sets the locations and add markers to all three campuses
     * It also sets the camera position to the start location and zoom to optimal level where
     * the users can view all three campus markers.
     * @param googleMap
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(latLngCityCampus).title("City Campus"));
        mMap.addMarker(new MarkerOptions().position(latLngSouthCampus).title("South Campus"));
        mMap.addMarker(new MarkerOptions().position(latLngNorthCampus).title("North Campus"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLngStartLocation));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10.5f));
    }

    // getter for mMap
    public GoogleMap getMap(){
        return this.mMap;
    }
}
