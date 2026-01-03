package richard.feldtz.draft_pool.repo;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import richard.feldtz.draft_pool.dto.Participant;

import java.util.ArrayList;
import java.util.List;

@Repository
@Getter
public class ParticipantRepository {
    private final List<Participant> participants = new ArrayList<>();

    public void saveAll(List<Participant> participants) {
        this.participants.addAll(participants);
    }

    @PostConstruct
    public void init() {
        saveAll(List.of(
                new Participant("Richard", "#1f77b4"),
                new Participant("Dan", "#ff7f0e"),
                new Participant("Alex", "#2ca02c"),
                new Participant("Matt", "#d62728")
        ));
    }
}
