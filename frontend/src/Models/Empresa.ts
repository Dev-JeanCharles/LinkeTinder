import { Usuario } from "./Usuario";

export class Empresa extends Usuario {
    constructor(
        public nome: string,
        public email: string,
        public cnpj: string,
        public pais: string,
        public estado: string,
        public cep: string,
        public descricao: string,
        public competencias: string[]
    ) {
        super(nome, estado, cep, email,descricao, competencias);
    }

    getAll() {
        return {
            nome: this.nome ,
            email: this.email ,
            cnpj: this.cnpj ,
            pais: this.pais ,
            estado: this.estado ,
            cep: this.cep ,
            descricao: this.descricao ,
            competencias: this.competencias
        };
    }
}