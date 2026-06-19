package VehicleManager;

import java.util.Scanner;

/**
 * Classe principal do sistema VehicleManager.
 * Responsável por gerenciar o fluxo do menu interativo e a comunicação com o usuário.
 * @author Marcus Paulo Nogueira Souza/MarcusNogueira
 * @version 1.0
 */
public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        FleetManager Manager = new FleetManager();

        // Inicializa o sistema carregando os dados salvos
        Manager.loadExcel();
        int op = -1;

        // Loop principal do menu interativo
        while(op != 0){
            System.out.println("-------------MENU-------------");
            System.out.println("1. Cadastrar veículo!");
            System.out.println("2. Remover veículo!");
            System.out.println("3. Listar todos os veículos!");
            System.out.println("4. Listar veículos de um tipo em específico!");
            System.out.println("5. Buscar veículo por Placa/Registro ANAC!");
            System.out.println("6. Atualizar/Editar dados de um veículo!");
            System.out.println("0. Salvar e sair!");
            System.out.println("-------------------------------");
            op = scan.nextInt();
            scan.nextLine(); // Consome a quebra de linha deixada pelo nextInt()

            // Direciona a ação do usuário para a funcionalidade correspondente
            switch(op){
                case 1:
                    Manager.vehicleRegistration();
                    break;
                case 2:
                    Manager.removeVehicle();
                    break;
                case 3:
                    Manager.listVehicle();
                    break;
                case 4:
                    Manager.listType();
                    break;
                case 5:
                    Manager.searchPlate();
                    break;
                case 6:
                    Manager.updatebyPlate();
                    break;
                case 0:
                    // Garante a persistência dos dados antes de encerrar o programa
                    Manager.exportExcel();
                    break;
                default:
                    System.out.println("Erro: Número Inválido!");
            }
        }
    }
}