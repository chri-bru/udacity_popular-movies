package android.chribru.dev.popularmovies.utils;

public class TheMoviePathResolver {

    private static final String BASE_IMAGE_URL = "http://image.tmdb.org/t/p/";

    public static final String SIZE_W92 = "w92";
    public static final String SIZE_W154 = "w154";
    public static final String SIZE_W185 = "w185"; // recommended
    public static final String SIZE_W342 = "w342";
    public static final String SIZE_W500 = "w500";
    public static final String SIZE_W780 = "w780";
    public static final String SIZE_ORIGINAL = "original";

    public static String getUrl(String relativePath, String size) {
        if (!relativePath.startsWith("/")) {
            relativePath = "/" + relativePath;
        }
        return BASE_IMAGE_URL + size + relativePath;
    }
}
