package com.fnm.ecodata.category;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fnm.ecodata.R;


/**
 * Created by Dev on 19/2/2016.
 */
public class CategoryListViewHolder extends RecyclerView.ViewHolder {

    View parent = null;
    public final TextView titleTV;
    public final SimpleDraweeView imageView;
    public final CardView cardView;

    public CategoryListViewHolder(final View parent, TextView titleTV, SimpleDraweeView imageView, CardView cardView) {
        super(parent);
        this.parent = parent;
        this.titleTV = titleTV;
        this.imageView = imageView;
        this.cardView = cardView;

    }

    public static CategoryListViewHolder newInstance(View parent) {

        SimpleDraweeView imageView = (SimpleDraweeView) parent.findViewById(R.id.medicineimage);
        TextView titleTV = (TextView) parent.findViewById(R.id.title);
        CardView cardView = (CardView) parent.findViewById(R.id.cardview);
        return new CategoryListViewHolder(parent, titleTV, imageView, cardView);
    }

}
