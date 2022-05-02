import { Component, DoCheck, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Product }                                                 from '../../../models/Product';
import { ProductsService }                                         from '../../../services/products.service';

@Component({
             selector: 'app-products-content',
             templateUrl: './content.component.html',
             styleUrls: ['./content.component.css']
           })
export class ContentComponent implements OnInit, DoCheck {

  items: Product[] = [];
  loaded: boolean = false;

  private EMPTY: string = 'empty';
  private CLEAR: string = 'clear';

  @Input() filterSkuInput!: string;

  @Output() buyContentEmitter = new EventEmitter<Product>();

  constructor(private productsService: ProductsService) {
  }

  ngOnInit(): void {
    this.load();
  }

  ngDoCheck(): void {
    this.refresh();
  }

  private load(): void {
    const response = this.productsService.all();
    response.subscribe(res => {
      this.items.push(...res);
      this.loaded = true;
    });
  }

  private refresh(): void {
    let value = this.filterSkuInput?.trim();
    if (!this.loaded) {
      this.load();
    } else if (value !== this.EMPTY && value !== this.CLEAR) {
      const response = this.productsService.getBySku(value);
      response.subscribe(res => {
        this.items = [];
        this.filterSkuInput = this.EMPTY;
        this.items.push(res);
      })
    } else if (value === this.CLEAR) {
      this.load();
      this.filterSkuInput = this.EMPTY;
    }
  }

  notifyBuyProduct(product: Product) {
    this.buyContentEmitter.emit(product);
  }
}
