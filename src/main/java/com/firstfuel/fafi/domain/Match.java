package com.firstfuel.fafi.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.firstfuel.fafi.domain.enumeration.Stage;

/**
 * A Match.
 */
@Entity
@Table(name = "fafi_match")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Match implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "start_date_time")
    private ZonedDateTime startDateTime;

    @Column(name = "end_date_time")
    private ZonedDateTime endDateTime;

    @Column(name = "points_earned_by_franchise")
    private Integer pointsEarnedByFranchise;

    @NotNull
    @Column(name = "match_name", nullable = false)
    private String matchName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "stage", nullable = false)
    private Stage stage;

    @Column(name = "venue")
    private String venue;

    @NotNull
    @Column(name = "completed", nullable = false)
    private Boolean completed;

    @ManyToOne
    private Tournament tournament;

    @ManyToOne
    private SeasonsFranchise winningFranchise;

    @ManyToOne
    private SeasonsFranchise team1;

    @ManyToOne
    private SeasonsFranchise team2;

    @ManyToOne
    private SeasonsFranchise team3;

    @ManyToOne
    private SeasonsFranchise team4;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getStartDateTime() {
        return startDateTime;
    }

    public Match startDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
        return this;
    }

    public void setStartDateTime(ZonedDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public ZonedDateTime getEndDateTime() {
        return endDateTime;
    }

    public Match endDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
        return this;
    }

    public void setEndDateTime(ZonedDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public Integer getPointsEarnedByFranchise() {
        return pointsEarnedByFranchise;
    }

    public Match pointsEarnedByFranchise(Integer pointsEarnedByFranchise) {
        this.pointsEarnedByFranchise = pointsEarnedByFranchise;
        return this;
    }

    public void setPointsEarnedByFranchise(Integer pointsEarnedByFranchise) {
        this.pointsEarnedByFranchise = pointsEarnedByFranchise;
    }

    public String getMatchName() {
        return matchName;
    }

    public Match matchName(String matchName) {
        this.matchName = matchName;
        return this;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public Stage getStage() {
        return stage;
    }

    public Match stage(Stage stage) {
        this.stage = stage;
        return this;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getVenue() {
        return venue;
    }

    public Match venue(String venue) {
        this.venue = venue;
        return this;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public Match completed(Boolean completed) {
        this.completed = completed;
        return this;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Match tournament(Tournament tournament) {
        this.tournament = tournament;
        return this;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public SeasonsFranchise getWinningFranchise() {
        return winningFranchise;
    }

    public Match winningFranchise(SeasonsFranchise seasonsFranchise) {
        this.winningFranchise = seasonsFranchise;
        return this;
    }

    public void setWinningFranchise(SeasonsFranchise seasonsFranchise) {
        this.winningFranchise = seasonsFranchise;
    }

    public SeasonsFranchise getTeam1() {
        return team1;
    }

    public Match team1(SeasonsFranchise seasonsFranchise) {
        this.team1 = seasonsFranchise;
        return this;
    }

    public void setTeam1(SeasonsFranchise seasonsFranchise) {
        this.team1 = seasonsFranchise;
    }

    public SeasonsFranchise getTeam2() {
        return team2;
    }

    public Match team2(SeasonsFranchise seasonsFranchise) {
        this.team2 = seasonsFranchise;
        return this;
    }

    public void setTeam2(SeasonsFranchise seasonsFranchise) {
        this.team2 = seasonsFranchise;
    }

    public SeasonsFranchise getTeam3() {
        return team3;
    }

    public Match team3(SeasonsFranchise seasonsFranchise) {
        this.team3 = seasonsFranchise;
        return this;
    }

    public void setTeam3(SeasonsFranchise seasonsFranchise) {
        this.team3 = seasonsFranchise;
    }

    public SeasonsFranchise getTeam4() {
        return team4;
    }

    public Match team4(SeasonsFranchise seasonsFranchise) {
        this.team4 = seasonsFranchise;
        return this;
    }

    public void setTeam4(SeasonsFranchise seasonsFranchise) {
        this.team4 = seasonsFranchise;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Match match = (Match) o;
        if (match.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), match.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Match{" +
            "id=" + getId() +
            ", startDateTime='" + getStartDateTime() + "'" +
            ", endDateTime='" + getEndDateTime() + "'" +
            ", pointsEarnedByFranchise=" + getPointsEarnedByFranchise() +
            ", matchName='" + getMatchName() + "'" +
            ", stage='" + getStage() + "'" +
            ", venue='" + getVenue() + "'" +
            ", completed='" + isCompleted() + "'" +
            "}";
    }
}
