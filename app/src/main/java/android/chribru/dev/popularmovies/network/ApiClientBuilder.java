package android.chribru.dev.popularmovies.network;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientBuilder {

    private static final String BASE_URL = "https://api.themoviedb.org/";
    private static Retrofit retrofit;
    private static ApiClientBuilder builder;

    private ApiClientBuilder(){}

    public static ApiClientBuilder getBuilder() {
        if (builder == null) {
            builder = new ApiClientBuilder();
        }

        return builder;
    }

    /**
     * Generates a default Retrofit client
     * @return a Retrofit client
     */
    public Retrofit getDefaultClient() {
        OkHttpClient client = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    /**
     * Generates a Retrofit client that uses an
     * application interceptor to add the specified api key
     * to each request
     * @param apiKey the API key to append to each request
     * @return a Retrofit client
     */
    public Retrofit getClientWithApikey(String apiKey) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor(apiKey))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

    /**
     * Custom interceptor for adding the api key query parameter
     */
    private class ApiKeyInterceptor implements Interceptor {
        private String apiKey;

        public ApiKeyInterceptor(String key) {
            apiKey = key;
        }

        /**
         * Intercepts the request to add the API key to it
         */
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("api_key", apiKey).build();

            request = request.newBuilder().url(url).build();

            return chain.proceed(request);
        }
    }
}
