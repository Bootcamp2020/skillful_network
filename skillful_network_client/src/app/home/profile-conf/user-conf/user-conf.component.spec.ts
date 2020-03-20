import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserConfComponent } from './user-conf.component';

describe('UserConfComponent', () => {
  let component: UserConfComponent;
  let fixture: ComponentFixture<UserConfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserConfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserConfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
