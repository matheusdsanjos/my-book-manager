# 📚 My Book Manager

**Sistema de Gerenciamento de Livros**  
Trabalho Prático - Desenvolvimento Web com Spring MVC, Thymeleaf e MySQL

---

## 🎥 Apresentação

Confira o vídeo de apresentação do projeto no YouTube:  
[https://www.youtube.com/watch?v=SoW9mI_4JZk](https://www.youtube.com/watch?v=SoW9mI_4JZk)

---

## 📖 **Funcionamento do Sistema**

O **My Book Manager** é um sistema web desenvolvido com **Spring MVC** seguindo o padrão arquitetural **MVC (Model-View-Controller)** que permite o gerenciamento completo de uma biblioteca através de operações **CRUD** (Create, Read, Update, Delete).

### **Principais Funcionalidades:**

1. **Gestão de Livros**
   - Cadastro de livros com informações completas (título, ISBN, autor, editora, ano, preço)
   - Listagem de todos os livros cadastrados
   - Busca avançada por título, autor, ISBN, ano e faixa de preço
   - Filtros por autor, editora, ano de publicação
   - Edição e exclusão de registros
   - Visualização detalhada de cada livro

2. **Gestão de Autores**
   - Cadastro de autores com nome e nacionalidade
   - Listagem de autores
   - Busca por nome
   - Edição e exclusão de registros

3. **Gestão de Editoras**
   - Cadastro de editoras com nome e cidade
   - Listagem de editoras
   - Busca por nome
   - Edição e exclusão de registros

4. **Sistema de Autenticação e Autorização**
   - Autenticação JWT (JSON Web Token)
   - Controle de acesso baseado em roles (ROLE_USER, ROLE_ADMIN)
   - Endpoints protegidos e públicos
   - Interface web para usuários autenticados
   - API REST com diferentes níveis de acesso

### **Fluxo de Funcionamento:**

1. **Requisição**: O usuário acessa uma URL através do navegador
2. **Spring Security**: Verifica autenticação e autorização
3. **Controller (Spring MVC)**: Recebe a requisição e processa os dados
4. **Service**: Implementa a lógica de negócio
5. **Repository (Spring Data JPA)**: Realiza operações no banco de dados
6. **Thymeleaf (View)**: Renderiza a resposta em HTML para o usuário

---

## 🔐 **Sistema de Autenticação**

O sistema implementa **Spring Security** com **JWT (JSON Web Token)** para autenticação e autorização.

### **Roles e Permissões:**

- **ROLE_USER**: Acesso básico aos dados (GET endpoints)
- **ROLE_ADMIN**: Acesso completo (todas as operações CRUD)

### **Endpoints Públicos:**
- `POST /api/auth/login` - Autenticação de usuários
- `GET /api/livros` - Listagem pública de livros
- Interface web principal (`/`)

### **Endpoints Protegidos:**

#### **Para ROLE_USER:**
- `GET /api/autores` - Listar autores
- `GET /api/editoras` - Listar editoras
- `GET /api/livros/{id}` - Visualizar livro específico
- `GET /api/livros/buscar` - Buscar livros
- Interface web completa

#### **Para ROLE_ADMIN:**
- Todos os endpoints de ROLE_USER
- `POST /api/autores` - Criar autor
- `PUT /api/autores/{id}` - Atualizar autor
- `DELETE /api/autores/{id}` - Remover autor
- `POST /api/editoras` - Criar editora
- `PUT /api/editoras/{id}` - Atualizar editora
- `DELETE /api/editoras/{id}` - Remover editora
- `POST /api/livros` - Criar livro
- `PUT /api/livros/{id}` - Atualizar livro
- `DELETE /api/livros/{id}` - Remover livro
- `GET /api/info` - Informações da API
- `GET /api/stats` - Estatísticas do sistema
- `GET /api/health` - Status da API

### **Como Autenticar:**

1. **Fazer login:**
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username": "admin", "password": "admin123"}'
   ```

2. **Usar o token retornado:**
   ```bash
   curl -X GET http://localhost:8080/api/autores \
     -H "Authorization: Bearer SEU_JWT_TOKEN_AQUI"
   ```

---

## 🔌 **API REST**

O sistema também disponibiliza uma **API REST completa** para integração com outros sistemas, aplicações móveis ou frontends SPA (Single Page Applications).

### **Endpoints Principais:**

**Base URL:** `http://localhost:8080/api`

#### **Autenticação:**
- `POST /api/auth/login` - Autenticação de usuários

#### **Informações da API (Admin apenas):**
- `GET /api/info` - Informações gerais da API
- `GET /api/stats` - Estatísticas do sistema
- `GET /api/health` - Status da API

#### **Autores:**
- `GET /api/autores` - Lista todos os autores
- `GET /api/autores/{id}` - Busca autor por ID
- `POST /api/autores` - Cria novo autor (ADMIN)
- `PUT /api/autores/{id}` - Atualiza autor (ADMIN)
- `DELETE /api/autores/{id}` - Remove autor (ADMIN)
- `GET /api/autores/buscar?nome={nome}` - Busca por nome
- `GET /api/autores/nacionalidade/{nacionalidade}` - Busca por nacionalidade

#### **Editoras:**
- `GET /api/editoras` - Lista todas as editoras
- `GET /api/editoras/{id}` - Busca editora por ID
- `POST /api/editoras` - Cria nova editora (ADMIN)
- `PUT /api/editoras/{id}` - Atualiza editora (ADMIN)
- `DELETE /api/editoras/{id}` - Remove editora (ADMIN)
- `GET /api/editoras/buscar?nome={nome}` - Busca por nome
- `GET /api/editoras/cidade/{cidade}` - Busca por cidade

#### **Livros:**
- `GET /api/livros` - Lista todos os livros (PÚBLICO)
- `GET /api/livros/{id}` - Busca livro por ID
- `POST /api/livros` - Cria novo livro (ADMIN)
- `PUT /api/livros/{id}` - Atualiza livro (ADMIN)
- `DELETE /api/livros/{id}` - Remove livro (ADMIN)
- `GET /api/livros/buscar?termo={termo}` - Busca por título, autor ou ISBN
- `GET /api/livros/autor/{autorId}` - Busca por autor
- `GET /api/livros/editora/{editoraId}` - Busca por editora
- `GET /api/livros/ano/{ano}` - Busca por ano
- `GET /api/livros/preco?precoMin={min}&precoMax={max}` - Busca por faixa de preço
- `GET /api/livros/isbn/{isbn}` - Busca por ISBN

### **Exemplos de Uso:**

#### **Autenticação:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "admin", "password": "admin123"}'
```

#### **Listar todos os autores (com autenticação):**
```bash
curl -X GET http://localhost:8080/api/autores \
  -H "Authorization: Bearer SEU_JWT_TOKEN_AQUI"
```

#### **Criar um novo autor (apenas admin):**
```bash
curl -X POST http://localhost:8080/api/autores \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_JWT_TOKEN_ADMIN" \
  -d '{
    "nome": "Carlos Drummond de Andrade",
    "nacionalidade": "Brasileiro"
  }'
