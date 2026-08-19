# Exemplo Prático: Análise de Código e Qualidade de Software

Este repositório contém um exemplo didático em Java desenvolvido para a disciplina de **Design Patterns**.

O objetivo deste projeto é servir de base para o estudo prático de manutenibilidade, legibilidade e boas práticas de arquitetura e código.

---

## 🎯 Objetivo da Atividade

Separar as classes de impressão de tabela em diferentes responsabilidades através do padrão de projetos **Adapter**.

* A interface `br.pucpr.table.TableData` representa os dados de uma tabela de forma genérica (cabeçalhos e linhas), independente do domínio de origem.
* `UsuarioPrinter` e `PlanetasPrinter` não renderizam mais tabelas por conta própria: ambos delegam para uma única classe `br.pucpr.table.Table`, que sabe apenas desenhar uma tabela a partir de um `TableData`.
* `UsuarioTableData` e `PlanetaTableData` são os **Adapters**: traduzem, respectivamente, `List<Usuario>` e `List<Planeta>` para o formato esperado por `TableData`.
* A largura de cada coluna é definida pela largura do seu cabeçalho.

### Estrutura

```
src/br/pucpr/table/
  TableData.java
  Table.java

src/br/pucpr/usuario/
  Usuario.java
  UsuarioTableData.java
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
