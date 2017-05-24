package com.fnm.ecodata.category;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.fnm.ecodata.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dev on 19/2/2016.
 */
public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListViewHolder> {
    Context context;
    View view = null;
    private CategoryListAdapterInterface parentNotifier;
    private final List<CategoryContent.CategoryItem> mValues;
    private final List<CategoryContent.CategoryItem> mValuesClone;

    public CategoryListAdapter(Context activity, List<CategoryContent.CategoryItem> items, CategoryListAdapterInterface parentNotifier) {
         context = activity;
        this.mValues = items;
        this.parentNotifier = parentNotifier;
        this.mValuesClone = new ArrayList<>();
        this.mValuesClone.addAll(mValues);
    }

    @Override
    public CategoryListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        view = LayoutInflater.from(context).inflate(R.layout.adapter_category_list_item, parent, false);
        return CategoryListViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(CategoryListViewHolder viewHolder, int position) {
        CategoryListViewHolder holder = (CategoryListViewHolder) viewHolder;
        CategoryContent.CategoryItem category = mValues.get(position);
        holder.titleTV.setText(category.title);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(category.imageSrcId).build();
        holder.imageView.setImageURI(imageRequest.getSourceUri());
        holder.parent.setTag(context.getResources().getString(category.title));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentNotifier.showSearchResult((String) view.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mValues.clear();
        if (charText.length() == 0) {
            mValues.addAll(mValuesClone);
        }
        else
        {
            for (CategoryContent.CategoryItem wp : mValuesClone)
            {
                String title = context.getString(wp.title);
                if (title.toLowerCase(Locale.getDefault()).contains(charText))
                {
                    mValues.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}