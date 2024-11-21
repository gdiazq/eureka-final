import { Component, OnInit } from '@angular/core';
import { Chart, LinearScale, BarElement, BarController, Title, CategoryScale } from 'chart.js';
import { ResultService } from '../../service/result.service';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html'
})
export class ResultComponent implements OnInit {

  public chart: any;

  constructor(
    private resultService: ResultService
  ) { Chart.register(LinearScale, BarController, BarElement, Title, CategoryScale); }

  ngOnInit(): void {
    this.loadResults();
  }

  loadResults(): void {
    this.resultService.showResults().subscribe
      (data => {
        const labels = data.map(item => item[0]);
        const values = data.map(item => item[1]);
        this.chart = new Chart('barChart', {
          type: 'bar',
          data: {
            labels: labels,
            datasets: [{
              label: 'Cantidad por zona',
              data: values,
              backgroundColor: 'rgba(75, 192, 192, 0.2)',
              borderColor: 'rgba(75, 192, 192, 1)',
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              x: {
                type: 'category',
                title: {
                  display: true,
                  text: 'Zonas'
                }
              },
              y: {
                beginAtZero: true,
                title: {
                  display: true,
                  text: 'Personas'
                }
              }
            }
          }
        })
      });
  }
}
