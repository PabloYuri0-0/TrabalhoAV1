import java.time.LocalDate;

public class ProdutoAlimenticio extends Produto {
    private double preco;
    private LocalDate dataValidade;
    private String categoria; 

    public ProdutoAlimenticio(int id, String descricao, int quantidade, double preco, LocalDate dataValidade, String categoria) {
        super(id, descricao, quantidade);
        this.preco = preco;
        this.dataValidade = dataValidade;
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getDetalhes() {
        return String.format("ID: %d, Descrição: %s, Quantidade: %d, Preço: %.2f, Data de Validade: %s, Categoria: %s",
                getId(), getDescricao(), getQuantidade(), preco, dataValidade, categoria);
    }
}
