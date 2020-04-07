import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimSynJobOfferListComponent } from './sim-syn-job-offer-list.component';

describe('SimSynJobOfferListComponent', () => {
  let component: SimSynJobOfferListComponent;
  let fixture: ComponentFixture<SimSynJobOfferListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimSynJobOfferListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimSynJobOfferListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
