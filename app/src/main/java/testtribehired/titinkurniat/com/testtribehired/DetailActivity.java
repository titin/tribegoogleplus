package testtribehired.titinkurniat.com.testtribehired;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

/**
 * Created by Titin Kurniati on 23-May-16.
 */
public class DetailActivity extends Activity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private ImageView imgPerson;
    private TextView rekomendasi;
    private TextView schedule;
    private TextView nama;
    private TextView alamat;
    private TextView spesial;
    private TextView rate;
    private TextView deskripsi;
    private static String TAG = DetailActivity.class.getSimpleName();
    private GoogleMap googleMap;
    private MapView nearMeMap;
    protected GoogleApiClient googleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);
        buildGoogleApiClient();

        imgPerson = (ImageView) findViewById(R.id.img_person);
        rekomendasi = (TextView) findViewById(R.id.rekomendasi);
        schedule = (TextView) findViewById(R.id.schedule);
        nama = (TextView) findViewById(R.id.name);
        alamat = (TextView) findViewById(R.id.alamat);
        spesial = (TextView) findViewById(R.id.spesial);
        rate = (TextView) findViewById(R.id.rate);
        deskripsi = (TextView) findViewById(R.id.deskripsi);
        nearMeMap = (MapView) findViewById(R.id.map_near_me);

        nearMeMap.onCreate(savedInstanceState);
        init();
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void initializeMap() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (status == ConnectionResult.SUCCESS) {
            googleMap = nearMeMap.getMap();
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            googleMap.getUiSettings().setZoomControlsEnabled(true);
            googleMap.setMyLocationEnabled(true);
            MapsInitializer.initialize(this);

            // set default to jakarta
            LatLng ll = new LatLng(-6.184694, 106.828145);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ll, 11));
        } else {
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        }
    }

    public void loadMarker(LatLng latLng) {
        MarkerOptions options = new MarkerOptions().position(latLng);
        options.title(getAddressFromLatLng(latLng));
        options.icon(BitmapDescriptorFactory.defaultMarker());
        googleMap.addMarker(options);
    }


    private String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder(this);
        String address = "";
        try {
            address = geocoder
                    .getFromLocation(latLng.latitude, latLng.longitude, 1)
                    .get(0).getAddressLine(0);
        } catch (IOException e) {
        }
        return address;
    }

    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;
    }

    public void init() {
        String id = getIntent().getStringExtra(Const.ID);
        String url = "http://52.76.85.10/test/profile/" + id + ".json";
        initializeMap();
        makeJsonObjectRequest(url);
    }

    private void makeJsonObjectRequest(String url) {
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    rekomendasi.setText(response.getString("recommendation"));
                    nama.setText(response.getString("name"));
                    schedule.setText(response.getString("schedule"));
                    alamat.setText(response.getString("area"));
                    spesial.setText(response.getString("speciality"));
                    deskripsi.setText(response.getString("description"));
                    rate.setText(response.getString("currency") + " " + Util.getFormat(response.getString("rate")));
                    Glide.with(DetailActivity.this).load(response.getString("photo"))
                            .placeholder(R.drawable.orang)
                            .diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.orang).into(imgPerson);

                    LatLng latLng = new LatLng(response.getDouble("latitude"), response.getDouble("longitute"));
                    loadMarker(latLng);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @Override
    protected void onResume() {

        nearMeMap.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        nearMeMap.onPause();
        super.onPause();
    }

    @Override
    public void onLowMemory() {
        nearMeMap.onLowMemory();
        super.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        nearMeMap.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        googleApiClient.connect();

    }


    @Override
    public void onConnected(Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

}
