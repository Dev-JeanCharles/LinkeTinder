import Chart from 'chart.js/auto';
import { mascararNome, mascararEmail, mascararCPF } from '../Utils/mascarar';
import {Candidato} from '../Models/Candidato'
import {Empresa} from '../Models/Empresa'

function listarLocalStorage(): (Candidato | Empresa)[] {
    const dadosLocalStorage: (Candidato | Empresa)[] = [];

    for (let i = 0; i < localStorage.length; i++) {
        const chave = localStorage.key(i);
        if (chave) {
            const valor = localStorage.getItem(chave);
            if (valor) {
                let cadastro: Candidato | Empresa;
                try {
                    cadastro = JSON.parse(valor);
                } catch (error) {
                    console.error(`Erro ao analisar o valor para a chave ${chave}:`, error);
                    continue;
                }
                dadosLocalStorage.push(cadastro);
            }
        }
    }
    return dadosLocalStorage;
}

function exibirDadosLocalStorage(): void {
    const lista = document.getElementById("lista-candidatos");
    if (lista) {
        lista.innerHTML = '';
        const dados = listarLocalStorage();
        dados.forEach(cadastro => {
            if ('cpf' in cadastro) {
                const candidato = cadastro as Candidato;
                const card = `
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${mascararNome(candidato.nome)}</h5>
                            <p class="card-text">Idade: ${candidato.idade}</p>
                            <p class="card-text">CPF: ${mascararCPF(candidato.cpf)}</p>
                            <p class="card-text">Estado: ${candidato.estado}</p>
                            <p class="card-text">CEP: ${candidato.cep}</p>
                            <p class="card-text">Email: ${mascararEmail(candidato.email)}</p>
                            <p class="card-text">Competências: ${candidato.competencias.join(", ")}</p>
                            <p class="card-text">Descrição: ${candidato.descricao}</p>
                        </div>
                    </div>
                </div>
            `;
            lista.innerHTML += card;
        }
    });
}
}

function criarGraficoBarras(): void {
    const dados = listarLocalStorage();
    const competenciasContagem: { [key: string]: number } = {};
    dados.forEach(candidato => {
        candidato.competencias.forEach(competencia => {
            if (!competenciasContagem[competencia]) {
                competenciasContagem[competencia] = 1;
            } else {
                competenciasContagem[competencia]++;
            }
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

exibirDadosLocalStorage();
criarGraficoBarras();
