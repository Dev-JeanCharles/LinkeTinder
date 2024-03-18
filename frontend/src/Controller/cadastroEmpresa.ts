interface CadastroEmpresa {
    nome: string;
    email: string;
    cnpj: string;
    pais: string;
    estado: string;
    cep: string;
    descricao: string;
    competencias: string[];
}

let empresa: CadastroEmpresa = {
    nome: "",
    email: "",
    cnpj: "",
    pais: "",
    estado: "",
    cep: "",
    descricao: "",
    competencias: []
};

function validarCamposEmpresa(): boolean {
    return (
        empresa.nome !== "" &&
        empresa.email !== "" &&
        empresa.cnpj !== "" &&
        empresa.pais !== "" &&
        empresa.estado !== "" &&
        empresa.cep !== "" &&
        empresa.competencias.length > 0 &&
        empresa.descricao !== ""
    );
}

function armazenarEmpresa(): void {
    localStorage.setItem(empresa.cnpj, JSON.stringify(empresa));
}

function iniciarListenersEmpresa(): void {
    const camposObrigatorios: string[] = ["nome", "email", "cnpj", "pais", "estado", "cep", "descricao"];
    camposObrigatorios.forEach((campo) => {
        const input: HTMLInputElement | null = document.getElementById(campo) as HTMLInputElement;
        input?.addEventListener("change", () => {
            switch (campo) {
                case "nome":
                    empresa.nome = input.value;
                    break;
                case "email":
                    empresa.email = input.value;
                    break;
                case "cnpj":
                    empresa.cnpj = input.value;
                    break;
                case "pais":
                    empresa.pais = input.value;
                    break;
                case "estado":
                    empresa.estado = input.value;
                    break;
                case "cep":
                    empresa.cep = input.value;
                    break;
                case "descricao":
                    empresa.descricao = input.value;
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
                empresa.competencias.push(checkbox.value);
            } else {
                empresa.competencias = empresa.competencias.filter((competencia) => competencia !== checkbox.value);
            }
        });
    });
}

const formEmpresa: HTMLFormElement | null = document.forms.namedItem("form2");
formEmpresa?.addEventListener("submit", (event) => {
    event.preventDefault();

    if (validarCamposEmpresa()) {
        armazenarEmpresa();

        alert("Cadastro realizado com sucesso!");

        window.location.href = `perfilEmpresa.html?cnpj=${encodeURIComponent(empresa.cnpj)}`;
    } else {
        alert("Por favor, preencha todos os campos obrigatórios.");
    }
});

iniciarListenersEmpresa();
