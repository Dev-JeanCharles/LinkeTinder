document.addEventListener("DOMContentLoaded", () => {
    const cpfInput = document.getElementById("cpf") as HTMLInputElement;
    const cnpjInput = document.getElementById("cnpj") as HTMLInputElement;
    const cepInput = document.getElementById("cep") as HTMLInputElement;
    const emailInput = document.getElementById("email") as HTMLInputElement;

    function formatarDocumento(documento: string, formato: number[]): string {
        let documentoFormatado = "";
        for (let i = 0; i < documento.length; i++) {
            if (formato.includes(i)) {
                documentoFormatado += ".";
            } else if (i === 8) {
                documentoFormatado += "-";
            }
            documentoFormatado += documento[i];
        }
        return documentoFormatado;
    }

    function formatarCpf(cpf: string): string {
        const cpfFormatado = cpf.substring(0, 11);
        return formatarDocumento(cpfFormatado, [3, 6]);
    }

    function formatarCnpj(cnpj: string): string {
        const cnpjFormatado = cnpj.substring(0, 14);
        return formatarDocumento(cnpjFormatado, [2, 5, 8, 12]);
    }

    function formatarCep(cep: string): string {
        return formatarDocumento(cep.substring(0, 8), [5]);
    }

    function formatarEmail(email: string): string {
        email = email.trim();
        const emailRegex = /^[^\s@]+@(?!.*\d)[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            return email;
        }
        if (email.endsWith('.')) {
            email = email.slice(0, -1);
        } else if (!email.endsWith('.com.br')) {
            email += '.br';
        }
        return email;
    }

    function inputHandler(event: Event, formatter: (value: string) => string) {
        const input = event.target as HTMLInputElement;
        const formattedValue = formatter(input.value.replace(/\D/g, ''));
        input.value = formattedValue;
    }

    if (cpfInput) {
        cpfInput.addEventListener("input", (event) => inputHandler(event, formatarCpf));
    }

    if (cnpjInput) {
        cnpjInput.addEventListener("input", (event) => inputHandler(event, formatarCnpj));
    }

    if (cepInput) {
        cepInput.addEventListener("input", (event) => inputHandler(event, formatarCep));
    }

    if (emailInput) {
        emailInput.addEventListener("input", (event) => {
            const input = event.target as HTMLInputElement;
            input.maxLength = formatarEmail(input.value).endsWith('.br') ? input.value.length : 50;
            inputHandler(event, formatarEmail);
        });
    }
});
