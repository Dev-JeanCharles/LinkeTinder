import { Usuario } from "./Usuario";

export class Empresa extends Usuario {
    constructor(
        nome: string,
        public email: string,
        public cnpj: string,
        public pais: string,
        estado: string,
        cep: string,
        descricao: string,
        competencias: string[]
    ) {
        super(nome, estado, cep, email, descricao, competencias);
    }

    // Retorna todos os detalhes da empresa como um objeto
    getAll(): Record<string, any> {
        return {
            nome: this.nome,
            email: this.email,
            cnpj: this.cnpj,
            pais: this.pais,
            estado: this.estado,
            cep: this.cep,
            descricao: this.descricao,
            competencias: this.competencias,
        };
    }
}