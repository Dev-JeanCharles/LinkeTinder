import { Usuario } from "./Usuario";

export class Candidato extends Usuario {
    constructor(        
        public nome: string,
        public email: string,
        public cpf: string,
        public competencias: string[],
        public descricao: string
    ){
        super(nome, email, cpf, descricao, competencias);
    }

    getAll() {
        return {
            nome: this.nome,
            email: this.email,
            cpf: this.cpf,
            estado: this.estado,
            competencias: this.competencias,
            descricao: this.descricao
        }
    }
}