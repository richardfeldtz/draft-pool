package richard.feldtz.draft_pool.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import richard.feldtz.draft_pool.dto.ScoreboardResponse;
import richard.feldtz.draft_pool.dto.FinalGame;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreboardService {
    private static final String URL =
            "http://site.api.espn.com/apis/site/v2/sports/basketball/mens-college-basketball/scoreboard?groups=50&limit=500";

    private final RestTemplate restTemplate;

    public ScoreboardService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<FinalGame> getFinalGames() {

        ScoreboardResponse response = restTemplate.getForObject(URL, ScoreboardResponse.class);

        return response.getEvents().stream()
                .map(e -> e.getCompetitions().get(0))
                .filter(c -> "STATUS_FINAL".equals(c.getStatus().getType().getName()))
                .map(c -> {

                    var home = c.getCompetitors().stream()
                            .filter(x -> "home".equalsIgnoreCase(x.getHomeAway()))
                            .findFirst().orElseThrow();

                    var away = c.getCompetitors().stream()
                            .filter(x -> "away".equalsIgnoreCase(x.getHomeAway()))
                            .findFirst().orElseThrow();

                    FinalGame game = new FinalGame();
                    game.setHomeTeam(home.getTeam().getDisplayName());
                    game.setHomeScore(Integer.parseInt(home.getScore()));
                    game.setAwayTeam(away.getTeam().getDisplayName());
                    game.setAwayScore(Integer.parseInt(away.getScore()));

                    return game;

                }).collect(Collectors.toList());
    }
}
