import { Component, OnInit } from '@angular/core';
import { TableService } from '../../service/table.service';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html'
})
export class TableComponent implements OnInit {

  constructor(
    private tableService: TableService
  ) { }

  title: string = 'Tabla de Resultados';
  tableData: any[] = [];

  ngOnInit(): void {
    this.loadResultsToTable();
  }

  loadResultsToTable(): void {
    this.tableService.showResults().subscribe(
      (data: any[]) => {
        this.tableData = data.map(item => ({
          nombre: item[0],
          cantidadPersonas: item[1]
        }));
      },
      (error: any[]) => {
        console.log("Error en conseguir los resultados", error);
      }
    );
  }
  
}
