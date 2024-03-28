--CREATE DATABASE LinkeTinder

CREATE TABLE competencias (
    id SERIAL PRIMARY KEY,
    competencia VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE candidatos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    data_de_nascimento DATE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    idade INT NOT NULL, 
    cpf VARCHAR(15) UNIQUE NOT NULL,
    pais VARCHAR(100) NOT NULL,
    cep VARCHAR(15) NOT NULL,
    descricao TEXT NOT NULL,
    senha VARCHAR(255) NOT NULL,
    competencia_id INT,
    FOREIGN KEY (competencia_id) REFERENCES competencias (id)
);

CREATE TABLE empresas (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cnpj VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,    
    pais VARCHAR(100) NOT NULL,
    cep VARCHAR(15) NOT NULL,
    descricao TEXT NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE vagas (
    id SERIAL PRIMARY KEY,    
    nome VARCHAR(100) NOT NULL,
    descricao TEXT NOT NULL,
    localidade VARCHAR(255) NOT NULL,
    empresa_id INT,
    FOREIGN KEY (empresa_id) REFERENCES empresas (id)
);

CREATE TABLE curtidas(
    candidato_id INT,
    empresa_id INT,
    FOREIGN KEY (candidato_id) REFERENCES candidatos (id),
    FOREIGN KEY (empresa_id) REFERENCES empresas (id)
);

CREATE TABLE matchs (
    candidato_id INT,
    empresa_id INT,
    FOREIGN KEY (candidato_id) REFERENCES candidatos (id),
    FOREIGN KEY (empresa_id) REFERENCES empresas (id)
);

INSERT INTO competencias (competencia) VALUES
    ('Java'),
    ('Groovy'),
    ('JavaScript'),
    ('TypeScript'),
    ('Teste Unitários'),
    ('Angular');

INSERT INTO candidatos (nome, sobrenome, data_de_nascimento, email, idade, cpf, pais, cep, descricao, senha, competencia_id) VALUES 
  ('Joao', 'Silva', '1999-07-04', 'joao.silva@example.com', 22, '12345678900', 'Brasil', '12345-678', 'Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...', 'senha123', 1),
  ('Vitor', 'Costa', '2000-03-17', 'vitor.costa@example.com', 34, '98765432100', 'Brasil', '54321-876', 'There is no one who loves pain itself, who seeks after it and wants to have it, simply because it is pain...', 'senha456', 2),
  ('Jean', 'Charles', '2000-11-25', 'jean.charles@example.com', 56, '45678912300', 'Brasil', '98765-432', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit.', 'senha789', 3),
  ('Vinicius', 'Nano', '1989-10-23', 'vinicius.nano@example.com', 19, '32165498700', 'Brasil', '56789-012', 'Lorem ipsum dolor.', 'senhaabc', 4),
  ('Clebinho', 'Santos', '1998-12-07', 'clebinho.santos@example.com', 21, '65432198700', 'Brasil', '10987-654', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque facilisis, leo a tempor commodo, dui mi dapibus lacus, sed euismod tellus mi non eros. Duis a dignissim purus. Proin.', 'senhaxyz', 5);

INSERT INTO empresas (nome, cnpj, email, pais, cep, descricao, senha) VALUES
    ('ZG Solutions', '12345678901234', 'zg.solutions@example.com', 'Brasil', '12345-678', 'Neque porro quisquam est qui dolorem ipsum.', 'senha123'),
    ('Dharma Innovations', '98765432109876', 'dharma.innovations@example.com', 'Brasil', '54321-876', 'There is no one who loves pain itself.', 'senha456'),
    ('BlueDot Tech Solutions', '65432109876543', 'blueDot.techsolutions@example.com', 'Brasil', '98765-432', 'consectetur, adipisci velit...', 'senha789'),
    ('Cirro Tech', '32109876543210', 'cirro.tech@example.com', 'Brasil', '67890-123', 'Donec et ullamcorper orci. Curabitur euismod dolor.', 'senhaabc'),
    ('Hino Tecnology', '56789012345678', 'hino.tecnology@example.com', 'Brasil', '01234-567', 'Cras massa ex, suscipit sit amet aliquet vel, imperdiet.', 'senhaxyz');

INSERT INTO vagas (empresa_id, nome, localidade, descricao) VALUES
    (1, 'Desenvolvedor Front-end Typescript', 'Brasil, Goiania', 'Estamos procurando um desenvolvedor Front-end Typescript.'),
    (2, 'Engenheiro de Software Java/Kotlin', 'Brasil, Rio de Janeiro', 'Estamos buscando um engenheiro de software Java/Kotlin.'),
    (3, 'Engenheiro de Software C#/Groovy', 'Brasil, Brasilia', 'Estamos buscando um engenheiro de software C#/Groovy.'),
    (4, 'Desenvolvedor Back-end Python', 'Brasil, Rio Grande do Norte', 'Estamos à procura de um desenvolvedor back-end Python.'),
    (5, 'Desenvolvedor Full-stack', 'Brasil, Espirito Santo', 'Procuramos um desenvolvedor Full-stack com foco em Java e experiências com Typescript, React e Javascript.');
INSERT INTO curtidas (candidato_id, empresa_id) VALUES
    (1, 4),
    (1, 2),
    (1, 3),
    (2, 5),
    (2, 1),
    (3, 2),
    (3, 3),
    (3, 1),
    (4, 4),
    (4, 5),
    (4, 2),
    (5, 4),
    (5, 5),
    (5, 1);

INSERT INTO matchs (candidato_id, empresa_id) VALUES
    (2, 2), 
    (5, 5),
    (5, 1),
    (5, 5);
