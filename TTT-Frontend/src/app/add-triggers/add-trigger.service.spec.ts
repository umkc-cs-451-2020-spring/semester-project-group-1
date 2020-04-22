import { TestBed } from '@angular/core/testing';

import { AddTriggerService } from './add-trigger.service';

describe('AddTriggerService', () => {
  let service: AddTriggerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AddTriggerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
