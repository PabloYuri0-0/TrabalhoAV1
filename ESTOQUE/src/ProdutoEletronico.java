public class ProdutoEletronico extends Produto {
    private double preco;
    private String marca;
    private String modelo;
    private int garantiaMeses; 

    public ProdutoEletronico(int id, String descricao, int quantidade, double preco, String marca, String modelo, int garantiaMeses) {
        super(id, descricao, quantidade);
        this.preco = preco;
        this.marca = marca;
        this.modelo = modelo;
        this.garantiaMeses = garantiaMeses;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getDetalhes() {
        return String.format("ID: %d, Descrição: %s, Quantidade: %d, Preço: %.2f, Marca: %s, Modelo: %s, Garantia: %d meses",
                getId(), getDescricao(), getQuantidade(), preco, marca, modelo, garantiaMeses);
    }
}
