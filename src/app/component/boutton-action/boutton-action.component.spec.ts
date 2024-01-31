import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BouttonActionComponent } from './boutton-action.component';

describe('BouttonActionComponent', () => {
  let component: BouttonActionComponent;
  let fixture: ComponentFixture<BouttonActionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BouttonActionComponent]
    });
    fixture = TestBed.createComponent(BouttonActionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
