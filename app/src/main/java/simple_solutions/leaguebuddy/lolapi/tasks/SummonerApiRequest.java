package simple_solutions.leaguebuddy.lolapi.tasks;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.concurrent.Callable;

import rx.Observable;
import simple_solutions.leaguebuddy.lolapi.data.Summoner;

/**
 * Concrete implementation of {@link ApiRequest} to retrieve a {@link Summoner} from the League of
 * Legends Api.
 *
 * @author Brian Mote
 */
public class SummonerApiRequest extends ApiRequest<Summoner> {
    private String name;

    /**
     * @param name The name of the {@link Summoner} to lookup.
     */
    protected SummonerApiRequest(String name) {
        this.name = name;
    }

    /**
     * Retrieves a {@link Summoner} object from the League of Legends Api on a new {@link Thread}
     *
     * @return Instance of {@link Observable<Summoner>}
     */
    @Override
    public Observable<Summoner> makeRequest() {
        return Observable.fromCallable(new Callable<Summoner>() {
            @Override
            public Summoner call() throws Exception {
                String url = makeUrlString(ApiMethod.GET_BY_NAME, name);
                String json = getJson(url);
                JSONObject jsonObject = new JSONObject(json);
                JSONObject summ = jsonObject.getJSONObject(name);
                return new Summoner.SummonerBuilder()
                        .name(summ.getString("name"))
                        .id(summ.getInt("id"))
                        .profileIconId(summ.getInt("profileIconId"))
                        .summonerLevel(summ.getInt("summonerLevel"))
                        .revisionDate(summ.getInt("revisionDate"))
                        .build();
            }
        });
    }


}
