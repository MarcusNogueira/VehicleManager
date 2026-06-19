package VehicleManager;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Classe responsável pelo gerenciamento da frota de veículos.
 * Contém as operações de CRUD(Criar, Ler, Atualizar, Deletar) e
 * persistência de dados em arquivos CSV.
 * @author Marcus Paulo Nogueira Souza/MarcusNogueira
 * @version 1.0
 */
public class FleetManager{

    // Armazena a lista unificada de veículos utilizando polimorfismo
    private ArrayList<Vehicle> vehicleList = new ArrayList<>();

    // Scanner configurado com Locale US para aceitar double com ponto(ex: 1.5) ao invés de vírgula
    Scanner scan = new Scanner(System.in).useLocale(java.util.Locale.US);

    /**
     * Realiza o cadastro de novos veículos(Carros, Motos ou Aviões).
     * Aplica regras de validação baseadas no ano histórico de invenção de cada tipo.
     */
    public void vehicleRegistration(){
        System.out.println("----------------------");
        System.out.println("Qual o tipo de veículo\ndeseja cadastrar?");
        System.out.println("1. Carro!");
        System.out.println("2. Moto!");
        System.out.println("3. Avião!");
        System.out.println("0. Voltar!");
        System.out.println("----------------------");
        int opcao = scan.nextInt();
        scan.nextLine(); // Consome a quebra de linha deixada pelo nextInt()

        // Coleta de dados comuns a todos os veículos
        System.out.println("Qual o registro/identificação do veículo(Placa ou ANAC)?");
        String placa = scan.nextLine();
        System.out.println("Qual a cor? ");
        String cor = scan.nextLine();
        System.out.println("Qual a quilometragem? ");
        double quilometragem = scan.nextDouble();
        scan.nextLine();
        System.out.println("Qual a capacidade do tanque? ");
        double capacidadeTanque = scan.nextDouble();
        scan.nextLine();
        System.out.println("Qual a marca? ");
        String marca = scan.nextLine();
        System.out.println("Qual o modelo? ");
        String modelo = scan.nextLine();
        System.out.println("Qual o ano? ");
        int ano = scan.nextInt();
        scan.nextLine();

        // Fluxo específico para Carros(Invenção do Benz Patent-Motorwagen: 1886)
        if(opcao == 1){
            if(ano>=1886){
                System.out.println("Qual o tipo de Carroceria do carro? ");
                String tipoCarroceria = scan.nextLine();
                System.out.println("O carro possui Ar Condicionado?(true/false): ");
                boolean possuiArCondicionado = scan.nextBoolean();
                scan.nextLine();
                System.out.println("O carro possui Teto Solar?(true/false): ");
                boolean temTetoSolar = scan.nextBoolean();
                scan.nextLine();
                System.out.println("Qual o quantidade de portas do carro? ");
                int quantidadePortas = scan.nextInt();
                scan.nextLine();
                Car newCar = new Car(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano, tipoCarroceria, possuiArCondicionado, temTetoSolar, quantidadePortas);
                vehicleList.add(newCar);
            }else{
                System.out.println("Erro: O ano digitado é inválido!");
            }
        // Fluxo específico para Motos(Invenção da Daimler Reitwagen: 1885)
        }else if(opcao == 2){
            if(ano>=1885){
                System.out.println("Quantas cilindradas a moto possui? ");
                int cilindradas = scan.nextInt();
                scan.nextLine();
                System.out.println("Qual o tipo de partida? ");
                String tipoPartida = scan.nextLine();
                System.out.println("A moto possui Freios Abs?(true/false): ");
                boolean possuiAbs = scan.nextBoolean();
                scan.nextLine();
                Motorcycle newMotorcycle = new Motorcycle(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano, cilindradas, tipoPartida, possuiAbs);
                vehicleList.add(newMotorcycle);
            }else{
                System.out.println("Erro: O ano digitado é inválido!");
            }
        // Fluxo específico para Aviões(Marco histórico do 14-Bis / Wright Flyers: 1903)
        }else if(opcao == 3){
            if(ano>=1903){
                System.out.println("Quantos motores o avião possui? ");
                int quantidadeMotores = scan.nextInt();
                scan.nextLine();
                System.out.println("Qual a altitude máxima ele pode chegar? ");
                double altitudeMaxima = scan.nextDouble();
                scan.nextLine();
                System.out.println("Qual o tipo de propulsão? ");
                String tipoPropulsao = scan.nextLine();
                Airplane newAirplane = new Airplane(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano, quantidadeMotores, altitudeMaxima, tipoPropulsao);
                vehicleList.add(newAirplane);
            }else{
                System.out.println("Erro: O ano digitado é inválido!");
            }
        }else if(opcao == 0){
            System.out.println("Voltando...");
        }
    }
    /**
     * Remove um veículo da lista comparando a placa/prefixo ignorando maiúsculas/minúsculas.
     */
    public void removeVehicle(){
        System.out.println("Qual a placa/prefixo ANAC do veículo que você deseja remover? ");
        String plc = scan.nextLine();

        // Predicado que remove o item se encontrar correspondência exata de texto
        boolean removido = vehicleList.removeIf(v -> v.getPlaca().equalsIgnoreCase(plc));
        if(removido){
            System.out.println("Sucesso: Veículo removido com sucesso!");
        } else {
            System.out.println("Erro: Não foi possível remover. Placa/registro ANAC não encontrado.");
        }
    }

