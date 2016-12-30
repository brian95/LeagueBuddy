package simple_solutions.leaguebuddy.lolapi.tasks;

/**
 * Created by Brian Mote on 12/30/2016.
 */

enum ApiResponseCode {
    OK("Success"),
    BAD_REQUEST("Bad Request"),
    UNAUTHORIZED("Unauthorized access"),
    NO_DATA_FOUND("No data found"),
    RATE_LIMIT_EXCEEDED("Exceeded the rate limit, try again later"),
    INTERNAL_SERVER_ERRROR("Internal server error"),
    SERVICE_UNAVAILABLE("Service unavailable");

    private String message;

    ApiResponseCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
