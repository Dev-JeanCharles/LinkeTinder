document.addEventListener("DOMContentLoaded", () => {
    const cpfInput = document.getElementById("cpf") as HTMLInputElement;
    const cnpjInput = document.getElementById("cnpj") as HTMLInputElement;
    const cepInput = document.getElementById("cep") as HTMLInputElement;
    const emailInput = document.getElementById("email") as HTMLInputElement;

    function validacaoDocumentoHtml(eventos: Event) {
        const input = eventos.target as HTMLInputElement;
        let validador = input.value.replace(/\D/g, "");

        if (input == cpfInput) {
            let cpf = validador.substring(0,11)
            let cpfValidado = ""

            for(let i = 0; i < cpf.length; i++) {
                if(i === 3 || i === 6) {
                    cpfValidado += "."
                }else if (i === 9) {
                    cpfValidado += "-";
                }
                cpfValidado += cpf[i]
            }
            input.value = cpfValidado
        
        } else if (input === cepInput) {
            let cep = validador.substring(0,8)
            let cepValidado = ""
        
            for(let i = 0; i < cep.length; i++) {
                if(i === 5) {
                    cepValidado += "-"
                }
                cepValidado += cep[i]
            }
            input.value = cepValidado

        } else if (input === cnpjInput) {
            let cnpj = validador.substring(0,14)
            let cnpjValidado = ""

            for(let i = 0; i < cnpj.length; i++) {
                if(i === 2 || i === 5) {
                    cnpjValidado += "."
                }else if (i === 8) {
                    cnpjValidado += "/"
                }else if (i === 12) {
                    cnpjValidado += "-"
                }
                cnpjValidado += cnpj[i]
            }
            input.value = cnpjValidado
        }
    }
    function validacaoEmail(event: Event) {
        const inputEmail =  event.target as HTMLInputElement
        let email = inputEmail.value.trim()

        const emailRegex = /^[^\s@]+@(?!.*\d)[^\s@]+\.[^\s@]+$/

        if(!emailRegex.test(email)) {
            return
        }

        const prox = email[email.length - 1]
        if (prox === ".") {
            email = email.slice(0, -1)
        }else if (!email.endsWith('.com.br')){
            email += ".br"
        }
        inputEmail.maxLength = email.endsWith('.br') ? email.length : 50
        inputEmail.value = email
    }

    if (cpfInput) {
        cpfInput.addEventListener("input", validacaoDocumentoHtml)
    }

    if (cnpjInput) {
        cpfInput.addEventListener("input", validacaoDocumentoHtml)
    }

    if (cepInput) {
        cpfInput.addEventListener("input", validacaoDocumentoHtml)
    }

    if (emailInput) {
        emailInput.addEventListener("input", validacaoEmail)
    }

});
