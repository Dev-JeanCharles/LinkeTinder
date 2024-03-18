import {Candidato} from "../Models/Candidato";
import Chart from "chart.js/auto";

export function criarGraficoBarras(dados: Candidato[]): void {
    const competenciasContagem: { [key: string]: number } = {};

    dados.forEach(candidato => {
        candidato.competencias.forEach(competencia => {
            competenciasContagem[competencia] = (competenciasContagem[competencia] || 0) + 1;
        });
    });

    const labels = Object.keys(competenciasContagem);
    const data = Object.values(competenciasContagem);

    const graficoBarras = new Chart('grafico-barras', {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Usuários por Competência',
                data: data,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    title: {
                        display: true,
                        text: 'Número de Usuários'
                    }
                },
                x: {
                    title: {
                        display: true,
                        text: 'Competência'
                    }
                }
            }
        }
    });
}