/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FafiTestModule } from '../../../test.module';
import { SeasonsFranchiseDialogComponent } from '../../../../../../main/webapp/app/entities/seasons-franchise/seasons-franchise-dialog.component';
import { SeasonsFranchiseService } from '../../../../../../main/webapp/app/entities/seasons-franchise/seasons-franchise.service';
import { SeasonsFranchise } from '../../../../../../main/webapp/app/entities/seasons-franchise/seasons-franchise.model';
import { SeasonService } from '../../../../../../main/webapp/app/entities/season';
import { FranchiseService } from '../../../../../../main/webapp/app/entities/franchise';
import { PlayerService } from '../../../../../../main/webapp/app/entities/player';

describe('Component Tests', () => {

    describe('SeasonsFranchise Management Dialog Component', () => {
        let comp: SeasonsFranchiseDialogComponent;
        let fixture: ComponentFixture<SeasonsFranchiseDialogComponent>;
        let service: SeasonsFranchiseService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FafiTestModule],
                declarations: [SeasonsFranchiseDialogComponent],
                providers: [
                    SeasonService,
                    FranchiseService,
                    PlayerService,
                    SeasonsFranchiseService
                ]
            })
            .overrideTemplate(SeasonsFranchiseDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(SeasonsFranchiseDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SeasonsFranchiseService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SeasonsFranchise(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.seasonsFranchise = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'seasonsFranchiseListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new SeasonsFranchise();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.seasonsFranchise = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'seasonsFranchiseListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
