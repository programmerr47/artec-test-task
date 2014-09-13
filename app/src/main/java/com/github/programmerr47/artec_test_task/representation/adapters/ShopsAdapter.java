package com.github.programmerr47.artec_test_task.representation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.programmerr47.artec_test_task.R;
import com.github.programmerr47.artec_test_task.api.objects.Shop;

import java.util.List;

/**
 * Simple realisation of {@link BindBaseAdapter} sharpened on flights.
 * Uses {@link com.github.programmerr47.artec_test_task.api.objects.Shop}
 * as items of list.
 *
 * @author Michael Spitsin
 * @since 2014-08-30
 */
public class ShopsAdapter extends BindBaseAdapter {
    private static final int FLIGHT_ITEM_ID = R.layout.shop_item;

    private Context mContext;
    private List<Shop> mItems;

    public ShopsAdapter(Context context, List<Shop> items) {
        if (context == null) {
            throw new IllegalArgumentException("Context must be not null");
        }

        if (items == null) {
            throw new IllegalArgumentException("Items must be not null");
        }

        mContext = context;
        mItems = items;
    }

    @Override
    protected View newView(ViewGroup parent, int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(FLIGHT_ITEM_ID, parent, false);
        ViewHolder holder = new ViewHolder();

        holder.distanceView = (TextView) view.findViewById(R.id.distanceView);
        holder.workTimeView = (TextView) view.findViewById(R.id.workTimeView);
        holder.streetView = (TextView) view.findViewById(R.id.streetView);

        view.setTag(holder);

        return view;
    }

    @Override
    protected void bindView(View view, int position) {
        ViewHolder holder = (ViewHolder) view.getTag();
        Shop shop = mItems.get(position);

        holder.streetView.setText(shop.getStreet());
        holder.workTimeView.setText(shop.getWorkTime());
        holder.distanceView.setText(String.format(mContext.getString(R.string.DISTANCE), shop.getDistance()));
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void refreshItems(List<Shop> items) {
        if (items == null) {
            throw new NullPointerException("Items must be not null");
        }

        mItems = items;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public TextView workTimeView;
        public TextView streetView;
        public TextView distanceView;
    }
}
