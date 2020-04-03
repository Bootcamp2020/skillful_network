import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimQuestionListComponent } from './sim-question-list.component';

describe('SimQuestionListComponent', () => {
  let component: SimQuestionListComponent;
  let fixture: ComponentFixture<SimQuestionListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimQuestionListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimQuestionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
