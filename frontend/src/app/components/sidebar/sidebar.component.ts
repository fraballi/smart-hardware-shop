import { Component, DoCheck, Input, ViewChild } from '@angular/core';
import { MatTable }                             from '@angular/material/table';
import { Product }                              from '../../models/Product';

@Component({
             selector: 'app-sidebar',
             templateUrl: './sidebar.component.html',
             styleUrls: ['./sidebar.component.css']
           })
export class SidebarComponent implements DoCheck {

  items: Product[] = [];
  displayedColumns: string[] = ['description', 'price', 'actions'];

  @Input() buyProductInput!: Product;

  @ViewChild('cart_table', { static: false }) table!: MatTable<any>;

  ngDoCheck(): void {
    if (this.buyProductInput?.sku) {
      const found = this.items.filter(prd => prd.sku === this.buyProductInput.sku);
      if (found.length == 0) {
        this.items.push(this.buyProductInput);
        this.items.sort((p1, p2) => p1.description.localeCompare(p2.description))
        this.table.renderRows();
      }
    }
  }

  clearCart() {
    this.buyProductInput = <Product>{};
    this.items = [];
    this.table.dataSource = [];
    this.table.renderRows();
  }

  deleteCartProduct(sku: string) {
    this.items.forEach((el, idx) => {
      if (el.sku === sku) {
        console.log(`Deleted: ${ sku }`);
        this.items.splice(idx, 1);

        if (this.items.length == 0) {
          this.clearCart();
        } else {
          this.table.renderRows();
        }
      }
    });
  }

  getTotalPrice() {
    return this.items.map(prd => prd.price).reduce((acc, value) => acc + value, 0);
  }
}
