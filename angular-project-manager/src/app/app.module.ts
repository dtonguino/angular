import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { PaisListComponent } from './auth/pais-list/pais-list.component';
import { HeaderComponent } from './comoon/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    PaisListComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
