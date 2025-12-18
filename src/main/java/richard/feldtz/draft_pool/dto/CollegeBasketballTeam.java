package richard.feldtz.draft_pool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
public class CollegeBasketballTeam {

    private String id;
    private String name;
    private boolean tournamentTeam;
    private int[] pointsByRound;
    private String pickedByName;

    public CollegeBasketballTeam(String id, String name) {
        this.id = id;
        this.name = name;
        this.tournamentTeam = false;
        pointsByRound = new int[6];
    }

    public int calculateTotalPoints() {
        return Arrays.stream(pointsByRound).sum();
    }
}
