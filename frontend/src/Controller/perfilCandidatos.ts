import { mascararCPF, mascararEmail } from '../Utils/mascarar';

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

function obterDadosCandidato(): CadastroCandidato | null {
    const cpfParam = new URLSearchParams(window.location.search).get('cpf');
    if (!cpfParam) return null;

    const candidatoJSON = localStorage.getItem(cpfParam);
    if (!candidatoJSON) return null;

    return JSON.parse(candidatoJSON);
}

function preencherPerfilCandidato(candidato: CadastroCandidato): void {
    const nomeElement = document.querySelector('.nome');
    if (nomeElement) nomeElement.textContent = candidato.nome;

    const emailElement = document.querySelector('.email');
    if (emailElement) emailElement.textContent = candidato.email;

    const residenciaElement = document.querySelector('ul.dadosGeograficos');
    if (residenciaElement) {
        residenciaElement.children[0].textContent = candidato.estado + ',';
        residenciaElement.children[1].textContent = candidato.cep;
    }

    const idadeElement = document.querySelector('ul.dadosDemograficos > li:nth-child(1)');
    if (idadeElement) idadeElement.textContent = candidato.idade.toString() + " anos";

    const cpfElement = document.querySelector('ul.dadosDemograficos > li:nth-child(2)');
    if (cpfElement) cpfElement.textContent = candidato.cpf;

    const competenciasElement = document.querySelector('ul.dadosDemograficos > li:nth-child(3) > ul');
    if (competenciasElement) {
        competenciasElement.innerHTML = candidato.competencias.map((competencia, index, array) => {
            if (index < array.length - 1) {
                return `<li class="me-1">${competencia},</li>`;
            } else {
                return `<li class="me-1">${competencia}</li>`;
            }
        }).join('');
    }

    const descricaoElement = document.querySelector('ul.dadosDemograficos > li:nth-child(4) > div');
    if (descricaoElement) descricaoElement.textContent = candidato.descricao;
}

function exibirVagasEmpresas(): void {
    const listaEmpresasElement = document.getElementById("lista-empresas");
    if (listaEmpresasElement) {
        listaEmpresasElement.innerHTML = '';

        // Aqui você busca as empresas do localStorage
        const dadosLocalStorage = listarLocalStorage();

        // Filtra apenas as empresas
        const empresas = dadosLocalStorage.filter((cadastro) => 'cnpj' in cadastro) as CadastroEmpresa[];

        empresas.forEach(empresa => {
            const card = `
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${empresa.nome}</h5>
                            <p class="card-text">CNPJ: ${empresa.cnpj}</p>
                            <p class="card-text">Estado: ${empresa.estado}</p>
                            <p class="card-text">CEP: ${empresa.cep}</p>
                            <p class="card-text">Email: ${empresa.email}</p>
                            <p class="card-text">Competências: ${empresa.competencias.join(", ")}</p>
                            <p class="card-text">Descrição: ${empresa.descricao}</p>
                        </div>
                    </div>
                </div>
            `;
            listaEmpresasElement.innerHTML += card;
        });
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const candidato = obterDadosCandidato();
    if (candidato) {
        preencherPerfilCandidato(candidato);
        exibirVagasEmpresas(); // Chama a função para exibir as vagas de empresas
    } else {
        console.error('Candidato não encontrado.');
    }
});
