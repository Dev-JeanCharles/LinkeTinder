(()=>{"use strict";var e={68:(e,t)=>{Object.defineProperty(t,"__esModule",{value:!0}),t.mascararCNPJ=t.mascararCPF=t.mascararEmail=t.mascararNome=void 0,t.mascararNome=function(e){return e.slice(0,2)+e.slice(2).replace(/./g,"*")},t.mascararEmail=function(e){const[t,c]=e.split("@");return t.slice(0,3)+"***"+t.slice(-2)+"@"+c.slice(3,0)+"*****"+c.slice(-8)},t.mascararCPF=function(e){return e.replace(/\d/g,"*")},t.mascararCNPJ=function(e){return e.replace(/\d/g,"*")}}},t={};function c(n){var o=t[n];if(void 0!==o)return o.exports;var a=t[n]={exports:{}};return e[n](a,a.exports,c),a.exports}(()=>{const e=c(68);document.addEventListener("DOMContentLoaded",(()=>{const t=function(){const e=new URLSearchParams(window.location.search).get("cpf");if(!e)return null;const t=localStorage.getItem(e);return t?JSON.parse(t):null}();t?(function(e){const t=document.querySelector(".nome");t&&(t.textContent=e.nome);const c=document.querySelector(".email");c&&(c.textContent=e.email);const n=document.querySelector("ul.dadosGeograficos");n&&(n.children[0].textContent=e.estado+",",n.children[1].textContent=e.cep);const o=document.querySelector("ul.dadosDemograficos > li:nth-child(1)");o&&(o.textContent=e.idade.toString()+" anos");const a=document.querySelector("ul.dadosDemograficos > li:nth-child(2)");a&&(a.textContent=e.cpf);const r=document.querySelector("ul.dadosDemograficos > li:nth-child(3) > ul");r&&(r.innerHTML=e.competencias.map(((e,t,c)=>t<c.length-1?`<li class="me-1">${e},</li>`:`<li class="me-1">${e}</li>`)).join(""));const s=document.querySelector("ul.dadosDemograficos > li:nth-child(4) > div");s&&(s.textContent=e.descricao)}(t),function(){const t=document.getElementById("lista-empresas");t&&(t.innerHTML="",function(){const e=[];for(let t=0;t<localStorage.length;t++){const c=localStorage.key(t);if(c){const t=localStorage.getItem(c);if(t)try{const c=JSON.parse(t);e.push(c)}catch(e){console.error(`Erro ao analisar o valor para a chave ${c}:`,e);continue}}}return e}().filter((e=>"cnpj"in e)).forEach((c=>{const n=`\n                <div class="col">\n                    <div class="card">\n                        <div class="card-body">\n                        <h5 class="card-title">${(0,e.mascararNome)(c.nome)}</h5>\n                            <p class="card-text">CNPJ: ${(0,e.mascararCNPJ)(c.cnpj)}</p>\n                            <p class="card-text">Estado: ${c.estado}</p>\n                            <p class="card-text">CEP: ${c.cep}</p>\n                            <p class="card-text">Email: ${(0,e.mascararEmail)(c.email)}</p>\n                            <p class="card-text">Competências: ${c.competencias.slice(0,-1).join(", ")}${c.competencias.length>1?",":""} ${c.competencias.slice(-1)}</p>   \n                            <p class="card-text">Descrição: ${c.descricao}</p>\n                            </div>\n                        </div>\n                    </div>\n                `;t.innerHTML+=n})))}()):console.error("Candidato não encontrado.")}))})()})();