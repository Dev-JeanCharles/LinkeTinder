import {Candidato} from "../Models/Candidato";
import { mascararNome, mascararEmail, mascararCPF} from "../Utils/mascarar";

export function construirCandidato (candidato: Candidato): string {
    return `
        <div class="candidato">
            <h2>${mascararNome(candidato.nome)}</h2>
            <p>Email: ${mascararEmail(candidato.email)}</p>
            <p>CPF: ${mascararCPF(candidato.cpf)}</p>                    
            <p>Competências: ${candidato.competencias.slice(0, -1).join(", ")}${candidato.competencias.length > 1 ? ',' : ''} ${candidato.competencias.slice(-1)}</p>                    
            <p>Descrição: ${candidato.descricao}</p>
        </div>
    `;
}