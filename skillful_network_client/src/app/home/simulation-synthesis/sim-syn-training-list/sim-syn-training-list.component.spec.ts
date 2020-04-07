import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimSynTrainingListComponent } from './sim-syn-training-list.component';

describe('SimSynTrainingListComponent', () => {
  let component: SimSynTrainingListComponent;
  let fixture: ComponentFixture<SimSynTrainingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimSynTrainingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimSynTrainingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
