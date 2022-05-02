import { HttpClientModule }        from '@angular/common/http';
import { NgModule }                from '@angular/core';
import { ReactiveFormsModule }     from '@angular/forms';
import { MatAutocompleteModule }   from '@angular/material/autocomplete';
import { MatBadgeModule }          from '@angular/material/badge';
import { MatButtonModule }         from '@angular/material/button';
import { MatCardModule }           from '@angular/material/card';
import { MatDialogModule }         from '@angular/material/dialog';
import { MatIconModule }           from '@angular/material/icon';
import { MatInputModule }          from '@angular/material/input';
import { MatMenuModule }           from '@angular/material/menu';
import { MatProgressBarModule }    from '@angular/material/progress-bar';
import { MatSidenavModule }        from '@angular/material/sidenav';
import { MatSlideToggleModule }    from '@angular/material/slide-toggle';
import { MatTableModule }          from '@angular/material/table';
import { MatToolbarModule }        from '@angular/material/toolbar';
import { BrowserModule }           from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Api }                     from '../../resources/Api';
import { MenuComponent }           from '../menu/menu.component';
import { BannerComponent }         from '../product/banner/banner.component';
import { CardComponent }           from '../product/card/card.component';
import { ContentComponent }        from '../product/content/content.component';
import { DetailsComponent }        from '../product/details/details.component';
import { SearchComponent }         from '../product/search/search.component';
import { SidebarComponent }        from '../sidebar/sidebar.component';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent }     from './app.component';

@NgModule({
            declarations: [
              AppComponent,
              MenuComponent,
              SidebarComponent,
              BannerComponent,
              ContentComponent,
              CardComponent,
              DetailsComponent,
              SearchComponent
            ],
            imports: [
              BrowserModule,
              HttpClientModule,
              AppRoutingModule,
              BrowserAnimationsModule,
              MatAutocompleteModule,
              MatInputModule,
              ReactiveFormsModule,
              MatSlideToggleModule,
              MatIconModule,
              MatMenuModule,
              MatButtonModule,
              MatToolbarModule,
              MatSidenavModule,
              MatTableModule,
              MatCardModule,
              MatBadgeModule,
              MatDialogModule,
              MatProgressBarModule
            ],
            providers: [Api],
            bootstrap: [AppComponent]
          })
export class AppModule {
}
