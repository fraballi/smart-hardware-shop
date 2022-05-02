import { HttpClientModule } from '@angular/common/http';
import { TestBed }          from '@angular/core/testing';
import { ProductsService } from './products.service';

describe('ProductsService', () => {
  let service: ProductsService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
         imports:[HttpClientModule]
       })
      .compileComponents();

    service = TestBed.inject(ProductsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
