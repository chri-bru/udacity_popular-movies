package android.chribru.dev.popularmovies.data;

import android.chribru.dev.popularmovies.BuildConfig;

public class Constants {

    /**
     * Key used to retrieve data from The Movie API
     */
    public static final String API_KEY = BuildConfig.THE_MOVIE_DB_API_TOKEN;


    /*
     * Constant for putting a movie extra
     */
    public static final String MOVIE_PARCELABLE = "MOVIE";
    /*
     * Constant for putting a movie extra
     */
    public static final String MOVIE_ID_PARCELABLE = "MOVIE_ID";

    /*
     * Constant for putting a results extra
     */
    public static final String RESULTS_PARCELABLE = "RESULTS";

    /*
     * Constant for putting a results extra
     */
    public static final String ACTIVITY_LABEL = "ACTIVITY_LABEL";
}
