package VehicleManager;

/**
 * Representa uma Motocicleta no sistema.
 * Esta classe estende a superclasse Vehicle, herdando seus atributos e métodos,
 * e adiciona propriedades específicas para veículos de duas rodas.
 * * @author Marcus Paulo Nogueira Souza/MarcusNogueira
 * @version 1.0
 */
public class Motorcycle extends Vehicle{

    // Atributos específicos da subclasse Motorcycle
    private int cilindradas;
    private String tipoPartida;
    private boolean possuiAbs;

    /**
     * Construtor da classe Motorcycle.
     * Repassa as informações básicas para a superclasse Vehicle através do super().
     */
    public Motorcycle(String placa, String cor, double quilometragem, double capacidadeTanque, String marca, String modelo, int ano, int cilindradas, String tipoPartida, boolean possuiAbs){
        // Invoca o construtor da classe mãe
        super(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano);

        // Inicializa os atributos exclusivos de motos
        this.cilindradas = cilindradas;
        this.tipoPartida = tipoPartida;
        this.possuiAbs = possuiAbs;
    }

    /**
     * Sobrescreve o método setAno para aplicar a validação do contexto de motos.
     * Garante que o ano não seja anterior à invenção da primeira moto de combustão interna (1885).
     */
    @Override
    public void setAno(int ano){
        // Delega a atribuição para a validação geral da superclasse se passar na trava histórica
        if (ano >= 1885){
            super.setAno(ano);
        } else {
            System.out.println("Erro: O ano " + ano + " é inválido para uma moto (mínimo 1885)!");
        }
    }

    // ==========================================
    //       GETTERS E SETTERS ESPECÍFICOS
    // ==========================================

    public int getCilindradas(){
        return cilindradas;
    }
    public void setCilindradas(int cilindradas){
        this.cilindradas = cilindradas;
    }
    public String getTipoPartida(){
        return tipoPartida;
    }
    public void setTipoPartida(String tipoPartida){
        this.tipoPartida = tipoPartida;
    }
    public boolean getPossuiAbs(){
        return possuiAbs;
    }
    public void setPossuiAbs(boolean possuiAbs){
        this.possuiAbs = possuiAbs;
    }

    /**
     * Exibe as informações completas da Motocicleta.
     * Invoca a impressão dos dados comuns da superclasse e adiciona as particularidades da moto.
     */
    public void displayData(){
        // Executa a exibição padrão de Vehicle
        super.displayData();

        // Complementa com as propriedades específicas de Motorcycle
        System.out.println("Cilindradas: " + getCilindradas());
        System.out.println("Partida de tipo: " + getTipoPartida());
        System.out.println("Possui Freios Abs: " + (getPossuiAbs() ? "Sim" : "Não"));
    }
}