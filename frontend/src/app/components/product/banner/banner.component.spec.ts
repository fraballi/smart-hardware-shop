import { ComponentFixture, TestBed } from '@angular/core/testing';
import 'zone.js';
import 'zone.js/testing';
import { MenuComponent }             from '../../menu/menu.component';
import { BannerComponent }           from './banner.component';

describe('BannerComponent', () => {
  let component: BannerComponent;
  let fixture: ComponentFixture<BannerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BannerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render content', () => {
    const fixture = TestBed.createComponent(BannerComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('.app-banner')?.textContent).toContain('NEWS / PRODUCTS BANNER');
  });
});
