package richard.feldtz.draft_pool.service;

import org.springframework.stereotype.Service;
import richard.feldtz.draft_pool.dto.CollegeBasketballTeam;
import richard.feldtz.draft_pool.dto.Participant;
import richard.feldtz.draft_pool.repo.CollegeBasketballTeamRepository;
import richard.feldtz.draft_pool.repo.ParticipantRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Service
public class TournamentCsvLoader {

    private final CollegeBasketballTeamRepository teamRepository;
    private final ParticipantRepository participantRepository;

    public TournamentCsvLoader(CollegeBasketballTeamRepository teamRepository,
                               ParticipantRepository participantRepository) {
        this.teamRepository = teamRepository;
        this.participantRepository = participantRepository;
    }

    public void load(InputStream csvInputStream) {
        List<Participant> participants = participantRepository.findAll();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(csvInputStream))) {

            // Skip header
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");

                String teamId = cols[0].trim();
                String participantName = cols[2].trim();

                CollegeBasketballTeam team = teamRepository.getTeamById(teamId)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Team not found: " + teamId));

                Participant participant = participants.stream()
                        .filter(p -> p.getName().equalsIgnoreCase(participantName))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException("Participant not found: " + participantName));

                // Link everything
                team.setTournamentTeam(true);
                team.setPickedByName(participant.getName());
                participant.getTournamentTeams().add(team);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load tournament CSV", e);
        }
    }
}
