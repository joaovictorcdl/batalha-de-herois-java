# ⚔️ Batalha de Heróis

> 🦸 Um simulador de batalhas entre heróis e vilões desenvolvido em Java!

## 📖 Sobre o projeto

**Batalha de Heróis** é um projeto acadêmico desenvolvido como atividade proposta pelo professor da disciplina de **Linguagem de Programação**, do curso de Bacharelado em Sistemas de Informação (BSI).

O objetivo é desenvolver um simulador de batalhas em linha de comando (CLI), aplicando conceitos de Programação Orientada a Objetos (POO) e o padrão arquitetural MVC.

O sistema permite cadastrar personagens, selecionar os participantes e acompanhar batalhas entre heróis e vilões, com regras próprias de combate.

## 🎮 Funcionalidades

- 📝 Cadastro de heróis e vilões.
- 📋 Listagem dos personagens cadastrados.
- 🆔 Identificação dos personagens por IDs.
- ⚔️ Seleção de participantes para as batalhas.
- 🎲 Sorteio de quem inicia o primeiro round.
- 🥊 Sistema de combate com ataque e defesa.
- ❤️ Controle e restauração da vida dos personagens.
- 🏆 Identificação do vencedor de cada round e da batalha.
- ⏳ Pausa entre os rounds para acompanhamento da batalha.

## ⚙️ Regras de combate

- Cada batalha acontece entre um herói e um vilão.
- O primeiro atacante é escolhido aleatoriamente.
- Os participantes alternam seus ataques.
- O dano é calculado pela fórmula:

  `Dano = max(1, Ataque do atacante - Defesa do alvo)`

- A vida nunca pode ficar abaixo de zero.
- Cada batalha possui até três rounds.
- A vida dos participantes é restaurada no início de cada novo round.
- O personagem que vencer dois rounds consecutivos é declarado vencedor, encerrando a batalha antes do terceiro round.

## 🧠 Conceitos aplicados

Durante o desenvolvimento, foram trabalhados conceitos fundamentais de Java e Programação Orientada a Objetos:

- Classes e objetos;
- Encapsulamento;
- Construtores;
- Herança;
- Sobrescrita de métodos (`@Override`);
- Polimorfismo;
- Listas e coleções (`ArrayList`);
- Tratamento de exceções;
- Separação de responsabilidades;
- Padrão arquitetural MVC (Model-View-Controller).

## 🏗️ Estrutura do projeto

O projeto foi organizado seguindo o padrão MVC:

```text
src/
└── br/ufrpe/uast/lp/batalhadeherois/
    ├── Main.java
    │
    ├── controller/
    │   └── ControllerBatalhaDeHerois.java
    │
    ├── model/
    │   ├── Batalha.java
    │   ├── CadastroPersonagens.java
    │   ├── Heroi.java
    │   ├── Personagem.java
    │   └── Vilao.java
    │
    └── view/
        └── ViewBatalhaDeHerois.java
```

### 📦 Model
Responsável pelos dados dos personagens, cadastro e regras de combate.

### 🎮 Controller
Responsável por coordenar as operações, receber solicitações e controlar o fluxo das batalhas.

### 🖥️ View
Responsável pela interação com o usuário, utilizando entrada e saída pelo terminal.

## 🛠️ Tecnologias utilizadas

- ☕ Java 21+
- 💻 Visual Studio Code
- 🔧 Git e GitHub

## ▶️ Como executar

1. Clone este repositório:

   ```bash
   git clone https://github.com/joaovictorcdl/batalha-de-herois-java.git
   ```

2. Abra a pasta do projeto no Visual Studio Code.

3. Certifique-se de ter o Java 21 ou superior instalado.

4. Localize o arquivo `Main.java`.

5. Execute o método `main()` para iniciar o simulador.

---

💡 **Projeto desenvolvido para fins acadêmicos, como prática dos conceitos de Java, Programação Orientada a Objetos e MVC.**

⚔️ Que comece a batalha!
