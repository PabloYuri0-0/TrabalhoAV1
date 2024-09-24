import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdutoFactory {

    // Contador estático para gerar IDs únicos
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    public static Produto criarProduto(int tipoProduto, String descricao, int quantidade, double preco, 
                                       LocalDate data, String categoria, String marca, String modelo, Integer garantiaMeses) {
        int id = idGenerator.incrementAndGet(); // Gera um ID único

        switch (tipoProduto) {
            case 1: // Produto Alimentício
                return new ProdutoAlimenticio(id, descricao, quantidade, preco, data, categoria);
            case 2: // Produto Eletrônico
                if (marca == null || modelo == null || garantiaMeses == null) {
                    throw new IllegalArgumentException("Marca, modelo e garantia são obrigatórios para produtos eletrônicos.");
                }
                return new ProdutoEletronico(id, descricao, quantidade, preco, marca, modelo, garantiaMeses);
            case 3: // Produto de Limpeza
                return new ProdutoLimpeza(id, descricao, quantidade, preco, data, categoria);
            default:
                throw new IllegalArgumentException("Tipo de produto inválido.");
        }
    }
}
