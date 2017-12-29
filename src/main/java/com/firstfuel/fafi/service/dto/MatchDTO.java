package com.firstfuel.fafi.service.dto;


import java.time.ZonedDateTime;
import javax.validation.constraints.*;

import com.firstfuel.fafi.domain.enumeration.Games;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Match entity.
 */
public class MatchDTO implements Serializable {

    private Long id;

    private ZonedDateTime startDateTime;

    private ZonedDateTime endDateTime;

    @NotNull
    @Min(value = 1)
    private Integer matchNumber;

    private Integer pointsEarnedByFranchise;

    private Long tournamentId;
    private Games tournamentType;

    private Long winningFranchiseId;

    private String winningFranchiseFranchiseName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(Integer matchNumber) {
        this.matchNumber = matchNumber;
    }

    public Integer getPointsEarnedByFranchise() {
        return pointsEarnedByFranchise;
    }

    public void setPointsEarnedByFranchise(Integer pointsEarnedByFranchise) {
        this.pointsEarnedByFranchise = pointsEarnedByFranchise;
    }

    public Long getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }

    public Long getWinningFranchiseId() {
        return winningFranchiseId;
    }

    public void setWinningFranchiseId(Long seasonsFranchiseId) {
        this.winningFranchiseId = seasonsFranchiseId;
    }

    public String getWinningFranchiseFranchiseName() {
        return winningFranchiseFranchiseName;
    }

    public void setWinningFranchiseFranchiseName(String seasonsFranchiseFranchiseName) {
        this.winningFranchiseFranchiseName = seasonsFranchiseFranchiseName;
    }

    public Games getTournamentType() {
		return tournamentType;
	}

	public void setTournamentType(Games tournamentType) {
		this.tournamentType = tournamentType;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MatchDTO matchDTO = (MatchDTO) o;
        if(matchDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
            "id=" + getId() +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", matchNumber=" + getMatchNumber() +
            ", pointsEarnedByFranchise=" + getPointsEarnedByFranchise() +
            "}";
    }
}
