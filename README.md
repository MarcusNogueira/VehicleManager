# VehicleManager 🚗🏍️✈️

O **VehicleManager** é um sistema de linha de comando (CLI) desenvolvido em Java para o gerenciamento centralizado e persistente de uma frota de veículos multimoldais, englobando **Carros**, **Motos** e **Aviões**. 

O projeto foi construído aplicando conceitos fundamentais de **Orientação a Objetos (POO)**, como herança, encapsulamento, polimorfismo e reaproveitamento de código, além de persistência de dados local via arquivos CSV.

---

## 🚀 Funcionalidades (CRUD)

* **Cadastrar Veículo:** Entrada de dados via console com validações históricas específicas (ex: impede carros anteriores a 1886, motos anteriores a 1885 ou aviões anteriores a 1903).
* **Remover Veículo:** Exclusão dinâmica da frota utilizando a placa ou registro ANAC do veículo.
* **Listar Frota Geral:** Exibe todos os veículos cadastrados utilizando polimorfismo para renderizar os dados específicos de cada tipo.
* **Filtrar por Tipo:** Permite listar de forma isolada apenas Carros, Motos ou Aviões através da verificação de instâncias em tempo de execução (`instanceof`).
* **Buscar por Identificador:** Localização rápida de um veículo pela placa/registro ANAC.
* **Atualização Dinâmica:** Menu de edição adaptável que identifica o tipo do veículo selecionado e libera a alteração de seus atributos exclusivos via *downcasting*.
* **Persistência Automática:** O sistema carrega os dados dos arquivos `.csv` ao iniciar e salva todas as alterações automaticamente ao fechar o programa.

---

## 🛠️ Arquitetura e Conceitos de POO Aplicados

* **Abstração e Herança:** A classe `Vehicle` funciona como base genérica compartilhando os atributos comuns (Placa, Marca, Modelo, Ano, Cor, etc.) para as subclasses especializadas: `Car`, `Motorcycle` e `Airplane`.
* **Polimorfismo:** Sobrescrita de métodos (`@Override`), como no método `displayData()` e `setAno()`, permitindo que comportamentos mudem dinamicamente de acordo com o tipo real do objeto.
* **Encapsulamento:** Todos os atributos protegidos por modificadores de acesso `private` e manipulados estritamente através de métodos acessores (*Getters* e *Setters*).
* **Tratamento de Exceções:** Uso de blocos `try-with-resources` para manipulação segura e fechamento automático de streams de arquivos (`BufferedReader` / `PrintWriter`), evitando vazamento de memória (*memory leaks*).

---

## 📁 Estrutura do Projeto

```text
VehicleManager/
│
├── Main.java             # Ponto de entrada do programa e fluxo do menu interativo
├── FleetManager.java     # Regras de negócio, operações de CRUD e manipulação de arquivos
├── Vehicle.java          # Superclasse abstrata com os dados genéricos dos veículos
├── Car.java              # Subclasse especializada em automóveis
├── Motorcycle.java       # Subclasse especializada em motocicletas
└── Airplane.java         # Subclasse especializada em aeronaves
