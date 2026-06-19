package VehicleManager;

/**
 * Representa um Carro no sistema.
 * Esta classe estende a superclasse Vehicle, herdando seus atributos e métodos,
 * e adiciona propriedades específicas de veículos terrestres de quatro ou mais rodas.
 * @author Marcus Paulo Nogueira Souza/MarcusNogueira
 * @version 1.0
 */
public class Car extends Vehicle{

    // Atributos específicos da subclasse Car
    private String tipoCarroceria;
    private boolean possuiArCondicionado;
    private boolean temTetoSolar;
    private int quantidadePortas;

    /**
     * Construtor da classe Car.
     * Utiliza o método super() para passar os dados comuns para o construtor da classe mãe (Vehicle).
     */
    public Car(String placa, String cor, double quilometragem, double capacidadeTanque, String marca, String modelo, int ano, String tipoCarroceria, boolean possuiArCondicionado, boolean temTetoSolar, int quantidadePortas){
        // Invoca o construtor da superclasse Vehicle
        super(placa, cor, quilometragem, capacidadeTanque, marca, modelo, ano);

        // Inicializa os atributos específicos desta subclasse
        this.tipoCarroceria = tipoCarroceria;
        this.possuiArCondicionado = possuiArCondicionado;
        this.temTetoSolar = temTetoSolar;
        this.quantidadePortas = quantidadePortas;
    }

    /**
     * Sobrescreve o método setAno da superclasse para aplicar uma validação histórica.
     * Garante que o ano do carro não seja anterior ao ano de invenção do primeiro automóvel (1886).
     */
    @Override
    public void setAno(int ano){
        if (ano >= 1886){
            // Se válido, envia o dado para ser tratado e salvo na superclasse
            super.setAno(ano);
        } else {
            System.out.println("Erro: O ano " + ano + " é inválido para um carro (mínimo 1886)!");
        }
    }

    // ==========================================
    //       GETTERS E SETTERS ESPECÍFICOS
    // ==========================================

    public String getTipoCarroceria(){
        return tipoCarroceria;
    }
    public void setTipoCarroceria(String tipoCarroceria){
        this.tipoCarroceria = tipoCarroceria;
    }
    public Boolean getPossuiArCondicionado(){
        return possuiArCondicionado;
    }
    public void setPossuiArCondicionado(boolean possuiArCondicionado){
        this.possuiArCondicionado = possuiArCondicionado;
    }
    public Boolean getTemTetoSolar(){
        return temTetoSolar;
    }
    public void setTemTetoSolar(boolean temTetoSolar){
        this.temTetoSolar = temTetoSolar;
    }
    public int getQuantidadePortas(){
        return quantidadePortas;
    }
    public void setQuantidadePortas(int quantidadePortas){
        this.quantidadePortas = quantidadePortas;
    }

    /**
     * Exibe as informações completas do Carro.
     * Reutiliza o método displayData() da superclasse para mostrar os dados básicos
     * e complementa com as propriedades específicas do carro.
     */
    public void displayData(){
        // Chama a impressão dos dados genéricos (Placa, Marca, Modelo, Ano, Cor)
        super.displayData();

        // Imprime os dados exclusivos da classe Car, tratando os booleans de forma amigável (? "Sim" : "Não")
        System.out.println("Tipo de Carroceria: " + getTipoCarroceria());
        System.out.println("Quantidade de portas: " + getQuantidadePortas());
        System.out.println("Tem teto solar: " + (getTemTetoSolar() ? "Sim" : "Não"));
        System.out.println("Quilometragem: " + getQuilometragem() + "Km");
        System.out.println("Capacidade do tanque: " + getCapacidadeTanque() + "L");
        System.out.println("Possui ar condicionado: " + (getPossuiArCondicionado() ? "Sim" : "Não"));
    }
}