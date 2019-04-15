package android.chribru.dev.popularmovies.adapters;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.interfaces.VideoOnClickHandler;
import android.chribru.dev.popularmovies.models.Video;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoCellViewHolder> {

    enum VideoType {
        TRAILER,
        TEASER,
        CLIP,
        FEATURETTE,
        NONE;

        static String getName(VideoType type) {
            switch (type) {
                case CLIP:
                    return "Clip";
                case TEASER:
                    return "Teaser";
                case TRAILER:
                    return "Trailer";
                case FEATURETTE:
                    return "Featurette";
            }
            return "";
        }

        static VideoType getType(String name) {
            if (name.equals("Clip")) {
                return CLIP;
            }
            if (name.equals("Teaser")) {
                return TEASER;
            }
            if (name.equals("Trailer")) {
                return TRAILER;
            }
            if (name.equals("Featurette")) {
                return FEATURETTE;
            }
            return NONE;
        }
    }

    private List<Video> videos;
    private Map<VideoType, List<Video>> countPerTypeMapping = new HashMap<>();

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
        Video video = this.videos.get(position);

        VideoType type = VideoType.getType(video.getType());
        int count =  this.countPerTypeMapping.get(type).indexOf(video);
        holder.title.setText(video.getType() + " " + count);
    }

    @Override
    public int getItemCount() {
        return this.videos == null ? 0 : this.videos.size();
    }

    public void setResults(List<Video> results) {
        this.videos = results;

        for (Video vid : this.videos) {
            List<Video> tempVideos;
            VideoType type = VideoType.getType(vid.getType());
            if (this.countPerTypeMapping != null && this.countPerTypeMapping.containsKey(type)) {
                tempVideos = this.countPerTypeMapping.get(type);
            } else {
                tempVideos = new ArrayList<>();
            }
            tempVideos.add(vid);
            this.countPerTypeMapping.put(type, tempVideos);
        }

        notifyDataSetChanged();
    }

    public class VideoCellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView title;

        VideoCellViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.video_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Video video = videos.get(getAdapterPosition());
            onClickHandler.onClick(video);
        }
    }

}
