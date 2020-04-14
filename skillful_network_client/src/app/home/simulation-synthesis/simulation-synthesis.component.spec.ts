import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimulationSynthesisComponent } from './simulation-synthesis.component';

describe('SimulationSynthesisComponent', () => {
  let component: SimulationSynthesisComponent;
  let fixture: ComponentFixture<SimulationSynthesisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimulationSynthesisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimulationSynthesisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
