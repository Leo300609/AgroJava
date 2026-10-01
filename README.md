# 🌱 AgroJava

O **AgroJava** é um projeto pessoal desenvolvido em **Java** para simular um sistema simples de monitoramento agrícola.

O programa permite cadastrar dados de **chuva dos últimos 7 dias** e a **umidade dos talhões de um campo 4x4**, além de exibir o mapa do campo e identificar áreas que precisam de irrigação.

## 📋 Funcionalidades

O sistema possui um menu interativo com as seguintes opções:

- **1 - Cadastrar Dados**
  - Registra a quantidade de chuva de cada um dos 7 dias da semana, em milímetros (mm).
  - Registra a umidade de cada talhão de um campo 4x4, em porcentagem (%).

- **2 - Exibir Mapa do Campo**
  - Exibe uma representação do campo utilizando uma matriz 4x4.
  - Mostra a umidade registrada em cada talhão.

- **3 - Relatório de Alertas de Irrigação**
  - Analisa a umidade de cada talhão.
  - Talhões com umidade abaixo de **30%** são identificados como necessitando de irrigação.

- **4 - Sair**
  - Encerra o programa.

## 🛠️ Tecnologias utilizadas

- **Java**
- `Scanner` para entrada de dados pelo terminal
- Arrays unidimensionais
- Arrays bidimensionais
- Estruturas de repetição (`for` e `do-while`)
- Estruturas condicionais (`if` e `else if`)

## 📂 Estrutura do projeto

```text
AgroJava/
├── src/
│   └── com/
│       └── example/
│           └── AgroJava.java
│
└── README.md
```

## ▶️ Como executar

### Pré-requisitos

Para executar o projeto, é necessário ter o **Java JDK** instalado na máquina.

Verifique se o Java está instalado:

```bash
java -version
```

Também é possível verificar a versão do compilador:

```bash
javac -version
```

### 📥 Clonando o projeto

Clone este repositório utilizando:

```bash
git clone https://github.com/Leo300609/AgroJava.git
```

Depois, entre na pasta do projeto:

```bash
cd AgroJava
```

### 🔨 Compilando o projeto

Compile o arquivo Java utilizando:

```bash
javac -d out src/com/example/AgroJava.java
```

Esse comando irá compilar o programa e colocar os arquivos `.class` dentro da pasta `out`.

### ▶️ Executando o projeto

Depois de compilar, execute o programa com:

```bash
java -cp out com.example.AgroJava
```

## 🖥️ Exemplo de uso

Ao iniciar o programa, o seguinte menu será exibido:

```text
======================
Bem-vindo ao AgroJava!
======================
1-) Cadastrar Dados
2-) Exibir mapa do campo
3-) Relatórios de Alertas de Irrigação
4-) Sair
```

### ☔ Cadastro de chuva

Ao escolher a opção `1`, o sistema solicitará a quantidade de chuva registrada em cada um dos 7 dias:

```text
Me diga a quantidade de chuva para cada um dos 7 dias da semana (em mm):

Dia 1: 12
Dia 2: 8
Dia 3: 15
Dia 4: 5
Dia 5: 10
Dia 6: 20
Dia 7: 7
```

### 🌱 Cadastro da umidade

Depois, o programa solicitará a umidade de cada talhão do campo 4x4:

```text
Agora me diga a quantidade de umidade de cada talhao do campo 4x4 (em %):

Talhão [0][0]: 25
Talhão [0][1]: 42
Talhão [0][2]: 31
Talhão [0][3]: 18
Talhão [1][0]: 55
Talhão [1][1]: 40
Talhão [1][2]: 28
Talhão [1][3]: 35
Talhão [2][0]: 60
Talhão [2][1]: 45
Talhão [2][2]: 22
Talhão [2][3]: 50
Talhão [3][0]: 38
Talhão [3][1]: 41
Talhão [3][2]: 27
Talhão [3][3]: 52
```

### 🗺️ Exibindo o mapa do campo

Ao escolher a opção `2`, o programa exibirá a umidade dos talhões:

```text
Mapa do campo (umidade em %):
25.0    42.0    31.0    18.0
55.0    40.0    28.0    35.0
60.0    45.0    22.0    50.0
38.0    41.0    27.0    52.0
```

### 🚨 Relatório de alertas

Ao escolher a opção `3`, o programa verifica quais talhões possuem umidade abaixo de **30%**.

Exemplo:

```text
Relatórios de Alertas de Irrigação:

Talhão [0][0] precisa de irrigação! Umidade atual: 25.0%
Talhão [0][3] precisa de irrigação! Umidade atual: 18.0%
Talhão [1][2] precisa de irrigação! Umidade atual: 28.0%
Talhão [2][2] precisa de irrigação! Umidade atual: 22.0%
Talhão [3][2] precisa de irrigação! Umidade atual: 27.0%
```

## 🎯 Objetivo do projeto

O **AgroJava** foi desenvolvido com o objetivo de praticar conceitos fundamentais da linguagem **Java**.

Durante o desenvolvimento, foram utilizados conceitos como:

- Declaração e manipulação de variáveis;
- Entrada de dados com `Scanner`;
- Arrays unidimensionais;
- Arrays bidimensionais;
- Estruturas de repetição `for`;
- Estrutura de repetição `do-while`;
- Estruturas condicionais `if` e `else if`;
- Manipulação de dados;
- Menus interativos no terminal.

## 🚀 Possíveis melhorias

Algumas funcionalidades que podem ser implementadas futuramente:

- [ ] Validar os dados inseridos pelo usuário;
- [ ] Impedir valores de umidade menores que 0% ou maiores que 100%;
- [ ] Calcular a média de chuva dos 7 dias;
- [ ] Exibir a maior e a menor quantidade de chuva;
- [ ] Calcular a média de umidade do campo;
- [ ] Contabilizar quantos talhões precisam de irrigação;
- [ ] Criar diferentes níveis de alerta de irrigação;
- [ ] Permitir alterar dados já cadastrados;
- [ ] Salvar os dados em arquivos;
- [ ] Utilizar banco de dados;
- [ ] Criar uma interface gráfica;
- [ ] Separar o projeto em diferentes classes;
- [ ] Aplicar conceitos de Programação Orientada a Objetos;
- [ ] Criar testes automatizados.

## 📌 Status

🚧 **Em desenvolvimento**

O projeto está sendo desenvolvido como uma forma de praticar e aprimorar conhecimentos em **Java** e fundamentos de programação.

## 👨‍💻 Autor

**Leonardo Almeida Canto**

Projeto pessoal desenvolvido para estudos e prática de programação em **Java**.

---

⭐ Se este projeto foi útil para você ou ajudou nos seus estudos, considere deixar uma estrela no repositório!
