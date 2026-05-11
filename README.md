# Sistema de Gestão Escolar (JDBC + DTOs + SQL)
## Seguindo como base o conteúdo do Professor Vinícius Trindade 
## 🖥️ [Código Base](https://github.com/profviniciustrindade/lista-contatos/tree/main)

Este projeto consiste em um sistema de gestão escolar construído em Java, utilizando JDBC para persistência de dados, o padrão DTO (Data Transfer Object) para transferência de dados entre as camadas, e banco de dados relacional.

## Estrutura do Banco de Dados

O banco de dados relacional contém as seguintes tabelas:

- **Aluno**: `id`, `nome`, `email`, `matricula`, `data_nascimento`
- **Professor**: `id`, `nome`, `email`, `disciplina`
- **Curso**: `id`, `nome`, `codigo`
- **Turma**: `id`, `nome`, `curso_id`, `professor_id`
- **Turma_Aluno (N:N)**: `turma_id`, `aluno_id`
- **Aula**: `id`, `turma_id`, `data_hora`, `assunto`
- **Nota**: `id`, `aluno_id`, `aula_id`, `valor`

## Padrão DTO (Data Transfer Object)

O projeto utiliza DTOs para separar as entidades de persistência dos objetos de transferência de dados, garantindo maior segurança e flexibilidade nas requisições e respostas da API.

### Mapeamento de DTOs

| Entidade | DTO Requisição (Dados enviados para a API) | DTO Resposta (Dados retornados pela API) |
| :--- | :--- | :--- |
| **Aluno** | `nome`, `email`, `matricula`, `dataNascimento` | `id`, `nome`, `email`, `matricula`, `dataNascimento` |
| **Professor** | `nome`, `email`, `disciplina` | `id`, `nome`, `email`, `disciplina` |
| **Curso** | `nome`, `codigo`, `listaProfessorIds` | `id`, `nome`, `codigo`, `listaProfessores` (nomes) |
| **Turma** | `nome`, `cursoId`, `professorId`, `listaAlunoIds` | `id`, `nome`, `nomeCurso`, `nomeProfessor`, `listaAlunos` (nomes) |
| **Aula** | `turmaId`, `dataHora`, `assunto` | `id`, `nomeTurma`, `dataHora`, `assunto` |
| **Nota** | `alunoId`, `aulaId`, `valor` | `id`, `alunoNome`, `aulaAssunto`, `valor` |

## Endpoints Sugeridos

### Aluno
- `GET /alunos` → Lista de `AlunoDTO`
- `GET /alunos/{id}` → `AlunoDTO` específico
- `POST /alunos` → Recebe `AlunoDTO` (req) e retorna `AlunoDTO` (resp)
- `PUT /alunos/{id}` → Recebe `AlunoDTO` (req) e retorna `AlunoDTO` (resp)
- `DELETE /alunos/{id}`

### Professor, Curso, Turma, Aula, Nota
- Possuem operações CRUD similares às de Aluno.

### Endpoints Extras (Relatórios/Consultas)
- `GET /alunos/{id}/notas` → Lista de `NotaDTO` de um aluno
- `GET /turmas/{id}/alunos` → Lista de `AlunoDTO` matriculados em uma turma
- `GET /cursos/{id}/turmas` → Lista de `TurmaDTO` pertencentes a um curso

## Objetivos e Atividades de Fixação

O projeto tem como objetivo fixar os seguintes conceitos:
1. Criação de **DTOs** para cada entidade (Requisição e Resposta).
2. Implementação de **Repositories JDBC** utilizando `PreparedStatement`.
3. Desenvolvimento de **Services** para a lógica de negócio, chamando os repositórios e convertendo entidades para DTOs.
4. Construção de **Controllers REST** que se comunicam com os clientes utilizando estritamente DTOs.
5. Utilização de **Consultas SQL com JOIN** para preencher corretamente os DTOs de resposta (ex: trazer o nome do professor em vez de apenas seu ID na turma).
6. Implementação de **Validações** de dados antes das inserções (ex: e-mail único, nota entre 0 e 10).
7. Testes dos endpoints utilizando ferramentas como **Postman** ou **Insomnia**.

## Script SQL (Tabelas e Dados Iniciais)

O arquivo com os scripts de criação de tabelas (`CREATE TABLE`) e inserção de dados (`INSERT INTO`) está disponível no projeto para facilitar a configuração inicial do banco de dados relacional.

## Atividade

[📄 Visualizar Atividade DTO.pdf](Atividade%20DTO.pdf)
