import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageDepotComponent } from './page-depot.component';

describe('PageDepotComponent', () => {
  let component: PageDepotComponent;
  let fixture: ComponentFixture<PageDepotComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageDepotComponent]
    });
    fixture = TestBed.createComponent(PageDepotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
