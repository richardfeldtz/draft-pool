package richard.feldtz.draft_pool.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import richard.feldtz.draft_pool.dto.CollegeBasketballTeam;
import richard.feldtz.draft_pool.dto.FinalGame;
import richard.feldtz.draft_pool.repo.CollegeBasketballTeamRepository;
import richard.feldtz.draft_pool.service.ScoreboardService;

import java.util.List;

@RestController
@AllArgsConstructor
public class TestController {

    private final ScoreboardService scoreboardService;
    private final CollegeBasketballTeamRepository teamRepository;

    @GetMapping("/games/final")
    public List<FinalGame> finalGames() {
        return scoreboardService.getFinalGames();
    }

    @GetMapping("/teams")
    public List<CollegeBasketballTeam> getTeams() {
        return teamRepository.findAll();
    }
}
