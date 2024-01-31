import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeTranactionsComponent } from './liste-tranactions.component';

describe('ListeTranactionsComponent', () => {
  let component: ListeTranactionsComponent;
  let fixture: ComponentFixture<ListeTranactionsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListeTranactionsComponent]
    });
    fixture = TestBed.createComponent(ListeTranactionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
