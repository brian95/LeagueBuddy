package simple_solutions.leaguebuddy.lolapi.tasks;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import rx.Observable;
import rx.functions.Func1;
import simple_solutions.leaguebuddy.data.league.League;
import simple_solutions.leaguebuddy.data.summoner.Summoner;

import static android.content.ContentValues.TAG;

/**
 * Created by Brian Mote on 12/8/2016.
 */

public class LeagueApiRequest extends ApiRequest {
    private String name;

    public LeagueApiRequest(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public Observable<League> makeRequest() {
        return getByName().flatMap(new Func1<Summoner, Observable<League>>() {
            @Override
            public Observable<League> call(Summoner summoner) {
                String url = makeUrlString(ApiMethod.GET_LEAGUE_BY_ID,
                        summoner.getId());
                String json = getJson(url);
                Gson gson = new Gson();
                JSONArray data = null;
                String result = null;
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    data = jsonObject.getJSONArray(summoner.getId());
                    result = data.getJSONObject(0).toString();
                    Log.d(TAG, data.getJSONObject(0).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                League league = gson.fromJson(result, League.class);
                return Observable.just(league);
            }
        });
    }

}
