package com.firstfuel.fafi.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// for static metamodels
import com.firstfuel.fafi.domain.Match_;
import com.firstfuel.fafi.domain.Season_;
import com.firstfuel.fafi.domain.SeasonsFranchisePlayer_;
import com.firstfuel.fafi.domain.SeasonsFranchise_;
import com.firstfuel.fafi.domain.Tournament;
import com.firstfuel.fafi.domain.Tournament_;
import com.firstfuel.fafi.repository.TournamentRepository;
import com.firstfuel.fafi.service.dto.TournamentCriteria;
import com.firstfuel.fafi.service.dto.TournamentDTO;
import com.firstfuel.fafi.service.mapper.TournamentMapper;

import io.github.jhipster.service.QueryService;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Service for executing complex queries for Tournament entities in the
 * database. The main input is a {@link TournamentCriteria} which get's
 * converted to {@link Specifications}, in a way that all the filters must
 * apply. It returns a {@link List} of {@link TournamentDTO} or a {@link Page}
 * of {@link TournamentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TournamentQueryService extends QueryService<Tournament> {

	private final Logger log = LoggerFactory.getLogger(TournamentQueryService.class);

	private final TournamentRepository tournamentRepository;

	private final TournamentMapper tournamentMapper;

	public TournamentQueryService(TournamentRepository tournamentRepository, TournamentMapper tournamentMapper) {
		this.tournamentRepository = tournamentRepository;
		this.tournamentMapper = tournamentMapper;
	}

	/**
	 * Return a {@link List} of {@link TournamentDTO} which matches the criteria
	 * from the database
	 * 
	 * @param criteria
	 *            The object which holds all the filters, which the entities should
	 *            match.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public List<TournamentDTO> findByCriteria(TournamentCriteria criteria) {
		log.debug("find by criteria : {}", criteria);
		final Specifications<Tournament> specification = createSpecification(criteria);
		return tournamentMapper.toDto(tournamentRepository.findAll(specification));
	}

	/**
	 * Return a {@link Page} of {@link TournamentDTO} which matches the criteria
	 * from the database
	 * 
	 * @param criteria
	 *            The object which holds all the filters, which the entities should
	 *            match.
	 * @param page
	 *            The page, which should be returned.
	 * @return the matching entities.
	 */
	@Transactional(readOnly = true)
	public Page<TournamentDTO> findByCriteria(TournamentCriteria criteria, Pageable page) {
		log.debug("find by criteria : {}, page: {}", criteria, page);
		final Specifications<Tournament> specification = createSpecification(criteria);
		final Page<Tournament> result = tournamentRepository.findAll(specification, page);
		return result.map(tournamentMapper::toDto);
	}

	/**
	 * Function to convert TournamentCriteria to a {@link Specifications}
	 */
	private Specifications<Tournament> createSpecification(TournamentCriteria criteria) {
		Specifications<Tournament> specification = Specifications.where(null);
		if (criteria != null) {
			if (criteria.getId() != null) {
				specification = specification.and(buildSpecification(criteria.getId(), Tournament_.id));
			}
			if (criteria.getStartDate() != null) {
				specification = specification
						.and(buildRangeSpecification(criteria.getStartDate(), Tournament_.startDate));
			}
			if (criteria.getEndDate() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getEndDate(), Tournament_.endDate));
			}
			if (criteria.getType() != null) {
				specification = specification.and(buildSpecification(criteria.getType(), Tournament_.type));
			}
			if (criteria.getSeasonId() != null) {
				specification = specification
						.and(buildReferringEntitySpecification(criteria.getSeasonId(), Tournament_.season, Season_.id));
			}
			if (criteria.getMatchesId() != null) {
				specification = specification.and(
						buildReferringEntitySpecification(criteria.getMatchesId(), Tournament_.matches, Match_.id));
			}
			if (criteria.getWinningFranchiseId() != null) {
				specification = specification.and(buildReferringEntitySpecification(criteria.getWinningFranchiseId(),
						Tournament_.winningFranchise, SeasonsFranchise_.id));
			}
			if (criteria.getPlayerOfTournamentId() != null) {
				specification = specification.and(buildReferringEntitySpecification(criteria.getPlayerOfTournamentId(),
						Tournament_.playerOfTournament, SeasonsFranchisePlayer_.id));
			}
			if (criteria.getActive() != null) {
				if (criteria.getActive().getEquals()) {
					LocalDateFilter dateFilter = new LocalDateFilter();
					dateFilter.setGreaterOrEqualThan(LocalDate.now());
					specification = specification.and(buildRangeSpecification(dateFilter, Tournament_.endDate));
				} else {
					LocalDateFilter dateFilter = new LocalDateFilter();
					dateFilter.setLessThan(LocalDate.now());
					specification = specification.and(buildRangeSpecification(dateFilter, Tournament_.endDate));
				}
			}
		}
		return specification;
	}

}
