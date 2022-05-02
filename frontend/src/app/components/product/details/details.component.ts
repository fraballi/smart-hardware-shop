import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA }   from '@angular/material/dialog';
import { Product }           from '../../../models/Product';

@Component({
             selector: 'app-details',
             templateUrl: './details.component.html',
             styleUrls: ['./details.component.css']
           })
export class DetailsComponent {

  item!: Product;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
    this.item = data.product;
  }
}
