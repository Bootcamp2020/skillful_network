import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimJobOfferListComponent } from './sim-job-offer-list.component';

describe('SimJobOfferListComponent', () => {
  let component: SimJobOfferListComponent;
  let fixture: ComponentFixture<SimJobOfferListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimJobOfferListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimJobOfferListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
