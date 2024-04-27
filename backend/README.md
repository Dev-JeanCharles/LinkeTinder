# Principais padrões de projetos aplicados.

## Padrão Singleton 
> Apliquei o padrão Singleton pois no meu app.groovy e o meu Factory dependiam da instanciação dos objetos de minha classe DBOperations e DBConnection passando a instância de conexão ao banco, agora é necessário chamar o método getInstance().

## Exemplo do Factory e App após a aplicação do Singleton

![image](https://github.com/Dev-JeanCharles/LinkeTinder-AceleraZG/assets/85767415/8e03e35f-f8b5-4af8-9abd-53397c455496)

![image](https://github.com/Dev-JeanCharles/LinkeTinder-AceleraZG/assets/85767415/8324a52c-32fa-4fe2-8ef8-c4afc5c6515d)

## Padrão Factory Method
> Apliquei o padrão Factory Method pois em minhas classes que trabalham com o DAO estavam instanciando várias outras classes que são dependentes no construtor e gostaria de ter apenas uma instância de acesso.

## Exemplo de uma classe DAO após aplicação do Factory Method:
![image](https://github.com/Dev-JeanCharles/LinkeTinder-AceleraZG/assets/85767415/18d39a2f-80f9-49b3-9779-eab25889945a)
