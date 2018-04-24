import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPointFormComponent } from './edit-point-form.component';

describe('EditPointFormComponent', () => {
  let component: EditPointFormComponent;
  let fixture: ComponentFixture<EditPointFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditPointFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPointFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
