package android.chribru.dev.popularmovies.adapters;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.models.Review;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewCellViewHolder> {

    private ReviewResults results;
    private final Context context;

    public ReviewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_cell, parent, false);
        return new ReviewCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewCellViewHolder holder, int position) {
        Review review = results.getReviews().get(position);
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.getReviews().size();
    }

    public void setResults(ReviewResults results) {
        this.results = results;
    }

    public class ReviewCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ReviewCellViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
