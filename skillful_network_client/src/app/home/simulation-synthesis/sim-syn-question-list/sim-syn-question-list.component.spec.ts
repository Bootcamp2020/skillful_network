import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimSynQuestionListComponent } from './sim-syn-question-list.component';

describe('SimSynQuestionListComponent', () => {
  let component: SimSynQuestionListComponent;
  let fixture: ComponentFixture<SimSynQuestionListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimSynQuestionListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimSynQuestionListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
