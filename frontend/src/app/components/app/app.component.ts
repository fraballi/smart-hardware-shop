import { Component } from '@angular/core';
import { Product }   from '../../models/Product';

@Component({
             selector: 'app-root',
             templateUrl: './app.component.html',
             styleUrls: ['./app.component.css']
           })
export class AppComponent {
  private EMPTY = 'empty';

  title = 'Smart Hardware Shop';
  filterProductSku: string = this.EMPTY;
  buyProduct!: Product;

  public updateContent(sku: string): void {
    if (sku && sku.length === 0) {
      this.filterProductSku = this.EMPTY;
    } else {
      this.filterProductSku = sku;
    }
  }

  updateBuyProduct(product: Product) {
    this.buyProduct = product;
  }
}
