import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTriggersComponent } from './edit-triggers.component';

describe('EditTriggersComponent', () => {
  let component: EditTriggersComponent;
  let fixture: ComponentFixture<EditTriggersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditTriggersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTriggersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
