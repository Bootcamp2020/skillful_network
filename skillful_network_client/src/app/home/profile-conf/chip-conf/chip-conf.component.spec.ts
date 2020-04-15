import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChipConfComponent } from './chip-conf.component';

describe('ChipConfComponent', () => {
  let component: ChipConfComponent;
  let fixture: ComponentFixture<ChipConfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChipConfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChipConfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
