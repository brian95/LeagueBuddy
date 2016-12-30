package simple_solutions.leaguebuddy.lolapi.tasks;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import simple_solutions.leaguebuddy.data.summoner.Summoner;

/**
 * Extend this class to make requests to the League of Legends Api.<br>
 *
 * @param <T> The {@link Object} to retrieve from the Api.
 * @author Brian Mote
 */
abstract class ApiRequest<T> {
    private static final String TAG = ApiRequest.class.getSimpleName();
    private static final String BASE_URL = "https://na.api.pvp.net/api/lol/na/";
    private static final String API_KEY = "?api_key=a727a24e-341d-4247-b065-9f9f38ae2913";
    private static final String ENTRY = "/entry";
    private String name;

    ApiRequest(String name) {
        this.name = name;
    }

    public abstract Observable<T> makeRequest();

    /**
     * @return Returns an {@link Observable} that emits a {@link Summoner} object.
     */
    Observable<Summoner> getByName() {
        return Observable.fromCallable(() -> {
            String url = makeUrlString(ApiMethod.GET_BY_NAME, name);
            String json = getJson(url);
            Gson gson = new Gson();
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject(name.toLowerCase());
            return gson.fromJson(data.toString(), Summoner.class);
        });
    }

    /**
     * Returns a {@link String} that contains the a Url to the League of Legends Api.
     * <br>
     * The {@link String} is created by using a {@link StringBuilder} to append the appropriate
     * Strings together that forms the final Url. Example:
     * <br>
     * {@code append(BASE_URL)}<br>
     * {@code append(ApiMethod.getValue())}<br>
     * {@code append(params)}<br>
     * {@code append(API_KEY)}
     *
     * @param apiMethod The {@link ApiMethod} to request.
     * @param params    The parameters for the given method.
     * @return {@link String} containing the Url.
     **/
    String makeUrlString(ApiMethod apiMethod, String params) {
        String urlParams = params.replaceAll(" ", "%20").toLowerCase();
        StringBuilder sb = new StringBuilder();
        sb.append(BASE_URL);
        sb.append(apiMethod.getValue());
        sb.append(urlParams);
        if (apiMethod == ApiMethod.GET_LEAGUE_BY_ID) {
            sb.append(ENTRY);
        }
        sb.append(API_KEY);
        Log.d(TAG, "Created Url: " + sb.toString());
        return sb.toString();
    }

    /**
     * Added in initial build.<br>
     * <br>
     * Returns a String with the JSON data from the given URL.
     *
     * @param urlString Url containing JSON data.
     * @return String containing JSON data.
     **/
    String getJson(@NonNull String urlString) {
        URL url;
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        InputStream in = null;
        StringBuilder sb = new StringBuilder(); //Will be used to store the JSON String.
        String line; //Used while reading the InputStream.
        try {
            //Create URL from the String given.
            url = new URL(urlString);
            //Connecting to the URL given.
            conn = (HttpURLConnection) url.openConnection();
            Log.d(TAG, "Connecting to the given Url");
            Log.d(TAG, "Response code: " + getResponse(conn.getResponseCode()).getMessage());
            try {
                //Get the Connection InputStream.
                in = conn.getInputStream();
                //Create a reader from the InputStream.
                reader = new BufferedReader(new InputStreamReader(in));
                //Reading the InputStream and appending to the StringBuilder.
                Log.d(TAG, "Reading the response");
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (FileNotFoundException e) {
                Log.d(TAG, e.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Closing the Connection if open.
            if (conn != null) {
                Log.d(TAG, "Closing connections");
                conn.disconnect();
            }

            //Closing the InputStream if open.
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Closing the Reader if open.
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d(TAG, "All connections closed");
        }
        return sb.toString();
    }

    private ApiResponseCode getResponse(int code) {
        ApiResponseCode response = null;
        switch (code) {
            case 200:
                response = ApiResponseCode.OK;
                break;
            case 400:
                response = ApiResponseCode.BAD_REQUEST;
                break;
            case 401:
                response = ApiResponseCode.UNAUTHORIZED;
                break;
            case 404:
                response = ApiResponseCode.NO_DATA_FOUND;
                break;
            case 429:
                response = ApiResponseCode.RATE_LIMIT_EXCEEDED;
                break;
            case 500:
                response = ApiResponseCode.INTERNAL_SERVER_ERRROR;
                break;
            case 503:
                response = ApiResponseCode.SERVICE_UNAVAILABLE;
                break;
        }
        return response;
    }
}