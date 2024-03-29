import {Candidato} from "../Candidato";

export class DTOCandidato {
    add(candidato: any): void {
        let candidatos = this.get();
        candidatos.push(candidato);
        localStorage.setItem("candidatos", JSON.stringify(candidatos));
    }

    get(): Candidato[] {
        const local = localStorage.getItem("candidatos");
        if (local) {
            return JSON.parse(local);
        } else {
            return [];
        }
    }
}