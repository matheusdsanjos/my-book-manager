# 📚 My Book Manager

**Sistema de Gerenciamento de Livros**  
Trabalho Prático - Desenvolvimento Web com Java, JDBC e MySQL

---

## 📖 **Funcionamento do Sistema**

O **My Book Manager** é um sistema web desenvolvido seguindo o padrão arquitetural **MVC (Model-View-Controller)** que permite o gerenciamento completo de uma biblioteca através de operações **CRUD** (Create, Read, Update, Delete).

### **Principais Funcionalidades:**

1. **Gestão de Livros**
   - Cadastro de livros com informações completas (título, ISBN, editora, ano, páginas)
   - Listagem de todos os livros cadastrados
   - Busca por título, autor ou ISBN
   - Edição e exclusão de registros

2. **Gestão de Autores**
   - Cadastro de autores com dados pessoais e biografia
   - Listagem e busca de autores por nome
   - Associação de autores aos livros (relacionamento N:N)

3. **Gestão de Categorias**
   - Criação de categorias para organização temática
   - Associação de livros às categorias (relacionamento N:1)

### **Fluxo de Funcionamento:**

1. **Requisição**: O usuário acessa uma URL através do navegador
2. **Servlet (Controller)**: Recebe a requisição e processa os dados
3. **DAO (Model)**: Realiza operações no banco de dados via JDBC
4. **JSP (View)**: Renderiza a resposta em HTML para o usuário

---

## 🛠️ **Tecnologias Utilizadas**

### **Backend:**
- **Java 11+**: Linguagem de programação principal
- **Jakarta Servlets**: Implementação dos controladores MVC
- **JDBC (Java Database Connectivity)**: API para acesso ao banco de dados
- **PreparedStatement**: Prevenção contra SQL Injection
- **DTOs (Data Transfer Objects)**: Transferência de dados entre camadas

### **Frontend:**
- **JSP (JavaServer Pages)**: Páginas dinâmicas server-side
- **JSTL (JSP Standard Tag Library)**: Tags customizadas para JSP
- **HTML5 & CSS3**: Estruturação e estilização das páginas

### **Banco de Dados:**
- **MySQL 8.0**: Sistema de gerenciamento de banco relacional
- **Relacionamentos**: Implementação de chaves estrangeiras e relacionamentos N:N

### **Servidor:**
- **Apache Tomcat 10.0**: Servidor de aplicação Java

### **Ferramentas de Desenvolvimento:**
- **Eclipse IDE**: Ambiente de desenvolvimento integrado
- **MySQL Workbench**: Administração e modelagem do banco
- **Git**: Controle de versão

---

## 📁 **Estrutura do Projeto**

```
my-book-manager/
├── src/
│   ├── dao/
│   │   ├── AutorDAO.java
│   │   ├── EditoraDAO.java
│   │   └── LivroDAO.java
│   ├── model/
│   │   ├── Autor.java
│   │   ├── Editora.java
│   │   └── Livro.java
│   ├── servlet/
│   │   ├── AutorServlet.java
│   │   ├── EditoraServlet.java
│   │   └── LivroServlet.java
│   └── util/
│       └── ConnectionFactory.java
├── webapp/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── lib/
│   ├── css/
│   ├── js/
│   └── *.jsp
├── database/
│   └── livraria.sql
└── README.me
```

---

## 📋 **Manual de Execução**

### **Pré-requisitos:**
- Java JDK 11 ou superior
- Apache Tomcat 9.0
- MySQL Server 8.0
- Eclipse IDE for Enterprise Java Developers

### **Passo a Passo:**

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/matheusdsanjos/my-book-manager.git
   ```

2. **Configure o banco de dados:**
   - Execute o script `database/create_database.sql` no MySQL
   - Ajuste as credenciais em `src/util/ConnectionFactory.java`

3. **Importe o projeto no Eclipse:**
   - File → Import → Existing Projects into Workspace
   - Selecione a pasta do projeto

4. **Configure o Tomcat:**
   - Servers → New → Apache Tomcat v9.0
   - Deploy o projeto no servidor

5. **Execute a aplicação:**
   - Start do servidor Tomcat
   - Acesse: `http://localhost:8080/my-book-manager/`

---

## 👥 **Desenvolvedores**

- **Matheus dos Santos** - Desenvolvimento Backend e Banco de Dados
- **[Nome do Parceiro]** - Desenvolvimento Frontend e Integração

---

## 📅 **Data de Entrega**

**15/05/2025** - Apresentação com demonstração prática do funcionamento do sistema.

---

**Repositório:** https://github.com/matheusdsanjos/my-book-manager
