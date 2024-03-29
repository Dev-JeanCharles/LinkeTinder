import { Empresa } from "../Models/Empresa";
import { Candidato } from "../Models/Candidato";
import {DTOEmpresa} from "../Models/dto/EmpresaDTO"
import {DTOCandidato} from "../Models/dto/CandidatoDTO"
import {construirCandidato} from "../Controller/estruturaCandidato"
import { criarGraficoBarras } from "../Controller/grafico"

function preencherPerfilEmpresa(empresa: Empresa): void {
    const nomeElement = document.querySelector('.nome');
    if (nomeElement) nomeElement.textContent = empresa.nome;

    const emailElement = document.querySelector('.email');
    if (emailElement) emailElement.textContent = empresa.email;

    const residenciaElement = document.querySelector('.dadosGeograficos');

    if (residenciaElement) {
        const estadoElement = residenciaElement.querySelector('.estado');
        const cepElement = residenciaElement.querySelector('.cep');
        if (estadoElement) estadoElement.textContent = empresa.estado;
        if (cepElement) cepElement.textContent = empresa.cep;
    }

    const cnpjElement = document.querySelector('.cnpj');
    if (cnpjElement) cnpjElement.textContent = empresa.cnpj;
    const competenciasElement = document.querySelector('.competencias');
    if (competenciasElement) {
        competenciasElement.innerHTML = empresa.competencias.map((competencia, index, array) => {
            return `<li class="me-1">${competencia}${index < array.length - 1 ? ',' : ''}</li>`;
        }).join('');
    }
    const descricaoElement = document.querySelector('.descricao');
    if (descricaoElement) descricaoElement.textContent = empresa.descricao;
    const cnpjLabelElement = document.querySelector('.cnpj-label');
    if (cnpjLabelElement) cnpjLabelElement.textContent = 'CNPJ:';
    const competenciasLabelElement = document.querySelector('.competencias-label');
    if (competenciasLabelElement) competenciasLabelElement.textContent = 'Competências:';
    const descricaoLabelElement = document.querySelector('.descricao-label');
    if (descricaoLabelElement) descricaoLabelElement.textContent = 'Sobre:';
}

document.addEventListener('DOMContentLoaded', () => {
    const dtoEmpresa = new DTOEmpresa();
    const empresas = dtoEmpresa.get();

    const cnpjParam = new URLSearchParams(window.location.search).get("cnpj");
    if (!cnpjParam) {
        console.error("CNPJ não encontrado na URL.");
        return;
    }

    const empresa = empresas.find((c: Empresa) => c.cnpj === cnpjParam);
    if (empresa) {
        preencherPerfilEmpresa(empresa);
    } else {
        console.error('Empresa não encontrada.');
    }

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
