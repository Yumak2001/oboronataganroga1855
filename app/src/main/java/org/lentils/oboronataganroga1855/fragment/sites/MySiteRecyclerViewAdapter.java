package org.lentils.oboronataganroga1855.fragment.sites;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.lentils.oboronataganroga1855.R;
import org.lentils.oboronataganroga1855.model.SiteContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MySiteRecyclerViewAdapter extends RecyclerView.Adapter<MySiteRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;

    public MySiteRecyclerViewAdapter(List<DummyItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_site, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mHeaderTitle.setText(mValues.get(position).title);
        holder.mHeaderData.setText(mValues.get(position).data);
        holder.mHeaderTime.setText(mValues.get(position).time);
        holder.mDescription.setText(mValues.get(position).description);
        holder.mLocation.setText(mValues.get(position).location);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mHeaderTitle;
        public final TextView mHeaderData;
        public final TextView mHeaderTime;
        public final TextView mDescription;
        public final TextView mLocation;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mHeaderTitle = (TextView) view.findViewById(R.id.site_header_title);
            mHeaderData = (TextView) view.findViewById(R.id.site_header_data);
            mHeaderTime = (TextView) view.findViewById(R.id.site_header_time);
            mDescription = (TextView) view.findViewById(R.id.site_description);
            mLocation = (TextView) view.findViewById(R.id.site_location);
        }
    }
}