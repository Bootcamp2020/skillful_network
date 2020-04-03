import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimulationStartComponent } from './simulation-start.component';

describe('SimulationStartComponent', () => {
  let component: SimulationStartComponent;
  let fixture: ComponentFixture<SimulationStartComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimulationStartComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimulationStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
