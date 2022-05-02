import { environment } from '../../environments/environment';

export class Api {

  private static path(segment?: string): string {
    return `${ environment.api }${ segment }`;
  }

  private segments = {
    all: 'products',
    get: 'products/:id',
    search: 'products/search',
    delete: 'products/sku/:sku',
    'get-by-sku': 'products/sku/:sku',
    'delete-by-id': 'products/:id'
  }

  public resources = {
    all: Api.path(this.segments.all),
    get: Api.path(this.segments.get),
    search: Api.path(this.segments.search),
    delete: Api.path(this.segments.delete),
    'get-by-sku': Api.path(this.segments['get-by-sku']),
    'delete-by-id': Api.path(this.segments['delete-by-id'])
  }
}