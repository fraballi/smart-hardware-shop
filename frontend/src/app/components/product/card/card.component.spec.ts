import { ComponentFixture, TestBed }                                 from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { Product }                                                   from '../../../models/Product';
import { CardComponent }                                             from './card.component';

class ProductDetailsDialogMock {
  open(component: any, config: any) {}
}

describe('CardComponent', () => {
  let component: CardComponent;
  let fixture: ComponentFixture<CardComponent>;
  let dialog: MatDialog;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
         imports: [MatDialogModule],
         providers: [
           { provide: MatDialog, useClass: ProductDetailsDialogMock },
           { provide: MAT_DIALOG_DATA, useValue: {} },
           { provide: MatDialogRef, useValue: {} }
         ],
         declarations: [CardComponent]
       })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should open product details dialog', function () {
    fixture = TestBed.createComponent(CardComponent);
    component = fixture.componentInstance;

    // Call dialog
    component.showItem(<Product>{});
    fixture.detectChanges();

    const compiled = fixture.nativeElement as HTMLElement;
    const querySelector = compiled.querySelector('mat-card');
    expect(querySelector?.textContent).toContain('Item:');
    expect(querySelector?.textContent).toContain('Price:');
  });
});
