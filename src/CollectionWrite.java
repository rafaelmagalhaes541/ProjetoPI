import java.io.*;
import java.util.ArrayList;

/**
 * Classe utilitária para ler e gravar coleções de {@link Item} em ficheiros CSV.
 * Permite carregar uma coleção existente de um ficheiro e gravar a coleção atual em CSV.
 */
public class CollectionWrite {

    /**
     * Lê uma coleção de itens a partir de um ficheiro CSV.
     * Cada linha do ficheiro deve ter o formato: "titulo,quantidade,data".
     *
     * @param caminhoFicheiro O caminho do ficheiro CSV a ler.
     * @return Uma {@link ArrayList} de {@link Item} representando a coleção carregada.
     *         Retorna uma lista vazia se o ficheiro não existir ou não houver itens válidos.
     */
    public static ArrayList<Item> lerDeCSV(String caminhoFicheiro) {
        ArrayList<Item> itens = new ArrayList<>();

        File ficheiro = new File(caminhoFicheiro);
        if (!ficheiro.exists()) {
            System.out.println("Ficheiro não existe. Será criada uma coleção vazia.");
            return itens;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(ficheiro))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(",");
                if (partes.length == 3) {
                    String titulo = partes[0].trim();
                    int quantidade = Integer.parseInt(partes[1].trim());
                    String data = partes[2].trim();

                    Item item = new Item(titulo, quantidade, data);
                    if (item.dataValida()) {
                        itens.add(item);
                    } else {
                        System.out.println("Data inválida para o item: " + titulo);
                    }
                }
            }
            System.out.println("Coleção carregada do ficheiro: " + caminhoFicheiro);
        } catch (IOException e) {
            System.out.println("Erro ao ler o ficheiro: " + e.getMessage());
        }

        return itens;
    }

    /**
     * Grava uma coleção de itens num ficheiro CSV.
     * Cada item é gravado numa linha com o formato: "titulo,quantidade,data".
     * Se o ficheiro já existir, será sobrescrito.
     *
     * @param caminhoFicheiro O caminho do ficheiro CSV onde a coleção será gravada.
     * @param items A lista de {@link Item} a gravar no ficheiro.
     */
    public static void gravarParaCSV(String caminhoFicheiro, ArrayList<Item> items) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(caminhoFicheiro))) {
            for (Item item : items) {
                pw.println(item.getTitulo() + "," + item.getQuantidade() + "," + item.getDataDeCompra());
            }
            System.out.println("Coleção gravada no ficheiro: " + caminhoFicheiro);
        } catch (IOException e) {
            System.out.println("Erro ao gravar o ficheiro: " + e.getMessage());
        }
    }
}
