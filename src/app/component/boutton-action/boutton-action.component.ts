import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-boutton-action',
  templateUrl: './boutton-action.component.html',
  styleUrls: ['./boutton-action.component.scss']
})
export class BouttonActionComponent implements OnInit{

  @Input()
  isExporterVisible=true;
  @Output()
  clickEvent= new EventEmitter();
  ngOnInit(): void {
      
  }

  bottonExporterClick():void{
    this.clickEvent.emit();
  }
}
