package android.chribru.dev.popularmovies.adapters;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.interfaces.OverviewAdapterOnClickHandler;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.Results;
import android.chribru.dev.popularmovies.utils.TheMoviePathResolver;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.net.URI;

public class OverviewAdapter extends RecyclerView.Adapter<OverviewAdapter.CellViewHolder> {

    private Results results;

    private OverviewAdapterOnClickHandler onClickHandler;
    private Context context;

    public OverviewAdapter(OverviewAdapterOnClickHandler handler, Context context) {
        this.onClickHandler = handler;
        this.context = context;
    }

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.overview_cell, viewGroup, false);
        return new CellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CellViewHolder cellViewHolder, int i) {
        Movie movie = results.getMovies().get(i);
        String uri = TheMoviePathResolver.getUrl(movie.getPosterPath(), TheMoviePathResolver.SIZE_W154);
        Glide.with(context)
                .load(uri)
                .placeholder(R.drawable.ic_img_placeholder)
                .into(cellViewHolder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.getMovies().size();
    }

    public void setResults(Results results) {
        this.results = results;
        notifyDataSetChanged();
    }

    public class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView thumbnail;

        public CellViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.iv_overview_thumbnail);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Movie movie = results.getMovies().get(getAdapterPosition());
            onClickHandler.onClick(movie);
        }
    }
}
