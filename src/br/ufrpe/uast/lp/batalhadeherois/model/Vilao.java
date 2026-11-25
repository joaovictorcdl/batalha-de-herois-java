package br.ufrpe.uast.lp.batalhadeherois.model;

public class Vilao extends Personagem{
    
    public Vilao(String nome, String genero, int vidaMaxima, int ataque, int defesa) {
        super(nome, genero, vidaMaxima, ataque, defesa, "Vilão/Vilã");
    }

    @Override
    public String exibirMensagemDeVitoria() {
        return "Vilão/Vilã: " + getNome() + " venceu a batalha!";
    }
}
