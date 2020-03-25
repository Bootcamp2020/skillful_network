import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExigencesComponent } from './exigences.component';

describe('ExigencesComponent', () => {
  let component: ExigencesComponent;
  let fixture: ComponentFixture<ExigencesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExigencesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExigencesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
