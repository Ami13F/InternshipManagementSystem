import { Component, OnInit, Input, Output , EventEmitter} from '@angular/core';
import { Internship } from '../../data/Internship';
import { MatDialog } from '@angular/material';
import { InternshipModalDetailsComponent } from '../../internship-modal-details/internship-modal-details.component';
import {InternshipDTO} from '../../data/InternshipDTO';



@Component({
  selector: 'app-internship-item',
  templateUrl: './internship-item.component.html',
  styleUrls: ['./internship-item.component.css']
})
export class InternshipItemComponent implements OnInit {

  @Input() internship : InternshipDTO;
  @Output() internshipSelected = new EventEmitter<void>();

   description = "Internshipul este poate cea mai populara forma de program. I" +
"n ultimii ani au fost lansate foarte multe programe de internship, ‑ ind o modalitate prin care"+
  "companiile isi recruteaza viitori angajati. Pentru tine, internship-ul reprezinta o oportunitate de invatare,"+
  "o modalitate prin care poti obtine experienta practica in domeniul de interes*.";

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  openDialog() {
    this.dialog.open(InternshipModalDetailsComponent, { data : this.internship });
  }
  onSelected(){
    this.internshipSelected.emit();
  }

}
