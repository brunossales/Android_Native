package br.ufc.quixada.cripto.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.controller.Codes;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class About_activity extends AppCompatActivity implements OnMapReadyCallback {
    private static final int REQUEST_CODE = 101;


    Intent intent;
    String nameUser;

    BottomNavigationView nav;

    TextView textViewNameUser;

    GoogleMap gMap;
    private FusedLocationProviderClient fusedLocationClient;
    Location currentLocation;

    String latitude;
    String longitude;

    boolean extrasHasInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();

        nameUser = getIntent().getExtras().getString(Codes.Key_BemVindo);
        textViewNameUser = findViewById(R.id.textViewNameAbout);
        textViewNameUser.setText(textViewNameUser.getText() + nameUser);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(About_activity.this);


        nav = findViewById(R.id.bottomNavigationView);
        nav.setSelectedItemId(R.id.aboutwithus);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.star:
                        intent = null;
                        intent = new Intent(About_activity.this, Star_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        intent = null;
                        intent = new Intent(About_activity.this, Find_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.homee:
                        intent = null;
                        intent = new Intent(About_activity.this, Feed_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;

                    default:
                }
                return true;
            }
        });

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(About_activity.this);
        fetchLocation();
    }

    private void fetchLocation() {

        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;

        }

        @SuppressLint("MissingPermission") Task<Location> task = fusedLocationClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {

                if (location != null) {

                    currentLocation = location;
                    latitude = "" + location.getLatitude();
                    longitude = "" + location.getLongitude();
                    Toast.makeText(getApplicationContext(), currentLocation.getLatitude() + "" + currentLocation.getLongitude(), Toast.LENGTH_SHORT).show();

                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(About_activity.this);

                }
            }
        });
    }

    @Override
    public void onMapReady( GoogleMap googleMap ) {
        gMap = googleMap;
        
        Toast.makeText(About_activity.this, "Map funcionando...", Toast.LENGTH_LONG).show();
        LatLng ufc = new LatLng(-4.979160617781738, -39.05642357630226); //UFC
        LatLng quixada = new LatLng(-4.969671795463958, -39.01481406469337); //Home
        MarkerOptions markerOptions = new MarkerOptions().position(quixada).title("Minha residência");
        MarkerOptions markerOptionsUFC = new MarkerOptions().position(ufc).title("UFC");
        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(quixada, 15));
        gMap.addMarker(markerOptions);
        gMap.addMarker(markerOptionsUFC);
        gMap.addPolyline(new PolylineOptions().add(ufc, quixada));
    }


}