import { Empresa } from "../Models/Empresa";
import { DTOEmpresa } from "../Models/dto/EmpresaDTO";

let nomeEmpresa: string;
let emailEmpresa: string;
let cnpjEmpresa: string;
let paisEmpresa: string;
let estadoEmpresa: string;
let cepEmpresa: string;
let descricaoEmpresa: string;
let competenciasEmpresa: string[] = [];

function main(): void {
    const formEmpresa: HTMLFormElement | null = document.forms.namedItem("form2");
    formEmpresa?.addEventListener("submit", handleSubmitEmpresa);
}

function handleSubmitEmpresa(event: Event): void {
    event.preventDefault();

    if (validarCamposEmpresa()) {
        const novaEmpresa = criarNovaEmpresa();

        const empresadto = new DTOEmpresa();
        empresadto.add(novaEmpresa);

        alert("Cadastro realizado com sucesso!");

        const encodedCnpj = encodeURIComponent(cnpjEmpresa);
        window.location.href = `perfilEmpresa.html?cnpj=${encodedCnpj}`;
    } else {
        alert("Por favor, preencha todos os campos obrigatórios.");
    }
}

function validarCamposEmpresa(): boolean {
    const camposObrigatorios: string[] = ["nome", "email", "cnpj", "pais", "estado", "cep", "descricao"];
    const emailRegex = /^[^\s@]+@(?!.*\d)[^\s@]+\.[^\s@]+$/;

    for (const campo of camposObrigatorios) {
        const input: HTMLInputElement | null = document.getElementById(campo) as HTMLInputElement;
        if (!input || input.value.trim() === "") {
            return false;
        } else if (campo === "email" && !emailRegex.test(input.value)) {
            alert('O e-mail inserido não é válido. Por favor, verifique e tente novamente.');
            return false;
        }
    }
    return true;
}

function criarNovaEmpresa(): Empresa {
    return new Empresa(
        nomeEmpresa,
        emailEmpresa,
        cnpjEmpresa,
        paisEmpresa,
        estadoEmpresa,
        cepEmpresa,
        descricaoEmpresa,
        competenciasEmpresa
    );
}

function iniciarListenersEmpresa(): void {
    const camposObrigatorios: string[] = ["nome", "email", "cnpj", "pais", "estado", "cep", "descricao"];
    camposObrigatorios.forEach((campo) => {
        const input: HTMLInputElement | null = document.getElementById(campo) as HTMLInputElement;
        input?.addEventListener("change", () => {
            switch (campo) {
                case "nome":
                    nomeEmpresa = input.value;
                    break;
                case "email":
                    emailEmpresa = input.value;
                    break;
                case "cnpj":
                    cnpjEmpresa = input.value;
                    break;
                case "pais":
                    paisEmpresa = input.value;
                    break;
                case "estado":
                    estadoEmpresa = input.value;
                    break;
                case "cep":
                    cepEmpresa = input.value;
                    break;
                case "descricao":
                    descricaoEmpresa = input.value;
                    break;
                default:
                    break;
            }
        });
    });

    const competenciasCheckbox: NodeListOf<HTMLInputElement> = document.querySelectorAll('input[name="competencias[]"]');
    competenciasCheckbox.forEach((checkbox) => {
        checkbox.addEventListener("change", () => {
            if (checkbox.checked) {
                competenciasEmpresa.push(checkbox.value);
            } else {
                competenciasEmpresa = competenciasEmpresa.filter((competencia) => competencia !== checkbox.value);
            }
        });
    });
}

iniciarListenersEmpresa();
main()
