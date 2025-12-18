package richard.feldtz.draft_pool.dto;

import java.util.List;

public class ScoreboardResponse {

    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public static class Event {
        private List<Competition> competitions;

        public List<Competition> getCompetitions() {
            return competitions;
        }
    }

    public static class Competition {
        private Status status;
        private List<Competitor> competitors;

        public Status getStatus() {
            return status;
        }

        public List<Competitor> getCompetitors() {
            return competitors;
        }
    }

    public static class Status {
        private Type type;

        public Type getType() {
            return type;
        }
    }

    public static class Type {
        private String name; // STATUS_FINAL

        public String getName() {
            return name;
        }
    }

    public static class Competitor {
        private String homeAway;
        private String score;
        private Team team;

        public String getHomeAway() {
            return homeAway;
        }

        public String getScore() {
            return score;
        }

        public Team getTeam() {
            return team;
        }
    }

    public static class Team {
        private String displayName;

        public String getDisplayName() {
            return displayName;
        }
    }
}
