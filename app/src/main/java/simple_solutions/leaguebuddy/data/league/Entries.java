package simple_solutions.leaguebuddy.data.league;

/**
 * Created by Brian Mote on 12/9/2016.
 */

public class Entries {
    private String leaguePoints;

    private String isHotStreak;

    private String isFreshBlood;

    private String division;

    private String isInactive;

    private String isVeteran;

    private String losses;

    private String playerOrTeamName;

    private String playerOrTeamId;

    private String wins;

    public String getLeaguePoints() {
        return leaguePoints;
    }

    public void setLeaguePoints(String leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public String getIsHotStreak() {
        return isHotStreak;
    }

    public void setIsHotStreak(String isHotStreak) {
        this.isHotStreak = isHotStreak;
    }

    public String getIsFreshBlood() {
        return isFreshBlood;
    }

    public void setIsFreshBlood(String isFreshBlood) {
        this.isFreshBlood = isFreshBlood;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getIsInactive() {
        return isInactive;
    }

    public void setIsInactive(String isInactive) {
        this.isInactive = isInactive;
    }

    public String getIsVeteran() {
        return isVeteran;
    }

    public void setIsVeteran(String isVeteran) {
        this.isVeteran = isVeteran;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getPlayerOrTeamName() {
        return playerOrTeamName;
    }

    public void setPlayerOrTeamName(String playerOrTeamName) {
        this.playerOrTeamName = playerOrTeamName;
    }

    public String getPlayerOrTeamId() {
        return playerOrTeamId;
    }

    public void setPlayerOrTeamId(String playerOrTeamId) {
        this.playerOrTeamId = playerOrTeamId;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return "ClassPojo [leaguePoints = " + leaguePoints + ", isHotStreak = " +
                isHotStreak + ", isFreshBlood = " + isFreshBlood + ", division = " +
                division + ", isInactive = " + isInactive + ", isVeteran = " + isVeteran +
                ", losses = " + losses + ", playerOrTeamName = " + playerOrTeamName + "," +
                " playerOrTeamId = " + playerOrTeamId + ", wins = " + wins + "]";
    }
}
