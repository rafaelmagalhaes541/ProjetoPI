import java.util.ArrayList;
import java.util.Scanner;
/**
 * Classe principal que gere a execução do programa de gestão de coleções.
 * Permite ao utilizador criar uma coleção, adicionar, remover e listar itens.
 */
public class CollectionManager {

    /**
     * Método principal que apresenta o menu e interage com o utilizador.
     *
     * @param args Argumentos da linha de comandos (não utilizados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nome da coleção: ");
        String nomeColecao = sc.nextLine();
        System.out.print("Quantidade limite da coleção: ");
        int limite = sc.nextInt();
        sc.nextLine(); // consumir quebra de linha

        Collection colecao = new Collection(nomeColecao, limite);

        int opcao;
        do {
            System.out.println("\n=== MENU COLEÇÃO: " + colecao.getNomeColecao() + " ===");
            System.out.println("1. Adicionar item");
            System.out.println("2. Apagar item");
            System.out.println("3. Listar itens por título");
            System.out.println("4. Mostrar total de itens");
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
                        System.out.println("📭 Nenhum item na coleção.");
                    } else {
                        System.out.println("📚 Itens ordenados por título:");
                        for (String t : lista) {
                            System.out.println(" - " + t);
                        }
                    }
                }

                case 4 -> {
                    System.out.println("Total de itens na coleção: " + colecao.calcularTotalItens());
                }

                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}

