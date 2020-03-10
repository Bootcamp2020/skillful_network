import { TestBed } from '@angular/core/testing';

import { RegisterpasswordeService } from './registerpassworde.service';

describe('RegisterpasswordeService', () => {
  let service: RegisterpasswordeService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegisterpasswordeService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
