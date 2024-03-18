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

function validarCamposObrigatoriosCandidato(
    nomeCandidato: string,
    idadeCandidato: number,
    cpfCandidato: string,
    estadoCandidato: string,
    cepCandidato: string,
    emailCandidato: string,
    descricaoCandidato: string,
    competenciasCandidato: string[]
): boolean {
    return (
        nomeCandidato !== "" &&
        idadeCandidato > 0 &&
        cpfCandidato !== "" &&
        estadoCandidato !== "" &&
        cepCandidato !== "" &&
        emailCandidato !== "" &&
        competenciasCandidato.length > 0 &&
        descricaoCandidato !== ""
    );
}

function armazenarDadosCandidato(candidato: CadastroCandidato): void {
    localStorage.setItem(candidato.cpf, JSON.stringify(candidato));
}

function iniciarListenersCandidato(): void {
    const camposObrigatorios: string[] = ["nome", "idade", "cpf", "estado", "cep", "email", "descricao"];
    camposObrigatorios.forEach((campo) => {
        const input: HTMLInputElement | null = document.getElementById(campo) as HTMLInputElement;
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

let nomeCandidato: string = "";
let idadeCandidato: number = 0;
let cpfCandidato: string = "";
let estadoCandidato: string = "";
let cepCandidato: string = "";
let emailCandidato: string = "";
let competenciasCandidato: string[] = [];
let descricaoCandidato: string = "";

const formCandidato: HTMLFormElement | null = document.forms.namedItem("form1");
formCandidato?.addEventListener("submit", (event) => {
    event.preventDefault();

    if (validarCamposObrigatoriosCandidato(nomeCandidato, idadeCandidato, cpfCandidato, estadoCandidato, cepCandidato, emailCandidato, descricaoCandidato, competenciasCandidato)) {
        const candidato: CadastroCandidato = {
            nome: nomeCandidato,
            idade: idadeCandidato,
            cpf: cpfCandidato,
            estado: estadoCandidato,
            cep: cepCandidato,
            email: emailCandidato,
            competencias: competenciasCandidato,
            descricao: descricaoCandidato
        };

        armazenarDadosCandidato(candidato);

        alert("Cadastro realizado com sucesso!");

        window.location.href = `perfilCandidato.html?cpf=${encodeURIComponent(cpfCandidato)}`;
    } else {
        alert("Por favor, preencha todos os campos obrigatórios.");
    }
});

iniciarListenersCandidato();