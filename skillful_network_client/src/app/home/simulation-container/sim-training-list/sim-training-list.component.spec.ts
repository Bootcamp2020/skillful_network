import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimTrainingListComponent } from './sim-training-list.component';

describe('SimTrainingListComponent', () => {
  let component: SimTrainingListComponent;
  let fixture: ComponentFixture<SimTrainingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimTrainingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimTrainingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
