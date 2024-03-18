import Chart from 'chart.js/auto';

interface CadastroCandidato {
    nome: string;
    idade: number;
    cpf: string;
    estado: string;
    cep: string;
    email: string;
    competencias: string[];
    descricao: string;
}

interface CadastroEmpresa {
    nome: string;
    cnpj: string;
    estado: string;
    cep: string;
    email: string;
    competencias: string[];
    descricao: string;
}

function listarLocalStorage(): (CadastroCandidato | CadastroEmpresa)[] {
    const dadosLocalStorage: (CadastroCandidato | CadastroEmpresa)[] = [];

    // Iterar sobre as chaves do LocalStorage e adicionar os dados ao array
    for (let i = 0; i < localStorage.length; i++) {
        const chave = localStorage.key(i);
        if (chave) {
            const valor = localStorage.getItem(chave);
            if (valor) {
                const cadastro: CadastroCandidato | CadastroEmpresa = JSON.parse(valor);
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
                // É um candidato
                const candidato = cadastro as CadastroCandidato;
                const card = `
                    <div class="col">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${candidato.nome}</h5>
                                <p class="card-text">Idade: ${candidato.idade}</p>
                                <p class="card-text">CPF: ${candidato.cpf}</p>
                                <p class="card-text">Estado: ${candidato.estado}</p>
                                <p class="card-text">CEP: ${candidato.cep}</p>
                                <p class="card-text">Email: ${candidato.email}</p>
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
