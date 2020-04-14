import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimSynInfoComponent } from './sim-syn-info.component';

describe('SimSynInfoComponent', () => {
  let component: SimSynInfoComponent;
  let fixture: ComponentFixture<SimSynInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimSynInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimSynInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
