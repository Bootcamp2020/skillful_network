import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoalValidationModalComponent } from './goal-validation-modal.component';

describe('GoalValidationModalComponent', () => {
  let component: GoalValidationModalComponent;
  let fixture: ComponentFixture<GoalValidationModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoalValidationModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoalValidationModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
