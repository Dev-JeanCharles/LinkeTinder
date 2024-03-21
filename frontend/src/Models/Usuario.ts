export abstract class Usuario {
    constructor(public nome: string, public estado: string, public cep: string, public email: string,
        public descricao: string, public competencias: string[]) {
        }
        
        abstract getAll(): void
}