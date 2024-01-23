import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageVirementComponent } from './page-virement.component';

describe('PageVirementComponent', () => {
  let component: PageVirementComponent;
  let fixture: ComponentFixture<PageVirementComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageVirementComponent]
    });
    fixture = TestBed.createComponent(PageVirementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
