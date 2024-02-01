import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import jsPDF from 'jspdf';
import { UserService } from 'src/app/services/user-service/user.service';
import { TransactionControllerService } from 'src/gs-api/src';

@Component({
  selector: 'app-consultation-tache',
  templateUrl: './consultation-tache.component.html',
  styleUrls: ['./consultation-tache.component.scss']
})
export class ConsultationTacheComponent implements OnInit{

  listTransaction:any;
  ngOnInit(): void {
      this.faireconsultationDesTransaction();
  }

  constructor(private transaction:TransactionControllerService, private userService:UserService, private http:HttpClient){}

  faireconsultationDesTransaction():void{
    this.http.get('http://localhost:8080/EgaWebService/v1/transactions/findAllByClientId/'+this.userService.getConnectedUser().id).subscribe(result=>{
      this.listTransaction=result;
    })
  }
  saveInPdf(){
    const doc = new jsPDF({
      orientation: 'landscape', // 'portrait' ou 'landscape'
      unit: 'mm', // unité de mesure
      format: 'a4', // format de page
    });
    const startY = 20;
    const margin = 10;
    const cellWidth = (doc.internal.pageSize.width - margin * 2) / 8;
    const cellHeight = 10;

    doc.setFontSize(12);
    doc.text('Liste des transactions Utilisateur', margin, startY);

    let currentY = startY + 15;

    doc.setFillColor(200, 200, 200); // Couleur de fond de l'en-tête
    doc.rect(margin, currentY, cellWidth, cellHeight, 'F'); // Dessiner la cellule

    // Remplir l'en-tête du tableau
    doc.setTextColor(0, 0, 0);
    doc.text('id', margin + 2, currentY + 8);
    doc.text('dateTran', margin + cellWidth + 2, currentY + 8);
    doc.text('Libelle', margin + cellWidth * 2 + 2, currentY + 8);
    doc.text('montant', margin + cellWidth * 3 + 2, currentY + 8);
    doc.text('typeTransaction', margin + cellWidth * 4 + 2, currentY + 8);
    doc.text('numero_Compte', margin + cellWidth * 5 + 2, currentY + 8);
    currentY += cellHeight;

    // Remplir les données du tableau
    this.listTransaction.forEach((transaction: { id: { toString: () => any; } | undefined; libelleTran: string; dateTran: any; montant: any; typeTransaction: any; numeroCpt: { ville: any; }; }) => {
      doc.rect(margin, currentY, cellWidth, cellHeight, 'S');
      doc.rect(margin + cellWidth, currentY, cellWidth, cellHeight, 'S');
      doc.rect(margin + cellWidth * 2, currentY, cellWidth, cellHeight, 'S');
      doc.rect(margin + cellWidth * 3, currentY, cellWidth, cellHeight, 'S');
      doc.rect(margin + cellWidth * 4, currentY, cellWidth, cellHeight, 'S');
      doc.rect(margin + cellWidth * 5, currentY, cellWidth, cellHeight, 'S');
      doc.rect(margin + cellWidth * 6, currentY, cellWidth, cellHeight, 'S');
      const idText = transaction.id !== undefined ? transaction.id.toString() : '';
      const Libelle = transaction.libelleTran?.substring(0, 5) + '...';
      doc.text(idText, margin + 2, currentY + 8);
      doc.text(transaction.dateTran || '', margin + cellWidth + 2, currentY + 8);
      doc.text(Libelle || '', margin + cellWidth * 2 + 2, currentY + 8);
      doc.text(transaction.montant || '', margin + cellWidth * 3 + 2, currentY + 8);
      doc.text(transaction.typeTransaction, margin + cellWidth * 4 + 2, currentY + 8);
      doc.text(transaction.numeroCpt.ville || '', margin + cellWidth * 5 + 2, currentY + 8);
  
      currentY += cellHeight;
    });
  
    doc.save('transaction_User.pdf');
  }

}
