package com.firstfuel.fafi.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.firstfuel.fafi.domain.TieMatch;
import com.firstfuel.fafi.service.TieMatchService;
import com.firstfuel.fafi.web.rest.errors.BadRequestAlertException;
import com.firstfuel.fafi.web.rest.util.HeaderUtil;
import com.firstfuel.fafi.web.rest.util.PaginationUtil;
import com.firstfuel.fafi.service.dto.TieMatchCriteria;
import com.firstfuel.fafi.service.TieMatchQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing TieMatch.
 */
@RestController
@RequestMapping("/api")
public class TieMatchResource {

    private final Logger log = LoggerFactory.getLogger(TieMatchResource.class);

    private static final String ENTITY_NAME = "tieMatch";

    private final TieMatchService tieMatchService;

    private final TieMatchQueryService tieMatchQueryService;

    public TieMatchResource(TieMatchService tieMatchService, TieMatchQueryService tieMatchQueryService) {
        this.tieMatchService = tieMatchService;
        this.tieMatchQueryService = tieMatchQueryService;
    }

    /**
     * POST  /tie-matches : Create a new tieMatch.
     *
     * @param tieMatch the tieMatch to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tieMatch, or with status 400 (Bad Request) if the tieMatch has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tie-matches")
    @Timed
    public ResponseEntity<TieMatch> createTieMatch(@Valid @RequestBody TieMatch tieMatch) throws URISyntaxException {
        log.debug("REST request to save TieMatch : {}", tieMatch);
        if (tieMatch.getId() != null) {
            throw new BadRequestAlertException("A new tieMatch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TieMatch result = tieMatchService.save(tieMatch);
        return ResponseEntity.created(new URI("/api/tie-matches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tie-matches : Updates an existing tieMatch.
     *
     * @param tieMatch the tieMatch to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tieMatch,
     * or with status 400 (Bad Request) if the tieMatch is not valid,
     * or with status 500 (Internal Server Error) if the tieMatch couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tie-matches")
    @Timed
    public ResponseEntity<TieMatch> updateTieMatch(@Valid @RequestBody TieMatch tieMatch) throws URISyntaxException {
        log.debug("REST request to update TieMatch : {}", tieMatch);
        if (tieMatch.getId() == null) {
            return createTieMatch(tieMatch);
        }
        TieMatch result = tieMatchService.save(tieMatch);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tieMatch.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tie-matches : get all the tieMatches.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of tieMatches in body
     */
    @GetMapping("/tie-matches")
    @Timed
    public ResponseEntity<List<TieMatch>> getAllTieMatches(TieMatchCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TieMatches by criteria: {}", criteria);
        Page<TieMatch> page = tieMatchQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tie-matches");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tie-matches/:id : get the "id" tieMatch.
     *
     * @param id the id of the tieMatch to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tieMatch, or with status 404 (Not Found)
     */
    @GetMapping("/tie-matches/{id}")
    @Timed
    public ResponseEntity<TieMatch> getTieMatch(@PathVariable Long id) {
        log.debug("REST request to get TieMatch : {}", id);
        TieMatch tieMatch = tieMatchService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tieMatch));
    }

    /**
     * DELETE  /tie-matches/:id : delete the "id" tieMatch.
     *
     * @param id the id of the tieMatch to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tie-matches/{id}")
    @Timed
    public ResponseEntity<Void> deleteTieMatch(@PathVariable Long id) {
        log.debug("REST request to delete TieMatch : {}", id);
        tieMatchService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
