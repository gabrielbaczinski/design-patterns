# Exemplo Prático: Análise de Código e Qualidade de Software

Este repositório contém um exemplo didático em Java desenvolvido para a disciplina de **Design Patterns**.

O objetivo deste projeto é servir de base para o estudo prático de manutenibilidade, legibilidade e boas práticas de arquitetura e código.

---

## 🎯 Objetivo da Atividade

Separar as classes de impressão de tabela em diferentes responsabilidades através do padrão de projetos **Adapter**.

* A interface `br.pucpr.table.TableData` representa os dados de uma tabela de forma genérica (cabeçalhos e linhas), independente do domínio de origem.
* `UsuarioPrinter` e `PlanetasPrinter` não renderizam mais tabelas por conta própria: ambos delegam para uma única classe `br.pucpr.table.Table`, que sabe apenas desenhar uma tabela a partir de um `TableData`.
* `PlanetaTableData` é o **Adapter** do planeta: traduz `List<Planeta>` para o formato esperado por `TableData`.
* Pro usuário, a tabela foi quebrada em colunas: `ColumnData<T>` define uma coluna (cabeçalho + valor) e `ColumnTableData<T>` monta o `TableData` juntando a lista de usuários com a lista de colunas. Cada coluna (`IdColumn`, `NomeColumn`, `EmailColumn`, `CpfColumn`) fica isolada em `br.pucpr.usuario`, então dá pra mexer numa coluna sem afetar as outras.
* A largura de cada coluna é definida pela largura do seu cabeçalho.

### Estrutura

```
src/br/pucpr/table/
  TableData.java
  ColumnData.java
  ColumnTableData.java
  Table.java

src/br/pucpr/usuario/
  Usuario.java
  IdColumn.java
  NomeColumn.java
  EmailColumn.java
  CpfColumn.java
  UsuarioPrinter.java

src/br/pucpr/planeta/
  Planeta.java
  PlanetaTableData.java
  PlanetasPrinter.java
```

---

## 🛠️ Requisitos para Execução

* **Linguagem:** Java 17 ou superior

### Como Executar

```bash
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out br.pucpr.usuario.UsuarioPrinter
java -cp out br.pucpr.planeta.PlanetasPrinter
```
