import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OffreComponent } from './offre.component';

describe('OffreComponent', () => {
  let component: OffreComponent;
  let fixture: ComponentFixture<OffreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OffreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OffreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
