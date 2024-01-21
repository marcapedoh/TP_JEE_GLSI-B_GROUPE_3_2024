import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageAceuilleComponent } from './page-aceuille.component';

describe('PageAceuilleComponent', () => {
  let component: PageAceuilleComponent;
  let fixture: ComponentFixture<PageAceuilleComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageAceuilleComponent]
    });
    fixture = TestBed.createComponent(PageAceuilleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
