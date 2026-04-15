# 📱 Avaliação Dev Mobile - Cadastro de Produtos

Este é um aplicativo Android desenvolvido para a avaliação de Desenvolvimento Mobile. O projeto consiste em um sistema simples de gerenciamento de estoque para uma loja de produtos eletrônicos.

## 🚀 Funcionalidades

- **Cadastro de Produtos:**
  - Validação de campos obrigatórios.
  - Validação de preço (positivo com até duas casas decimais).
  - Validação de quantidade (número inteiro positivo).
  - Feedback visual de erros via `TextInputLayout`.
- **Persistência de Dados Local:**
  - Armazenamento offline utilizando a biblioteca **Room Database**.
- **Listagem de Produtos:**
  - Exibição de Nome, Código, Preço e Quantidade em um `RecyclerView`.
  - Interface moderna utilizando `MaterialCardView`.
- **Navegação:**
  - Navegação fluida entre a tela de cadastro e a lista de produtos.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java
- **Banco de Dados:** Room Persistence Library
- **Interface (UI):** XML com Material Design Components
- **Arquitetura:** DAO (Data Access Object) e Singleton para o banco de dados.

## 📋 Critérios de Avaliação Atendidos

1. **Cadastro com Validação:** Implementado com checagem de erros e feedback visual.
2. **Persistência com Room:** Entidade `Product`, Interface `ProductDao` e Classe `AppDatabase`.
3. **Listagem de Produtos:** Uso de `RecyclerView` com `Adapter` personalizado.
4. **Interface e Navegação:** Layout responsivo e navegação via `Intent`.
5. **Boas Práticas:** 
   - Externalização de textos em `strings.xml`.
   - Código comentado e organizado.
   - Uso de componentes do Material Design.

---
**Desenvolvido para fins avaliativos.**
