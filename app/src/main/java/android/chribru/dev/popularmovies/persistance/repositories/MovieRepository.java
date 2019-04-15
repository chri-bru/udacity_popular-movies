package android.chribru.dev.popularmovies.persistance.repositories;

import android.app.Application;
import android.chribru.dev.popularmovies.models.Movie;
import android.chribru.dev.popularmovies.models.MovieResults;
import android.chribru.dev.popularmovies.models.ReviewResults;
import android.chribru.dev.popularmovies.models.VideoResults;
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

    public LiveData<Movie> getMovieDetails(int id) {
        final MutableLiveData<Movie> data = new MutableLiveData<>();

        LiveData<Movie> movie = movieDao.getMovie(id);
        movie.observeForever(movie1 -> {
            if (movie1 == null) {
                client.getMovieDetails(id).enqueue(new MovieCallbackHandler(data));
            } else {
                data.setValue(movie1);
            }
        });

        // favorited movies are already persisted
        return Transformations.map(data, input -> {
                    if (input != null && !input.getUserFavorite()) {
                        insertMovie(input);
                    }
                    return input;
                });
    }

    public LiveData<MovieResults> getPopularMovies() {
        final MutableLiveData<MovieResults> data = new MutableLiveData<>();
        client.getPopularMovies().enqueue(new MovieResultsCallbackHandler(data));
        return data;
    }

    public LiveData<MovieResults> getTopRatedMovies() {
        final MutableLiveData<MovieResults> data = new MutableLiveData<>();
        client.getTopRatedMovies().enqueue(new MovieResultsCallbackHandler(data));
        return data;
    }

    public LiveData<VideoResults> getVideosForMovie(int id, String language) {
        final MutableLiveData<VideoResults> data = new MutableLiveData<>();
        client.getVideosForMovie(id, language).enqueue(new VideosCallbackHandler(data));
        return data;
    }

    public LiveData<ReviewResults> getReviewsForMovie(int id, int page) {
        final MutableLiveData<ReviewResults> data = new MutableLiveData<>();
        client.getReviewsForMovie(id, page).enqueue(new ReviewsCallbackHandler(data));
        return data;
    }

    // Favorites
    public LiveData<List<Movie>> getFavorites() {
        return movieDao.getAllMovies();
    }

    public void insertToFavorites(Movie movie) {
        movie.setUserFavorite(true);
        new InsertAsyncTask(movieDao).execute(movie);
    }

    private void insertMovie(Movie movie) {
        movie.setUserFavorite(false);
        new InsertAsyncTask(movieDao).execute(movie);
    }

    private class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        InsertAsyncTask(MovieDao dao) {
            movieDao = dao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.insert(movies[0]);
            return null;
        }
    }

    public void deleteFromFavorites(Movie movie) {
        new DeleteAsysncTask(movieDao).execute(movie);
    }

    private class DeleteAsysncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDao movieDao;

        DeleteAsysncTask(MovieDao dao) {
            movieDao = dao;
        }

        @Override
        protected Void doInBackground(Movie... movies) {
            movieDao.delete(movies[0]);
            return null;
        }
    }


}
