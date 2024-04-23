document.addEventListener("DOMContentLoaded", () => {
    const cpfInput = document.getElementById("cpf") as HTMLInputElement;
    const cnpjInput = document.getElementById("cnpj") as HTMLInputElement;
    const cepInput = document.getElementById("cep") as HTMLInputElement;
    const emailInput = document.getElementById("email") as HTMLInputElement;

    function formatarDocumento(evento: Event) {
        const input = evento.target as HTMLInputElement;
        let valor = input.value.replace(/\D/g, "");

        if (input === cpfInput) {
            input.value = formatarCPF(valor);
        } else if (input === cepInput) {
            input.value = formatarCEP(valor);
        } else if (input === cnpjInput) {
            input.value = formatarCNPJ(valor);
        }
    }

    function formatarCPF(valor: string): string {
        let cpfFormatado = "";

        for (let i = 0; i < valor.length; i++) {
            if (i === 3 || i === 6) {
                cpfFormatado += ".";
            } else if (i === 9) {
                cpfFormatado += "-";
            }
            cpfFormatado += valor[i];
        }

        return cpfFormatado.substring(0, 14);
    }

    function formatarCEP(valor: string): string {
        let cepFormatado = "";

        for (let i = 0; i < valor.length; i++) {
            if (i === 5) {
                cepFormatado += "-";
            }
            cepFormatado += valor[i];
        }

        return cepFormatado.substring(0, 9);
    }

    function formatarCNPJ(valor: string): string {
        let cnpjFormatado = "";

        for (let i = 0; i < valor.length; i++) {
            if (i === 2 || i === 5) {
                cnpjFormatado += ".";
            } else if (i === 8) {
                cnpjFormatado += "/";
            } else if (i === 12) {
                cnpjFormatado += "-";
            }
            cnpjFormatado += valor[i];
        }

        return cnpjFormatado.substring(0, 18);
    }

    function validarEmail(evento: Event) {
        const inputEmail = evento.target as HTMLInputElement;
        let email = inputEmail.value.trim();

        const emailRegex = /^[^\s@]+@(?!.*\d)[^\s@]+\.[^\s@]+$/;

        if (!emailRegex.test(email)) {
            return;
        }

        const lastChar = email[email.length - 1];
        if (lastChar === '.') {
            email = email.slice(0, -1);
        } else if (!email.endsWith('.com.br')) {
            email += '.br';
        }

        inputEmail.maxLength = email.endsWith('.br') ? email.length : 50;
        inputEmail.value = email;
    }

    if (cpfInput) {
        cpfInput.addEventListener("input", formatarDocumento);
    }

    if (cnpjInput) {
        cnpjInput.addEventListener("input", formatarDocumento);
    }

    if (cepInput) {
        cepInput.addEventListener("input", formatarDocumento);
    }

    if (emailInput) {
        emailInput.addEventListener("input", validarEmail);
    }
});
