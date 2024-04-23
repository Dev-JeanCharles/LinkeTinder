import { Candidato } from "../Models/Candidato";
import { DTOCandidato } from "../Models/dto/CandidatoDTO";
import { DTOEmpresa } from "../Models/dto/EmpresaDTO";
import { aplicarMascara } from "../Utils/mascarar";

function preencherPerfilCandidato(candidato: Candidato): void {
    const nomeElement = document.querySelector('.nome');
    if (nomeElement) nomeElement.textContent = candidato.nome;

    const emailElement = document.querySelector('.email');
    if (emailElement) emailElement.textContent = aplicarMascara(candidato.email, 'email');

    const idadeElement = document.querySelector('.idade');
    if (idadeElement) idadeElement.textContent = `${candidato.idade} anos`;

    const cpfElement = document.querySelector('.cpf');
    if (cpfElement) cpfElement.textContent = aplicarMascara(candidato.cpf, 'cpf');

    const competenciasElement = document.querySelector('.competencias');
    if (competenciasElement) {
        competenciasElement.innerHTML = candidato.competencias.map(competencia => `<li>${competencia}</li>`).join('');
    }

    const descricaoElement = document.querySelector('.sobre');
    if (descricaoElement) descricaoElement.textContent = candidato.descricao;
}

// Evento disparado quando a página é carregada
document.addEventListener('DOMContentLoaded', () => {
    // Obter candidatos e empresas
    const candidatodto = new DTOCandidato();
    const candidatos = candidatodto.get();

    const cpfParam = new URLSearchParams(window.location.search).get("cpf");
    if (!cpfParam) {
        console.error("CPF não encontrado na URL.");
        return;
    }

    const candidato = candidatos.find((c: Candidato) => c.cpf === cpfParam);
    if (candidato) {
        preencherPerfilCandidato(candidato);
    } else {
        console.error('Candidato não encontrado.');
    }

    mostrarEmpresas();
});

function mostrarEmpresas(): void {
    const empresadto = new DTOEmpresa();
    const empresas = empresadto.get();
    const listaEmpresasElement = document.getElementById("lista-empresas");

    if (listaEmpresasElement) {
        listaEmpresasElement.innerHTML = '';

        empresas.forEach(empresa => {
            const card = `
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <h5 class="card-title">${aplicarMascara(empresa.nome, 'nome')}</h5>
                            <p class="card-text">CNPJ: ${aplicarMascara(empresa.cnpj, 'cnpj')}</p>
                            <p class="card-text">Estado: ${empresa.estado}</p>
                            <p class="card-text">CEP: ${empresa.cep}</p>
                            <p class="card-text">Email: ${aplicarMascara(empresa.email, 'email')}</p>
                            <p class="card-text">Competências: ${empresa.competencias.slice(0, -1).join(", ")}${empresa.competencias.length > 1 ? ',' : ''} ${empresa.competencias.slice(-1)}</p>   
                            <p class="card-text">Descrição: ${empresa.descricao}</p>
                        </div>
                    </div>
                </div>
            `;
            listaEmpresasElement.innerHTML += card;
        });
    }
}
