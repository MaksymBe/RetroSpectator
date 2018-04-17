import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sprint',
  templateUrl: './sprint.component.html',
  styleUrls: ['./sprint.component.css']
})
export class SprintComponent implements OnInit {
  private titleInput: string;

  private pointsMinus = [];
  private pointsPlus = [];
  private isMine = true;

  constructor() {
  }

  ngOnInit() {
  }

  addPoint(type) {
    if (this.titleInput === '' || this.titleInput === undefined || this.titleInput === null) {
      return;
    }

    if (type === 'minus') {
      this.pointsMinus.push({type: 'minus', title: this.titleInput});
    }
    if (type === 'plus') {
      this.pointsPlus.push({type: 'plus', title: this.titleInput});
    }

    this.titleInput = '';
  }

  changePoints() {
    this.isMine = !this.isMine;

  }
}
