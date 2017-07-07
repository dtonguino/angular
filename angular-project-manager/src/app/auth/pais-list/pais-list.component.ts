import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pais-list',
  templateUrl: './pais-list.component.html',
  styleUrls: ['./pais-list.component.css']
})
export class PaisListComponent implements OnInit {
  isLoading = true;
  constructor() { }

  ngOnInit() {
  }

}
