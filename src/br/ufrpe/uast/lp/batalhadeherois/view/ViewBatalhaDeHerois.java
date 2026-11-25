package br.ufrpe.uast.lp.batalhadeherois.view;
import java.util.Scanner;

public class ViewBatalhaDeHerois {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println(" -=-=-= BOAS - VINDAS AO MENU INICIAL =-=-=- ");
        System.out.println("Escolha uma das opções abaixo: ");
        System.out.println(" [1] - Cadastrar personagem");
        System.out.println(" [2] - Listar personagens cadastrados");
        System.out.println(" [3] - Iniciar batalha");
        System.out.println(" [0] - Sair");
    }

    public void mostrarMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void mostrarErro(String erro) {
        System.out.println("Erro: " + erro);
    }

    public String fazerPergunta() {
        //  não farei try e catch pois vai que alguém queira colocar o nome de um personagem como número
        return sc.nextLine();
    }

    public int fazerPerguntaNumeros() {
        // vai ficar tentando fazer o usuário digitar um número válido, pensei que deveria estar no controller...
        // mas como quem cuida da essência (o scanner em si) é esse método, então fica aqui mesmo
        while (true) {
            try {
                int numero = sc.nextInt();
                sc.nextLine();
                return numero;
            } catch (java.util.InputMismatchException e) {
                mostrarErro("Informe um número válido.");
                sc.nextLine();
            }
        }
    }
}
