import java.time.LocalDate;

public class ProdutoLimpeza extends Produto {
    private double preco;
    private LocalDate dataFabricacao;
    private String categoria; 

    public ProdutoLimpeza(int id, String descricao, int quantidade, double preco, LocalDate dataFabricacao, String categoria) {
        super(id, descricao, quantidade);
        this.preco = preco;
        this.dataFabricacao = dataFabricacao;
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getDetalhes() {
        return String.format("ID: %d, Descrição: %s, Quantidade: %d, Preço: %.2f, Data de Fabricação: %s, Categoria: %s",
                getId(), getDescricao(), getQuantidade(), preco, dataFabricacao, categoria);
    }
}
