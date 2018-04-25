import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionPointComponent } from './action-point.component';

describe('ActionPointComponent', () => {
  let component: ActionPointComponent;
  let fixture: ComponentFixture<ActionPointComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActionPointComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActionPointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
