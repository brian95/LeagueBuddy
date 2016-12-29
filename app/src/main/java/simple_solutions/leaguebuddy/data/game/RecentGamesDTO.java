package simple_solutions.leaguebuddy.data.game;

/**
 * Created by Brian Mote on 12/8/2016.
 */

public class RecentGamesDTO {
    private Games[] games;

    private String summonerId;

    public Games[] getGames ()
    {
        return games;
    }

    public void setGames (Games[] games)
    {
        this.games = games;
    }

    public String getSummonerId ()
    {
        return summonerId;
    }

    public void setSummonerId (String summonerId)
    {
        this.summonerId = summonerId;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [games = "+games+", summonerId = "+summonerId+"]";
    }
}
