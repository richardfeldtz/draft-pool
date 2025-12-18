package richard.feldtz.draft_pool.repo;

import org.springframework.stereotype.Repository;
import richard.feldtz.draft_pool.dto.CollegeBasketballTeam;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CollegeBasketballTeamRepository {
    private final List<CollegeBasketballTeam> teams = new ArrayList<>();

    public void saveAll(List<CollegeBasketballTeam> teams) {
        this.teams.addAll(teams);
    }

    public List<CollegeBasketballTeam> findAll() {
        return new ArrayList<>(teams);
    }
}
