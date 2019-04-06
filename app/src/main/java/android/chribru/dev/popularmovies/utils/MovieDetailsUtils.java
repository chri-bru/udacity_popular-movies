package android.chribru.dev.popularmovies.utils;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.models.Genre;
import android.chribru.dev.popularmovies.models.Movie;
import android.content.Context;
import android.text.TextUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDetailsUtils {

    public static String transformRuntime(Movie movie, Context context) {
        if (movie == null) {
            return "";
        }
        return context.getString(R.string.movie_runtime, movie.getRuntime().toString());
    }

    public static String transformGenres(Movie movie) {
        if (movie == null) {
            return "";
        }
        return TextUtils.join(", ", getGenreNames(movie));
    }

    private static List<String> getGenreNames(Movie movie) {
        List<String> list = new ArrayList<>();

        for (Genre genre : movie.getGenres()) {
            list.add(genre.getName());
        }

        return list;
    }

    public static String transformDate(String date) {
        if (date == null || date.equals("")) {
            return "";
        }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = format.parse(date);
            return DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
         return "";
    }
}
