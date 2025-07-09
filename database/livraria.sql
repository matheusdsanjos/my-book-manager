-- Script SQL para criação do banco de dados livraria
-- My Book Manager - Sistema de Gerenciamento de Livros

-- Criar banco de dados
CREATE DATABASE IF NOT EXISTS livraria;
USE livraria;

-- Criar tabela autor
CREATE TABLE IF NOT EXISTS autor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    nacionalidade VARCHAR(50)
);

-- Criar tabela editora
CREATE TABLE IF NOT EXISTS editora (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cidade VARCHAR(50)
);

-- Criar tabela livro
CREATE TABLE IF NOT EXISTS livro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    ano_publicacao INT,
    preco DECIMAL(10,2),
    autor_id BIGINT,
    editora_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES autor(id),
    FOREIGN KEY (editora_id) REFERENCES editora(id)
);

-- Tabela de usuários
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Tabela de roles dos usuários
CREATE TABLE IF NOT EXISTS user_roles (
    user_id BIGINT NOT NULL,
    role VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Inserir dados de exemplo - Autores
INSERT INTO autor (nome, nacionalidade) VALUES 
('Machado de Assis', 'Brasileira'),
('Clarice Lispector', 'Brasileira'),
('Jorge Amado', 'Brasileira'),
('Graciliano Ramos', 'Brasileira'),
('Carlos Drummond de Andrade', 'Brasileira'),
('José de Alencar', 'Brasileira'),
('Cecília Meireles', 'Brasileira'),
('Lima Barreto', 'Brasileira'),
('Monteiro Lobato', 'Brasileira'),
('Paulo Coelho', 'Brasileira'),
('Rubem Fonseca', 'Brasileira'),
('Luís Fernando Veríssimo', 'Brasileira'),
('Chico Buarque', 'Brasileira'),
('Raduan Nassar', 'Brasileira'),
('João Guimarães Rosa', 'Brasileira');

-- Inserir dados de exemplo - Editoras
INSERT INTO editora (nome, cidade) VALUES 
('Companhia das Letras', 'São Paulo'),
('Record', 'Rio de Janeiro'),
('Editora José Olympio', 'Rio de Janeiro'),
('Nova Fronteira', 'Rio de Janeiro'),
('Editora Rocco', 'Rio de Janeiro'),
('Editora Globo', 'São Paulo'),
('Martins Fontes', 'São Paulo'),
('Autêntica', 'Belo Horizonte'),
('Editora 34', 'São Paulo'),
('Objetiva', 'Rio de Janeiro');

-- Inserir dados de exemplo - Livros
INSERT INTO livro (titulo, isbn, ano_publicacao, preco, autor_id, editora_id) VALUES
('Dom Casmurro', '9788535914849', 1899, 39.90, 1, 1),
('Memórias Póstumas de Brás Cubas', '9788535914207', 1881, 42.00, 1, 1),
('A Hora da Estrela', '9788535926842', 1977, 35.00, 2, 1),
('Perto do Coração Selvagem', '9788535915389', 1943, 38.00, 2, 2),
('Capitães da Areia', '9788520932660', 1937, 36.90, 3, 3),
('Gabriela, Cravo e Canela', '9788520932752', 1958, 44.90, 3, 3),
('Vidas Secas', '9788503010663', 1938, 30.00, 4, 4),
('São Bernardo', '9788503011905', 1934, 29.00, 4, 4),
('Alguma Poesia', '9788573261616', 1930, 25.00, 5, 5),
('Claro Enigma', '9788572328280', 1951, 26.00, 5, 5),
('Iracema', '9788538080046', 1865, 22.90, 6, 6),
('O Guarani', '9788538080190', 1857, 24.00, 6, 6),
('Romanceiro da Inconfidência', '9788535900606', 1953, 28.00, 7, 7),
('Viagem', '9788535900613', 1939, 27.00, 7, 7),
('Triste Fim de Policarpo Quaresma', '9788520928687', 1915, 30.00, 8, 2),
('Clara dos Anjos', '9788520928700', 1948, 32.00, 8, 2),
('Reinações de Narizinho', '9788578270699', 1931, 26.90, 9, 3),
('O Saci', '9788578270705', 1921, 24.90, 9, 3),
('O Alquimista', '9788575427584', 1988, 34.90, 10, 4),
('Veronika Decide Morrer', '9788575428772', 1998, 35.00, 10, 4),
('Agosto', '9788535921991', 1990, 38.00, 11, 5),
('O Cobrador', '9788535920031', 1979, 32.90, 11, 5),
('Comédias da Vida Privada', '9788525044931', 1994, 29.90, 12, 6),
('As Mentiras que os Homens Contam', '9788525044924', 2000, 30.00, 12, 6),
('Leite Derramado', '9788535915310', 2009, 36.00, 13, 1),
('Budapeste', '9788535907094', 2003, 33.00, 13, 1),
('Lavoura Arcaica', '9788535903898', 1975, 39.00, 14, 1),
('Um Copo de Cólera', '9788535906240', 1978, 25.00, 14, 1),
('Grande Sertão: Veredas', '9788573261194', 1956, 44.90, 15, 7),
('Sagarana', '9788573261200', 1946, 36.00, 15, 7);

-- Usuário admin de exemplo (senha: admin123)
-- Hash gerado com BCrypt: $2a$10$7QJQw8Qw8Qw8Qw8Qw8Qw8eQw8Qw8Qw8Qw8Qw8Qw8Qw8Qw8Qw8Qw8
-- Usuário comum de exemplo (senha: user123)
-- Hash gerado com BCrypt: $2a$10$8QJQw8Qw8Qw8Qw8Qw8Qw8eQw8Qw8Qw8Qw8Qw8Qw8Qw8Qw8Qw8Qw8

INSERT INTO users (username, password) VALUES 
('admin', '$2a$12$eogKszOX1/GLZnWGQpNYr.zV88C3dahBEDRzKl4wa3FYLP8ffRJQW'),
('user', '$2a$12$DDpO6f5322G.MX3O/UUgd.OFSG.b16ImKE.5lKdV3.CS3XyUhUrsW');

-- Adiciona ambos os roles para o admin
INSERT INTO user_roles (user_id, role) VALUES 
(1, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(2, 'ROLE_USER');

-- Verificar dados inseridos
SELECT 'Autores cadastrados:' as info;
SELECT COUNT(*) as total_autores FROM autor;

SELECT 'Editoras cadastradas:' as info;
SELECT COUNT(*) as total_editoras FROM editora;

SELECT 'Livros cadastrados:' as info;
SELECT COUNT(*) as total_livros FROM livro;

-- Exemplo de consulta para verificar os dados
SELECT 
    l.titulo,
    l.isbn,
    l.ano_publicacao,
    l.preco,
    a.nome as autor,
    e.nome as editora
FROM livro l
JOIN autor a ON l.autor_id = a.id
JOIN editora e ON l.editora_id = e.id
ORDER BY l.titulo
LIMIT 10;