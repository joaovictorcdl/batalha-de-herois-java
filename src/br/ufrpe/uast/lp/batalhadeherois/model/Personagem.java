package br.ufrpe.uast.lp.batalhadeherois.model;

// abstract porque não quero que seja instanciado em nenhum lugar
public abstract class Personagem {
    private String nome;
    private String genero;
    private String categoria;
    private int vidaMaxima;
    private int vidaAtual;
    private int ataque;
    private int defesa;
    private int id;

    public Personagem(String nome, String genero, int vidaMaxima, int ataque, int defesa, String categoria) {
        this.nome = nome;
        this.genero = genero;
        this.vidaMaxima = vidaMaxima;
        this.vidaAtual = this.vidaMaxima;
        this.defesa = defesa;
        this.ataque = ataque;
        this.categoria = categoria;
    }

    public boolean receberDano(int valorDano) {
        // vai garantir que a vida atual seja pelo menos 0, nada menos que isso
        // o math.max retorna executará a subtração primeiro, e caso dê um número negativo
        // obviamente o 0 será maior, e ela vai atribuir esse valor 0 para a variável vidaAtual
        // ao invés de permitir valores menores que 0
        vidaAtual = Math.max(0, vidaAtual - valorDano);
        if (vidaAtual == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void restaurarVida() {
        vidaAtual = vidaMaxima;
    }

    public String getStatusDeVida(){
        if (vidaAtual == vidaMaxima) {
            return "MAX";
        } else if (vidaAtual > 0 && vidaAtual < vidaMaxima) {
            return "EM BATALHA";
        } else if (vidaAtual == 0) {
            return "DESTRUÍDO";
        }
        return null;
    }

    public String exibirMensagemDeVitoria() {
        return categoria + ": " + nome + " venceu a batalha! ";
    }

    // GETTERS E SETTERS
    public String getNome() {
        return this.nome;
    }
    public String getGenero() {
        return this.genero;
    }
    public String getCategoria() {
        return this.categoria;
    }
    public int getVidaAtual() {
        return this.vidaAtual;
    }
    public int getDefesa() {
        return this.defesa;
    }
    public int getVidaMaxima() {
        return this.vidaMaxima;
    }
    public int getAtaque() {
        return this.ataque;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

}