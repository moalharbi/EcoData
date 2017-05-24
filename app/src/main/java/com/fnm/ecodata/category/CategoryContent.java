package com.fnm.ecodata.category;

import com.fnm.ecodata.R;

import java.util.ArrayList;
import java.util.List;

// This class will be used populate categories
public class CategoryContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<CategoryItem> ITEMS = new ArrayList<CategoryItem>();

    static {
        // Add some sample items.
        addItem(createCategoryItem(R.string.cat_plant_animal, R.drawable.plants_animals));
        addItem(createCategoryItem(R.string.cat_vegitation, R.drawable.vegetation));
        addItem(createCategoryItem(R.string.cat_t_eco_system, R.drawable.ecosystems));
        addItem(createCategoryItem(R.string.cat_fresh_water, R.drawable.freshwater_processes));
        addItem(createCategoryItem(R.string.cat_soil, R.drawable.soils));
        addItem(createCategoryItem(R.string.cat_agriculture, R.drawable.agriculture));
        addItem(createCategoryItem(R.string.cat_ocean, R.drawable.oceans_processes));
        addItem(createCategoryItem(R.string.cat_climate, R.drawable.climate));
        addItem(createCategoryItem(R.string.cat_human_interaction, R.drawable.human_interactions));
        addItem(createCategoryItem(R.string.cat_energy, R.drawable.energy_water_and_gas_exchange));

        //addItem(createCategoryItem(R.string.parcel_type_laundry, R.drawable.type_laundry, ItemType.LAUNDRY));
    }

    private static void addItem(CategoryItem item) {
        ITEMS.add(item);
        // ITEM_MAP.put(item.title, item);
    }

    private static CategoryItem createCategoryItem(int title, int resourceId) {
        return new CategoryItem(title, resourceId);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class CategoryItem {
        public final int title;
        public final int imageSrcId;

        public CategoryItem(int title, int imageSrcId) {
            this.title = title;
            this.imageSrcId = imageSrcId;
        }
    }
}
