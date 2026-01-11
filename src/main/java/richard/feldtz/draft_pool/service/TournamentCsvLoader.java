package richard.feldtz.draft_pool.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import richard.feldtz.draft_pool.dto.Participant;
import richard.feldtz.draft_pool.dto.TournamentTeam;
import richard.feldtz.draft_pool.repo.CollegeBasketballTeamRepository;
import richard.feldtz.draft_pool.repo.ParticipantRepository;
import richard.feldtz.draft_pool.repo.TournamentTeamRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class TournamentCsvLoader {

    private final CollegeBasketballTeamRepository teamRepository;
    private final TournamentTeamRepository tournamentTeamRepository;
    private final ParticipantRepository participantRepository;

    public void load(InputStream csvInputStream) {
        List<Participant> participants = participantRepository.getParticipants();

        try (BufferedReader reader =
                     new BufferedReader(new InputStreamReader(csvInputStream))) {

            // Skip header
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",");

                String region = cols[0].trim();
                String seed = cols[1].trim();
                String teamId = cols[2].trim();
                String teamName = cols[3].trim();
                String participantName = cols[4].trim();

                if (teamRepository.getTeamById(teamId).isEmpty()) {
                    throw new IllegalArgumentException("Team not found: " + teamId + " team: " + teamName);
                }

                Participant participant = participants.stream()
                        .filter(p -> p.getName().equalsIgnoreCase(participantName))
                        .findFirst()
                        .orElseThrow(() ->
                                new IllegalArgumentException("Participant not found: " + participantName));

                // Link everything
                TournamentTeam tournamentTeam = new TournamentTeam(region, Integer.parseInt(seed));
                tournamentTeam.setId(teamId);
                tournamentTeam.setName(teamName);
                participant.getTournamentTeams().add(tournamentTeam);

                tournamentTeamRepository.save(tournamentTeam);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load tournament CSV", e);
        }
    }
}
