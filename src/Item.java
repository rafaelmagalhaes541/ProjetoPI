/**
 * Representa um item dentro de uma coleção, contendo título, quantidade e data de compra.
 */
public class Item {
    private String titulo;
    private int quantidade;
    private String dataDeCompra;

    /**
     * Construtor da classe Item.
     *
     * @param titulo        Título do item.
     * @param quantidade    Quantidade do item.
     * @param dataDeCompra  Data de compra no formato dd/mm/aaaa.
     */

    public Item(String titulo, int quantidade, String dataDeCompra) {
        this.titulo = titulo;
        this.quantidade = quantidade;
        this.dataDeCompra = dataDeCompra;
    }

    /**
     * Aumenta a quantidade do item.
     *
     * @param quantidade Quantidade a adicionar.
     */
    void aumentarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    /**
     * Reduz a quantidade do item, se possível.
     *
     * @param quantidade Quantidade a remover.
     */
    void reduzirQuantidade(int quantidade) {
        if ((this.quantidade - quantidade) >= 0) {
            this.quantidade -= quantidade; // corrigido
        }
    }

    /**
     * Verifica se a data de compra é válida.
     *
     * @return true se a data for válida, false caso contrário.
     */
    boolean dataValida() {
        String[] partes = dataDeCompra.split("/");

        if (partes.length != 3) {
            return false;
        }

        int dia, mes, ano;

        try {
            dia = Integer.parseInt(partes[0]);
            mes = Integer.parseInt(partes[1]);
            ano = Integer.parseInt(partes[2]);
        } catch (NumberFormatException e) {
            return false;
        }

        if (mes < 1 || mes > 12) {
            return false;
        }
        if (dia < 1) {
            return false;
        }

        int[] diasMes = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

        if (mes == 2 && ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0))) {
            return dia <= 29;
        } else {
            return dia <= diasMes[mes - 1];
        }
    }

    /**
     * Obtém o título do item.
     *
     * @return Título do item.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtém a quantidade do item.
     *
     * @return Quantidade do item.
     */
    public int getQuantidade() {
        return quantidade;
    }
}
