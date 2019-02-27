package android.chribru.dev.popularmovies.adapters;

import android.chribru.dev.popularmovies.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.CellViewHolder> {

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.overview_cell, viewGroup, false);
        return new CellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellViewHolder cellViewHolder, int i) {
        return;
    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public class CellViewHolder extends RecyclerView.ViewHolder {

        public CellViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
