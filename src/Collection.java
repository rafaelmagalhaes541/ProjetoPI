import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa uma coleção de itens, com um limite máximo de quantidade total.
 */
public class Collection {

    private String nomeColecao;
    private ArrayList<Item> items;
    private int quantidadeLimite;

    /**
     * Construtor da classe Collection.
     *
     * @param nomeColecao      Nome da coleção.
     * @param quantidadeLimite Quantidade total máxima de itens permitida na coleção.
     */
    public Collection(String nomeColecao, int quantidadeLimite) {
        this.quantidadeLimite = quantidadeLimite;
        this.nomeColecao = nomeColecao;
        this.items = new ArrayList<>();
    }

    /**
     * Calcula o total de itens existentes na coleção.
     *
     * @return Soma das quantidades de todos os itens.
     */
    int calcularTotalItens() {
        int total = 0;
        for (Item i : items) {
            total += i.getQuantidade();
        }
        return total;
    }

    /**
     * Apaga (ou reduz a quantidade de) um item existente na coleção.
     *
     * @param item Item a ser removido ou reduzido.
     * @return true se o item foi removido ou atualizado com sucesso, false caso contrário.
     */

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

    /**
     * Adiciona um item à coleção, caso haja espaço disponível.
     * Se o item já existir, a sua quantidade é aumentada.
     *
     * @param item Item a adicionar.
     * @return true se o item foi adicionado com sucesso, false se a coleção estiver cheia.
     */
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

    /**
     * Lista os títulos de todos os itens na coleção, ordenados alfabeticamente.
     *
     * @return Lista de títulos dos itens ordenada.
     */
    ArrayList<String> listarPorTitulo() {
        ArrayList<String> titulos = new ArrayList<>();
        for (Item item : items) {
            titulos.add(item.getTitulo());
        }
        Collections.sort(titulos);
        return titulos;
    }

    /**
     * Obtém o nome da coleção.
     *
     * @return Nome da coleção.
     */
    public String getNomeColecao() {
        return nomeColecao;
    }

    /**
     * Obtém a lista de itens da coleção.
     *
     * @return Lista de itens.
     */
    public ArrayList<Item> getItems() {
        return items;

    }
    /**
     * Obtém o limite máximo de quantidade de itens da coleção.
     *
     * @return Limite máximo de itens.
     */
    public int getQuantidadeLimite() {
        return quantidadeLimite;
    }
}
