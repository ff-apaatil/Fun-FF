package com.firstfuel.fafi.service;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.firstfuel.fafi.domain.TieMatch;
import com.firstfuel.fafi.domain.*; // for static metamodels
import com.firstfuel.fafi.repository.TieMatchRepository;
import com.firstfuel.fafi.service.dto.TieMatchCriteria;

import com.firstfuel.fafi.domain.enumeration.TieType;

/**
 * Service for executing complex queries for TieMatch entities in the database.
 * The main input is a {@link TieMatchCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TieMatch} or a {@link Page} of {@link TieMatch} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TieMatchQueryService extends QueryService<TieMatch> {

    private final Logger log = LoggerFactory.getLogger(TieMatchQueryService.class);


    private final TieMatchRepository tieMatchRepository;

    public TieMatchQueryService(TieMatchRepository tieMatchRepository) {
        this.tieMatchRepository = tieMatchRepository;
    }

    /**
     * Return a {@link List} of {@link TieMatch} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TieMatch> findByCriteria(TieMatchCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<TieMatch> specification = createSpecification(criteria);
        return tieMatchRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link TieMatch} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TieMatch> findByCriteria(TieMatchCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<TieMatch> specification = createSpecification(criteria);
        return tieMatchRepository.findAll(specification, page);
    }

    /**
     * Function to convert TieMatchCriteria to a {@link Specifications}
     */
    private Specifications<TieMatch> createSpecification(TieMatchCriteria criteria) {
        Specifications<TieMatch> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TieMatch_.id));
            }
            if (criteria.getTieType() != null) {
                specification = specification.and(buildSpecification(criteria.getTieType(), TieMatch_.tieType));
            }
            if (criteria.getMatchId() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getMatchId(), TieMatch_.match, Match_.id));
            }
            if (criteria.getTeam1Player1Id() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTeam1Player1Id(), TieMatch_.team1Player1, SeasonsFranchisePlayer_.id));
            }
            if (criteria.getTeam1Player2Id() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTeam1Player2Id(), TieMatch_.team1Player2, SeasonsFranchisePlayer_.id));
            }
            if (criteria.getTeam2Player1Id() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTeam2Player1Id(), TieMatch_.team2Player1, SeasonsFranchisePlayer_.id));
            }
            if (criteria.getTeam2Player2Id() != null) {
                specification = specification.and(buildReferringEntitySpecification(criteria.getTeam2Player2Id(), TieMatch_.team2Player2, SeasonsFranchisePlayer_.id));
            }
        }
        return specification;
    }

}
