import { Empresa } from "../Models/Empresa";
import { Candidato } from "../Models/Candidato";
import { DTOEmpresa } from "../Models/dto/EmpresaDTO";
import { DTOCandidato } from "../Models/dto/CandidatoDTO";
import { construirCandidato } from "../Controller/estruturaCandidato";
import { criarGraficoBarras } from "../Controller/grafico";

function preencherPerfilEmpresa(empresa: Empresa): void {
    const nomeElement = document.querySelector('.nome');
    const emailElement = document.querySelector('.email');
    const residenciaElement = document.querySelector('.dadosGeograficos');
    const cnpjElement = document.querySelector('.cnpj');
    const competenciasElement = document.querySelector('.competencias');
    const descricaoElement = document.querySelector('.descricao');
    const cnpjLabelElement = document.querySelector('.cnpj-label');
    const competenciasLabelElement = document.querySelector('.competencias-label');
    const descricaoLabelElement = document.querySelector('.descricao-label');

    if (nomeElement) nomeElement.textContent = empresa.nome;
    if (emailElement) emailElement.textContent = empresa.email;
    if (residenciaElement) {
        const estadoElement = residenciaElement.querySelector('.estado');
        const cepElement = residenciaElement.querySelector('.cep');
        if (estadoElement) estadoElement.textContent = empresa.estado;
        if (cepElement) cepElement.textContent = empresa.cep;
    }
    if (cnpjElement) cnpjElement.textContent = empresa.cnpj;
    if (competenciasElement) {
        competenciasElement.innerHTML = empresa.competencias.map((competencia, index, array) => {
            return `<li class="me-1">${competencia}${index < array.length - 1 ? ',' : ''}</li>`;
        }).join('');
    }
    if (descricaoElement) descricaoElement.textContent = empresa.descricao;
    if (cnpjLabelElement) cnpjLabelElement.textContent = 'CNPJ:';
    if (competenciasLabelElement) competenciasLabelElement.textContent = 'Competências:';
    if (descricaoLabelElement) descricaoLabelElement.textContent = 'Sobre:';
}

// Evento disparado quando a página é carregada
document.addEventListener('DOMContentLoaded', () => {
    const dtoEmpresa = new DTOEmpresa();
    const empresas = dtoEmpresa.get();

    const cnpjParam = new URLSearchParams(window.location.search).get("cnpj");
    if (!cnpjParam) {
        console.error("CNPJ não encontrado na URL.");
        return;
    }

    // Buscar empresa correspondente ao CNPJ na URL
    const empresa = empresas.find((c: Empresa) => c.cnpj === cnpjParam);
    if (empresa) {
        preencherPerfilEmpresa(empresa);
    } else {
        console.error('Empresa não encontrada.');
    }

    // Obter candidatos e exibir na página
    const dtoCandidato = new DTOCandidato();
    const candidatos = dtoCandidato.get();
    exibirCandidatos(candidatos);

    criarGraficoBarras(candidatos);
});

function exibirCandidatos(candidatos: Candidato[]): void {
    const listaCandidatosElement = document.getElementById("lista-candidatos");

    if (listaCandidatosElement) {
        listaCandidatosElement.innerHTML = '';

        candidatos.forEach((candidato: Candidato) => {
            listaCandidatosElement.innerHTML += construirCandidato(candidato);
        });
    }
}
