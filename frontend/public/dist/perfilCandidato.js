(()=>{"use strict";var e={29:(e,a)=>{Object.defineProperty(a,"__esModule",{value:!0}),a.DTOCandidato=void 0,a.DTOCandidato=class{add(e){let a=this.get();a.push(e),localStorage.setItem("candidatos",JSON.stringify(a))}get(){const e=localStorage.getItem("candidatos");return e?JSON.parse(e):[]}}},253:(e,a)=>{Object.defineProperty(a,"__esModule",{value:!0}),a.DTOEmpresa=void 0,a.DTOEmpresa=class{add(e){let a=this.get();a.push(e),localStorage.setItem("empresas",JSON.stringify(a))}get(){const e=localStorage.getItem("empresas");return e?JSON.parse(e):[]}}},68:(e,a)=>{function t(e){return e.slice(0,2)+e.slice(2).replace(/./g,"*")}function c(e){const[a,t]=e.split("@");return a.slice(0,3)+"***"+a.slice(-2)+"@"+t.slice(3,0)+"*****"+t.slice(-8)}function n(e){return e.replace(/\d/g,"*")}function r(e){return e.replace(/\d/g,"*")}Object.defineProperty(a,"__esModule",{value:!0}),a.mascararCNPJ=a.mascararCPF=a.mascararEmail=a.mascararNome=a.mascararDados=void 0,a.mascararDados=function(e,a){switch(a){case"nome":return t(e);case"email":return c(e);case"cpf":return n(e);case"cnpj":return r(e);default:return e}},a.mascararNome=t,a.mascararEmail=c,a.mascararCPF=n,a.mascararCNPJ=r}},a={};function t(c){var n=a[c];if(void 0!==n)return n.exports;var r=a[c]={exports:{}};return e[c](r,r.exports,t),r.exports}(()=>{const e=t(29),a=t(253),c=t(68);document.addEventListener("DOMContentLoaded",(()=>{const t=(new e.DTOCandidato).get(),n=new URLSearchParams(window.location.search).get("cpf");if(!n)return void console.error("CPF não encontrado na URL.");const r=t.find((e=>e.cpf===n));r?function(e){const a=document.querySelector(".nome");a&&(a.textContent=e.nome);const t=document.querySelector(".email");t&&(t.textContent=(0,c.mascararDados)(e.email,"email"));const n=document.querySelector(".idade");n&&(n.textContent=e.idade.toString()+" anos");const r=document.querySelector(".cpf");r&&(r.textContent=(0,c.mascararDados)(e.cpf,"cpf"));const s=document.querySelector(".competencias");s&&(s.innerHTML=e.competencias.map((e=>`<li>${e}</li>`)).join(""));const o=document.querySelector(".sobre");o&&(o.textContent=e.descricao)}(r):console.error("Candidato não encontrado."),function(){const e=(new a.DTOEmpresa).get(),t=document.getElementById("lista-empresas");t&&(t.innerHTML="",e.forEach((e=>{const a=`\n                <div class="col">\n                    <div class="card">\n                        <div class="card-body">\n                            <h5 class="card-title">${(0,c.mascararNome)(e.nome)}</h5>\n                            <p class="card-text">CNPJ: ${(0,c.mascararDados)(e.cnpj,"cnpj")}</p>\n                            <p class="card-text">Estado: ${e.estado}</p>\n                            <p class="card-text">CEP: ${e.cep}</p>\n                            <p class="card-text">Email: ${(0,c.mascararDados)(e.email,"email")}</p>\n                            <p class="card-text">Competências: ${e.competencias.slice(0,-1).join(", ")}${e.competencias.length>1?",":""} ${e.competencias.slice(-1)}</p>   \n                            <p class="card-text">Descrição: ${e.descricao}</p>\n                        </div>\n                    </div>\n                </div>\n            `;t.innerHTML+=a})))}()}))})()})();