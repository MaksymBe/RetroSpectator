import { TestBed, inject } from '@angular/core/testing';

import { ActionPointService } from './action-point.service';

describe('ActionPointService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ActionPointService]
    });
  });

  it('should be created', inject([ActionPointService], (service: ActionPointService) => {
    expect(service).toBeTruthy();
  }));
});
