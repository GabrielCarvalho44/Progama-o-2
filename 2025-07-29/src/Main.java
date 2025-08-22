import java.util.Scanner;

class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public double valorTotalEmEstoque() {
        return preco * quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public void removerEstoque(int quantidade) {
        this.quantidade -= quantidade;
    }

    public String toString() {
        return nome + ", R$ " + String.format("%.2f", preco) + ", " + quantidade + " unidades, Total: R$ " + String.format("%.2f", valorTotalEmEstoque());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Entrada de dados do produto
        System.out.println("Entre os dados do produto:");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Preço: ");
        double preco = sc.nextDouble();
        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt();

        Produto produto = new Produto(nome, preco, quantidade);

        // Mostrar dados do produto
        System.out.println("\nDados do produto: " + produto);

        // Entrada no estoque
        System.out.print("\nDigite o número de produtos a ser adicionado ao estoque: ");
        int quantidadeAdicionada = sc.nextInt();
        produto.adicionarEstoque(quantidadeAdicionada);
        System.out.println("Dados atualizados: " + produto);

        // Saída no estoque
        System.out.print("\nDigite o número de produtos a ser removido do estoque: ");
        int quantidadeRemovida = sc.nextInt();
        produto.removerEstoque(quantidadeRemovida);
        System.out.println("Dados atualizados: " + produto);

        sc.close();
    }
}
