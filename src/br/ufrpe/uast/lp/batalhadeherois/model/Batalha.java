package br.ufrpe.uast.lp.batalhadeherois.model;
import java.util.List;
import java.util.ArrayList;

public class Batalha {
    private Personagem heroiEscolhido;
    private Personagem vilaoEscolhido;
    private List<String> ordemDosAcontecimentos = new ArrayList<>();

    public Batalha(Personagem heroi, Personagem vilao) {
        this.heroiEscolhido = heroi;
        this.vilaoEscolhido = vilao;
    }

    /* a batalha é controlada por aqui, o controller apenas faz as classes conversarem entre si...
    ao início de cada round, os dois são marcados como vivos e têm suas vidas restauradas, recebo o valor, que
    vai ser true ou false*/
    public boolean iniciarRound(boolean HeroiComeca) {
        boolean heroiEstaVivo = true;
        boolean vilaoEstaVivo = true;
        heroiEscolhido.restaurarVida();
        vilaoEscolhido.restaurarVida();
        boolean vezDoHeroiAtacar = HeroiComeca;

        // enquanto os dois estiverem vivos
        while (heroiEstaVivo && vilaoEstaVivo) {
            // se for a vez do herói atacar... (true)
            if (vezDoHeroiAtacar) {
                // o realizarAtaque vai retornar se o alvo está vivo
                vilaoEstaVivo = realizarAtaque(heroiEscolhido, vilaoEscolhido);
                // se não é o herói, o vilão que ataca
            } else {
                // o herói está vivo?
                heroiEstaVivo = realizarAtaque(vilaoEscolhido, heroiEscolhido);
            }
            vezDoHeroiAtacar = !vezDoHeroiAtacar;
        }

        // vai retornar se o herói ficou vivo ou não após o round
        if (heroiEstaVivo) {
            return true;
        } else {
            return false;
        }
    }

    private int calcularDano(Personagem personagemQueAtaca, Personagem personagemQueRecebeODano) {
        /* não preciso criar uma variável dano como atributo, já que é um valor temporário
        que não terá tanta importancia para a classe batalha em si, mas sim para a classe personagem,
        logo, é melhor pôr o return para só dar o resultado diretamente */
        return Math.max(1, personagemQueAtaca.getAtaque() - personagemQueRecebeODano.getDefesa());
    }

    public boolean realizarAtaque(Personagem personagemAtacante, Personagem personagemRecebeDano) {
        /* armazeno o valor do dano na variável dano */
        int dano =  calcularDano(personagemAtacante, personagemRecebeDano);
        // coloco as mensagens na lista
        ordemDosAcontecimentos.add(personagemAtacante.getNome() + " atacou " + personagemRecebeDano.getNome() + ".");
        ordemDosAcontecimentos.add(personagemRecebeDano.getNome() + " tem " + personagemRecebeDano.getVidaAtual() + " pontos de vida.");
        
        // retorna false se a vida for = 0, caso contrário... true
        boolean alvoVivo = personagemRecebeDano.receberDano(dano);
        ordemDosAcontecimentos.add(personagemRecebeDano.getNome() + " recebeu " + dano + " pontos de dano.");
        
        return alvoVivo;
    }

    public List<String> getOrdemDosAcontecimentos() {
        // retorno uma cópia da ordem dos acontecimentos (mensagens armazenadas no método acima)
        return new ArrayList<>(ordemDosAcontecimentos);
    }
}