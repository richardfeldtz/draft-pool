package richard.feldtz.draft_pool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TournamentTeam extends CollegeBasketballTeam {

    private final String region;
    private final int seed;
    private int[] pointsByRound;

    public TournamentTeam(String region, int seed) {
        this.region = region;
        this.seed = seed;
        pointsByRound = new int[6];
    }
}
