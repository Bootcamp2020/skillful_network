import { TestBed } from '@angular/core/testing';

import { JobDetailsService } from './job-details.service';

describe('JobDetailsService', () => {
  let service: JobDetailsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobDetailsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
