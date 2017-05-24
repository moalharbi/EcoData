package com.fnm.ecodata.map;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.fnm.ecodata.BOs.MarkerDataRealmBO;
import com.fnm.ecodata.R;
import com.fnm.ecodata.category.CategoryContent;
import com.fnm.ecodata.category.CategoryListAdapterInterface;
import com.fnm.ecodata.category.CategoryListViewHolder;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Dev on 19/2/2016.
 */
public class DetailListAdapter extends RecyclerView.Adapter<DetailListViewHolder> {
    Context context;
    View view = null;
    private CategoryListAdapterInterface parentNotifier;
    private RealmResults<MarkerDataRealmBO> realmResults;

    public DetailListAdapter(Context activity, RealmResults<MarkerDataRealmBO> realmResults, CategoryListAdapterInterface parentNotifier) {
         context = activity;
        this.realmResults = realmResults;
        this.parentNotifier = parentNotifier;
    }

    @Override
    public DetailListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.adapter_description_list_item, parent, false);
        return DetailListViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(DetailListViewHolder viewHolder, int position) {
        DetailListViewHolder holder =  viewHolder;
        final MarkerDataRealmBO dataObj = realmResults.get(position);
        holder.titleNo.setText((position+1)+".");
        holder.titleTV.setText(dataObj.getTitle());
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentNotifier.showMarkerDetail(dataObj.getTitle(), dataObj.getSnipet(), dataObj.getCitationInfoSearch(), dataObj.getSimplifiedTitle(), dataObj.getSlug());
            }
        });
    }

//    @Override
//    public DetailListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(DetailListViewHolder holder, int position) {
//
//    }

    @Override
    public int getItemCount() {
        return realmResults == null ? 0 : realmResults.size();
    }

}