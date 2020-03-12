import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FilActualitesComponent } from './fil-actualites.component';

describe('FilActualitesComponent', () => {
  let component: FilActualitesComponent;
  let fixture: ComponentFixture<FilActualitesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FilActualitesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FilActualitesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