```

#### **Buscar livros por termo (público):**
```bash
curl -X GET "http://localhost:8080/api/livros/buscar?termo=Dom"
```

### **Características da API:**
- **Autenticação JWT** para endpoints protegidos
- **Controle de acesso baseado em roles** (USER/ADMIN)
- **Respostas em JSON** para todas as operações
- **Códigos de status HTTP** apropriados (200, 201, 204, 400, 401, 403, 404, 500)
- **Validação de dados** com mensagens de erro detalhadas
- **Tratamento global de exceções** com respostas padronizadas
- **DTOs** para separação entre entidades JPA e dados da API
- **Busca case-insensitive** em todos os filtros

Para documentação completa da API, consulte o arquivo `API_REST_DOCUMENTATION.md`.

---

## 🛠️ **Tecnologias Utilizadas**

### **Backend:**
- **Java 17**: Linguagem de programação principal
- **Spring Boot 3.x**: Framework para desenvolvimento de aplicações Java
- **Spring MVC**: Implementação do padrão MVC
- **Spring Security**: Autenticação e autorização
- **Spring Data JPA**: Abstração para acesso a dados
- **JWT (JSON Web Token)**: Autenticação stateless
- **Hibernate**: ORM (Object-Relational Mapping)

### **Frontend:**
- **Thymeleaf**: Template engine server-side
- **Bootstrap 5**: Framework CSS para interface responsiva
- **Font Awesome**: Ícones
- **HTML5 & CSS3**: Estruturação e estilização das páginas

### **Banco de Dados:**
- **MySQL 8.0**: Sistema de gerenciamento de banco relacional
- **Relacionamentos**: Implementação de chaves estrangeiras entre Livros, Autores e Editoras

### **Servidor:**
- **Spring Boot Embedded Tomcat**: Servidor de aplicação integrado

### **Ferramentas de Desenvolvimento:**
- **Eclipse IDE**: Ambiente de desenvolvimento integrado
- **MySQL Workbench**: Administração e modelagem do banco
- **Git**: Controle de versão
- **Maven**: Gerenciamento de dependências
- **Postman**: Testes de API

---

## 📁 **Estrutura do Projeto**

```
my-book-manager-springmvc/
├── src/
│   └── main/
│       ├── java/
│       │   └── br/edu/ifsp/demo/my_book_manager/
│       │       ├── controller/
│       │       │   ├── HomeController.java
│       │       │   ├── LivroController.java
│       │       │   ├── AutorController.java
│       │       │   ├── EditoraController.java
│       │       │   ├── LivroRestController.java
│       │       │   ├── AutorRestController.java
│       │       │   ├── EditoraRestController.java
│       │       │   ├── ApiInfoController.java
│       │       │   └── AdminRestController.java
│       │       ├── model/
│       │       │   ├── Livro.java
│       │       │   ├── Autor.java
│       │       │   └── Editora.java
│       │       ├── dto/
│       │       │   ├── LivroRequestDTO.java
│       │       │   ├── LivroResponseDTO.java
│       │       │   ├── AutorRequestDTO.java
│       │       │   ├── AutorResponseDTO.java
│       │       │   ├── EditoraRequestDTO.java
│       │       │   └── EditoraResponseDTO.java
│       │       ├── repository/
│       │       │   ├── LivroRepository.java
│       │       │   ├── AutorRepository.java
│       │       │   └── EditoraRepository.java
│       │       ├── service/
│       │       │   ├── LivroService.java
│       │       │   ├── AutorService.java
│       │       │   └── EditoraService.java
│       │       ├── exception/
│       │       │   ├── ResourceNotFoundException.java
│       │       │   └── GlobalExceptionHandler.java
│       │       ├── security/
│       │       │   ├── JwtAuthenticationFilter.java
│       │       │   ├── JwtTokenProvider.java
│       │       │   └── SecurityConfig.java
│       │       └── MyBookManagerSpringmvcApplication.java
│       └── resources/
│           ├── templates/
│           │   ├── fragments.html
│           │   ├── livro/
│           │   │   ├── list.html
│           │   │   ├── form.html
│           │   │   └── view.html
│           │   ├── autor/
│           │   │   ├── list.html
│           │   │   └── form.html
│           │   └── editora/
│           │       ├── list.html
│           │       └── form.html
│           └── application.properties
├── database/
│   └── livraria.sql
├── pom.xml
├── README.md
└── API_REST_DOCUMENTATION.md
```

---

## 📋 **Manual de Execução**

### **Pré-requisitos:**
- Java JDK 17 ou superior
- MySQL 8.0
- Eclipse IDE for Enterprise Java Developers
- Maven

### ⚙️ Instalação do Spring Tools no Eclipse

Para facilitar o desenvolvimento com Spring Boot e Spring MVC, recomenda-se instalar o plugin **Spring Tools (Spring Tool Suite - STS)** no Eclipse.

**Passo a Passo:**

1. Abra o Eclipse
2. No menu, vá em: `Help` → `Eclipse Marketplace...`
3. Na barra de busca, digite: `Spring Tools`
4. Encontre o resultado **Spring Tools (aka Spring Tool Suite) 4.x** (ícone verde com folha)
5. Clique em **Install** (ou **Update**, se já estiver instalado)
6. Aceite os termos e aguarde a instalação
7. Reinicie o Eclipse quando solicitado

**Vantagens do Spring Tools:**
- Autocomplete e navegação para projetos Spring Boot/Spring MVC
- Criação facilitada de classes, controllers, services, etc.
- Visualização de beans e dependências
- Ferramentas para testes e execução de aplicações Spring

### **Passo a Passo:**

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/matheusdsanjos/my-book-manager-springmvc.git
   ```

