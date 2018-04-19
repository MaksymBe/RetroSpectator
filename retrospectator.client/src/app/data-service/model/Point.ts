import {User} from './User';

export class Point {
  title: string;
  type: string;
  date: string;
  id: number;
  author: User;

  constructor(title: string, type: string, date: string) {
    this.title = title;
    this.type = type;
    this.date = date;
  }
}
