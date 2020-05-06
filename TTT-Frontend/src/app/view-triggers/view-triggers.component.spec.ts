import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTriggersComponent } from './view-triggers.component';

describe('ViewTriggersComponent', () => {
  let component: ViewTriggersComponent;
  let fixture: ComponentFixture<ViewTriggersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewTriggersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTriggersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
