import { Candidato } from "../Models/Candidato";
import { DTOCandidato } from "../Models/dto/CandidatoDTO";

function main(): void {
    const formCandidato: HTMLFormElement | null = document.forms.namedItem("form1");
    formCandidato?.addEventListener("submit", handleSubmit);
}

function handleSubmit(event: Event): void {
    event.preventDefault();

    if (validarCamposCandidato()) {
        const novoCandidato = criarNovoCandidato();

        const candidatodto = new DTOCandidato();
        candidatodto.add(novoCandidato);

        alert("Cadastro realizado com sucesso!");

        const encodedCpf = encodeURIComponent(cpfCandidato);
        window.location.href = `perfilCandidato.html?cpf=${encodedCpf}`;
    } else {
        alert("Por favor, preencha todos os campos obrigatórios.");
    }
}

function validarCamposCandidato(): boolean {
    const camposObrigatorios: string[] = ["nome", "idade", "cpf", "estado", "cep", "email", "descricao"];
    let todosCamposPreenchidos = true;
    const emailRegex = /^[^\s@]+@(?!.*\d)[^\s@]+\.[^\s@]+$/;

    camposObrigatorios.forEach((campo) => {
        const input = getInputById(campo);
        if (!input || input.value.trim() === "") {
            todosCamposPreenchidos = false;
        } else if (campo === "email") {
            if (!emailRegex.test(input.value)) {
                todosCamposPreenchidos = false;
                alert('O e-mail inserido não é válido. Por favor, verifique e tente novamente.');
            }
        }
    });

    return todosCamposPreenchidos;
}

function criarNovoCandidato(): Candidato {
    return new Candidato(
        nomeCandidato,
        idadeCandidato,
        cpfCandidato,
        estadoCandidato,
        cepCandidato,
        emailCandidato,
        competenciasCandidato,
        descricaoCandidato
    );
}

function getInputById(id: string): HTMLInputElement | null {
    return document.getElementById(id) as HTMLInputElement;
}

function iniciarListenersCandidato(): void {
    const camposObrigatorios: string[] = ["nome", "idade", "cpf", "estado", "cep", "email", "descricao"];
    camposObrigatorios.forEach((campo) => {
        const input = getInputById(campo);
        input?.addEventListener("change", () => {
            switch (campo) {
                case "nome":
                    nomeCandidato = input.value;
                    break;
                case "idade":
                    idadeCandidato = parseInt(input.value, 10);
                    break;
                case "cpf":
                    cpfCandidato = input.value;
                    break;
                case "estado":
                    estadoCandidato = input.value;
                    break;
                case "cep":
                    cepCandidato = input.value;
                    break;
                case "email":
                    emailCandidato = input.value;
                    break;
                case "descricao":
                    descricaoCandidato = input.value;
                    break;
                default:
                    break;
            }
        });
    });

    const checkboxes: NodeListOf<HTMLInputElement> = document.querySelectorAll('input[name="competencias[]"]');
    checkboxes.forEach((checkbox) => {
        checkbox.addEventListener("change", () => {
            if (checkbox.checked) {
                competenciasCandidato.push(checkbox.value);
            } else {
                competenciasCandidato = competenciasCandidato.filter((competencia) => competencia !== checkbox.value);
            }
        });
    });
}

let nomeCandidato: string;
let idadeCandidato: number;
let cpfCandidato: string;
let estadoCandidato: string;
let cepCandidato: string;
let emailCandidato: string;
let competenciasCandidato: string[] = [];
let descricaoCandidato: string;

iniciarListenersCandidato();
main();
