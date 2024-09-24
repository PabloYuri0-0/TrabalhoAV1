import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class Estoque {

    private static final String FILE_NAME = "estoque.txt";

    // Adiciona um produto ao arquivo
    public void adicionarProduto(Produto produto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(formatarProdutoParaLinha(produto));
            writer.newLine();
            System.out.println("Produto adicionado ao estoque.");
        } catch (IOException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    // Lista todos os produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Produto produto = formatarLinhaParaProduto(linha);
                produtos.add(produto);
            }
        } catch (IOException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
        return produtos;
    }

    // Busca produto por ID
    public Produto buscarProduto(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Produto produto = formatarLinhaParaProduto(linha);
                if (produto.getId() == id) {
                    return produto;
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }

    // Remove um produto pelo ID
    public void removerProduto(int id) {
        List<Produto> produtos = listarProdutos();
        boolean removido = produtos.removeIf(p -> p.getId() == id);

        if (removido) {
            salvarProdutos(produtos);
            System.out.println("Produto removido.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Atualiza um produto pelo ID
    public void atualizarProduto(int id, Produto novoProduto) {
        List<Produto> produtos = listarProdutos();
        boolean atualizado = false;
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getId() == id) {
                produtos.set(i, novoProduto);
                atualizado = true;
                break;
            }
        }

        if (atualizado) {
            salvarProdutos(produtos);
            System.out.println("Produto atualizado.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    // Salva todos os produtos no arquivo
    private void salvarProdutos(List<Produto> produtos) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Produto produto : produtos) {
                writer.write(formatarProdutoParaLinha(produto));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    // Converte um produto em uma linha para ser escrita no arquivo
    private String formatarProdutoParaLinha(Produto produto) {
        if (produto instanceof ProdutoAlimenticio) {
            ProdutoAlimenticio pa = (ProdutoAlimenticio) produto;
            return String.join(";",
                    String.valueOf(produto.getId()),
                    produto.getDescricao(),
                    String.valueOf(produto.getQuantidade()),
                    String.valueOf(pa.getPreco()),
                    pa.getDataValidade().toString(),
                    pa.getCategoria(),
                    "Alimenticio");
        } else if (produto instanceof ProdutoEletronico) {
            ProdutoEletronico pe = (ProdutoEletronico) produto;
            return String.join(";",
                    String.valueOf(produto.getId()),
                    produto.getDescricao(),
                    String.valueOf(produto.getQuantidade()),
                    String.valueOf(pe.getPreco()),
                    pe.getMarca(),
                    pe.getModelo(),
                    String.valueOf(pe.getGarantiaMeses()),
                    "Eletronico");
        } else if (produto instanceof ProdutoLimpeza) {
            ProdutoLimpeza pl = (ProdutoLimpeza) produto;
            return String.join(";",
                    String.valueOf(produto.getId()),
                    produto.getDescricao(),
                    String.valueOf(produto.getQuantidade()),
                    String.valueOf(pl.getPreco()),
                    pl.getDataFabricacao().toString(),
                    pl.getCategoria(),
                    "Limpeza");
        }
        return "";
    }

    // Converte uma linha do arquivo em um objeto Produto
    private Produto formatarLinhaParaProduto(String linha) {
        String[] campos = linha.split(";");
        int id = Integer.parseInt(campos[0]);
        String tipo = campos[campos.length - 1];

        switch (tipo) {
            case "Alimenticio":
                return new ProdutoAlimenticio(id, campos[1], Integer.parseInt(campos[2]),
                        Double.parseDouble(campos[3]), LocalDate.parse(campos[4]), campos[5]);
            case "Eletronico":
                return new ProdutoEletronico(id, campos[1], Integer.parseInt(campos[2]),
                        Double.parseDouble(campos[3]), campos[4], campos[5], Integer.parseInt(campos[6]));
            case "Limpeza":
                return new ProdutoLimpeza(id, campos[1], Integer.parseInt(campos[2]),
                        Double.parseDouble(campos[3]), LocalDate.parse(campos[4]), campos[5]);
            default:
                return null;
        }
    }
}
