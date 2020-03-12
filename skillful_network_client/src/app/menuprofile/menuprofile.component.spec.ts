import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MenuprofileComponent } from './menuprofile.component';

describe('MenuprofileComponent', () => {
  let component: MenuprofileComponent;
  let fixture: ComponentFixture<MenuprofileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MenuprofileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MenuprofileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
