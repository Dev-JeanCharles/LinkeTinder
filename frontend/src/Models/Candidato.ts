import { Usuario } from "./Usuario";

export class Candidato extends Usuario {
    constructor(        
        public nome: string,
        public email: string,
        public cnpj: string,
        public pais: string,
        public estado: string,
        public cep: string,
        public competencias: string[],
        public descricao: string
    ){
        super(nome, estado, cep, descricao, competencias);
        this.email = email;
        this.cnpj = cnpj;
        this.pais = pais;
    }

    getAll() {
        return {
            nome: this.nome,
            email: this.email,
            cnpj: this.cnpj,
            pais: this.pais,
            estado: this.estado,
            cep: this.cep,
            competencias: this.competencias,
            descricao: this.descricao
        }
    }
}