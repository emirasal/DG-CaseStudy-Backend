package com.dreamgamescasestudy.rest.web.response;

import com.dreamgamescasestudy.rest.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class GroupLeaderboardResponse {

    private final Long userID;
    private final String username;
    private final Country country;
    private final int score;
}
