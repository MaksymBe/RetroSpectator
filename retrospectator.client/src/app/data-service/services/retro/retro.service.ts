import { Injectable } from '@angular/core';
import {of} from 'rxjs/observable/of';

@Injectable()
export class RetroService {

  constructor() { }

  getRetrosByTeam() {
    return of([]);
  }
}
