import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatDialog }                              from '@angular/material/dialog';
import { Product }                                from '../../../models/Product';
import { DetailsComponent }                       from '../details/details.component';

@Component({
             selector: 'app-card',
             templateUrl: './card.component.html',
             styleUrls: ['./card.component.css']
           })
export class CardComponent {

  @Input() item!: Product;

  @Output() buyProductEmitter = new EventEmitter<Product>();

  constructor(private detailsDialog: MatDialog) {
  }

  buyItem(item: Product) {
    this.buyProductEmitter.emit(item);
  }

  showItem(item: Product) {
    const options = { width: '400px', data: { product: item } };
    this.detailsDialog.open(DetailsComponent, options);
  }
}
