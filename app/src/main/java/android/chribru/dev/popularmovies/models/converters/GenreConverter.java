package android.chribru.dev.popularmovies.models.converters;

import android.chribru.dev.popularmovies.models.dto.GenreDto;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.room.TypeConverter;

public class GenreConverter {

    @TypeConverter
    public static List<GenreDto> fromString(String value) {
        Type type = new TypeToken<List<GenreDto>>() {}.getType();
        return new Gson().fromJson(value, type);
    }

    @TypeConverter
    public static String fromList(List<GenreDto> list) {
        return new Gson().toJson(list);
    }
}
