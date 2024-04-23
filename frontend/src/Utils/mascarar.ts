export function mascararDados(dado: string, tipo: string): string {
    switch (tipo) {
        case 'nome':
            return mascararNome(dado);
        case 'email':
            return mascararEmail(dado);
        case 'cpf':
            return mascararCPF(dado);
        case 'cnpj':
            return mascararCNPJ(dado);
        default:
            return dado;
    }
}

function mascararNome(nome: string): string {
    const primeiroParte = nome.slice(0, 2);
    const resto = nome.slice(2).replace(/./g, '*');
    return primeiroParte + resto;
}

function mascararEmail(email: string): string {
    const [usuario, dominio] = email.split('@');
    const usuarioMascarado = usuario.slice(0, 3) + '***' + usuario.slice(-2);
    const dominioMascarado = dominio.slice(3, 0) + '*****' + dominio.slice(-8);
    return usuarioMascarado + '@' + dominioMascarado;
}

function mascararCPF(cpf: string): string {
    return cpf.replace(/\d/g, '*');
}

function mascararCNPJ(cnpj: string): string {
    return cnpj.replace(/\d/g, '*');
}
