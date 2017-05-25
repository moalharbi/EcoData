package com.fnm.ecodata;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.fnm.ecodata.Utils.API;
import com.fnm.ecodata.aboutus.AboutusActivity;
import com.fnm.ecodata.category.CategoryContent;
import com.fnm.ecodata.category.CategoryListAdapter;
import com.fnm.ecodata.category.CategoryListAdapterInterface;
import com.fnm.ecodata.map.MapsActivity;
import com.fnm.ecodata.setting.SettingsActivity;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CategoryListAdapterInterface {

    private CategoryListAdapter mAdapter;
    // onCreate method is used to initiate views objects in this activity layout
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.catrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CategoryListAdapter(this, CategoryContent.ITEMS, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // onCreateOptionsMenu method is used to add action buttons in actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        if (null != searchView) {
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(true);
        }

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // this is your adapter that will be filtered
                Log.i("OnQueryTextListener", newText);
                mAdapter.filter(newText);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                //Here u can get the value "query" which is entered in the search box.
                Log.i("onQueryTextSubmit", query);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            gotoSettingActivity();
            return true;
        }

        if (id == R.id.action_aboutus) {
            gotoAboutActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search_location) {
            // Handle the camera action
            showSearchResult("SearchLocation");
        } else if (id == R.id.nav_search_boundry) {
            showSearchResult("SpatialBoundary");
        } else if(id == R.id.nav_search_data_text) {
            showSearchResult("SearchText");
        } else if(id == R.id.nav_search_states){
            showSearchResult("SearchStates");
        }else if (id == R.id.nav_about_us) {
            gotoAboutActivity();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void gotoAboutActivity(){
        Intent intent = new Intent(this, AboutusActivity.class);
        startActivity(intent);
    }

    private void gotoSettingActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }


    @Override
    public void showSearchResult(String catName) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("cat_name", catName);
        startActivity(intent);
    }

    @Override
    public void showMarkerDetail(String title, String description, String citation, String access, String slug) {

    }

}
