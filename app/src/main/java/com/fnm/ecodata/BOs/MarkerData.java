package com.fnm.ecodata.BOs;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MuhammadDev on 2/7/17.
 */

public class MarkerData  {

    String id;
    String title;
    String slug;
    String[] citation_info_search;
    String simplified_title;
    String[] description_type;
    String[] description_value;
    String[] spatial_coverage_centres;
    String access_rights;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getDescription_type() {
        return description_type;
    }

    public void setDescription_type(String[] description_type) {
        this.description_type = description_type;
    }

    public String[] getDescription_value() {
        return description_value;
    }

    public void setDescription_value(String[] description_value) {
        this.description_value = description_value;
    }

    public String[] getSpatial_coverage_centres() {
        return spatial_coverage_centres;
    }

    public void setSpatial_coverage_centres(String[] spatial_coverage_centres) {
        this.spatial_coverage_centres = spatial_coverage_centres;
    }

    public String getAccess_rights() {
        return access_rights;
    }

    public void setAccess_rights(String access_rights) {
        this.access_rights = access_rights;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //    public static RealmResults<MarkerData> getMarkerData(Realm realm){
//        return realm.where(MarkerData.class).findAll();
//    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String[] getCitation_info_search() {
        return citation_info_search;
    }

    public void setCitation_info_search(String[] citation_info_search) {
        this.citation_info_search = citation_info_search;
    }

    public String getSimplified_title() {
        return simplified_title;
    }

    public void setSimplified_title(String simplified_title) {
        this.simplified_title = simplified_title;
    }
}
