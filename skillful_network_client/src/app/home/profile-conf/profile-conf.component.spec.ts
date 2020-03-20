import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileConfComponent } from './profile-conf.component';

describe('ProfileConfComponent', () => {
  let component: ProfileConfComponent;
  let fixture: ComponentFixture<ProfileConfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileConfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileConfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
