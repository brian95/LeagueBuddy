package simple_solutions.leaguebuddy.data.league;

/**
 * Created by Brian Mote on 12/9/2016.
 */

public class LeagueContainer {
    private String queue;

    private String name;

    private String tier;

    private Entries[] entries;

    public String getQueue ()
    {
        return queue;
    }

    public void setQueue (String queue)
    {
        this.queue = queue;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getTier ()
    {
        return tier;
    }

    public void setTier (String tier)
    {
        this.tier = tier;
    }

    public Entries[] getEntries ()
    {
        return entries;
    }

    public void setEntries (Entries[] entries)
    {
        this.entries = entries;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [queue = "+queue+", name = "+name+", tier = "+tier+", entries = "+entries+"]";
    }
}
