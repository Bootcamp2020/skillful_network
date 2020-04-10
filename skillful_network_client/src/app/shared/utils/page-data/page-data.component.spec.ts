import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDataComponent } from './page-data.component';

describe('PageDataComponent', () => {
  let component: PageDataComponent;
  let fixture: ComponentFixture<PageDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PageDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PageDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
