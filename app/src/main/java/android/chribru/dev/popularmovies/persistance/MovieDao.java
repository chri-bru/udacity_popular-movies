package android.chribru.dev.popularmovies.persistance;

import android.chribru.dev.popularmovies.models.Movie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movie_table WHERE id == :movieId")
    LiveData<Movie> getMovie(int movieId);

    @Query("SELECT * FROM movie_table ORDER BY title ASC")
    LiveData<List<Movie>> getAllMovies();
}
