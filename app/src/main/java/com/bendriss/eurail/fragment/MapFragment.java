package com.bendriss.eurail.fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;

import android.location.LocationManager;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bendriss.eurail.R;

import com.bendriss.eurail.config.AppDatabase;
import com.bendriss.eurail.model.LocationModel;
import com.bendriss.eurail.utils.ConnectivityUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Random;

public class MapFragment extends Fragment implements OnMapReadyCallback {


    private AppDatabase database;

    private GoogleMap mMap;
    private SupportMapFragment mapFragment;
    private LocationManager locationManager;
    private LocationListener locationListener;


    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    public MapFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        init();

        if (!ConnectivityUtils.checkLocationServices(getContext()))
            Toast.makeText(getContext(), getResources().getString(R.string.check_location), Toast.LENGTH_SHORT).show();


        return view;
    }

    /**
     * This function will initiate our vars
     */
    private void init() {
        database = AppDatabase.getDatabase(getContext());

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        fetchLastLocation();

        mapFragment = (SupportMapFragment) this.getChildFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);

    }

    /**
     * This function will return the list of savec locations
     *
     * @return
     */
    private List<LocationModel> getAllSavedLocations() {
        return database.locationDao().getAllLocations();
    }

    /**
     * This function will save the locationModel to the local database
     *
     * @param locationModel
     */
    private void saveLocationToDatabase(LocationModel locationModel) {
        database.locationDao().addLocation(locationModel);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastKnownLocation != null)
                centreMapOnLocation(lastKnownLocation, "Your LocationModel");
        } else {

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }


    public void centreMapOnLocation(Location location, String title) {

        LatLng userLocation = new LatLng(location.getLatitude(), location.getLongitude());

        /**
         * Get a random near location from the current location
         */
        Location randomNearLocation = getRandomNearLocation(location.getLongitude(), location.getLatitude(), 7000);

        /**
         * Save this random near location to the local database
         */
        LocationModel locationModel = new LocationModel(1, System.currentTimeMillis() + "", randomNearLocation.getLongitude() + "", randomNearLocation.getLatitude() + "");
        saveLocationToDatabase(locationModel);

        /**
         * Adding markers
         */
        LatLng randomLocationLatLng = new LatLng(randomNearLocation.getLatitude(), randomNearLocation.getLongitude());
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(userLocation).title(title));
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.pin);

        mMap.addMarker(new MarkerOptions().position(randomLocationLatLng).title(title).icon(icon));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, 12));

        /**
         * The calculation of the distance between the two places
         */
        double distance = (double) Math.round((location.distanceTo(randomNearLocation) / 1000) * 100) / 100;
        Toast.makeText(getContext(), getResources().getString(R.string.distance_between_two) + " " + distance + " KM", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

                Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //LocationModel lastKnownLocation = getLastKnownLocation();
                if (lastKnownLocation != null)
                    centreMapOnLocation(lastKnownLocation, "Your LocationModel");
            }
        }

    }

    /**
     * This function will return a random nearby location using a radius (I defined it to 7000 meters)
     *
     * @param x0
     * @param y0
     * @param radius
     * @return
     */
    private Location getRandomNearLocation(double x0, double y0, int radius) {
        Random random = new Random();

        /**
         * Convert radius from meters to degrees
         */
        double radiusInDegrees = radius / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        double new_x = x / Math.cos(Math.toRadians(y0));

        double foundLongitude = new_x + x0;
        double foundLatitude = y + y0;
        Location l = new Location("Random nearby location");
        l.setLongitude(foundLongitude);
        l.setLatitude(foundLatitude);
        return l;
    }

    /**
     * This function will fetch for the last location
     */
    private void fetchLastLocation() {
        if (ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    centreMapOnLocation(location, "Your LocationModel");
                }
            }
        });
    }

    /*
    LocationManager mLocationManager;
    private Location getLastKnownLocation() {
        mLocationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        Location l = null;
        for (String provider : providers) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                l = mLocationManager.getLastKnownLocation(provider);
            }
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
            }
        }
        return bestLocation;
    }
*/

}
