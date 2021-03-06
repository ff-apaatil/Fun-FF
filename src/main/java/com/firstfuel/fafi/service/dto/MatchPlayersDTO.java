package com.firstfuel.fafi.service.dto;


import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.firstfuel.fafi.domain.enumeration.Games;

/**
 * A DTO for the MatchPlayers entity.
 */
public class MatchPlayersDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1774492836887750990L;

    private Long id;

    @NotNull
    private Boolean playerOfTheMatch;

    private Integer playerPointsEarned;

    private Long matchId;

    private String matchName;

    private Games matchType;

    private Long seasonsFranchisePlayerId;

    private String seasonsFranchisePlayerFirstName;
    private String seasonsFranchisePlayerLastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isPlayerOfTheMatch() {
        return playerOfTheMatch;
    }

    public void setPlayerOfTheMatch(Boolean playerOfTheMatch) {
        this.playerOfTheMatch = playerOfTheMatch;
    }

    public Integer getPlayerPointsEarned() {
        return playerPointsEarned;
    }

    public void setPlayerPointsEarned(Integer playerPointsEarned) {
        this.playerPointsEarned = playerPointsEarned;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }


    public String getMatchName() {
		return matchName;
	}

	public void setMatchName(String matchName) {
		this.matchName = matchName;
	}

	public Boolean getPlayerOfTheMatch() {
		return playerOfTheMatch;
	}

	public Long getSeasonsFranchisePlayerId() {
        return seasonsFranchisePlayerId;
    }

    public void setSeasonsFranchisePlayerId(Long seasonsFranchisePlayerId) {
        this.seasonsFranchisePlayerId = seasonsFranchisePlayerId;
    }



    public String getSeasonsFranchisePlayerFirstName() {
		return seasonsFranchisePlayerFirstName;
	}

	public void setSeasonsFranchisePlayerFirstName(String seasonsFranchisePlayerFirstName) {
		this.seasonsFranchisePlayerFirstName = seasonsFranchisePlayerFirstName;
	}

	public String getSeasonsFranchisePlayerLastName() {
		return seasonsFranchisePlayerLastName;
	}

	public void setSeasonsFranchisePlayerLastName(String seasonsFranchisePlayerLastName) {
		this.seasonsFranchisePlayerLastName = seasonsFranchisePlayerLastName;
	}

	public Games getMatchType() {
        return matchType;
    }

    public void setMatchType(Games matchType) {
        this.matchType = matchType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MatchPlayersDTO matchPlayersDTO = (MatchPlayersDTO) o;
        if(matchPlayersDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchPlayersDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MatchPlayersDTO{" +
            "id=" + getId() +
            ", playerOfTheMatch='" + isPlayerOfTheMatch() + "'" +
            ", playerPointsEarned=" + getPlayerPointsEarned() +
            "}";
    }
}
