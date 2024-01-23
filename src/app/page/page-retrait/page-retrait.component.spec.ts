import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageRetraitComponent } from './page-retrait.component';

describe('PageRetraitComponent', () => {
  let component: PageRetraitComponent;
  let fixture: ComponentFixture<PageRetraitComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PageRetraitComponent]
    });
    fixture = TestBed.createComponent(PageRetraitComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
