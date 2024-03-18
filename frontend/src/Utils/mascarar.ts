export function mascararNome(nome: string): string {
    const primeiroParte = nome.slice(0, 2);
    const resto = nome.slice(2).replace(/./g, '*');
    return primeiroParte + resto;
}

export function mascararEmail(email: string): string {
    const [usuario, dominio] = email.split('@');
    const usuarioMascarado = usuario.slice(0, 3) + '***' + usuario.slice(-2);
    const dominioMascarado = dominio.slice(3, 0) + '*****' + dominio.slice(-8);
    return usuarioMascarado + '@' + dominioMascarado;
}

export function mascararCPF(cpf: string): string {
    return cpf.replace(/\d/g, '*');
}

export function mascararCNPJ(cnpj: string): string {
    return cnpj.replace(/\d/g, '*');
}