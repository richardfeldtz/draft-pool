package richard.feldtz.draft_pool.scheduler;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import richard.feldtz.draft_pool.service.ScoreboardService;

import static richard.feldtz.draft_pool.util.Constants.FIVE_MINUTES;

@Slf4j
@Component
@AllArgsConstructor
public class ScoreboardScheduler {

    private final ScoreboardService scoreboardService;

    /**
     * Runs every 5 minutes
     */
    @Scheduled(fixedRate = FIVE_MINUTES)
    public void pollFinalGames() {
        log.info("Polling ESPN scoreboard for final games...");

        try {
            var finalGames = scoreboardService.getFinalGames();
            log.info("Found {} final games", finalGames.size());

            // 🔜 later:
            // - update team points
            // - persist results
            // - prevent double-processing

        } catch (Exception e) {
            log.error("Error polling final games", e);
        }
    }
}
