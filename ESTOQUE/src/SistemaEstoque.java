import java.time.LocalDate;
import java.util.Scanner;

public class SistemaEstoque {

    private static Estoque estoque = new Estoque();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer de nova linha

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarProdutos();
                    break;
                case 3:
                    buscarProduto();
                    break;
                case 4:
                    atualizarProduto();
                    break;
                case 5:
                    removerProduto();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n--- Sistema de Estoque ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Buscar Produto");
        System.out.println("4. Atualizar Produto");
        System.out.println("5. Remover Produto");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarProduto() {
        System.out.println("Escolha o tipo de produto:");
        System.out.println("1. Alimentício");
        System.out.println("2. Eletrônico");
        System.out.println("3. Limpeza");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); 

        Produto produto = null;
        switch (tipo) {
            case 1: // Produto Alimentício
                System.out.print("Data de validade (aaaa-mm-dd): ");
                String dataValidadeStr = scanner.nextLine();
                LocalDate dataValidade = LocalDate.parse(dataValidadeStr);
                System.out.print("Categoria (Ex: Fruta, Grão, Laticínio): ");
                String categoria = scanner.nextLine();
                produto = ProdutoFactory.criarProduto(tipo, descricao, quantidade, preco, dataValidade, categoria, null, null, null);
                break;
            case 2: // Produto Eletrônico
                System.out.print("Marca: ");
                String marca = scanner.nextLine();
                System.out.print("Modelo: ");
                String modelo = scanner.nextLine();
                System.out.print("Garantia em meses: ");
                int garantia = scanner.nextInt();
                scanner.nextLine(); 
                produto = ProdutoFactory.criarProduto(tipo, descricao, quantidade, preco, null, null, marca, modelo, garantia);
                break;
            case 3: // Produto de Limpeza
                System.out.print("Data de validade (aaaa-mm-dd): ");
                String dataLimpezaStr = scanner.nextLine();
                LocalDate dataLimpeza = LocalDate.parse(dataLimpezaStr);
                System.out.print("Categoria: ");
                String categoriaLimpeza = scanner.nextLine();
                produto = ProdutoFactory.criarProduto(tipo, descricao, quantidade, preco, dataLimpeza, categoriaLimpeza, null, null, null);
                break;
            default:
                System.out.println("Tipo de produto inválido.");
                return;
        }

        estoque.adicionarProduto(produto);
    }

    private static void listarProdutos() {
        System.out.println("--- Produtos em Estoque ---");
        for (Produto produto : estoque.listarProdutos()) {
            System.out.println(produto.getDetalhes());
        }
    }

    private static void buscarProduto() {
        System.out.print("Informe o ID do produto que deseja buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Produto produto = estoque.buscarProduto(id);
        if (produto != null) {
            System.out.println(produto.getDetalhes());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private static void atualizarProduto() {
        System.out.print("Informe o ID do produto que deseja atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        Produto produtoExistente = estoque.buscarProduto(id);
        if (produtoExistente == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.println("Atualizando produto...");
        System.out.println("Escolha o tipo de produto:");
        System.out.println("1. Alimentício");
        System.out.println("2. Eletrônico");
        System.out.println("3. Limpeza");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print("Nova descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Nova quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Novo preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine(); 

        Produto produtoAtualizado = null;
        switch (tipo) {
            case 1: // Alimentício
                System.out.print("Nova data de validade (aaaa-mm-dd): ");
                String dataValidadeStr = scanner.nextLine();
                LocalDate dataValidade = LocalDate.parse(dataValidadeStr);
                System.out.print("Nova categoria: ");
                String categoria = scanner.nextLine();
                produtoAtualizado = new ProdutoAlimenticio(id, descricao, quantidade, preco, dataValidade, categoria);
                break;
            case 2: // Eletrônico
                System.out.print("Nova marca: ");
                String marca = scanner.nextLine();
                System.out.print("Novo modelo: ");
                String modelo = scanner.nextLine();
                System.out.print("Nova garantia em meses: ");
                int garantia = scanner.nextInt();
                scanner.nextLine(); 
                produtoAtualizado = new ProdutoEletronico(id, descricao, quantidade, preco, marca, modelo, garantia);
                break;
            case 3: // Limpeza
                System.out.print("Nova data de validade (aaaa-mm-dd): ");
                String dataLimpezaStr = scanner.nextLine();
                LocalDate dataLimpeza = LocalDate.parse(dataLimpezaStr);
                System.out.print("Nova categoria: ");
                String categoriaLimpeza = scanner.nextLine();
                produtoAtualizado = new ProdutoLimpeza(id, descricao, quantidade, preco, dataLimpeza, categoriaLimpeza);
                break;
            default:
                System.out.println("Tipo de produto inválido.");
                return;
        }

        if (produtoAtualizado != null) {
            estoque.atualizarProduto(id, produtoAtualizado);
        }
    }

    private static void removerProduto() {
        System.out.print("Informe o ID do produto que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        estoque.removerProduto(id);
    }
}
