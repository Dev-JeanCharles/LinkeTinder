import {Candidato} from "../Candidato";

export interface DTOUsuario {
    add(usuario: Candidato): void
    get(): any []
}