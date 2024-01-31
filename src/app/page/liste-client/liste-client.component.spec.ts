import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeClientComponent } from './liste-client.component';

describe('ListeClientComponent', () => {
  let component: ListeClientComponent;
  let fixture: ComponentFixture<ListeClientComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListeClientComponent]
    });
    fixture = TestBed.createComponent(ListeClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
