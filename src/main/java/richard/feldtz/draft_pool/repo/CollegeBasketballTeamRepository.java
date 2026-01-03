package richard.feldtz.draft_pool.repo;

import org.springframework.stereotype.Repository;
import richard.feldtz.draft_pool.dto.CollegeBasketballTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CollegeBasketballTeamRepository {
    private final List<CollegeBasketballTeam> teams = new ArrayList<>();

    public void saveAll(List<CollegeBasketballTeam> teams) {
        this.teams.addAll(teams);
    }

    public List<String> findAll() {
        return teams.stream().map(CollegeBasketballTeam::getName).collect(Collectors.toList());
    }

    public List<String> findAllTournamentTeams() {
        return teams.stream()
                .filter(CollegeBasketballTeam::isTournamentTeam)
                .map(team -> team.getId() + ": " + team.getName() + ", selected by: " + team.getPickedByName())
                .collect(Collectors.toList());
    }

    public List<String> getTeamIdsAndNames() {
        return teams.stream().map(team -> team.getId() + ": " + team.getName())
                .collect(Collectors.toList());
    }

    public Optional<CollegeBasketballTeam> getTeamById(String id) {
        return teams.stream().filter(team -> team.getId().equals(id)).findFirst();
    }
}
