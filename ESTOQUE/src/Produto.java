public abstract class Produto {
    private int id; // Novo campo ID
    private String descricao;
    private int quantidade;

    public Produto(int id, String descricao, int quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // Método para obter detalhes do produto
    public abstract String getDetalhes();
}
