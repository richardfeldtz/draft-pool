package richard.feldtz.draft_pool.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollegeBasketballTeam {

    private String id;
    private String name;
    private boolean tournamentTeam;
    private int[] pointsByRound;

    public CollegeBasketballTeam(String id, String name) {
        this.id = id;
        this.name = name;
        this.tournamentTeam = false;
        pointsByRound = new int[6];
    }
}
