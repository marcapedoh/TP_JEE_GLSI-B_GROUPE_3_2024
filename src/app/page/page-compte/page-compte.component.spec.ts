import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageCompteComponent } from './page-compte.component';

describe('PageCompteComponent', () => {
  let component: PageCompteComponent;
  let fixture: ComponentFixture<PageCompteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageCompteComponent]
    });
    fixture = TestBed.createComponent(PageCompteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
