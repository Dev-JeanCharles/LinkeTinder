import { Usuario } from "./Usuario";

export class Candidato extends Usuario {
    constructor(
        nome: string,
        public idade: number,
        public cpf: string,
        estado: string,
        cep: string,
        email: string,
        competencias: string[],
        descricao: string
    ) {
        super(nome, estado, cep, email, descricao, competencias);
    }

    // Retorna todos os detalhes do candidato como um objeto
    getAll(): Record<string, any> {
        return {
            nome: this.nome,
            idade: this.idade,
            cpf: this.cpf,
            estado: this.estado,
            cep: this.cep,
            email: this.email,
            competencias: this.competencias,
            descricao: this.descricao,
        };
    }
}