package VehicleManager;

/**
 * Representa um Avião no sistema.
 * Esta classe estende a superclasse Vehicle, herdando seus atributos e métodos,
 * e adiciona propriedades específicas para aeronaves.
 * @author Marcus Paulo Nogueira Souza/MarcusNogueira
 * @version 1.0
 */
public class Airplane extends Vehicle{

    // Atributos específicos da subclasse Airplane
    private int quantidadeMotores;
    private double altitudeMaxima;
    private String tipoPropulsao;

    /**
     * Construtor da classe Airplane.
     * Repassa as informações básicas (incluindo o prefixo ANAC no lugar da placa)
     * para a superclasse Vehicle através do super().
     */
    public Airplane(String placa, String cor, double quilometragem, double capacidadeTanque, String marca, String modelo, int ano, int quantidadeMotores, double altitudeMaxima, String tipoPropulsao){
        // Invoca o construtor da classe mãe
        super(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano);

        // Inicializa os atributos exclusivos de aviões
        this.quantidadeMotores = quantidadeMotores;
        this.altitudeMaxima = altitudeMaxima;
        this.tipoPropulsao = tipoPropulsao;
    }

    /**
     * Sobrescreve o método setAno para aplicar a validação do contexto de aviação.
     * Garante que o ano não seja anterior aos marcos históricos da aviação controlada (1903).
     */
    @Override
    public void setAno(int ano){
        if (ano >= 1903){
            // Delega a atribuição para a superclasse após a validação histórica
            super.setAno(ano);
        } else {
            System.out.println("Erro: O ano " + ano + " é inválido para um avião (mínimo 1903)!");
        }
    }

    // ==========================================
    //       GETTERS E SETTERS ESPECÍFICOS
    // ==========================================

    public int getQuantidadeMotores(){
        return quantidadeMotores;
    }
    public void setQuantidadeMotores(int quantidadeMotores){
        this.quantidadeMotores = quantidadeMotores;
    }
    public double getAltitudeMaxima(){
        return altitudeMaxima;
    }
    public void setAltitudeMaxima(double altitudeMaxima){
        this.altitudeMaxima = altitudeMaxima;
    }
    public String getTipoPropulsao(){
        return tipoPropulsao;
    }
    public void setTipoPropulsao(String TipoPropulsao){
        this.tipoPropulsao = tipoPropulsao;
    }

    /**
     * Exibe as informações completas do Avião.
     * Invoca a impressão dos dados comuns da superclasse e adiciona as particularidades da aeronave.
     */
    public void displayData(){
        // Executa a exibição padrão de Vehicle
        super.displayData();

        // Complementa com as propriedades específicas de Airplane
        System.out.println("Quantidade de Motores: " +  getQuantidadeMotores());
        System.out.println("Altitude Máxima: " + getAltitudeMaxima());
        System.out.println("Tipo de Propulsão: " +  getTipoPropulsao());
    }
}