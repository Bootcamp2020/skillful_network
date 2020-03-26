import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormationsAssocieesComponent } from './formations-associees.component';

describe('FormationsAssocieesComponent', () => {
  let component: FormationsAssocieesComponent;
  let fixture: ComponentFixture<FormationsAssocieesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormationsAssocieesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormationsAssocieesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
