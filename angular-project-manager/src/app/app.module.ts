import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { InputTextModule, ButtonModule, DataTableModule, DialogModule, GrowlModule, ConfirmDialogModule } from 'primeng/primeng';
import { ConfirmationService } from 'primeng/primeng';

import { AppComponent } from './app.component';
import { PaisListComponent } from './auth/pais-list/pais-list.component';
import { HeaderComponent } from './common/header/header.component';
import { LoaderComponent } from './common/loader/loader.component';

import { HttpService } from 'app/common/services/http.service';
import { PaisListService } from './auth/pais-list/services/pais-list.service';

@NgModule({
  declarations: [
    AppComponent,
    PaisListComponent,
    HeaderComponent,
    LoaderComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    DialogModule,
    GrowlModule,
    ConfirmDialogModule
  ],
  providers: [
    HttpService,
    ConfirmationService,
    PaisListService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
