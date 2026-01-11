package richard.feldtz.draft_pool.repo;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import richard.feldtz.draft_pool.dto.TournamentTeam;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class TournamentTeamRepository {
    private final List<TournamentTeam> tournamentTeams = new ArrayList<>();

    public void save(TournamentTeam team) {
        this.tournamentTeams.add(team);
    }
}
