import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { PaisListComponent } from './auth/pais-list/pais-list.component';
import { HeaderComponent } from './comoon/header/header.component';
import { LoaderComponent } from './comoon/loader/loader.component';

import {PaisListService} from './auth/pais-list/services/pais-list.service';

@NgModule({
  declarations: [
    AppComponent,
    PaisListComponent,
    HeaderComponent,
    LoaderComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [PaisListService],
  bootstrap: [AppComponent]
})
export class AppModule { }
