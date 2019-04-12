package android.chribru.dev.popularmovies.models.converters;

import android.chribru.dev.popularmovies.models.Genre;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class GenreConverter {

    @TypeConverter
    public static List<Genre> fromString(String value) {
        Type type = new TypeToken<List<Genre>>() {}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromList(List<Genre> list) {
        return new Gson().toJson(list);
    }
}
