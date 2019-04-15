package android.chribru.dev.popularmovies.adapters;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.models.Review;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewCellViewHolder> {

    private List<Review> reviews;
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
        Review review = this.reviews.get(position);
        holder.reviewText.setText(review.getContent());
        holder.author.setText(review.getAuthor());
    }

    @Override
    public int getItemCount() {
        return this.reviews == null ? 0 : this.reviews.size();
    }

    public void setResults(List<Review> results) {
        this.reviews = results;
        notifyDataSetChanged();
    }

    public class ReviewCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView author;
        private final TextView reviewText;

        ReviewCellViewHolder(@NonNull View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.review_author);
            reviewText = itemView.findViewById(R.id.review_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // don't do anything for now
        }
    }
}
