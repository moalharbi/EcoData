package com.fnm.ecodata.map;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fnm.ecodata.BOs.MarkerData;
import com.fnm.ecodata.BOs.MarkerDataRealmBO;
import com.fnm.ecodata.EcoDataApp;
import com.fnm.ecodata.R;
import com.fnm.ecodata.Utils.API;
import com.fnm.ecodata.Utils.Util;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.realm.RealmResults;

import static com.fnm.ecodata.EcoDataApp.realm;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        SearchDialogFragment.MapDialogActivityNotifier,
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    Map<Marker, String> markerHash = new HashMap<>();
    private GoogleMap mMap;
    private ProgressDialog progressDialog;
    private static final int ENABLE_LOCATION_SETTINGS = 0;
    private ArrayList<MarkerData> myModelList = null;
    private String url = null;
    private String catName = "";
    private String urlToShare = "http://portal.tern.org.au/#/630e03c7";
    private double sourceLat = 0.0;
    private double sourceLong = 0.0;
    private GoogleApiClient mGoogleApiClient;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    private int notInRangeCount = 0;
    private int validDataSets = 0;
    private String state = null;
    private static int count = 100;
    private boolean isDestroyed;
    private CountDownTimer timer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isDestroyed = true;
        if(timer != null)
            timer.cancel();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        progressDialog = new ProgressDialog(this);
        if (realm.isInTransaction()) {
            realm.commitTransaction();
        }
        realm.beginTransaction();
        realm.delete(MarkerDataRealmBO.class);
        realm.commitTransaction();

        final TextView downloadingMessage = (TextView) findViewById(R.id.downloading_message);
        timer = new CountDownTimer(Long.MAX_VALUE, 1000) {
            public void onTick(long millisUntilFinished) {
                if (downloadingMessage.getVisibility() == View.VISIBLE) {
                    downloadingMessage.setVisibility(View.INVISIBLE);
                } else {
                    downloadingMessage.setVisibility(View.VISIBLE);
                }
            }

            public void onFinish() {
            }
        };
    }

    // onMapReady method will be called when map is ready to use
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-23.576931, 134.480706);
        mMap.setMyLocationEnabled(true);
        // float bearing = mMap.getMyLocation().getBearing();
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(sydney)
                .zoom(3.5f)

                .tilt(10)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(MapsActivity.this);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(MapsActivity.this);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(MapsActivity.this);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                MarkerDataRealmBO markerDataRealmBO = MarkerDataRealmBO.getMarkerData(realm, marker.getTitle());
                String title = markerDataRealmBO.getTitle();
                String desc = markerDataRealmBO.getSnipet();
                String citation = markerDataRealmBO.getCitationInfoSearch();
                String simplifiedTitle = markerDataRealmBO.getSimplifiedTitle();
                String slug = markerDataRealmBO.getSlug();
                urlToShare = "http://portal-dev.tern.org.au/" + slug;
                markerDescriptionDialog(title, desc, citation, simplifiedTitle);
                return true;
            }
        });

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                if (url != null && url.equalsIgnoreCase(API.urlSearchByLocation()) && catName.equalsIgnoreCase("SpatialBoundary")) {
                    sourceLat = latLng.latitude;
                    sourceLong = latLng.longitude;
                    mMap.clear();
                    showProgressDialog(R.string.maps_loading_markers);
                    fetchMarkerInfo(url);
                }
            }
        });

        if (getIntent().hasExtra("cat_name")) {
            catName = getIntent().getStringExtra("cat_name");
            FragmentManager dialog = getFragmentManager();
            // This switch will filter info to show on map based on select category
            switch (catName) {
                case "Plants And Animals":
                    urlToShare = "";
                    count = 0;
                    String urlAnimalPlanet = API.getPlantAndAnimalMarkerNewInfo(count);
                    url = urlAnimalPlanet.substring(0, urlAnimalPlanet.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlAnimalPlanet);
                    break;

                case "Vegetation":
                    urlToShare = "";
                    count = 0;
                    String urlVegitation = API.getVegetationMarkerNewInfo(count);
                    url = urlVegitation.substring(0, urlVegitation.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlVegitation);
                    break;

                case "Terrestrial Eco System":
                    urlToShare = "";
                    count = 0;
                    String urlTerrestrial = API.getTerrestrialMarkerNewInfo(count);
                    url = urlTerrestrial.substring(0, urlTerrestrial.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlTerrestrial);
                    break;

                case "Fresh Water And Estuarine":
                    urlToShare = "";
                    count = 0;
                    String urlFreshWater = API.getFreshWaterMarkerNewInfo(count);
                    url = urlFreshWater.substring(0, urlFreshWater.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlFreshWater);
                    break;

                case "Land Surface And Soil":
                    urlToShare = "";
                    count = 0;
                    String urlLandAndSoilWater = API.getLandsurfaceAndSoilMarkerNewInfo(count);
                    url = urlLandAndSoilWater.substring(0, urlLandAndSoilWater.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlLandAndSoilWater);
                    break;

                case "Agriculture":
                    urlToShare = "";
                    count = 0;
                    String urlAgriculture = API.getAgricultureMarkerNewInfo(count);
                    url = urlAgriculture.substring(0, urlAgriculture.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlAgriculture);
                    break;

                case "Ocean And Coasts":
                    urlToShare = "";
                    count = 0;
                    String urlOcean = API.getOceanCostMarkerNewInfo(count);
                    url = urlOcean.substring(0, urlOcean.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlOcean);
                    break;

                case "Climate":
                    urlToShare = "";
                    count = 0;
                    String urlClimate = API.getClimateMarkerNewInfo(count);
                    url = urlClimate.substring(0, urlClimate.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlClimate);
                    break;

                case "Human - Nature Interaction":
                    urlToShare = "";
                    count = 0;
                    String urlHumanNature = API.getHumanNatureMarkerNewInfo(count);
                    url = urlHumanNature.substring(0, urlHumanNature.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlHumanNature);
                    break;

                case "Energy, Water And Gas":
                    urlToShare = "";
                    count = 0;
                    String urlEnergy = API.getEnergyWaterGasMarkerNewInfo(count);
                    url = urlEnergy.substring(0, urlEnergy.indexOf('&'));
                    showProgressDialog(R.string.maps_loading_markers);
                    timer.start();
                    fetchMarkerNewInfo(urlEnergy);
                    break;

                case "SearchLocation":
                    urlToShare = "";
                    String urlSearchByLocation = API.urlSearchByLocation();
                    url = urlSearchByLocation;
                    if (!(isLocationEnabled(getApplicationContext()))) {
                        enableLocationWarningDialog();
                    }
                    Toast.makeText(getApplicationContext(), "Searching for current location...", Toast.LENGTH_LONG).show();
                    break;
                case "SearchText":
                    // SearchDialogFragment will show a popup dialog to user to key in search filters
                    SearchDialogFragment dialogFragment = new SearchDialogFragment();
                    dialogFragment.setOnSubmitClickListener(this);
                    dialogFragment.show(dialog, "SearchText");
                    break;

                case "SearchStates":
                    // SearchDialogFragment will show a popup dialog to user to key in search filters
                    SearchDialogFragment dialogFragmentStates = new SearchDialogFragment();
                    dialogFragmentStates.setOnSubmitClickListener(this);
                    dialogFragmentStates.show(dialog, "SearchStates");
                    break;

                case "SpatialBoundary":
                    urlToShare = "";
                    url = API.urlSearchByLocation();
                    spatialBoundryWarningDialog();
                    break;
            }
        }

        buildGoogleApiClient();
    }

    // fetchMarkerNewInfo method is used to fetch markers information for selected category with paging
    private void fetchMarkerNewInfo(final String urlGetUserInfo) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlGetUserInfo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray json = new JSONArray(response);
                            if (json.length() < 1) {
                                hideProgressDialog();
                                timer.cancel();
                                return;
                            }

                            if (json != null) {
                                Gson gson = new Gson();
                                myModelList = gson.fromJson(response, new TypeToken<ArrayList<MarkerData>>() {
                                }.getType());
                                myModelList.size();
                                addMarkersWithPaging(myModelList, url);
                            } else {
                                hideProgressDialog();
                            }

                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressDialog();
                        timer.cancel();
                    }
                }
        );

        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,//time to wait for it in this case 20s
                1,//tryies in case of error
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        EcoDataApp.getInstance().addToRequestQueue(stringRequest);
    }

    // fetchMarkerInfo method is used to fetch markers information for selected category
    private void fetchMarkerInfo(final String urlGetUserInfo) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlGetUserInfo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if (response.length() < 1) {
                            hideProgressDialog();
                            return;
                        }
                        try {
                            JSONObject json = new JSONObject(response);
                            if (json != null) {
                                Gson gson = new Gson();
                                myModelList = gson.fromJson(json.getJSONObject("response").getJSONArray("docs").toString(), new TypeToken<ArrayList<MarkerData>>() {
                                }.getType());
                                myModelList.size();
                                addMarkers(myModelList, urlGetUserInfo);
                            } else {
                                hideProgressDialog();
                            }

                        } catch (Exception e) {
                            hideProgressDialog();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideProgressDialog();
                    }
                }
        );

        stringRequest.setShouldCache(false);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                5000,//time to wait for it in this case 20s
                1,//tryies in case of error
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        ));

        EcoDataApp.getInstance().addToRequestQueue(stringRequest);
    }

    // addMarkersWithPaging method is used to place markers on map for new paging API
    private void addMarkersWithPaging(ArrayList<MarkerData> myModelList, final String url) {

        realm.beginTransaction();
        for (final MarkerData data : myModelList) {
            double destLat, destLang;
            if (data.getSpatial_coverage_centres() != null) {
                String[] centres = data.getSpatial_coverage_centres();//[0].split(",");
                String coordiates = centres[0];
                final String[] latitudeLang = coordiates.split(",");
                destLat = Double.valueOf(latitudeLang[1]);
                destLang = Double.valueOf(latitudeLang[0]);

                Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(destLat, destLang)).flat(false));
                marker.setTitle(data.getId());
                //markerHash.put(marker, data.getSlug());
                String snipet = "";
                if (data.getDescription_value() != null && data.getDescription_value().length > 0) {
                    snipet = data.getDescription_value()[0];
                    //marker.setSnippet(snipet);
                }

                String citation = "";
                if (data.getCitation_info_search() != null && data.getCitation_info_search().length > 0) {
                    citation = data.getCitation_info_search()[0];
                }

                final MarkerDataRealmBO markerDataRealmBO = new MarkerDataRealmBO();
                markerDataRealmBO.setId(data.getId());
                markerDataRealmBO.setTitle(data.getTitle());
                markerDataRealmBO.setSnipet(snipet);
                markerDataRealmBO.setCitationInfoSearch(citation);
                markerDataRealmBO.setSimplifiedTitle(data.getSimplified_title());
                markerDataRealmBO.setLat(latitudeLang[1]);
                markerDataRealmBO.setLongi(latitudeLang[0]);
                markerDataRealmBO.setSnipet(snipet);
                markerDataRealmBO.setUrl(url);
                markerDataRealmBO.setSlug(data.getSlug());
                realm.copyToRealmOrUpdate(markerDataRealmBO);
            } else {
            }
        }
        hideProgressDialog();
        realm.commitTransaction();

        if (!isDestroyed) {
            count = count + 100;
            String urlAnimalPlanet = API.getPagingURLForCategory(catName, state, count);
            fetchMarkerNewInfo(urlAnimalPlanet);
        }
    }


    // addMarkers method is used to place markers on map
    private void addMarkers(ArrayList<MarkerData> myModelList, String url) {
        notInRangeCount = validDataSets = 0;
        realm.beginTransaction();
        for (MarkerData data : myModelList) {
            double destLat, destLang;
            if (data.getSpatial_coverage_centres() != null) {
                validDataSets++;
                String[] centres = data.getSpatial_coverage_centres();//[0].split(",");
                String coordiates = centres[0];
                String[] latitudeLang = coordiates.split(",");
                destLat = Double.valueOf(latitudeLang[1]);
                destLang = Double.valueOf(latitudeLang[0]);

                if (url.equalsIgnoreCase(API.urlSearchByLocation()) && !isInRange(sourceLat, sourceLong, destLat, destLang, 'K', Util.getRadius(this))) {
                    hideProgressDialog();
                    notInRangeCount++;
                    continue;
                }

                Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(destLat, destLang)).flat(false));
                marker.setTitle(data.getId());
                //markerHash.put(marker, data.getSlug());
                String snipet = "";
                if (data.getDescription_value() != null && data.getDescription_value().length > 0) {
                    snipet = data.getDescription_value()[0];
                    //marker.setSnippet(snipet);
                }

                String citation = "";
                if (data.getCitation_info_search() != null && data.getCitation_info_search().length > 0) {
                    citation = data.getCitation_info_search()[0];
                }

                MarkerDataRealmBO markerDataRealmBO = new MarkerDataRealmBO();
                markerDataRealmBO.setId(data.getId());
                markerDataRealmBO.setTitle(data.getTitle());
                markerDataRealmBO.setSnipet(snipet);
                markerDataRealmBO.setCitationInfoSearch(citation);
                markerDataRealmBO.setSimplifiedTitle(data.getSimplified_title());
                markerDataRealmBO.setLat(latitudeLang[1]);
                markerDataRealmBO.setLongi(latitudeLang[0]);
                markerDataRealmBO.setSnipet(snipet);
                markerDataRealmBO.setUrl(url);
                markerDataRealmBO.setSlug(data.getSlug());
                realm.copyToRealmOrUpdate(markerDataRealmBO);

            } else {
            }
        }

        if (notInRangeCount == validDataSets) {
            Toast.makeText(getApplicationContext(), "No results found", Toast.LENGTH_SHORT).show();
        }
        hideProgressDialog();
        realm.commitTransaction();
    }

    private void showProgressDialog(int message) {
        progressDialog.setMessage(getResources().getString(message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();
    }

    // shareResult method is used to share results
    public void shareResult(View view) {

        if (mMap == null) {
            Toast.makeText(this, "Results are not ready yet to share.", Toast.LENGTH_SHORT).show();
        } else if (mMap != null) {

            if (view != null) {
                urlToShare = "http://portal-dev.tern.org.au";
            }
            chooserIntent();
        }
    }

    // showDetail method is used to show list of centres
    public void showDetail(View view) {
        if (url != null) {
            Intent intent = new Intent(this, DetailListActivity.class);
            intent.putExtra("url", url);
            startActivity(intent);
        }
    }

    // enableLocationWarningDialog method is used to warn user if location is off in settings
    private void enableLocationWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.nfc_location_dialog);
        builder.setPositiveButton(R.string.onboarding_location_enablegpssettingbutton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(
                        new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS), ENABLE_LOCATION_SETTINGS);
            }
        });
        builder.setNegativeButton(R.string.onboarding_location_enablegpscancelbutton, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }

    // spatialBoundryWarningDialog method is used to display message to user about usages of this category
    private void spatialBoundryWarningDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.spatial_boundry_dialog_title);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    // markerDescriptionDialog method is used to display centre info when click on marker
    private void markerDescriptionDialog(String title, String description, String citation, String access) {
        LayoutInflater inflater = getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.detail_dialog_view, null);
        TextView title1 = (TextView) dialoglayout.findViewById(R.id.title1);
        title1.setText(R.string.title_desc);
        TextView desc1 = (TextView) dialoglayout.findViewById(R.id.title1_desc);
        if(description != null)
        desc1.setText(description.trim());

        TextView title2 = (TextView) dialoglayout.findViewById(R.id.title2);
        title2.setText(R.string.title_citation);
        TextView desc2 = (TextView) dialoglayout.findViewById(R.id.title2_desc);
        if(citation != null) {
            desc2.setText(Html.fromHtml(citation).toString());
        }

        TextView title3 = (TextView) dialoglayout.findViewById(R.id.title3);
        title3.setText(R.string.title_access);
        TextView desc3 = (TextView) dialoglayout.findViewById(R.id.title3_desc);
        if(access != null)
        desc3.setText(access.trim());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setView(dialoglayout);

        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Share", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                shareResult(null);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    // isLocationEnabled method is used to check if device location is on or off
    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }
            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }
    }

    // chooserIntent method is used to select platform on which user want to share results
    private void chooserIntent() {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, urlToShare);
        shareIntent.setType("text/plain");
        startActivity(Intent.createChooser(shareIntent, "Share"));
    }

    // searchByText method is used to show results of search by text
    @Override
    public void searchByText(String query) {
        String urlSearchByText = API.urlSearchByText(query);
        url = urlSearchByText;
        Log.i("searchByText", "urlSearchByText = " + urlSearchByText);
        showProgressDialog(R.string.maps_loading_markers);
        fetchMarkerInfo(urlSearchByText);
    }

    // searchByTextAdvance method is used to show results of search by text and state
    @Override
    public void searchByTextAdvance(String url) {
        Log.i("searchByTextAdvance", "url = " + url);
        this.url = url;
        showProgressDialog(R.string.maps_loading_markers);
        fetchMarkerInfo(url);
    }

    // searchByState method is used to show results of search by state
    @Override
    public void searchByState(String query) {
        catName = "searchByState";
        String urlSearchByState = API.getStatesUrl(0, query);//API.urlSearchByText(query);
        url = urlSearchByState;
        state = query;
        showProgressDialog(R.string.maps_loading_markers);
        timer.start();
        fetchMarkerNewInfo(urlSearchByState);
    }

    // buildGoogleApiClient method is used to prepare GoogleApiClient to get users current location
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    // onConnected method is called when GoogleApiClient is connected with google
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        } else {
            startLocationUpdates();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    // onLocationChanged method will be called when current location will be detected or changed
    @Override
    public void onLocationChanged(Location location) {
        try {

            if (url != null && url.equalsIgnoreCase(API.urlSearchByLocation()) && catName.equalsIgnoreCase("SearchLocation")) {
                if (location.getLatitude() != 0.0) {
                    sourceLat = location.getLatitude();
                    sourceLong = location.getLongitude();
                    //Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).flat(false));
                    LatLng sydney = new LatLng(location.getLatitude(), location.getLongitude());
                    // float bearing = mMap.getMyLocation().getBearing();
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(sydney)
                            .zoom(3.5f)

                            .tilt(10)
                            .build();

                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    showProgressDialog(R.string.maps_loading_markers);
                    fetchMarkerInfo(url);
                } else {
                    Toast.makeText(getApplicationContext(), "Unable to find current location info", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
            mGoogleApiClient.disconnect();
            mGoogleApiClient = null;
        }

    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(
                mGoogleApiClient, this);
    }

    // startLocationUpdates method is used to start retrieve current location from satellite
    protected void startLocationUpdates() {
        LocationRequest mLocationRequest = createLocationRequest();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        PendingResult<Status> pendingResult = LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);
    }

    // checkLocationPermission method is used to check if user has granted permission to get current location for android version 23 or above
    private void checkLocationPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        } else {
            startLocationUpdates();
        }

    }

    // createLocationRequest method is used to configure current location request like how often your location should be updated on map etc...
    protected LocationRequest createLocationRequest() {
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return mLocationRequest;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ENABLE_LOCATION_SETTINGS) {

            return;
        }
    }

    // onRequestPermissionsResult method is called when user grant or not the permission to get his current location for android version 23 and above
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    startLocationUpdates();
                } else {

                }
                break;
        }
    }

    // isInRange method is used to check if data centre is in range defined by user in app settings
    private boolean isInRange(double lat1, double lon1, double lat2, double lon2, char unit, String range) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }

        if (!(range.isEmpty()) && range != null) {
            double VendorRange = Double.parseDouble(range);
            if (dist <= VendorRange) {
                return true;
            }
        }

        return false;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

}
