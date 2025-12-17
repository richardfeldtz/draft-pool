package richard.feldtz.draft_pool.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Participant {

    private String name;
    private List<Team> selectedTeams;
}
