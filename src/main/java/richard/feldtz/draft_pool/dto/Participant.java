package richard.feldtz.draft_pool.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Participant {

    private String name;
    private String color;
    private List<TournamentTeam> tournamentTeams;
    private int[] pointsByRound;

    public Participant(String name, String color) {
        this.name = name;
        this.color = color;
        this.tournamentTeams = new ArrayList<>();
        pointsByRound = new int[6];
    }

    public void calculatePointsByRound() {
        Arrays.fill(pointsByRound, 0);

        for (TournamentTeam team : tournamentTeams) {
            for (int i = 0; i < pointsByRound.length; i++) {
                pointsByRound[i] += team.getPointsByRound()[i];
            }
        }
    }

    public int calculateTotalPoints() {
        return Arrays.stream(pointsByRound).sum();
    }
}
