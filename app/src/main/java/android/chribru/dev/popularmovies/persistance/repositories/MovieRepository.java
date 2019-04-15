package android.chribru.dev.popularmovies.persistance.repositories;

import android.app.Application;
import android.chribru.dev.popularmovies.models.dto.MovieDto;
import android.chribru.dev.popularmovies.models.dto.MovieResultsDto;
import android.chribru.dev.popularmovies.models.dto.ReviewResultsDto;
import android.chribru.dev.popularmovies.models.dto.VideoResultsDto;
import android.chribru.dev.popularmovies.network.MovieClient;
import android.chribru.dev.popularmovies.persistance.MovieDao;
import android.chribru.dev.popularmovies.persistance.MovieRoomDatabase;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.MovieCallbackHandler;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.MovieResultsCallbackHandler;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.ReviewsCallbackHandler;
import android.chribru.dev.popularmovies.persistance.repositories.handlers.VideosCallbackHandler;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class MovieRepository {

    // data sources
    private MovieClient client;
    private MovieDao movieDao;

    public MovieRepository(Application application) {
        client = new MovieClient();
        MovieRoomDatabase db = MovieRoomDatabase.getInstance(application);
        movieDao = db.movieDao();
    }

    public LiveData<MovieDto> getMovieDetails(int id) {
        final MutableLiveData<MovieDto> data = new MutableLiveData<>();

        LiveData<MovieDto> movie = movieDao.getMovie(id);
        movie.observeForever(movie1 -> {
            if (movie1 == null) {
                client.getMovieDetails(id).enqueue(new MovieCallbackHandler(data));
            } else {
                data.setValue(movie1);
            }
        });

        // favorited movies are already persisted
        return Transformations.map(data, input -> {
                    if (input != null && !input.getFavorited()) {
                        insertMovie(input);
                    }
                    return input;
                });
    }

    public LiveData<MovieResultsDto> getPopularMovies(int page) {
        final MutableLiveData<MovieResultsDto> data = new MutableLiveData<>();
        client.getPopularMovies(page).enqueue(new MovieResultsCallbackHandler(data));
        return data;
    }

    public LiveData<MovieResultsDto> getTopRatedMovies(int page) {
        final MutableLiveData<MovieResultsDto> data = new MutableLiveData<>();
        client.getTopRatedMovies(page).enqueue(new MovieResultsCallbackHandler(data));
        return data;
    }

    public LiveData<VideoResultsDto> getVideosForMovie(int id, String language) {
        final MutableLiveData<VideoResultsDto> data = new MutableLiveData<>();
        client.getVideosForMovie(id, language).enqueue(new VideosCallbackHandler(data));
        return data;
    }

    public LiveData<ReviewResultsDto> getReviewsForMovie(int id, int page) {
        final MutableLiveData<ReviewResultsDto> data = new MutableLiveData<>();
        client.getReviewsForMovie(id, page).enqueue(new ReviewsCallbackHandler(data));
        return data;
    }

    // Favorites
    public LiveData<List<MovieDto>> getFavorites() {
        return movieDao.getAllMovies();
    }

    public void insertToFavorites(MovieDto movieDto) {
        movieDto.setFavorited(true);
        new InsertAsyncTask(movieDao).execute(movieDto);
    }

    private void insertMovie(MovieDto movieDto) {
        movieDto.setFavorited(false);
        new InsertAsyncTask(movieDao).execute(movieDto);
    }

    private class InsertAsyncTask extends AsyncTask<MovieDto, Void, Void> {
        private MovieDao movieDao;

        InsertAsyncTask(MovieDao dao) {
            movieDao = dao;
        }

        @Override
        protected Void doInBackground(MovieDto... movieDtos) {
            movieDao.insert(movieDtos[0]);
            return null;
        }
    }

    public void deleteFromFavorites(MovieDto movieDto) {
        new DeleteAsysncTask(movieDao).execute(movieDto);
    }

    private class DeleteAsysncTask extends AsyncTask<MovieDto, Void, Void> {
        private MovieDao movieDao;

        DeleteAsysncTask(MovieDao dao) {
            movieDao = dao;
        }

        @Override
        protected Void doInBackground(MovieDto... movieDtos) {
            movieDao.delete(movieDtos[0]);
            return null;
        }
    }


}
