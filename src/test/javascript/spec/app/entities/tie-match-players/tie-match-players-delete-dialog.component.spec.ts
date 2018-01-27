/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FafiTestModule } from '../../../test.module';
import { TieMatchPlayersDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/tie-match-players/tie-match-players-delete-dialog.component';
import { TieMatchPlayersService } from '../../../../../../main/webapp/app/entities/tie-match-players/tie-match-players.service';

describe('Component Tests', () => {

    describe('TieMatchPlayers Management Delete Component', () => {
        let comp: TieMatchPlayersDeleteDialogComponent;
        let fixture: ComponentFixture<TieMatchPlayersDeleteDialogComponent>;
        let service: TieMatchPlayersService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FafiTestModule],
                declarations: [TieMatchPlayersDeleteDialogComponent],
                providers: [
                    TieMatchPlayersService
                ]
            })
            .overrideTemplate(TieMatchPlayersDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TieMatchPlayersDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TieMatchPlayersService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
