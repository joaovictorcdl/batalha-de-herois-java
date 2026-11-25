package br.ufrpe.uast.lp.batalhadeherois.model;

public class Heroi extends Personagem{

    public Heroi(String nome, String genero, int vidaMaxima, int ataque, int defesa) {
        super(nome, genero, vidaMaxima, ataque, defesa, "Herói/Heroína");
    }

    @Override
    public String exibirMensagemDeVitoria() {
        return "Herói/Heroína: " + getNome() + " venceu a batalha!";
    }
}
