import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubscriptConfComponent } from './subscript-conf.component';

describe('SubscriptConfComponent', () => {
  let component: SubscriptConfComponent;
  let fixture: ComponentFixture<SubscriptConfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubscriptConfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubscriptConfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