2. **Configure o banco de dados:**
   - Execute o script `database/livraria.sql` no MySQL
   - Ajuste as credenciais em `src/main/resources/application.properties`

3. **Importe o projeto no Eclipse:**
   - File → Import → Maven → Existing Maven Projects
   - Selecione a pasta do projeto
   - Aguarde o Eclipse baixar as dependências

4. **Configure o banco de dados:**
   - Abra `src/main/resources/application.properties`
   - Ajuste as configurações do MySQL:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/livraria
     spring.datasource.username=seu_usuario
     spring.datasource.password=sua_senha
     ```

5. **Execute a aplicação:**
   - Clique com botão direito no projeto
   - Run As → Spring Boot App
   - Ou execute a classe `MyBookManagerSpringmvcApplication`
   - Acesse: `http://localhost:8080/`

### **Testando a API REST:**

Após executar a aplicação, você pode testar a API REST:

1. **Fazer login para obter token JWT:**
   ```bash
   curl -X POST http://localhost:8080/api/auth/login \
     -H "Content-Type: application/json" \
     -d '{"username": "admin", "password": "admin123"}'
   ```

2. **Verificar status da API (admin apenas):**
   ```bash
   curl -H "Authorization: Bearer SEU_JWT_TOKEN" \
     http://localhost:8080/api/health
   ```

