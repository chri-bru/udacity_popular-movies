package android.chribru.dev.popularmovies.persistance;

import android.chribru.dev.popularmovies.models.dto.MovieDto;

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
    void insert(MovieDto movieDto);

    @Delete
    void delete(MovieDto movieDto);

    @Query("SELECT * FROM MovieDto WHERE id == :movieId")
    LiveData<MovieDto> getMovie(int movieId);

    @Query("SELECT * FROM MovieDto ORDER BY title ASC")
    LiveData<List<MovieDto>> getAllMovies();
}
