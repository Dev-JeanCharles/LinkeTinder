export abstract class Usuario {
    constructor(
        public nome: string,
        public estado: string,
        public cep: string,
        public email: string,
        public descricao: string,
        public competencias: string[]
    ) {}

    // Método abstrato para retornar todos os detalhes do usuário
    abstract getAll(): Record<string, any>;
}