import { HttpClientModule }          from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatAutocompleteModule }     from '@angular/material/autocomplete';
import { AppComponent }              from '../../app/app.component';
import { SearchComponent } from './search.component';

describe('SearchComponent', () => {
  let component: SearchComponent;
  let fixture: ComponentFixture<SearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
         imports: [HttpClientModule, MatAutocompleteModule],
         providers:[AppComponent],
         declarations: [SearchComponent]
       })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
