import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTriggersComponent } from './add-triggers.component';

describe('AddTriggersComponent', () => {
  let component: AddTriggersComponent;
  let fixture: ComponentFixture<AddTriggersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddTriggersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddTriggersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
