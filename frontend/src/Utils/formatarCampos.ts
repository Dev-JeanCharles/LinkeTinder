document.addEventListener("DOMContentLoaded", () => {
    const cpfInput = document.getElementById("cpf") as HTMLInputElement;
    const cnpjInput = document.getElementById("cnpj") as HTMLInputElement;
    const cepInput = document.getElementById("cep") as HTMLInputElement;
    const emailInput = document.getElementById("email") as HTMLInputElement;

    function formatarDocumento(event: Event) {
        const input = event.target as HTMLInputElement;
        let documento = input.value.replace(/\D/g, "");

        if (input === cpfInput) {
            let cpf = documento.substring(0, 11);
            let cpfFormatado = "";

            for (let i = 0; i < cpf.length; i++) {
                if (i === 3 || i === 6) {
                    cpfFormatado += ".";
                } else if (i === 9) {
                    cpfFormatado += "-";
                }
                cpfFormatado += cpf[i];
            }

            input.value = cpfFormatado;
        } else if (input === cepInput) {
            let cep = documento.substring(0, 8);
            let cepFormatado = "";

            for (let i = 0; i < cep.length; i++) {
                if (i === 5) {
                    cepFormatado += "-";
                }
                cepFormatado += cep[i];
            }

            input.value = cepFormatado;
        } else if (input === cnpjInput) {
            let cnpj = documento.substring(0, 14);
            let cnpjFormatado = "";

            for (let i = 0; i < cnpj.length; i++) {
                if (i === 2 || i === 5) {
                    cnpjFormatado += ".";
                } else if (i === 8) {
                    cnpjFormatado += "/";
                } else if (i === 12) {
                    cnpjFormatado += "-";
                }
                cnpjFormatado += cnpj[i];
            }

            input.value = cnpjFormatado;
        }
    }

    function formatarEmail(event: Event) {
        const input = event.target as HTMLInputElement;
        let email = input.value.trim();

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

        input.maxLength = email.endsWith('.br') ? email.length : 50;
        input.value = email;
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
        emailInput.addEventListener("input", formatarEmail);
    }
});