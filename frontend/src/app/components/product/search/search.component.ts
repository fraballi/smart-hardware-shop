import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl }                     from '@angular/forms';
import { MatOptionSelectionChange }        from '@angular/material/core';
import { map, Observable, startWith }      from 'rxjs';
import 'zone.js';
import 'zone.js/testing';
import { SearchProduct }                   from '../../../models/SearchProduct';
import { ProductsService }                 from '../../../services/products.service';
import { AppComponent }                    from '../../app/app.component';

@Component({
             selector: 'app-products-search',
             templateUrl: './search.component.html',
             styleUrls: ['./search.component.css']
           })
export class SearchComponent {

  options: SearchProduct[] = [];
  filteredProducts: Observable<SearchProduct[]>;
  productsSearchCtrl: FormControl = new FormControl();

  @Output() searchEmitter = new EventEmitter<string>();

  constructor(private productsService: ProductsService, private app: AppComponent) {
    this.filteredProducts = this
      .productsSearchCtrl
      .valueChanges
      .pipe(startWith(''), map(
        description => (
          description ? this.filterProducts(description) : this.options.slice()
        ))
      );
  }

  private filterProducts(description: string): SearchProduct[] {
    const value = description.toLowerCase();
    let searchProducts = this.productsService.search();

    let results: SearchProduct[] = [];
    searchProducts.subscribe(searchResults => {
      const matches = searchResults
        .filter(product =>
                  product.description
                    .toLowerCase()
                    .includes(value)
        );
      results.push(...matches);
    });

    return results;
  }

  selected($event: MatOptionSelectionChange<string>) {
    let sku = $event.source.id;
    this.searchEmitter.emit(sku);
  }

  public updateSearch($event: Event) {
    const searchBox = $event.target as HTMLInputElement;
    if (searchBox?.value.trim() === '') {
      this.searchEmitter.emit('clear');
    }
  }
}
