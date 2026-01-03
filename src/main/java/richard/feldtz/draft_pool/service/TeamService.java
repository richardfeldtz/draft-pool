package richard.feldtz.draft_pool.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import richard.feldtz.draft_pool.dto.CollegeBasketballTeam;
import richard.feldtz.draft_pool.repo.CollegeBasketballTeamRepository;
import tools.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private static final String ESPN_API =
            "http://site.api.espn.com/apis/site/v2/sports/basketball/mens-college-basketball/teams?groups=50&limit=1000";

    private final CollegeBasketballTeamRepository collegeBasketballTeamRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public TeamService(CollegeBasketballTeamRepository collegeBasketballTeamRepository, RestTemplate restTemplate) {
        this.collegeBasketballTeamRepository = collegeBasketballTeamRepository;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        fetchAndStoreTeams();
    }

    public void fetchAndStoreTeams() {
        JsonNode root = restTemplate.getForObject(ESPN_API, JsonNode.class);

        if (root == null || !root.has("sports")) {
            return; // no data
        }

        JsonNode teamsArray =
                root.path("sports")
                        .get(0)
                        .path("leagues")
                        .get(0)
                        .path("teams");

        List<CollegeBasketballTeam> teams = new ArrayList<>();
        for (JsonNode node : teamsArray) {
            JsonNode teamObj = node.path("team");
            String id = teamObj.path("id").asString();
            String displayName = teamObj.path("displayName").asString();

            teams.add(new CollegeBasketballTeam(id, displayName));
        }

        collegeBasketballTeamRepository.saveAll(teams);
    }
}
