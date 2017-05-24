package com.fnm.ecodata.map;

import com.fnm.ecodata.R;
import com.fnm.ecodata.category.CategoryContent;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MuhammadMahmoor on 5/4/17.
 */

public class StatesBound {

    public static final HashMap<String, LatLngBounds> boundsMap = new HashMap<>();

    static {
        // Add some sample items.
        addItem("Ashmore and Cartier Islands", new LatLngBounds(new LatLng(-12.5328591, 122.9620769), new LatLng(-12.2394612,123.5588064)));
        addItem("Australian Antarctic Territory", new LatLngBounds(new LatLng(-90, -180), new LatLng(-60.1086999,180)));
        addItem("Australian Capital Territory", new LatLngBounds(new LatLng(-35.9207621, 148.7626752), new LatLng(-35.124517,149.3992845)));
        addItem("Cocos (Keeling) Islands", new LatLngBounds(new LatLng(-12.2118513, 96.8134118), new LatLng(-11.819973,96.93271639999999)));
        addItem("Coral Sea Islands", new LatLngBounds(new LatLng(-20.3369991, 148.9511539), new LatLng(-20.3340649,148.9538743)));
        addItem("Heard Island and McDonald Islands", new LatLngBounds(new LatLng(-53.19168759999999, 73.25065599999999), new LatLng(-52.9609444,73.77920159999999)));
        addItem("Jervis Bay Territory", new LatLngBounds(new LatLng(-35.2120291, 150.5913551), new LatLng(-35.1121876,150.7769123)));
        addItem("New South Wales", new LatLngBounds(new LatLng(-37.5052801, 140.9992793), new LatLng(-28.15702,159.1054441)));
        addItem("Norfolk Island", new LatLngBounds(new LatLng(-29.137506, 167.9134083), new LatLng(-28.9929014,167.9985523)));
        addItem("Northern Territory", new LatLngBounds(new LatLng(-25.9986183, 129.0004761), new LatLng(-10.809854,138.0011997)));
        addItem("Queensland", new LatLngBounds(new LatLng(-29.1778976, 137.9959572), new LatLng(-9.210063,153.6497891)));
        addItem("South Australia", new LatLngBounds(new LatLng(-38.1345913, 129.00134), new LatLng(-25.9963765,141.0029556)));
        addItem("Tasmania", new LatLngBounds(new LatLng(-44.0557135, 143.708067), new LatLng(-39.1296041,148.6166749)));
        addItem("Victoria", new LatLngBounds(new LatLng(-39.18316069999999, 140.9616819), new LatLng(-33.9806474,150.0169685)));
        addItem("Western Australia", new LatLngBounds(new LatLng(-35.2132016, 112.7604507), new LatLng(-13.6105008,129.0018535)));


        //addItem(createCategoryItem(R.string.parcel_type_laundry, R.drawable.type_laundry, ItemType.LAUNDRY));
    }

    private static void addItem(String key, LatLngBounds latLng) {
        boundsMap.put(key, latLng);
    }
}
