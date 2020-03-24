import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillConfComponent } from './skill-conf.component';

describe('SkillConfComponent', () => {
  let component: SkillConfComponent;
  let fixture: ComponentFixture<SkillConfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SkillConfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SkillConfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
