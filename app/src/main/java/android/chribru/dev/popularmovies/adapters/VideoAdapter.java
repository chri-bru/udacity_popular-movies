package android.chribru.dev.popularmovies.adapters;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.interfaces.VideoOnClickHandler;
import android.chribru.dev.popularmovies.models.Video;
import android.chribru.dev.popularmovies.models.VideoResults;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoCellViewHolder> {

    private VideoResults results;

    private final VideoOnClickHandler onClickHandler;
    private final Context context;

    public VideoAdapter(VideoOnClickHandler handler, Context context) {
        this.onClickHandler = handler;
        this.context = context;
    }

    @NonNull
    @Override
    public VideoCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_cell, parent, false);
        return new VideoCellViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoCellViewHolder holder, int position) {
        Video video = results.getVideos().get(position);
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.getVideos().size();
    }

    public void setResults(VideoResults results) {
        this.results = results;
    }

    public class VideoCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        VideoCellViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Video video = results.getVideos().get(getAdapterPosition());
            onClickHandler.onClick(video);
        }
    }

}
