import java.util.ArrayList;
import java.util.Scanner;

public class CollectionManager {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Collection colecao;
        ArrayList<Item> itensExistentes = new ArrayList<>();
        String caminhoCSV = "";

        System.out.print("Já tens uma caderneta existente? (s/n): ");
        String resposta = sc.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            // Pedir nome do ficheiro
            System.out.print("Indica o nome do ficheiro CSV (ex: caderneta.csv): ");
            caminhoCSV = sc.nextLine().trim();

            // Tenta ler os itens existentes
            itensExistentes = CollectionWrite.lerDeCSV(caminhoCSV);

            // Perguntar o nome da coleção e limite
            System.out.print("Nome da coleção: ");
            String nomeColecao = sc.nextLine();

            System.out.print("Quantidade limite da coleção: ");
            int limite = sc.nextInt();
            sc.nextLine();

            colecao = new Collection(nomeColecao, limite);

            // Adiciona os itens carregados
            for (Item i : itensExistentes) {
                colecao.adiconarItem(i);
            }

        } else {
            // Criar coleção nova
            System.out.print("Nome da coleção: ");
            String nomeColecao = sc.nextLine();

            System.out.print("Quantidade limite da coleção: ");
            int limite = sc.nextInt();
            sc.nextLine();

            colecao = new Collection(nomeColecao, limite);
        }

        // Menu principal
        int opcao;
        do {
            System.out.println("\n=== MENU COLEÇÃO: " + colecao.getNomeColecao() + " ===");
            System.out.println("1. Adicionar item");
            System.out.println("2. Apagar item");
            System.out.println("3. Listar itens por título");
            System.out.println("4. Mostrar total de itens");
            System.out.println("5. Gravar coleção no ficheiro CSV");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Título do item: ");
                    String titulo = sc.nextLine();

                    System.out.print("Quantidade: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Data de compra (dd/mm/aaaa): ");
                    String data = sc.nextLine();

                    Item novoItem = new Item(titulo, quantidade, data);

                    if (!novoItem.dataValida()) {
                        System.out.println("Data inválida. Item não adicionado.");
                    } else if (colecao.adiconarItem(novoItem)) {
                        System.out.println("Item adicionado com sucesso!");
                    } else {
                        System.out.println("Coleção cheia ou limite excedido!");
                    }
                }

                case 2 -> {
                    System.out.print("Título do item a apagar: ");
                    String titulo = sc.nextLine();

                    System.out.print("Quantidade a remover: ");
                    int quantidade = sc.nextInt();
                    sc.nextLine();

                    Item itemRemover = new Item(titulo, quantidade, "01/01/2000"); // data não relevante

                    if (colecao.apagarItem(itemRemover)) {
                        System.out.println("Item removido com sucesso!");
                    } else {
                        System.out.println("Item não encontrado ou quantidade inválida.");
                    }
                }

                case 3 -> {
                    ArrayList<String> lista = colecao.listarPorTitulo();
                    if (lista.isEmpty()) {
                        System.out.println("Nenhum item na coleção.");
                    } else {
                        System.out.println("Itens ordenados por título:");
                        for (String t : lista) {
                            System.out.println(" - " + t);
                        }
                    }
                }

                case 4 -> {
                    int totalItens = colecao.calcularTotalItens();
                    System.out.println("Total de itens na coleção: " + totalItens);

                    ArrayList<Item> items = colecao.getItems();
                    if (items.isEmpty()) {
                        System.out.println("Nenhum item na coleção.");
                    } else {
                        System.out.println("Detalhes dos itens:");
                        for (Item i : items) {
                            System.out.println(" - " + i.getTitulo() + ": " + i.getQuantidade() + " unidades");
                        }
                    }
                }

                case 5 -> {
                    if (caminhoCSV.isEmpty()) {
                        System.out.print("Indica o nome do ficheiro CSV para gravar: ");
                        caminhoCSV = sc.nextLine();
                    }
                    CollectionWrite.gravarParaCSV(caminhoCSV, colecao.getItems());
                }

                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        // Grava automaticamente ao sair, se tiver nome de ficheiro definido
        if (!caminhoCSV.isEmpty()) {
            CollectionWrite.gravarParaCSV(caminhoCSV, colecao.getItems());
        }

        sc.close();
    }
}
