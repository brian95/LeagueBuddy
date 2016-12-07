package simple_solutions.leaguebuddy.lolapi.tasks;

/**
 * Created by Brian Mote on 12/6/2016.
 */

/**
 * Use this Class to create a new instance of {@link ApiRequest} implementation.
 *
 */
public class ApiRequestFactory {

    private ApiRequestFactory() {
        //No instance.
    }

    /**
     * Returns an implementation of {@link ApiRequest} based on which method is called.
     *
     * @param method The {@link ApiMethod} to be used.
     * @param params The paramaters to pass into the {@link ApiMethod}
     * @return Concrete implementation of {@link ApiRequest}
     */
    public static ApiRequest createRequest(ApiMethod method, String params) {
        switch (method) {
            case GET_BY_NAME:
                return new SummonerApiRequest(params);
            default:
                return null;
        }
    }
}
