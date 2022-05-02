import { Component, DoCheck, Input, ViewChild } from '@angular/core';
import { MatTable }                             from '@angular/material/table';
import { map, Observable, of }                  from 'rxjs';
import { Product }                              from '../../models/Product';

@Component({
             selector: 'app-sidebar',
             templateUrl: './sidebar.component.html',
             styleUrls: ['./sidebar.component.css']
           })
export class SidebarComponent implements DoCheck {

  size!: number;
  totals!: number;

  items: Observable<Product[]> = of([]);
  displayedColumns: string[] = ['description', 'price', 'actions'];

  @Input() buyProductInput!: Product;

  @ViewChild('cart_table', { static: false }) table!: MatTable<any>;

  ngDoCheck(): void {
    this.items.subscribe(items => {
      if (this.buyProductInput?.sku) {
        const result = items.filter(prd => prd.sku === this.buyProductInput.sku);
        if (result.length === 0) {
          items.push(this.buyProductInput);
          items.sort((p1, p2) => p1.description.localeCompare(p2.description))
          this.table.renderRows();
        }
      }

      this.size = items.length;
      this.totals = items.map(prd => prd.price).reduce((acc, value) => acc + value, 0)
    })
  }

  clearCart() {
    this.buyProductInput = <Product>{};
    this.items = of([]);
    this.table.renderRows();
  }

  removeProduct(item: Product) {
    this.items = this.items.pipe(map(src => src.filter(prd => prd.sku !== item.sku)));
    console.log(`Deleted: ${ item.sku }`);
  }
}
