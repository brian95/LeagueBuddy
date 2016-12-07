package simple_solutions.leaguebuddy.lolapi.data;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Brian Mote on 12/6/2016.
 */

/**
 * POJO Class that can only be instantiated through the {@link SummonerBuilder} build method.
 */
public class Summoner {
    private String name;
    private int id;
    private int profileIconId;
    private int summonerLevel;
    private int revisionDate;


    private Summoner() {
        //Must use the SummonerBuilder
    }

    /**
     * @param builder The {@link SummonerBuilder} to be used.
     */
    public Summoner(SummonerBuilder builder) {
        this.name = builder.name;
        this.id = builder.id;
        this.profileIconId = builder.profileIconId;
        this.summonerLevel = builder.summonerLevel;
        this.revisionDate = builder.revisionDate;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getProfileIconId() {
        return profileIconId;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public Integer getRevisionDate() {
        return revisionDate;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(name).append("\n");
        sb.append("Id: ").append(id).append("\n");
        sb.append("Profile Icon Id: ").append(profileIconId).append("\n");
        sb.append("Summoner Level: ").append(summonerLevel).append("\n");
        sb.append("Revision Date: ").append(revisionDate).append("\n");
        return sb.toString();
    }

    /**
     * Must use this class and this class only to create a new {@link Summoner} instance.
     */
    public static class SummonerBuilder {
        private String name;
        private int id;
        private int profileIconId;
        private int summonerLevel;
        private int revisionDate;

        public SummonerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SummonerBuilder id(int id) {
            this.id = id;
            return this;
        }

        public SummonerBuilder profileIconId(int profileIconId) {
            this.profileIconId = profileIconId;
            return this;
        }


        public SummonerBuilder summonerLevel(int summonerLevel) {
            this.summonerLevel = summonerLevel;
            return this;
        }

        public SummonerBuilder revisionDate(int revisionDate) {
            this.revisionDate = revisionDate;
            return this;
        }

        public Summoner build() {
            validate();
            return new Summoner(this);
        }

        private void validate() {
            String msg = "Must set the Summoner's ";
            if (name == null) {
                msg += "name";
                throw new IllegalStateException(msg);
            } else if (id == 0) {
                msg += "id";
                throw new IllegalStateException(msg);
            } else if (profileIconId == 0) {
                msg += "profileIcondId";
                throw new IllegalStateException(msg);
            } else if (summonerLevel == 0) {
                msg += "summonerLevel";
                throw new IllegalStateException(msg);
            } else if (revisionDate == 0) {
                msg+= "revisionDate";
                throw new IllegalStateException(msg);
            }
        }
    }
}
