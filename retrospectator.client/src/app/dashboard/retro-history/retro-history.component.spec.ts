import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RetroHistoryComponent } from './retro-history.component';

describe('RetroHistoryComponent', () => {
  let component: RetroHistoryComponent;
  let fixture: ComponentFixture<RetroHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RetroHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RetroHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
