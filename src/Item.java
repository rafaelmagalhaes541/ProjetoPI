public class Item {
    private String titulo;
    private int quantidade;
    private String dataDeCompra;

    public Item(String titulo, int quantidade, String dataDeCompra) {
        this.titulo = titulo;
        this.quantidade = quantidade;
        this.dataDeCompra = dataDeCompra;
    }

    void aumentarQuantidade(int quantidade) {
        this.quantidade += quantidade;
    }

    void reduzirQuantidade(int quantidade) {
        if ((this.quantidade - quantidade) >= 0) {
            this.quantidade += quantidade;
        }
    }

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

    public String getTitulo() {
        return titulo;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
