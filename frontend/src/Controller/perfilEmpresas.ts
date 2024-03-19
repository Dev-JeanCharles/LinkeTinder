import { Empresa } from "../Models/Empresa";

function obterDadosEmpresa(): Empresa | null {
    const cnpjParam = new URLSearchParams(window.location.search).get('cnpj');
    if (!cnpjParam) return null;

    const empresaJSON = localStorage.getItem(cnpjParam);
    if (!empresaJSON) return null;

    return JSON.parse(empresaJSON);
}

function preencherPerfilEmpresa(empresa: Empresa): void {
    const nomeElement = document.querySelector('.nome');
    if (nomeElement) nomeElement.textContent = empresa.nome;

    const emailElement = document.querySelector('.email');
    if (emailElement) emailElement.textContent = empresa.email;

    const residenciaElement = document.querySelector('ul.dadosGeograficos');
    if (residenciaElement) {
        residenciaElement.children[0].textContent = `${empresa.estado},`;
        residenciaElement.children[1].textContent = empresa.cep;
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
    const empresa = obterDadosEmpresa();
    if (empresa) {
        preencherPerfilEmpresa(empresa);
    } else {
        console.error('Empresa não encontrada.');
    }
});
