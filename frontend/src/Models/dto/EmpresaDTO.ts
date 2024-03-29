import {Empresa} from "../Empresa";

export class DTOEmpresa {
    add(empresa: any): void {
        let empresas = this.get();
        empresas.push(empresa);
        localStorage.setItem("empresas", JSON.stringify(empresas));
    }

    get(): Empresa[] {
        const local = localStorage.getItem("empresas");
        if (local) {
            return JSON.parse(local);
        } else {
            return [];
        }
    }
}