    /**
     * Exibe todos os veículos cadastrados na frota utilizando polimorfismo para chamar displayData().
     */
    public void listVehicle(){
        int i = 1;
        for(Vehicle v : vehicleList){
            System.out.println("------------- Veículo " + i + " -------------");
            v.displayData();
            System.out.println("--------------------------------------");
            i++;
        }
    }

    /**
     * Filtra e exibe os veículos de acordo com o tipo escolhido(Carro, Moto ou Avião).
     */
    public void listType(){
        if(vehicleList.isEmpty()){
            System.out.println("Aviso: A frota está vazia.");
            return;
        }
        System.out.println("----------------------");
        System.out.println("Qual o tipo de veículo\ndeseja listar?");
        System.out.println("1. Carro!");
        System.out.println("2. Moto!");
        System.out.println("3. Avião!");
        System.out.println("0. Voltar!");
        System.out.println("----------------------");
        int op = scan.nextInt();
        scan.nextLine();
        int i = 1;
        System.out.println("========== VEÍCULOS FILTRADOS ==========");
        for(Vehicle v : vehicleList){

            // Utiliza o operador instanceof para verificar a sub-classe real em tempo de execução
            if(op == 1 && v instanceof Car){
                System.out.println("------------ Carro " + i + " ------------");
                v.displayData();
                System.out.println("------------------------");
                i++;
            }else if(op == 2 && v instanceof Motorcycle){
                System.out.println("------------ Moto " + i + " ------------");
                v.displayData();
                System.out.println("------------------------");
                i++;
            }else if(op == 3 && v instanceof Airplane){
                System.out.println("------------ Avião " + i + " ------------");
                v.displayData();
                System.out.println("------------------------");
                i++;
            }else if(op == 0){
                System.out.println("Voltando...");
            }
        }
        if(op != 0){
            System.out.println("========================================");
        }
    }

    /**
     * Busca um veículo específico na lista a partir de sua placa ou prefixo ANAC.
     */
    public void searchPlate(){
        System.out.print("Digite a placa/registro ANAC do veículo que deseja buscar: ");
        String placaBuscada = scan.nextLine();
        boolean encontrado = false;
        for(Vehicle v : vehicleList){
            if(v.getPlaca().equalsIgnoreCase(placaBuscada)){
                System.out.println("\n--- VEÍCULO ENCONTRADO ---");
                v.displayData();
                encontrado = true;
                break; // Interrompe a busca ao encontrar o primeiro resultado
            }
        }
        if(!encontrado){
            System.out.println("Aviso: Nenhum veículo com a placa/registro ANAC '" + placaBuscada + "' foi encontrado.");
        }
    }

