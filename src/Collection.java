import java.util.ArrayList;
import java.util.Collections;

public class Collection {

    private String nomeColecao;
    private ArrayList<Item> items;
    private int quantidadeLimite;


    public Collection(String nomeColecao, int quantidadeLimite) {
        this.quantidadeLimite = quantidadeLimite;
        this.nomeColecao = nomeColecao;
        this.items = new ArrayList<>();
    }

    int calcularTotalItens() {
        int total = 0;
        for (Item i : items) {
            total += i.getQuantidade();
        }
        return total;
    }

    boolean apagarItem(Item item) {
        for (Item item1 : items) {
            if (item1.getTitulo().equalsIgnoreCase(item.getTitulo())) {
                if (item.getQuantidade() > item1.getQuantidade()) {
                    return false;
                }
                if (item.getQuantidade() == item1.getQuantidade()) {
                    items.remove(item);
                } else {
                    item1.reduzirQuantidade(item.getQuantidade());
                }
                return true;
            }
        }
        return false;
    }

    boolean adiconarItem(Item item) {
        int totalAtual = calcularTotalItens();

        // verifica se há espaço suficiente
        if (totalAtual + item.getQuantidade() > quantidadeLimite) {
            return false;
        }

        for (Item existente : items) {
            if (existente.getTitulo().equalsIgnoreCase(item.getTitulo())) {
                existente.aumentarQuantidade(item.getQuantidade());
                return true;
            }
        }

        items.add(item);
        return true;
    }

    ArrayList<String> listarPorTitulo() {
        ArrayList<String> titulos = new ArrayList<>();
        for (Item item : items) {
            titulos.add(item.getTitulo());
        }
        Collections.sort(titulos);
        return titulos;
    }

    public String getNomeColecao() {
        return nomeColecao;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getQuantidadeLimite() {
        return quantidadeLimite;
    }
}
