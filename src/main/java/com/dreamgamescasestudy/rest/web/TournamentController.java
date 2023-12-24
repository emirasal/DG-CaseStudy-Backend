package com.dreamgamescasestudy.rest.web;

import com.dreamgamescasestudy.rest.domain.CountryLeaderboard;
import com.dreamgamescasestudy.rest.domain.GroupLeaderboard;
import com.dreamgamescasestudy.rest.service.TournamentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/tournament")
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping("/enter-tournament/{userID}")
    public List<GroupLeaderboard> EnterTournamentRequest(@PathVariable Long userID) throws InterruptedException {

        tournamentService.enterTournament(userID);

        List<GroupLeaderboard> response = tournamentService.checkGroupLeaderboard(userID);
        // keep checking until user is matched with a group
        while (response == null) {
            Thread.sleep(2000);
            response = tournamentService.checkGroupLeaderboard(userID);
        }
        return response;
    }

    @GetMapping("/get-rank/{userID}")
    public int GetGroupRankRequest(@PathVariable Long userID, @RequestParam Long tournamentID) {

        return tournamentService.GetGroupRank(userID, tournamentID);
    }

    @GetMapping("/get-group-leaderboard/{groupID}")
    public List<GroupLeaderboard> GetGroupLeaderboardRequest(@PathVariable Long groupID){

        return tournamentService.GetGroupLeaderboard((groupID));
    }

    @GetMapping("/get-country-leaderboard/{tournamentID}")
    public List<CountryLeaderboard> GetCountryLeaderboardRequest(@PathVariable Long tournamentID){

        return tournamentService.GetCountryLeaderboard(tournamentID);
    }

}
