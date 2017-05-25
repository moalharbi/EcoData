package com.fnm.ecodata.map;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fnm.ecodata.BOs.MarkerDataRealmBO;
import com.fnm.ecodata.EcoDataApp;
import com.fnm.ecodata.R;
import com.fnm.ecodata.category.CategoryContent;
import com.fnm.ecodata.category.CategoryListAdapter;
import com.fnm.ecodata.category.CategoryListAdapterInterface;

import io.realm.RealmResults;

// This class will be used show list of data centres for selected category
public class DetailListActivity extends AppCompatActivity implements CategoryListAdapterInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initViews();
    }

    // Setting up adapter to show list of all points under selected category
    private void initViews() {
        String url = getIntent().getStringExtra("url");
        RealmResults<MarkerDataRealmBO> realmResults = MarkerDataRealmBO.getMarkerDataForURL(EcoDataApp.realm, url);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.detail_rcv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new DetailListAdapter(this, realmResults, this));
    }

    @Override
    public void showSearchResult(String catName) {

    }

    @Override
    public void showMarkerDetail(String title, String description, String citation, String access, String slug) {
        markerDescriptionDialog(title, description, citation, access, slug);
    }

    // markerDescriptionDialog will be called to show centre detail
    private void markerDescriptionDialog(String title, String description, String citation, String access, String slug) {
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
        title3.setVisibility(View.GONE);
        title3.setText(R.string.title_access);
        TextView desc3 = (TextView) dialoglayout.findViewById(R.id.title3_desc);
        if(access != null)
        desc3.setText(access.trim());
        desc3.setVisibility(View.GONE);

        final String urlToShare = "http://portal-dev.tern.org.au/" + slug;

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
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, urlToShare);
                //shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(path));
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "Share"));
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
