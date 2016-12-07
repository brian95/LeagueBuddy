package simple_solutions.leaguebuddy.lolapi.tasks;

/**
 * Created by Brian Mote on 12/6/2016.
 */

public enum ApiMethod {
    GET_BY_NAME("v1.4/summoner/by-name/");

    private String value;

    ApiMethod(String value) {
        this.value = value;
    }

    public String getValue() {

        return value;
    }
}
