import { HttpClientModule }                         from '@angular/common/http';
import { ComponentFixture, TestBed }                from '@angular/core/testing';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Observable, of }                           from 'rxjs';
import { Product }                                  from '../../../models/Product';
import { ProductsService }                          from '../../../services/products.service';
import { CardComponent }                            from '../card/card.component';
import { ContentComponent }                         from './content.component';

describe('ContentComponent', () => {
  let component: ContentComponent;
  let fixture: ComponentFixture<ContentComponent>;

  class ProductServiceMock {

    private mockProduct: Product = {
      id: 0,
      description: 'Test Product',
      sku: 'Test Sku',
      price: 0,
      quantity: 0,
      url: ''
    };

    all(): Observable<Product[]> {
      return of([this.mockProduct]);
    }

    getBySku(): Observable<Product> {
      return of(this.mockProduct);
    }
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
         imports: [HttpClientModule],
         providers: [
           {
             provide: ProductsService, useClass: ProductServiceMock
           },
           { provide: MatDialog, useValue: {} },
           { provide: MAT_DIALOG_DATA, useValue: {} },
           { provide: MatDialogRef, useValue: {} }
         ],
         declarations: [ContentComponent, CardComponent]
       })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have product cards', function () {
    fixture = TestBed.createComponent(ContentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    const compiled = fixture.nativeElement as HTMLElement;

    const querySelector = compiled.querySelector('app-card');
    expect(querySelector?.textContent).toMatch('Item:');
    expect(querySelector?.innerHTML).toMatch('Test Product');
  });
});
