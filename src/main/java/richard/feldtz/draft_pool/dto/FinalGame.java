package richard.feldtz.draft_pool.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinalGame {
    private String homeTeam;
    private int homeScore;
    private String awayTeam;
    private int awayScore;
}
