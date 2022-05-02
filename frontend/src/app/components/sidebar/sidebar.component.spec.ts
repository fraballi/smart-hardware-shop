import { HttpClientModule }          from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Product }                   from '../../models/Product';
import { SidebarComponent }          from './sidebar.component';

describe('SidebarComponent', () => {
  let component: SidebarComponent;
  let fixture: ComponentFixture<SidebarComponent>;

  const mockProduct: Product = {
    id: 0,
    description: 'Test Product',
    sku: 'Test Sku',
    price: 0,
    quantity: 0,
    url: ''
  };
  const mockProducts: Product[] = [mockProduct];

  beforeEach(async () => {

    await TestBed.configureTestingModule({
                                           imports: [HttpClientModule],
                                           declarations: [SidebarComponent]
                                         })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have cart', async function () {
    fixture = TestBed.createComponent(SidebarComponent);
    component = fixture.componentInstance;

    fixture.detectChanges();

    const compiled = fixture.debugElement.nativeElement as HTMLElement;
    let querySelector = compiled.querySelector('.app-sidebar-header');

    expect(querySelector?.innerHTML).toMatch('Shopping Cart');
  });
});
