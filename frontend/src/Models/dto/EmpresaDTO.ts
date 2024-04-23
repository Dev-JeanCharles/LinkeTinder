import { Empresa } from "../Empresa";

export class DTOEmpresa {
    // Adiciona uma empresa ao armazenamento local
    add(empresa: Empresa): void {
        try {
            const empresas = this.get();
            empresas.push(empresa);
            localStorage.setItem("empresas", JSON.stringify(empresas));
        } catch (error) {
            console.error("Erro ao adicionar empresa:", error);
        }
    }

    // Obtém todas as empresas do armazenamento local
    get(): Empresa[] {
        try {
            const local = localStorage.getItem("empresas");
            return local ? JSON.parse(local) : [];
        } catch (error) {
            console.error("Erro ao obter empresas:", error);
            return [];
        }
    }
}
