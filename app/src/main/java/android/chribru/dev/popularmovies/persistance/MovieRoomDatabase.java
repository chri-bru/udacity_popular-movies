package android.chribru.dev.popularmovies.persistance;

import android.chribru.dev.popularmovies.models.Movie;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    private static volatile MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieRoomDatabase.class, "movie_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract MovieDao movieDao();
}
