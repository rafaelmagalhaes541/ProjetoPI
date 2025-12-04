import java.io.*;
import java.util.ArrayList;

public class CollectionWrite {
    // Ler coleção de CSV e retornar lista de Items
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

    // Gravar coleção para CSV
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
