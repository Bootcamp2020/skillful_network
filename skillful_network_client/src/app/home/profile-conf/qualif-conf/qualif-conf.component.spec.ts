import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QualifConfComponent } from './qualif-conf.component';

describe('QualifConfComponent', () => {
  let component: QualifConfComponent;
  let fixture: ComponentFixture<QualifConfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QualifConfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QualifConfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
