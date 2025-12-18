package richard.feldtz.draft_pool.repo;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import richard.feldtz.draft_pool.dto.Participant;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ParticipantRepository {
    private final List<Participant> participants = new ArrayList<>();

    public void saveAll(List<Participant> participants) {
        this.participants.addAll(participants);
    }

    public List<Participant> findAll() {
        return new ArrayList<>(participants);
    }

//    @PostConstruct
//    public void init() {
//          TODO: implement
//    }

}
