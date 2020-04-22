import { TestBed } from '@angular/core/testing';

import { EditTriggerService } from './edit-trigger.service';

describe('EditTriggerService', () => {
  let service: EditTriggerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditTriggerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
