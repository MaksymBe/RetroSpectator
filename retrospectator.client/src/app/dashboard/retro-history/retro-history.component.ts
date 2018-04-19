import { Component, OnInit } from '@angular/core';
import {Retro} from '../../data-service/model/Retro';
import {RetroService} from '../../data-service/services/retro/retro.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-retro-history',
  templateUrl: './retro-history.component.html',
  styleUrls: ['./retro-history.component.css']
})
export class RetroHistoryComponent implements OnInit {

  private retros: Retro[];

  constructor(private retroService: RetroService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.retros = getRetros(params.teamKey);
    });
  }

  getRetros(teamIdentifier): Retro[] {
    const retros;
    this.retroService.getRetrosByTeam().subscribe(r => {
      retros = r;
    });

    return retros;
  }

  getLink(retroId) {
    let teamIdentifier;

    this.activatedRoute.params.subscribe(params => {
      teamIdentifier = params.teamKey;
    });

    return 'dashboard/' + teamIdentifier + '/retro/' + retroId;
  }
}
