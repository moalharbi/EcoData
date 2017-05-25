package com.fnm.ecodata.BOs;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import io.realm.annotations.PrimaryKey;

/**
 * Created by MuhammadDev on 2/7/17.
 */

public class MarkerDataRealmBO extends RealmObject {

    @PrimaryKey
    String id;

    String slug;
    String title;
    String lat;
    String longi;
    String snipet;
    String citationInfoSearch;
    String simplifiedTitle;
    String accessRight;
    String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getSnipet() {
        return snipet;
    }

    public void setSnipet(String snipet) {
        this.snipet = snipet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getCitationInfoSearch() {
        return citationInfoSearch;
    }

    public void setCitationInfoSearch(String citationInfoSearch) {
        this.citationInfoSearch = citationInfoSearch;
    }

    public String getSimplifiedTitle() {
        return simplifiedTitle;
    }

    public void setSimplifiedTitle(String simplifiedTitle) {
        this.simplifiedTitle = simplifiedTitle;
    }

    public String getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(String accessRight) {
        this.accessRight = accessRight;
    }

    public static MarkerDataRealmBO getMarkerData(Realm realm, String id) {
        return realm.where(MarkerDataRealmBO.class).equalTo("id", id).findFirst();
    }

    public static RealmResults<MarkerDataRealmBO> getMarkerDataForURL(Realm realm, String URL) {
        return realm.where(MarkerDataRealmBO.class).equalTo("url", URL).findAll();
    }

    public static RealmResults<MarkerDataRealmBO> getMarkerData(Realm realm) {
        return realm.where(MarkerDataRealmBO.class).findAll();
    }
}
