package com.dreamgamescasestudy.rest.web;

import com.dreamgamescasestudy.rest.domain.*;
import com.dreamgamescasestudy.rest.service.TournamentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TournamentController.class)
class TournamentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TournamentService tournamentService;

    @Mock
    TournamentController tournamentController;

    @BeforeEach
    void setUp() {
        tournamentController = new TournamentController(tournamentService);
        mockMvc = MockMvcBuilders.standaloneSetup(tournamentController).build();
    }

    @Test
    void testGetGroupRankRequest() throws Exception {
        when(tournamentService.getGroupRank(anyLong(), anyLong())).thenReturn(5);

        mockMvc.perform(get("/api/v1/tournament/get-rank")
                        .param("userID", "1")
                        .param("tournamentID", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(5));

        verify(tournamentService, times(1)).getGroupRank(1L, 1L);
    }

    @Test
    void testGetGroupLeaderboardRequest() throws Exception {
        // Creating mock TournamentUserScore objects
        List<TournamentUserScore> mockLeaderboard = Arrays.asList(
                TournamentUserScore.builder().tournamentUserId(1L).score(100).user(User.builder().build()).build(),
                TournamentUserScore.builder().tournamentUserId(2L).score(90).user(User.builder().build()).build()
        );

        when(tournamentService.getGroupLeaderboard(anyLong())).thenReturn(mockLeaderboard);

        mockMvc.perform(get("/api/v1/tournament/group-leaderboard")
                        .param("groupID", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].score").value(100))
                .andExpect(jsonPath("$[1].score").value(90));

        verify(tournamentService, times(1)).getGroupLeaderboard(1L);
    }

    @Test
    void testGetCountryLeaderboardRequest() throws Exception {
        // Creating mock TournamentCountryScore objects
        List<TournamentCountryScore> mockLeaderboard = Arrays.asList(
                TournamentCountryScore.builder().country(Country.TURKEY).score(100).build(),
                TournamentCountryScore.builder().country(Country.GERMANY).score(90).build()
        );

        when(tournamentService.getCountryLeaderboard(anyLong())).thenReturn(mockLeaderboard);

        mockMvc.perform(get("/api/v1/tournament/country-leaderboard")
                        .param("tournamentID", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Assuming JSON response
                .andExpect(jsonPath("$[0].country").value("TURKEY")) // Assuming the response structure
                .andExpect(jsonPath("$[0].score").value(100))
                .andExpect(jsonPath("$[1].country").value("GERMANY"))
                .andExpect(jsonPath("$[1].score").value(90));

        verify(tournamentService, times(1)).getCountryLeaderboard(1L);
    }

    @Test
    void testUpdateTournamentScore() throws Exception {
        mockMvc.perform(put("/api/v1/tournament/update-tournament-score")
                        .param("userID", "1"))
                .andExpect(status().isOk());

        verify(tournamentService, times(1)).updateTournamentScore(1L);
    }
}