    /**
     * Localiza um veículo pela placa e abre um menu dinâmico para edição de seus atributos,
     * fazendo Downcasting quando necessário para acessar atributos específicos da sub-classe.
     */
    public void updatebyPlate(){
        System.out.print("Digite a placa do veículo que deseja atualizar: ");
        String placaBuscada = scan.nextLine();
        boolean encontrado = false;
        for(Vehicle v : vehicleList){
            if(v.getPlaca().equalsIgnoreCase(placaBuscada)){
                encontrado = true;
                System.out.println("\n--- Veículo Encontrado ---");
                System.out.println("O que você deseja atualizar?");
                System.out.println("1. Marca");
                System.out.println("2. Modelo");
                System.out.println("3. Ano");
                System.out.println("4. Cor");

                // Exibe opções adicionais baseadas no tipo real do objeto obtido
                if(v instanceof Car){
                    System.out.println("5. Tipo de Carroceria");
                    System.out.println("6. Quantidade de portas");
                    System.out.println("7. Possui teto solar");
                    System.out.println("8. Quilometragem");
                    System.out.println("9. Capacidade do tanque");
                    System.out.println("10. Possui Ar Condicionado");
                } else if(v instanceof Motorcycle){
                    System.out.println("5. Cilindradas");
                    System.out.println("6. Tipo de partida");
                    System.out.println("7. Possui freios ABS");
                } else if(v instanceof Airplane){
                    System.out.println("5. Quantidade de Motores");
                    System.out.println("6. Altitude Máxima");
                    System.out.println("7. Tipo de propulsão");
                }
                System.out.print("Escolha uma opção: ");
                int op = scan.nextInt();
                scan.nextLine();
                switch(op){
                    case 1:
                        System.out.print("Nova Marca(Atual: " + v.getMarca() + "): ");
                        v.setMarca(scan.nextLine());
                        System.out.println("Marca atualizada com sucesso!");
                        break;
                    case 2:
                        System.out.print("Novo Modelo(Atual: " + v.getModelo() + "): ");
                        v.setModelo(scan.nextLine());
                        System.out.println("Modelo atualizada com sucesso!");
                        break;
                    case 3:
                        System.out.print("Novo Ano(Atual: " + v.getAno() + "): ");
                        v.setAno(scan.nextInt());
                        scan.nextLine();
                        System.out.println("Ano atualizado com sucesso!");
                        break;
                    case 4:
                        System.out.print("Nova Cor(Atual: " + v.getCor() + "): ");
                        v.setCor(scan.nextLine());
                        System.out.println("Cor atualizada com sucesso!");
                        break;
                    case 5:
                        // Realiza o Downcasting seguro para modificar os campos específicos
                        if(v instanceof Car){
                            Car c =(Car) v;
                            System.out.print("Novo Tipo de Carroceria(Atual: " + c.getTipoCarroceria() + "): ");
                            c.setTipoCarroceria(scan.nextLine());
                        } else if(v instanceof Motorcycle){
                            Motorcycle m =(Motorcycle) v;
                            System.out.print("Nova Cilindrada(Atual: " + m.getCilindradas() + "): ");
                            m.setCilindradas(scan.nextInt());
                            scan.nextLine();
                        } else if(v instanceof Airplane){
                            Airplane a =(Airplane) v;
                            System.out.print("Nova Quantidade de Motores(Atual: " + a.getQuantidadeMotores() + "): ");
                            a.setQuantidadeMotores(scan.nextInt());
                            scan.nextLine();
                        }
                        System.out.println("Dado atualizado com sucesso!");
                        break;
                    case 6:
                        if(v instanceof Car){
                            Car c =(Car) v;
                            System.out.print("Nova Quantidade de Portas(Atual: " + c.getQuantidadePortas() + "): ");
                            c.setQuantidadePortas(scan.nextInt());
                            scan.nextLine();
                        } else if(v instanceof Motorcycle){
                            Motorcycle m =(Motorcycle) v;
                            System.out.print("Novo Tipo de Partida(Atual: " + m.getTipoPartida() + "): ");
                            m.setTipoPartida(scan.nextLine());
                        } else if(v instanceof Airplane){
                            Airplane a =(Airplane) v;
                            System.out.print("Nova Altitude Máxima(Atual: " + a.getAltitudeMaxima() + "): ");
                            a.setAltitudeMaxima(scan.nextDouble());
                            scan.nextLine();
                        }
                        System.out.println("Dado atualizado com sucesso!");
                        break;
                    case 7:
                        if(v instanceof Car){
                            Car c =(Car) v;
                            System.out.print("Possui teto solar?(true/false)(Atual: " + c.getTemTetoSolar() + "): ");
                            c.setTemTetoSolar(scan.nextBoolean());
                            scan.nextLine();
                        } else if(v instanceof Motorcycle){
                            Motorcycle m =(Motorcycle) v;
                            System.out.print("Possui freios ABS?(true/false)(Atual: " + m.getPossuiAbs() + "): ");
                            m.setPossuiAbs(scan.nextBoolean());
                            scan.nextLine();
                        } else if(v instanceof Airplane){
                            Airplane a =(Airplane) v;
                            System.out.print("Novo Tipo de Propulsão(Atual: " + a.getTipoPropulsao() + "): ");
                            a.setTipoPropulsao(scan.nextLine());
                        }
                        System.out.println("Dado atualizado com sucesso!");
                        break;
                    case 8:
                        if(v instanceof Car){
                            Car c =(Car) v;
                            System.out.print("Nova Quilometragem(Atual: " + c.getQuilometragem() + "): ");
                            c.setQuilometragem(scan.nextDouble());
                            scan.nextLine();
                            System.out.println("Quilometragem atualizada com sucesso!");
                        } else {
                            System.out.println("Opção inválida para este tipo de veículo.");
                        }
                        break;
                    case 9:
                        if(v instanceof Car){
                            Car c =(Car) v;
                            System.out.print("Nova Capacidade do Tanque(Atual: " + c.getCapacidadeTanque() + "): ");
                            c.setCapacidadeTanque(scan.nextDouble());
                            scan.nextLine();
                            System.out.println("Capacidade do tanque atualizada com sucesso!");
                        } else {
                            System.out.println("Opção inválida para este tipo de veículo.");
                        }
                        break;
                    case 10:
                        if(v instanceof Car){
                            Car c =(Car) v;
                            System.out.print("Possui Ar Condicionado?(true/false)(Atual: " + c.getPossuiArCondicionado() + "): ");
                            c.setPossuiArCondicionado(scan.nextBoolean());
                            scan.nextLine();
                            System.out.println("Ar condicionado atualizado com sucesso!");
                        } else {
                            System.out.println("Opção inválida para este tipo de veículo.");
                        }
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                break; // Encerra o loop após atualizar o veículo correto
            }
        }
        if(!encontrado){
            System.out.println("Erro: Nenhum veículo com a placa/registro ANAC " + placaBuscada + " foi encontrado.");
        }
    }

    /**
     * Exporta os dados da lista de veículos para três arquivos CSV separados.
     * Utiliza a estrutura try-with-resources para garantir o fechamento automático dos escritores.
     */
    public void exportExcel(){
        // Bloco de Exportação de Carros
        try(PrintWriter writer = new PrintWriter(new FileWriter("carros.csv"))){
            writer.println("Placa;Marca;Modelo;Ano;Cor;Quilometragem;Capacidade do Tanque;Carroceria;Portas;Teto Solar;Ar Condicionado");
            for(Vehicle v : vehicleList){
                if(v instanceof Car){
                    Car c =(Car) v;
                    writer.println(c.getPlaca() + ";" + c.getMarca() + ";" + c.getModelo() + ";" +
                            c.getAno() + ";" + c.getCor() + ";" + c.getQuilometragem() + ";" +
                            c.getCapacidadeTanque() + ";" + c.getTipoCarroceria() + ";" +
                            c.getQuantidadePortas() + ";" +(c.getTemTetoSolar() ? "Sim" : "Não") + ";" +
                           (c.getPossuiArCondicionado() ? "Sim" : "Não"));
                }
            }
        } catch(IOException e){
            System.out.println("Erro ao gerar tabela de carros: " + e.getMessage());
        }

        // Bloco de Exportação de Motos
        try(PrintWriter writer = new PrintWriter(new FileWriter("motos.csv"))){
            writer.println("Placa;Marca;Modelo;Ano;Cor;KM;Tanque;Cilindradas;Partida;ABS");
            for(Vehicle v : vehicleList){
                if(v instanceof Motorcycle){
                    Motorcycle m =(Motorcycle) v;
                    writer.println(m.getPlaca() + ";" + m.getMarca() + ";" + m.getModelo() + ";" +
                            m.getAno() + ";" + m.getCor() + ";" + m.getQuilometragem() + ";" +
                            m.getCapacidadeTanque() + ";" + m.getCilindradas() + ";" +
                            m.getTipoPartida() + ";" +(m.getPossuiAbs() ? "Sim" : "Não"));
                }
            }
        } catch(IOException e){
            System.out.println("Erro ao gerar tabela de motos: " + e.getMessage());
        }

        // Bloco de Exportação de Aviões
        try(PrintWriter writer = new PrintWriter(new FileWriter("avioes.csv"))){
            writer.println("Prefixo ANAC;Marca;Modelo;Ano;Cor;Horas de Voo;Tanque;Motores;Alt Maxima;Propulsao");
            for(Vehicle v : vehicleList){
                if(v instanceof Airplane){
                    Airplane a =(Airplane) v;
                    writer.println(a.getPlaca() + ";" + a.getMarca() + ";" + a.getModelo() + ";" +
                            a.getAno() + ";" + a.getCor() + ";" + a.getQuilometragem() + ";" +
                            a.getCapacidadeTanque() + ";" + a.getQuantidadeMotores() + ";" +
                            a.getAltitudeMaxima() + ";" + a.getTipoPropulsao());
                }
            }
        } catch(IOException e){
            System.out.println("Erro ao gerar tabela de aviões: " + e.getMessage());
        }
        System.out.println("Sucesso: Três arquivos(.csv) gerados! Eles aparecerão na raiz do seu projeto e podem ser abertos direto no Excel.");
    }

    /**
     * Carrega os dados salvos nos arquivos CSV reconstruindo os objetos e populando a frota.
     * Limpa a lista atual antes do carregamento para evitar duplicidade.
     */
    public void loadExcel(){
        vehicleList.clear();

        // Leitura e Parser de Carros
        try(BufferedReader reader = new BufferedReader(new FileReader("carros.csv"))){
            String linha = reader.readLine();
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                String placa = dados[0];
                String marca = dados[1];
                String modelo = dados[2];
                int ano = Integer.parseInt(dados[3]);
                String cor = dados[4];
                double quilometragem = Double.parseDouble(dados[5]);
                double capacidadeTanque = Double.parseDouble(dados[6]);
                String tipoCarroceria = dados[7];
                int quantidadePortas = Integer.parseInt(dados[8]);
                boolean temTetoSolar = dados[9].equalsIgnoreCase("Sim");
                boolean possuiArCondicionado = dados[10].equalsIgnoreCase("Sim");
                Car c = new Car(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano, tipoCarroceria, possuiArCondicionado, temTetoSolar, quantidadePortas);
                vehicleList.add(c);
            }
        } catch(IOException e){
            System.out.println("Aviso: Arquivo 'carros.csv' não encontrado. Pulando...");
        }

        // Leitura e Parser de Motos
        try(BufferedReader reader = new BufferedReader(new FileReader("motos.csv"))){
            String linha = reader.readLine();
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                String placa = dados[0];
                String marca = dados[1];
                String modelo = dados[2];
                int ano = Integer.parseInt(dados[3]);
                String cor = dados[4];
                double quilometragem = Double.parseDouble(dados[5]);
                double capacidadeTanque = Double.parseDouble(dados[6]);
                int cilindradas = Integer.parseInt(dados[7]);
                String tipoPartida = dados[8];
                boolean possuiAbs = dados[9].equalsIgnoreCase("Sim");
                Motorcycle m = new Motorcycle(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano, cilindradas, tipoPartida, possuiAbs);
                vehicleList.add(m);
            }
        } catch(IOException e){
            System.out.println("Aviso: Arquivo 'motos.csv' não encontrado. Pulando...");
        }

        // Leitura e Parser de Aviões
        try(BufferedReader reader = new BufferedReader(new FileReader("avioes.csv"))){
            String linha = reader.readLine();
            while((linha = reader.readLine()) != null){
                String[] dados = linha.split(";");
                String placa = dados[0];
                String marca = dados[1];
                String modelo = dados[2];
                int ano = Integer.parseInt(dados[3]);
                String cor = dados[4];
                double quilometragem = Double.parseDouble(dados[5]);
                double capacidadeTanque = Double.parseDouble(dados[6]);
                int quantidadeMotores = Integer.parseInt(dados[7]);
                double altitudeMaxima = Double.parseDouble(dados[8]);
                String tipoPropulsao = dados[9];
                Airplane a = new Airplane(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano, quantidadeMotores, altitudeMaxima, tipoPropulsao);
                vehicleList.add(a);
            }
        } catch(IOException e){
            System.out.println("Aviso: Arquivo 'avioes.csv' não encontrado. Pulando...");
        }
        System.out.println("Sistema: " + vehicleList.size() + " veículos carregados com sucesso!");
    }
}