package com.misterj.appofsecrets;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.Marker;
import com.google.android.material.snackbar.Snackbar;
import com.misterj.appofsecrets.adapters.PlacesAdapter;
import com.misterj.appofsecrets.items.PlacesItems;
import com.misterj.appofsecrets.utils.DataProccessor;
import com.misterj.appofsecrets.utils.LocationCalculations;
import com.misterj.appofsecrets.utils.PathUtil;
import com.misterj.appofsecrets.webservice.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.misterj.appofsecrets.webservice.imgbbAPI;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class PlacesActivity extends AppCompatActivity implements LocationListener, View.OnClickListener, OnMapReadyCallback {

    SeekBar seekbar;
    TextView tvSeekbarValue, tvLocationMode, tvVerifiedMode;
    EditText etCoordinates, etNewPlaceName;
    Button btnApplyPlaceFilters, btnSubmitNewPlace;
    FloatingActionButton fabAddNewPlace;
    ImageView imgPlaceFilter, imgPlaceFilterClose, imgNewPlaceClose;
    RelativeLayout filter_panel, new_panel;
    List<PlacesItems> list;
    GridView lstPlaces;
    Spinner spnCategory, spnNewPlaceCategory;
    Intent intent;
    String verified = null;

    Marker selectedLocation = null;

    Switch swLocation, swVerified;
    double lat, lng;
    LocationManager locationManager;
    private GoogleMap googleMapNewPlace;
    private  static int PICK_IMAGE_REQUEST = 180994;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        spnCategory = (Spinner) findViewById(R.id.spnCategory);
        spnNewPlaceCategory = (Spinner) findViewById(R.id.spnNewPlaceCategory);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        tvSeekbarValue = (TextView) findViewById(R.id.tvSeekbarValue);
        tvLocationMode = (TextView) findViewById(R.id.tvLocationMode);
        tvVerifiedMode = (TextView) findViewById(R.id.tvVerifiedMode);
        btnApplyPlaceFilters = (Button) findViewById(R.id.btnApplyPlaceFilters);
        btnSubmitNewPlace = (Button) findViewById(R.id.btnSubmitNewPlace);
        lstPlaces = (GridView) findViewById(R.id.lstPlaces);
        filter_panel = findViewById(R.id.filter_panel);
        imgPlaceFilter = (ImageView) findViewById(R.id.imgPlaceFilter);
        imgNewPlaceClose = (ImageView) findViewById(R.id.imgNewPlaceClose);
        imgPlaceFilterClose = (ImageView) findViewById(R.id.imgPlaceFilterClose);
        filter_panel = (RelativeLayout) findViewById(R.id.filter_panel);
        new_panel = (RelativeLayout) findViewById(R.id.new_panel);
        swLocation = (Switch) findViewById(R.id.swLocation);
        swVerified = (Switch) findViewById(R.id.swVerified);
        etCoordinates = (EditText) findViewById(R.id.etCoordinates);
        etNewPlaceName = (EditText) findViewById(R.id.etNewPlaceName);
        fabAddNewPlace = (FloatingActionButton) findViewById(R.id.fabAddNewPlace);

        imgPlaceFilter.setOnClickListener(this);
        imgPlaceFilterClose.setOnClickListener(this);
        btnApplyPlaceFilters.setOnClickListener(this);
        swLocation.setOnClickListener(this);
        swVerified.setOnClickListener(this);
        fabAddNewPlace.setOnClickListener(this);
        imgNewPlaceClose.setOnClickListener(this);
        btnSubmitNewPlace.setOnClickListener(this);

        if (new DataProccessor(getApplicationContext()).getInt("distance") > 0) {
            seekbar.setProgress(new DataProccessor(getApplicationContext()).getInt("distance"));
            tvSeekbarValue.setText(Integer.toString(seekbar.getProgress()) + " km");
        } else {
            seekbar.setProgress(30);
            new DataProccessor(getApplicationContext()).setInt("distance",30);
            tvSeekbarValue.setText(Integer.toString(seekbar.getProgress()) + " km");
        }

        if (new DataProccessor(getApplicationContext()).getInt("category") >= 0) {
            spnCategory.setSelection(new DataProccessor(getApplicationContext()).getInt("category"));
        }

        if (new DataProccessor(getApplicationContext()).getStr("location") != null) {
            swLocation.setChecked(new DataProccessor(getApplicationContext()).getBool("mode"));
            etCoordinates.setText(new DataProccessor(getApplicationContext()).getStr("location"));
        }

        if (new DataProccessor(getApplicationContext()).getStr("verfied") != null) {
            swVerified.setChecked(true);
            tvVerifiedMode.setText(getString(R.string.verified));
        }
        else{
            swVerified.setChecked(false);
            tvVerifiedMode.setText(getString(R.string.any));
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLastKnownLocation();
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMapNewPlace);
        mapFragment.getMapAsync(this);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress >= 0 && progress <= seekbar.getMax()) {
                    String value = Integer.toString(progress) + " km";
                    tvSeekbarValue.setText(value);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void slideUpDownFilter() {
        if (!isPanelShown()) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
            filter_panel.startAnimation(bottomUp);
            filter_panel.setVisibility(View.VISIBLE);
            fabAddNewPlace.setVisibility(View.GONE);
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
            filter_panel.startAnimation(bottomDown);
            filter_panel.setVisibility(View.GONE);
            fabAddNewPlace.setVisibility(View.VISIBLE);
        }
    }

    private boolean isPanelShown() {
        return filter_panel.getVisibility() == View.VISIBLE;
    }

    private void LoadPlaces(double lat, double lng, int distance, int category, String country, String state, String verified) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                ArrayList<Places.PlaceInfo> arrPlaces = new Places().ClosePlaces(lat, lng, distance, category, country, state, verified);

                list = new ArrayList<>();
                for (Places.PlaceInfo place : arrPlaces) {
                    list.add(new PlacesItems(place.id, place.name, place.category, place.status,
                            place.country, place.state, place.lat, place.lng, place.sponsored,
                            place.image, place.verified, place.created_at, place.distance));
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //UI Thread work here
                        PlacesAdapter customAdapter = new PlacesAdapter(PlacesActivity.this, R.layout.item_place, list);
                        lstPlaces.setAdapter(customAdapter);
                    }
                });
            }
        });
    }

    public void getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        if (swLocation.isChecked()) {
            Location myLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (myLocation == null) {
                myLocation = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
                if (myLocation != null) {
                    lat = myLocation.getLatitude();
                    lng = myLocation.getLongitude();

                    LoadPlaces(lat, lng,
                            new DataProccessor(getApplicationContext()).getInt("distance"),
                            new DataProccessor(getApplicationContext()).getInt("category"),
                            null,
                            null,
                            verified);
                }
            } else {
                lat = myLocation.getLatitude();
                lng = myLocation.getLongitude();

                LoadPlaces(lat, lng,
                        new DataProccessor(getApplicationContext()).getInt("distance"),
                        new DataProccessor(getApplicationContext()).getInt("category"),
                        null,
                        null,
                        verified);
            }
        } else {
            String strLocation = etCoordinates.getText().toString();
            if (etCoordinates.getText().toString().contains(",")) {
                LoadPlaces(Double.parseDouble(strLocation.split(",")[0]),
                        Double.parseDouble(strLocation.split(",")[1]),
                        new DataProccessor(getApplicationContext()).getInt("distance"),
                        new DataProccessor(getApplicationContext()).getInt("category"),
                        null,
                        null,
                        verified);
            } else {
                Toast.makeText(PlacesActivity.this, "Hold down a second, we are getting things ready!", Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if (swLocation.isChecked()) {
            if ((int) new LocationCalculations().distanceInMeters(lat, location.getLatitude(),
                    lng, location.getLongitude()) >= 20) {
                lat = location.getLatitude();
                lng = location.getLongitude();

                if (lstPlaces.getAdapter() == null) {
                    LoadPlaces(lat, lng,
                            new DataProccessor(getApplicationContext()).getInt("distance"),
                            new DataProccessor(getApplicationContext()).getInt("category"),
                            null,
                            null,
                            verified);
                }
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    public void slideUpDownNewPlace() {
        if (!isPlacePanelShown()) {
            // Show the panel
            Animation bottomUp = AnimationUtils.loadAnimation(this, R.anim.bottom_up);
            new_panel.startAnimation(bottomUp);
            new_panel.setVisibility(View.VISIBLE);
            fabAddNewPlace.setVisibility(View.GONE);
        } else {
            // Hide the Panel
            Animation bottomDown = AnimationUtils.loadAnimation(this, R.anim.bottom_down);
            new_panel.startAnimation(bottomDown);
            new_panel.setVisibility(View.GONE);
            fabAddNewPlace.setVisibility(View.VISIBLE);
        }
    }

    private boolean isPlacePanelShown() {
        return new_panel.getVisibility() == View.VISIBLE;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnApplyPlaceFilters:

                if (swVerified.isChecked()) {
                    new DataProccessor(getApplicationContext()).setStr("verified", "1");

                }
                else{
                    new DataProccessor(getApplicationContext()).setStr("verified", null);
                }

                if (swLocation.isChecked()) {
                    new DataProccessor(getApplicationContext()).setBool("mode", swLocation.isChecked());
                    new DataProccessor(getApplicationContext()).setInt("distance", seekbar.getProgress());
                    new DataProccessor(getApplicationContext()).setInt("category", spnCategory.getSelectedItemPosition());

                    if (lat != 0 || lng != 0) {
                        LoadPlaces(lat,
                                lng,
                                new DataProccessor(getApplicationContext()).getInt("distance"),
                                new DataProccessor(getApplicationContext()).getInt("category"),
                                null,
                                null,
                                verified);
                    } else {
                        Toast.makeText(PlacesActivity.this, "Hold down a second, we are getting things ready!", Toast.LENGTH_LONG);
                    }
                } else {
                    String strLocation = etCoordinates.getText().toString().replaceAll("[^\\d.,-]", "");
                    new DataProccessor(getApplicationContext()).setBool("mode", swLocation.isChecked());
                    new DataProccessor(getApplicationContext()).setStr("location", strLocation);
                    new DataProccessor(getApplicationContext()).setInt("distance", seekbar.getProgress());
                    new DataProccessor(getApplicationContext()).setInt("category", spnCategory.getSelectedItemPosition());

                    if (etCoordinates.getText().toString().contains(",")) {
                        LoadPlaces(Double.parseDouble(strLocation.split(",")[0]),
                                Double.parseDouble(strLocation.split(",")[1]),
                                new DataProccessor(getApplicationContext()).getInt("distance"),
                                new DataProccessor(getApplicationContext()).getInt("category"),
                                null,
                                null,
                                verified);
                    } else {
                        Toast.makeText(PlacesActivity.this, "Hold down a second, we are getting things ready!", Toast.LENGTH_LONG).show();
                    }
                }

                break;

            case R.id.imgPlaceFilterClose:
                slideUpDownFilter();
                break;

            case R.id.imgPlaceFilter:
                slideUpDownFilter();
                break;
            case R.id.swLocation:
                if (swLocation.isChecked()) {
                    tvLocationMode.setText(getString(R.string.real));
                    etCoordinates.setEnabled(false);
                } else {
                    tvLocationMode.setText(getString(R.string.mock));
                    etCoordinates.setEnabled(true);
                }
                break;
            case R.id.fabAddNewPlace:
                slideUpDownNewPlace();
                break;
            case R.id.imgNewPlaceClose:
                slideUpDownNewPlace();
                break;
            case R.id.btnSubmitNewPlace:
                new DataProccessor(getApplicationContext()).setStr("Image_Uploaded", null);
                if( etNewPlaceName.getText().toString() != "" && etNewPlaceName.getText().toString() != null &&
                    spnNewPlaceCategory.getSelectedItemPosition() >= 0 && selectedLocation != null)
                {
                    if(checkPermission())
                    {
                        new SweetAlertDialog(PlacesActivity.this, SweetAlertDialog.WARNING_TYPE)
                                .setTitleText(getString(R.string.important))
                                .setContentText(getString(R.string.new_place_next_step))
                                .setConfirmText(getString(R.string.sure))
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                        intent = new Intent(Intent.ACTION_GET_CONTENT);
                                        intent.setType("image/*");
                                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                                    }
                                })
                                .setCancelButton(getString(R.string.cancel), new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog.dismissWithAnimation();
                                    }
                                }).show();
                    }
                    else
                    {
                        requestPermission();
                    }
                }
                else
                {
                    if (selectedLocation == null)
                    {
                        Snackbar.make(v,getString(R.string.pick_location),Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.okey, this)
                                .show();
                    }
                    else
                    {
                        Snackbar.make(v,getString(R.string.please_complete_the_form),Snackbar.LENGTH_INDEFINITE)
                                .setAction(R.string.okey, this)
                                .show();
                    }
                }
                break;
            case R.id.swVerified:
                if (swVerified.isChecked())
                {
                    verified = "1";
                    tvVerifiedMode.setText(getString(R.string.verified));
                }
                else
                {
                    verified = null;
                    tvVerifiedMode.setText(getString(R.string.any));
                }
                break;
        }
    }

    private static int PERMISSION_REQUEST_CODE = 100;

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(PlacesActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(PlacesActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(PlacesActivity.this, "Write External Storage permission allows us to do store images. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(PlacesActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }




    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMapNewPlace = googleMap;


        googleMapNewPlace.getUiSettings().setZoomControlsEnabled(true);
        googleMapNewPlace.getUiSettings().setMapToolbarEnabled(false);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        googleMapNewPlace.setMyLocationEnabled(true);
        // Focus camera in last known location
        googleMapNewPlace.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 13));



        googleMapNewPlace.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                if(selectedLocation!=null)
                {
                    googleMapNewPlace.clear();
                }
                selectedLocation = googleMapNewPlace.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(etNewPlaceName.getText().toString()));
                googleMapNewPlace.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {

            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Uri uri = data.getData();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                String filePath = PathUtil.getPath(PlacesActivity.this, uri);
                                if(filePath!=null)
                                {
                                    Log.e("Selected_Picture", filePath);

                                    new imgbbAPI(getApplicationContext()).UploadAndSubmitPlace(filePath,
                                            etNewPlaceName.getText().toString(),
                                            spnNewPlaceCategory.getSelectedItemPosition(),
                                            selectedLocation.getPosition().latitude,
                                            selectedLocation.getPosition().longitude);
                                }
                            } catch (URISyntaxException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            });

        }

        if(requestCode == PERMISSION_REQUEST_CODE)
        {
            if (resultCode == RESULT_OK) {
                new SweetAlertDialog(PlacesActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText(getString(R.string.important))
                        .setContentText(getString(R.string.new_place_next_step))
                        .setConfirmText(getString(R.string.sure))
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                intent = new Intent(Intent.ACTION_GET_CONTENT);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                            }
                        })
                        .setCancelButton(getString(R.string.cancel), new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        }).show();
            } else {
                Log.e("value", "Permission Denied, You cannot use local drive .");
            }
        }
    }
}