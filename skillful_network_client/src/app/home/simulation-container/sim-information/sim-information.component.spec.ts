import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SimInformationComponent } from './sim-information.component';

describe('SimInformationComponent', () => {
  let component: SimInformationComponent;
  let fixture: ComponentFixture<SimInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SimInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SimInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
