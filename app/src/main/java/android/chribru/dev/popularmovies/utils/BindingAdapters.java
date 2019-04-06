package android.chribru.dev.popularmovies.utils;

import android.chribru.dev.popularmovies.R;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter({"bind:url", "bind:size"})
    public static void setImage(ImageView view, String url, String size) {
        String resolvedUrl = TheMoviePathResolver.getUrl(url, size);
        Glide.with(view.getContext())
                .load(resolvedUrl)
                .placeholder(R.drawable.ic_img_placeholder)
                .into(view);
    }
}
