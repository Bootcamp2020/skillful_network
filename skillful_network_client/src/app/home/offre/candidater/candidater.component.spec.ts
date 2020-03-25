import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidaterComponent } from './candidater.component';

describe('CandidaterComponent', () => {
  let component: CandidaterComponent;
  let fixture: ComponentFixture<CandidaterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidaterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidaterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
