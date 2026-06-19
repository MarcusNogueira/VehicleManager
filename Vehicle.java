package VehicleManager;

/**
 * Representa a superclasse abstrata (conceitualmente) para todos os veículos do sistema.
 * Define os atributos e comportamentos comuns que serão herdados por Car, Motorcycle e Airplane.
 * @author Marcus Paulo Nogueira Souza/MarcusNogueira
 * @version 1.0
 */
public class Vehicle{

    // Atributos privados garantindo o princípio do encapsulamento
    private String placa;
    private String cor;
    private double quilometragem;
    private double capacidadeTanque;
    private String marca;
    private String modelo;
    private int ano;

    /**
     * Construtor completo para inicializar as propriedades comuns de qualquer veículo.
     */
    public Vehicle(String placa, String cor, double quilometragem, double capacidadeTanque, String marca, String modelo, int ano){
        this.placa = placa;
        this.cor = cor;
        this.quilometragem = quilometragem;
        this.capacidadeTanque = capacidadeTanque;
        this.marca = marca;
        this.modelo = modelo;
        // Utiliza o método setAno para aplicar a validação já na criação do objeto
        setAno(ano);
    }

    // ==========================================
    //           GETTERS E SETTERS
    // ==========================================

    public String getPlaca(){
        return placa;
    }
    public void setPlaca(String placa){
        this.placa = placa;
    }
    public String getCor(){
        return cor;
    }
    public void setCor(String cor){
        this.cor = cor;
    }
    public double getQuilometragem(){
        return quilometragem;
    }
    public void setQuilometragem(double quilometragem){
        this.quilometragem = quilometragem;
    }
    public double getCapacidadeTanque(){
        return capacidadeTanque;
    }
    public void setCapacidadeTanque(double capacidadeTanque){
        this.capacidadeTanque = capacidadeTanque;
    }
    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public String getModelo(){
        return modelo;
    }
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    public int getAno(){
        return ano;
    }

    /**
     * Define o ano do veículo aplicando uma regra de validação para impedir anos futuros.
     */
    public void setAno(int ano){
        if (ano <= 2026){
            this.ano = ano;
        }else{
            System.out.println("Ano totalmente inválido!");
        }
    }

    /**
     * Imprime no console as informações básicas e genéricas do veículo.
     * Este método pode ser sobrescrito pelas subclasses para exibir dados específicos.
     */
    public void displayData(){
        System.out.println("Placa/Prefixo ANAC: " + getPlaca());
        System.out.println("Marca: " + getMarca());
        System.out.println("Modelo: " + getModelo());
        System.out.println("Ano: " + getAno());
        System.out.println("Cor: " + getCor());
    }
}