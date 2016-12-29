package simple_solutions.leaguebuddy.lolapi.tasks;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import rx.Observable;
import rx.functions.Func1;
import simple_solutions.leaguebuddy.data.summoner.Summoner;
import simple_solutions.leaguebuddy.data.game.RecentGamesDTO;


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
    public SummonerApiRequest(String name) {
        super(name);
        this.name = name;
    }

    /**
     * Retrieves a {@link Summoner} object from the League of Legends Api on a new {@link Thread}
     *
     * @return Instance of {@link Observable<Summoner>}
     */
    @Override
    public Observable<Summoner> makeRequest() {
        return Observable.fromCallable(() -> {
            String url = makeUrlString(ApiMethod.GET_BY_NAME, name);
            String json = getJson(url);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject data = jsonObject.getJSONObject(name);
            Gson gson = new Gson();
            return gson.fromJson(data.toString(), Summoner.class);
        });
    }

    public Observable<RecentGamesDTO> getGameStuff() {
        return makeRequest()
                .flatMap(new Func1<Summoner, Observable<RecentGamesDTO>>() {
                    @Override
                    public Observable<RecentGamesDTO> call(Summoner summoner) {
                        String url = "https://na.api.pvp.net/api/lol/na/v1.3/game/by-summoner/"
                                + summoner.getId() + "/recent" + API_KEY;
                        String json = getJson(url);
                        Gson gson = new Gson();
                        RecentGamesDTO games = gson.fromJson(json, new TypeToken<RecentGamesDTO>() {
                        }.getType());
                        return Observable.just(games);
                    }
                });
    }

}
