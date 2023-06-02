package com.example.sharp_salon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.util.ArrayList;


public class Tracking extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle("Map");
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.clr));


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

       /* mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

            }
        }); */
//we can use this short line of code because we used onMapReadyCallback interface in main class
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

    }

    @Override



    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(31.34668134467634, 75.55927451979674);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("Sharp Unisex saloon");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));

//        circle
        mMap.addCircle(new CircleOptions()
                .center(latLng)
                .radius(100)
                .fillColor(Color.CYAN)
                .strokeColor(Color.DKGRAY)
        );

//        polygon
      /*  mMap.addPolygon(new PolygonOptions().
                add(new LatLng(31.347686809125296, 75.54588115631888),
                        new LatLng(31.347686809125296, 75.54588115631888),
                        new LatLng(31.347686809125296, 75.54588115631888),
                        new LatLng(31.347686809125296, 75.54588115631888),
                        new LatLng(31.347686809125296, 75.54588115631888))
                        .fillColor(Color.YELLOW)
                        .strokeColor(Color.GREEN)
                ); */
        mMap.addGroundOverlay(new GroundOverlayOptions()
                .position(latLng, 30f, 30f)
                .image(BitmapDescriptorFactory.fromResource(R.drawable.h1))
                .clickable(true)
        );
//we can also show to the user all maptype in the dropdown
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked here!!"));
                Geocoder geocoder = new Geocoder(Tracking.this);
                try {
                    ArrayList<Address> arrAdr = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude,1);
                    Log.d("Addr",arrAdr.get(0).getAddressLine(0));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        });


    }
}