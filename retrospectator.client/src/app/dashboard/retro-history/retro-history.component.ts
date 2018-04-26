import { Component, OnInit } from '@angular/core';
import {Retro} from '../../data-service/model/Retro';
import {RetroService} from '../../data-service/services/retro/retro.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-retro-history',
  templateUrl: './retro-history.component.html',
  styleUrls: ['./retro-history.component.css']
})
export class RetroHistoryComponent implements OnInit {

  public retros: Retro[];

  constructor(private retroService: RetroService,
              private activatedRoute: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.getRetros(params.teamKey);
    });
  }

  getRetros(teamIdentifier): void {
    this.retroService.getRetrosByTeam(teamIdentifier).subscribe(r => {
      this.retros = r.slice(0, r.length - 1);
    });
  }

  rotate(retro) {
    this.activatedRoute.params.subscribe(params => {
      console.log(params.teamKey);
      console.log(retro);
      console.log(`/dashboard/${params.teamKey}/retro/${retro.id}`);
      this.router.navigate(['dashboard', params.teamKey, 'retro', retro.id]);
    });
  }

  sortRetros(retros: Retro[]): Retro[] {
    const orderedRetros = retros;

    orderedRetros.sort((a, b) => {
      return (a.finishDate < b.finishDate) ? 1 : -1;
    });

    return orderedRetros;
  }
}
