import { TestBed } from '@angular/core/testing';

import { ViewTriggersService } from './view-triggers.service';

describe('ViewTriggersService', () => {
  let service: ViewTriggersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewTriggersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
