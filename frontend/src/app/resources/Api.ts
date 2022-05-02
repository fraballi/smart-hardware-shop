export class Api {

  private static root: string = 'http://localhost:8080/api/v1';
  private static segments = {
    'all': '/products',
    'get': '/products/:id',
    'search': '/products/search',
    'get-by-sku': '/products/sku/:sku',
    'delete': '/products/sku/:sku',
    'delete-by-id': '/products/:id'
  }
  public static resources = {
    'all': Api.path(Api.segments.all),
    'get': Api.path(Api.segments.get),
    'search': Api.path(Api.segments.search),
    'get-by-sku': Api.path(Api.segments['get-by-sku']),
    'delete': Api.path(Api.segments.delete),
    'delete-by-id': Api.path(Api.segments['delete-by-id'])
  }

  private static path(segment?: string): string {
    return `${ this.root }${ segment }`;
  }
}