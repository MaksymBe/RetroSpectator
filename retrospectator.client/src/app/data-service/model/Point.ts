import {User} from './User';
import {Retro} from './Retro';

export class Point {
  title: string;
  type: string;
  date: string;
  id: number;
  author: User;
  retro: Retro;

  constructor(title: string, type: string, date: string) {
    this.title = title;
    this.type = type;
    this.date = date;
  }
}
