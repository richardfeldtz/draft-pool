package richard.feldtz.draft_pool.init;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import richard.feldtz.draft_pool.service.TeamService;
import richard.feldtz.draft_pool.service.TournamentCsvLoader;

import java.io.InputStream;

@Component
@AllArgsConstructor
public class TournamentDataInitializer {

    private final TournamentCsvLoader loader;
    private final TeamService teamService;

    @PostConstruct
    public void init() {
        teamService.fetchAndStoreTeams();

        InputStream csv = getClass().getResourceAsStream("/tournament_picks.csv");
        loader.load(csv);
    }
}

