# API REST - My Book Manager

## Visão Geral

Esta documentação descreve a API REST do sistema My Book Manager, que permite gerenciar livros, autores e editoras através de endpoints HTTP.

**Base URL:** `http://localhost:8080/api`

## Endpoints Principais

### 1. Informações da API

#### GET /api/info
Retorna informações gerais sobre a API.

**Resposta:**
```json
{
  "nome": "My Book Manager API",
  "versao": "1.0.0",
  "descricao": "API REST para gerenciamento de livros, autores e editoras",
  "endpoints": {
    "autores": "/api/autores",
    "editoras": "/api/editoras",
    "livros": "/api/livros"
  }
}
```

#### GET /api/stats
Retorna estatísticas do sistema.

**Resposta:**
```json
{
  "totalAutores": 5,
  "totalEditoras": 3,
  "totalLivros": 12
}
```

#### GET /api/health
Verifica o status da API.

**Resposta:**
```json
{
  "status": "UP",
  "message": "API está funcionando normalmente"
}
```

### 2. Autores (/api/autores)

#### GET /api/autores
Lista todos os autores.

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Machado de Assis",
    "nacionalidade": "Brasileiro"
  },
  {
    "id": 2,
    "nome": "Clarice Lispector",
    "nacionalidade": "Brasileira"
  }
]
```

#### GET /api/autores/{id}
Busca um autor específico por ID.

**Resposta:**
```json
{
  "id": 1,
  "nome": "Machado de Assis",
  "nacionalidade": "Brasileiro"
}
```

#### POST /api/autores
Cria um novo autor.

**Corpo da requisição:**
```json
{
  "nome": "Jorge Amado",
  "nacionalidade": "Brasileiro"
}
```

**Resposta (201 Created):**
```json
{
  "id": 3,
  "nome": "Jorge Amado",
  "nacionalidade": "Brasileiro"
}
```

#### PUT /api/autores/{id}
Atualiza um autor existente.

**Corpo da requisição:**
```json
{
  "nome": "Jorge Amado",
  "nacionalidade": "Brasileiro"
}
```

#### DELETE /api/autores/{id}
Remove um autor.

**Resposta:** 204 No Content

#### GET /api/autores/buscar?nome={nome}
Busca autores por nome.

#### GET /api/autores/nacionalidade/{nacionalidade}
Busca autores por nacionalidade.

### 3. Editoras (/api/editoras)

#### GET /api/editoras
Lista todas as editoras.

**Resposta:**
```json
[
  {
    "id": 1,
    "nome": "Companhia das Letras",
    "cidade": "São Paulo"
  },
  {
    "id": 2,
    "nome": "Record",
    "cidade": "Rio de Janeiro"
  }
]
```

#### GET /api/editoras/{id}
Busca uma editora específica por ID.

#### POST /api/editoras
Cria uma nova editora.

**Corpo da requisição:**
```json
{
  "nome": "Nova Editora",
  "cidade": "Belo Horizonte"
}
```

#### PUT /api/editoras/{id}
Atualiza uma editora existente.

#### DELETE /api/editoras/{id}
Remove uma editora.

#### GET /api/editoras/buscar?nome={nome}
Busca editoras por nome.

#### GET /api/editoras/cidade/{cidade}
Busca editoras por cidade.

### 4. Livros (/api/livros)

#### GET /api/livros
Lista todos os livros.

**Resposta:**
```json
[
  {
    "id": 1,
    "titulo": "Dom Casmurro",
    "isbn": "9788535902778",
    "anoPublicacao": 1899,
    "preco": 29.90,
    "autorId": 1,
    "autorNome": "Machado de Assis",
    "editoraId": 1,
    "editoraNome": "Companhia das Letras"
  }
]
```

#### GET /api/livros/{id}
Busca um livro específico por ID.

#### POST /api/livros
Cria um novo livro.

**Corpo da requisição:**
```json
{
  "titulo": "Capitães da Areia",
  "isbn": "9788535909555",
  "anoPublicacao": 1937,
  "preco": 35.50,
  "autorId": 3,
  "editoraId": 1
}
```

#### PUT /api/livros/{id}
Atualiza um livro existente.

#### DELETE /api/livros/{id}
Remove um livro.

#### GET /api/livros/buscar?termo={termo}
Busca livros por título, autor ou ISBN.

#### GET /api/livros/autor/{autorId}
Busca livros por autor.

#### GET /api/livros/editora/{editoraId}
Busca livros por editora.

#### GET /api/livros/ano/{ano}
Busca livros por ano de publicação.

#### GET /api/livros/preco?precoMin={min}&precoMax={max}
Busca livros por faixa de preço.

#### GET /api/livros/isbn/{isbn}
Busca um livro específico por ISBN.

## Códigos de Status HTTP

- **200 OK:** Requisição bem-sucedida
- **201 Created:** Recurso criado com sucesso
- **204 No Content:** Requisição bem-sucedida sem conteúdo (DELETE)
- **400 Bad Request:** Dados de entrada inválidos
- **404 Not Found:** Recurso não encontrado
- **500 Internal Server Error:** Erro interno do servidor

## Tratamento de Erros

A API retorna erros em formato JSON padronizado:

```json
{
  "error": "Recurso não encontrado",
  "message": "Autor não encontrado com ID: 999",
  "status": 404,
  "timestamp": "2024-01-15T10:30:00"
}
```

Para erros de validação:

```json
{
  "error": "Erro de validação",
  "message": "Dados de entrada inválidos",
  "status": 400,
  "timestamp": "2024-01-15T10:30:00",
  "validationErrors": {
    "nome": "Nome é obrigatório",
    "preco": "Preço deve ser positivo"
  }
}
```

## Validações

### Autor
- **nome:** Obrigatório, 2-100 caracteres
- **nacionalidade:** Opcional, máximo 50 caracteres

### Editora
- **nome:** Obrigatório, 2-100 caracteres
- **cidade:** Opcional, máximo 50 caracteres

### Livro
- **titulo:** Obrigatório, 2-100 caracteres
- **isbn:** Opcional, máximo 20 caracteres
- **anoPublicacao:** Opcional
- **preco:** Opcional, deve ser positivo
- **autorId:** Obrigatório
- **editoraId:** Obrigatório

## Exemplos de Uso com cURL

### Listar todos os autores
```bash
curl -X GET http://localhost:8080/api/autores
```

### Criar um novo autor
```bash
curl -X POST http://localhost:8080/api/autores \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Carlos Drummond de Andrade",
    "nacionalidade": "Brasileiro"
  }'
```

### Buscar livros por termo
```bash
curl -X GET "http://localhost:8080/api/livros/buscar?termo=Dom"
```

### Atualizar um livro
```bash
curl -X PUT http://localhost:8080/api/livros/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Dom Casmurro - Edição Especial",
    "isbn": "9788535902778",
    "anoPublicacao": 1899,
    "preco": 39.90,
    "autorId": 1,
    "editoraId": 1
  }'
```

### Remover um autor
```bash
curl -X DELETE http://localhost:8080/api/autores/1
```

## Notas Importantes

1. **Relacionamentos:** Ao criar ou atualizar livros, os IDs de autor e editora devem existir no sistema.
2. **ISBN Único:** O sistema verifica se o ISBN já existe antes de salvar um livro.
3. **Integridade Referencial:** Não é possível excluir autores ou editoras que possuem livros vinculados.
4. **Busca Case-Insensitive:** As buscas por nome ignoram maiúsculas e minúsculas.
5. **Paginação:** A API atual não implementa paginação, retornando todos os registros.

## Próximas Melhorias

- Implementação de paginação
- Autenticação JWT
- Filtros avançados
- Ordenação de resultados
- Cache de consultas frequentes
- Documentação com Swagger/OpenAPI 