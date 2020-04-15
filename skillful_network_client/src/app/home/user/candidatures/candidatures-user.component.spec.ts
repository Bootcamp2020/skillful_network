import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CandidaturesUserComponent } from './candidatures-user.component';

describe('CandidaturesComponent', () => {
  let component: CandidaturesUserComponent;
  let fixture: ComponentFixture<CandidaturesUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CandidaturesUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CandidaturesUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
