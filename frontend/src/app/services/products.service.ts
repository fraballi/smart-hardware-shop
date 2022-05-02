import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product }    from '../models/Product';

import { SearchProduct } from '../models/SearchProduct';
import { Api }           from '../resources/Api';

@Injectable({ providedIn: 'root' })
export class ProductsService {

  constructor(private http: HttpClient) {
  }

  all(): Observable<Product[]> {
    const url = Api.resources.all;
    return this.http.get<Product[]>(url);
  }

  search(): Observable<SearchProduct[]> {
    const url = Api.resources.search;
    return this.http.get<SearchProduct[]>(url);
  }

  getBySku(sku: string): Observable<Product> {
    const url = Api.resources['get-by-sku'];
    const parameterizedUrl = url.replace(':sku', sku);
    return this.http.get<Product>(parameterizedUrl);
  }
}
