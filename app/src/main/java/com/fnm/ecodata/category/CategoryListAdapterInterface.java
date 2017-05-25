package com.fnm.ecodata.category;

import android.app.Activity;

public interface CategoryListAdapterInterface {
    public void showSearchResult(String catName);
    public void showMarkerDetail(String title, String description, String citation, String access, String slug);
}
