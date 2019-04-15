package android.chribru.dev.popularmovies.utils;

import android.chribru.dev.popularmovies.R;
import android.chribru.dev.popularmovies.models.dto.GenreDto;
import android.chribru.dev.popularmovies.models.dto.MovieDto;
import android.content.Context;
import android.text.TextUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDetailsUtils {

    public static String transformRuntime(MovieDto movieDto, Context context) {
        if (movieDto == null) {
            return "";
        }
        return context.getString(R.string.movie_runtime, movieDto.getRuntime().toString());
    }

    public static String transformGenres(MovieDto movieDto) {
        if (movieDto == null) {
            return "";
        }
        return TextUtils.join(", ", getGenreNames(movieDto));
    }

    private static List<String> getGenreNames(MovieDto movieDto) {
        List<String> list = new ArrayList<>();

        for (GenreDto genreDto : movieDto.getGenreDtos()) {
            list.add(genreDto.getName());
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
