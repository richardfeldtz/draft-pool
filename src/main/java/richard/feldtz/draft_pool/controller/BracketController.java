package richard.feldtz.draft_pool.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import richard.feldtz.draft_pool.dto.CollegeBasketballTeam;
import richard.feldtz.draft_pool.dto.Participant;
import richard.feldtz.draft_pool.repo.ParticipantRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
public class BracketController {

    private final ParticipantRepository participantRepository;

    @GetMapping("/")
    public String showBracket(Model model) {
        List<Participant> participants = participantRepository.getParticipants();

        // Map: team ID -> participant color
        Map<String, String> teamColorMap = new HashMap<>();

        for (Participant p : participants) {
            for (CollegeBasketballTeam team : p.getTournamentTeams()) {
                teamColorMap.put(team.getId(), p.getColor());
            }
        }

        model.addAttribute("participants", participants);
        model.addAttribute("teamColorMap", teamColorMap);
        return "index";
    }
}