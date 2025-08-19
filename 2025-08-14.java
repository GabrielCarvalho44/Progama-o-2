import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

abstract class Contribuinte {
    private String nome;
    private Double rendaAnual;

    public Contribuinte(String nome, Double rendaAnual) {
        this.nome = nome;
        this.rendaAnual = rendaAnual;
    }

    public String getNome() {
        return nome;
    }

    public Double getRendaAnual() {
        return rendaAnual;
    }

    public abstract double imposto();
}

class PessoaFisica extends Contribuinte {
    private Double gastosSaude;

    public PessoaFisica(String nome, Double rendaAnual, Double gastosSaude) {
        super(nome, rendaAnual);
        this.gastosSaude = gastosSaude;
    }

    @Override
    public double imposto() {
        double impostoBase;
        if (getRendaAnual() < 20000.0) {
            impostoBase = getRendaAnual() * 0.15;
        } else {
            impostoBase = getRendaAnual() * 0.25;
        }
        impostoBase -= gastosSaude * 0.5;
        if (impostoBase < 0) impostoBase = 0.0;
        return impostoBase;
    }
}

class PessoaJuridica extends Contribuinte {
    private Integer numFuncionarios;

    public PessoaJuridica(String nome, Double rendaAnual, Integer numFuncionarios) {
        super(nome, rendaAnual);
        this.numFuncionarios = numFuncionarios;
    }

    @Override
    public double imposto() {
        if (numFuncionarios > 10) {
            return getRendaAnual() * 0.14;
        } else {
            return getRendaAnual() * 0.16;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Contribuinte> lista = new ArrayList<>();

        System.out.print("Digite o número de contribuintes: ");
        int N = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            System.out.println("Dados do contribuinte #" + i + ":");
            System.out.print("Pessoa física ou jurídica (f/j)? ");
            char tipo = sc.next().charAt(0);
            System.out.print("Nome: ");
            sc.nextLine();
            String nome = sc.nextLine();
            System.out.print("Renda anual: ");
            double rendaAnual = sc.nextDouble();

            if (tipo == 'f') {
                System.out.print("Gastos com saúde: ");
                double gastosSaude = sc.nextDouble();
                lista.add(new PessoaFisica(nome, rendaAnual, gastosSaude));
            } else {
                System.out.print("Número de funcionários: ");
                int numFuncionarios = sc.nextInt();
                lista.add(new PessoaJuridica(nome, rendaAnual, numFuncionarios));
            }
        }

        System.out.println();
        System.out.println("IMPOSTOS PAGOS:");
        double total = 0.0;
        for (Contribuinte c : lista) {
            double imposto = c.imposto();
            System.out.printf("%s: R$ %.2f%n", c.getNome(), imposto);
            total += imposto;
        }

        System.out.println();
        System.out.printf("TOTAL DE IMPOSTOS ARRECADADOS: R$ %.2f%n", total);

        sc.close();
    }
}
