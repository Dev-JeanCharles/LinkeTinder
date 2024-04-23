import { Candidato } from "../Candidato";

export class DTOCandidato {
    // Adiciona um candidato ao armazenamento local
    add(candidato: Candidato): void {
        try {
            const candidatos = this.get();
            candidatos.push(candidato);
            localStorage.setItem("candidatos", JSON.stringify(candidatos));
        } catch (error) {
            console.error("Erro ao adicionar candidato:", error);
        }
    }

    // Obtém todos os candidatos do armazenamento local
    get(): Candidato[] {
        try {
            const local = localStorage.getItem("candidatos");
            return local ? JSON.parse(local) : [];
        } catch (error) {
            console.error("Erro ao obter candidatos:", error);
            return [];
        }
    }
}
