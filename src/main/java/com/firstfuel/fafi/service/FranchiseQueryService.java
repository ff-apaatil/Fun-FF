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

import com.firstfuel.fafi.domain.Franchise;
import com.firstfuel.fafi.domain.*; // for static metamodels
import com.firstfuel.fafi.repository.FranchiseRepository;
import com.firstfuel.fafi.service.dto.FranchiseCriteria;

import com.firstfuel.fafi.service.dto.FranchiseDTO;
import com.firstfuel.fafi.service.mapper.FranchiseMapper;

/**
 * Service for executing complex queries for Franchise entities in the database.
 * The main input is a {@link FranchiseCriteria} which get's converted to {@link Specifications},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FranchiseDTO} or a {@link Page} of {@link FranchiseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FranchiseQueryService extends QueryService<Franchise> {

    private final Logger log = LoggerFactory.getLogger(FranchiseQueryService.class);


    private final FranchiseRepository franchiseRepository;

    private final FranchiseMapper franchiseMapper;

    public FranchiseQueryService(FranchiseRepository franchiseRepository, FranchiseMapper franchiseMapper) {
        this.franchiseRepository = franchiseRepository;
        this.franchiseMapper = franchiseMapper;
    }

    /**
     * Return a {@link List} of {@link FranchiseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FranchiseDTO> findByCriteria(FranchiseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specifications<Franchise> specification = createSpecification(criteria);
        return franchiseMapper.toDto(franchiseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FranchiseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FranchiseDTO> findByCriteria(FranchiseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specifications<Franchise> specification = createSpecification(criteria);
        final Page<Franchise> result = franchiseRepository.findAll(specification, page);
        return result.map(franchiseMapper::toDto);
    }

    /**
     * Function to convert FranchiseCriteria to a {@link Specifications}
     */
    private Specifications<Franchise> createSpecification(FranchiseCriteria criteria) {
        Specifications<Franchise> specification = Specifications.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Franchise_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Franchise_.name));
            }
            if (criteria.getLogoPath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLogoPath(), Franchise_.logoPath));
            }
            if (criteria.getActive() != null) {
                specification = specification.and(buildSpecification(criteria.getActive(), Franchise_.active));
            }
        }
        return specification;
    }

}
