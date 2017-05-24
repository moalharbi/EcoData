package com.fnm.ecodata.map;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fnm.ecodata.R;


/**
 * Created by Dev on 19/2/2016.
 */
public class DetailListViewHolder extends RecyclerView.ViewHolder {

    View parent = null;
    public final TextView titleTV, titleNo;
    public final CardView cardView;

    public DetailListViewHolder(final View parent, TextView titleTV, TextView titleNo, CardView cardView) {
        super(parent);
        this.parent = parent;
        this.titleTV = titleTV;
        this.titleNo = titleNo;
        this.cardView = cardView;

    }

    public static DetailListViewHolder newInstance(View parent) {

        TextView titleTV = (TextView) parent.findViewById(R.id.title);
        TextView titleNo = (TextView) parent.findViewById(R.id.title_no);
        CardView cardView = (CardView) parent.findViewById(R.id.cardview);
        return new DetailListViewHolder(parent, titleTV, titleNo, cardView);
    }

}
