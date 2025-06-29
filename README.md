# 📚 My Book Manager

**Sistema de Gerenciamento de Livros**  
Trabalho Prático - Desenvolvimento Web com Spring MVC, Thymeleaf e MySQL

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

### **Fluxo de Funcionamento:**

1. **Requisição**: O usuário acessa uma URL através do navegador
2. **Controller (Spring MVC)**: Recebe a requisição e processa os dados
3. **Service**: Implementa a lógica de negócio
4. **Repository (Spring Data JPA)**: Realiza operações no banco de dados
5. **Thymeleaf (View)**: Renderiza a resposta em HTML para o usuário

---

## 🛠️ **Tecnologias Utilizadas**

### **Backend:**
- **Java 17**: Linguagem de programação principal
- **Spring Boot 3.x**: Framework para desenvolvimento de aplicações Java
- **Spring MVC**: Implementação do padrão MVC
- **Spring Data JPA**: Abstração para acesso a dados
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
│       │       │   └── EditoraController.java
│       │       ├── model/
│       │       │   ├── Livro.java
│       │       │   ├── Autor.java
│       │       │   └── Editora.java
│       │       ├── repository/
│       │       │   ├── LivroRepository.java
│       │       │   ├── AutorRepository.java
│       │       │   └── EditoraRepository.java
│       │       ├── service/
│       │       │   ├── LivroService.java
│       │       │   ├── AutorService.java
│       │       │   └── EditoraService.java
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
└── README.md
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

---

## 🎯 **Funcionalidades Avançadas**

- **Sistema de busca avançada** com múltiplos filtros
- **Validação de dados** com Bean Validation
- **Tratamento de erros** com mensagens amigáveis
- **Interface responsiva** que funciona em dispositivos móveis
- **Navegação intuitiva** entre as seções
- **Formatação monetária** para preços
- **Relacionamentos** entre entidades com integridade referencial

---

## 👥 **Alunos**

- **Matheus dos Anjos de Oliveira**
- **Vitor Siedschlag Hervella**

---

## 📝 **Versões**

- **v1.0.0** - Versão anterior (Servlet + JSP)
- **v2.0.0** - Versão atual (Spring MVC + Thymeleaf)
