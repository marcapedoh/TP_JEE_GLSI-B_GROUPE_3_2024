import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationTacheComponent } from './consultation-tache.component';

describe('ConsultationTacheComponent', () => {
  let component: ConsultationTacheComponent;
  let fixture: ComponentFixture<ConsultationTacheComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ConsultationTacheComponent]
    });
    fixture = TestBed.createComponent(ConsultationTacheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