3. **Listar todos os autores (com autenticação):**
   ```bash
   curl -H "Authorization: Bearer SEU_JWT_TOKEN" \
     http://localhost:8080/api/autores
   ```

4. **Listar livros (endpoint público):**
   ```bash
   curl http://localhost:8080/api/livros
   ```

5. **Criar um novo autor (apenas admin):**
   ```bash
   curl -X POST http://localhost:8080/api/autores \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer SEU_JWT_TOKEN_ADMIN" \
     -d '{"nome": "Teste API", "nacionalidade": "Brasileiro"}'
   ```

### **Usuários Padrão:**
- **admin/admin123** - Role: ROLE_ADMIN (acesso completo)
- **user/user123** - Role: ROLE_USER (acesso limitado)

---

## 🎯 **Funcionalidades Avançadas**

- **Sistema de busca avançada** com múltiplos filtros
- **Validação de dados** com Bean Validation
- **Tratamento de erros** com mensagens amigáveis
- **Interface responsiva** que funciona em dispositivos móveis
- **Navegação intuitiva** entre as seções
- **Formatação monetária** para preços
- **Relacionamentos** entre entidades com integridade referencial
- **API REST completa** para integração com outros sistemas
- **DTOs** para separação de responsabilidades
- **Tratamento global de exceções** com respostas padronizadas
- **Autenticação JWT** com controle de acesso baseado em roles
- **Spring Security** para proteção de endpoints
- **Endpoints públicos e protegidos** com diferentes níveis de acesso

---

## 👥 **Alunos**

- **Matheus dos Anjos de Oliveira**
- **Vitor Siedschlag Hervella**
