package br.ufrpe.uast.lp.batalhadeherois.controller;
import br.ufrpe.uast.lp.batalhadeherois.model.Batalha;
import br.ufrpe.uast.lp.batalhadeherois.model.CadastroPersonagens;
import br.ufrpe.uast.lp.batalhadeherois.model.Heroi;
import br.ufrpe.uast.lp.batalhadeherois.model.Personagem;
import br.ufrpe.uast.lp.batalhadeherois.model.Vilao;
import br.ufrpe.uast.lp.batalhadeherois.view.ViewBatalhaDeHerois;
import java.util.Random;
import java.util.List;

public class ControllerBatalhaDeHerois {
    private ViewBatalhaDeHerois view = new ViewBatalhaDeHerois();
    private CadastroPersonagens cadastroPersonagens = new CadastroPersonagens();
    private Random sorteio = new Random();
    private Personagem heroi;
    private Personagem vilao;
    
    public void menuInicial() {
        int opcaoMenu;
        do {
            view.menu();
            opcaoMenu = view.fazerPerguntaNumeros();
            // O SWITCH TEM EFEITO CASCATA - LEMBRAR DE COLOCAR BREAK NAS OPÇÕES!!!!
            switch (opcaoMenu) {
                case 0:
                    view.mostrarMensagem("Encerrando...");
                    break;
                case 1:
                    cadastrarPersonagem();
                    break;
                case 2:
                    listarPersonagens();
                    break;
                case 3:
                    iniciarBatalha();
                    break;
                default:
                    view.mostrarErro("Opção inexistente.");
                    break;
            }
        } while (opcaoMenu != 0);
    }

    private void controlarBatalha(Batalha batalha) {
        int numeroDeRodadas = 0;
        int numeroDeVitoriasConsecutivasHeroi = 0;
        int numeroDeVitoriasConsecutivasVilao = 0;

        // vai retornar true ou false
        boolean heroiComeca = sorteio.nextBoolean();
        while (numeroDeRodadas < 3 && numeroDeVitoriasConsecutivasHeroi < 2 && numeroDeVitoriasConsecutivasVilao < 2) {
            view.mostrarMensagem(" ============ " + (numeroDeRodadas + 1) + "º ROUND ============" );

            int quantidadeAnterior = batalha.getOrdemDosAcontecimentos().size();
            // mando o valor boolean, se for true, o herói começa, se for false, o vião começa
            boolean vencedor = batalha.iniciarRound(heroiComeca);

            List<String> acontecimentos = batalha.getOrdemDosAcontecimentos();
            for (int i = quantidadeAnterior; i < acontecimentos.size(); i++) {
                view.mostrarMensagem(acontecimentos.get(i));

                if ((i - quantidadeAnterior + 1) % 3 == 0) {
                    view.mostrarMensagem(" -------------------------------- ");
                }
            }

            // significa que o heroi venceu
            if (vencedor) {
                numeroDeVitoriasConsecutivasHeroi++;
                numeroDeVitoriasConsecutivasVilao = 0;
                view.mostrarMensagem(" ");
                view.mostrarMensagem("+=+=+=+=+=++=+=+=+=+=++=+=+=+=+=+=+=+=+=+=+");
                view.mostrarMensagem(" ");
                view.mostrarMensagem("|    "  + heroi.getNome() + " venceu o round atual!" + "    |");
                view.mostrarMensagem(" ");
                view.mostrarMensagem("+=+=+=+=+=++=+=+=+=+=++=+=+=+=+=+=+=+=+=+=+");
                view.mostrarMensagem(" ");
            } else {
                numeroDeVitoriasConsecutivasVilao++;
                numeroDeVitoriasConsecutivasHeroi = 0;
                view.mostrarMensagem(" ");
                view.mostrarMensagem("+=+=+=+=+=++=+=+=+=+=++=+=+=+=+=+=+=+=+=+=+");
                view.mostrarMensagem(" ");
                view.mostrarMensagem("|    "  + vilao.getNome() + " venceu o round atual!" + "    |");
                view.mostrarMensagem(" ");
                view.mostrarMensagem("+=+=+=+=+=++=+=+=+=+=+=+=+=+=+=++=+=+=+=+=+");
                view.mostrarMensagem(" ");
            } 

            heroiComeca = vencedor;
            numeroDeRodadas++;

            //confirmação se quer ir para o segundo/outro round
            if (numeroDeRodadas < 3 && numeroDeVitoriasConsecutivasHeroi < 2 && numeroDeVitoriasConsecutivasVilao < 2) {
                view.mostrarMensagem("Aperte enter para irmos para o próximo round!");
                view.fazerPergunta();
            }
        }
        
        view.mostrarMensagem("==========================================");

        if (numeroDeVitoriasConsecutivasHeroi == 2) {
            view.mostrarMensagem(heroi.exibirMensagemDeVitoria());
            view.mostrarMensagem("==========================================");
        } else if (numeroDeVitoriasConsecutivasVilao == 2) {
            view.mostrarMensagem(vilao.exibirMensagemDeVitoria());
            view.mostrarMensagem("==========================================");
        } else {
            view.mostrarMensagem("Não houve um vencedor definido!\nNinguém obteve duas vitórias consecutivas.");
            view.mostrarMensagem("==========================================");
        }
    }

    public void iniciarBatalha() {
        // pega o acesso às listas via método, mas não exibe ao usuário
        List<Personagem> herois = cadastroPersonagens.listarPorCategoria("Herói/Heroína");
        List<Personagem> viloes = cadastroPersonagens.listarPorCategoria("Vilão/Vilã");
        // se uma delas for vazia...
        if (herois.isEmpty() || viloes.isEmpty()) {
            view.mostrarErro("Uma das classes não possui personagens.");
            view.mostrarErro("Para iniciarmos, é necessário que exista ao menos um personagem na classe dos vilões e um personagem na classe dos heróis.");
            return;
        }

        /* vai percorrer a lista de herois da categoria Herói/Heroína, mostrar nome e ID */
        view.mostrarMensagem(" Heróis e Heroínas ");
        for (Personagem heroisCadastrados : herois) {
            view.mostrarMensagem("ID: " + heroisCadastrados.getId() + " | " + heroisCadastrados.getNome());
            view.mostrarMensagem(" ----------------------------- ");
        }

        // aqui o usuário pode escolher o seu personagem através do ID
        view.mostrarMensagem("Escolha o seu herói/heroína:\n[ DIGITE O ID CORRESPONDENTE ]");
        int idHeroi = view.fazerPerguntaNumeros();
        // instancio uma variável do tipo personagem a busca em si vai acontecer aqui, ela vai receber o id digitado como parâmetro
        // e puxar o personagem com o id correspondente para preencher o valor dessa variável que a princípio estava vazia
        Personagem heroiSelecionado = cadastroPersonagens.buscarPorId(idHeroi);

        // se o heroi for null, então é porque a pessoa não digitou um ID válido, já que ela obrigatóriamente tem que escolher, ou então ela digitou
        // o ID de alguém que não é herói, os heróis são mostrados primeiro no cenário de escolha, então o usuário não poderia ver o ID dos vilões, em teoria
        // só que existe o método de listar personagens, e por lá o usuário pode ter acesso aos IDs, então isso vai prevenir 
        if (heroiSelecionado == null || !heroiSelecionado.getCategoria().equalsIgnoreCase("Herói/Heroína")) {
            view.mostrarErro("O herói/heroína correspondente a esse ID não existe.\nOu ele não é um herói.");
            return;
        }

        this.heroi = heroiSelecionado;

        // ------  A MESMA COISA DO CÓDIGO ACIMA, PORÉM, AGORA PARA OS VILÕES  ------------
        view.mostrarMensagem(" Vilões e vilãs ");
        for (Personagem viloesCadastrados : viloes) {
            view.mostrarMensagem("ID: " + viloesCadastrados.getId() + " | " + viloesCadastrados.getNome());
            view.mostrarMensagem(" ----------------------------- ");
        }

        view.mostrarMensagem("Esolha o seu vilão/vilã:\n[ DIGITE O ID CORRESPONDENTE ]");
        int idVilao = view.fazerPerguntaNumeros();
        Personagem vilaoSelecionado = cadastroPersonagens.buscarPorId(idVilao);

        if (vilaoSelecionado == null || !vilaoSelecionado.getCategoria().equalsIgnoreCase("Vilão/Vilã")) {
            view.mostrarErro("O vilão/vilã correspondente a esse ID não existe.\nOu ele não é um vilão.");
            return;
        }

        this.vilao = vilaoSelecionado;
        view.mostrarMensagem(" ----------------------------- ");
        view.mostrarMensagem("Batalha: "  + heroi.getNome() + " contra " + vilao.getNome());
        view.mostrarMensagem(" ----------------------------- ");
        Batalha batalha = new Batalha(heroiSelecionado, vilaoSelecionado);
        controlarBatalha(batalha);
    }

    public void listarPersonagens() {
        // colocando a lista de todos os personagens que foi retornada no método cadastro de personagens em uma 
        // variável do tipo lista (local), ao fazer a atribuição abaixo, o método já é chamado durante
        // a própria atribuição
        List<Personagem> listaDosPersonagens = cadastroPersonagens.listarTodosOsPersonagens();
        if (listaDosPersonagens.isEmpty()) {
            view.mostrarErro("Não há personagens cadastrados.");
            return;
        }
        for (Personagem personagemNaLista : listaDosPersonagens) {
            view.mostrarMensagem("ID: " + personagemNaLista.getId());
            view.mostrarMensagem("Nome: " + personagemNaLista.getNome());
            view.mostrarMensagem("Gênero: " + personagemNaLista.getGenero());
            view.mostrarMensagem("Classe: " + personagemNaLista.getCategoria());
            view.mostrarMensagem("Vida máxima: " + personagemNaLista.getVidaMaxima());
            view.mostrarMensagem("Poder de ataque: " + personagemNaLista.getAtaque());
            view.mostrarMensagem("Defesa: " + personagemNaLista.getDefesa());
            view.mostrarMensagem("Vida atual: " + personagemNaLista.getVidaAtual());
            view.mostrarMensagem("Status atual: " + personagemNaLista.getStatusDeVida());
             view.mostrarMensagem(" -------------------------------------------- ");
        }
    }

    public void cadastrarPersonagem() {
        view.mostrarMensagem("Informe o nome do(a) personagem: ");
        String nomeDoPersonagem = view.fazerPergunta();
        // verificação para o nome não ser vazio
        if (nomeDoPersonagem.isBlank()) {
            view.mostrarErro("O nome não pode ser vazio. Tente novamente.");
            return;
        }

        view.mostrarMensagem("Informe o gênero do(a) personagem:");
        view.mostrarMensagem("[1] - Masculino\n[2] - Feminino\n[3] - Outro/Autodescrição\n[4] - Não informar");
        int generoDoPersonagemEscolha = view.fazerPerguntaNumeros();
        // vai ser passado como parâmtro, inicia como  null e dependendo do que for digitado, vai receber um valor específico nos
        // ifs abaixo
        String generoDoPersonagem = null;

        // verificações de gêneros existentes
        if (generoDoPersonagemEscolha == 1) {
            generoDoPersonagem = "Masculino";
        } else if (generoDoPersonagemEscolha == 2) {
            generoDoPersonagem = "Feminino";
        } else if (generoDoPersonagemEscolha == 3) {
            view.mostrarMensagem("Informe qual o outro/autodescrição: ");
            String autodescricaoOuOutro = view.fazerPergunta();
            if (autodescricaoOuOutro.isBlank()) {
                view.mostrarErro("O campo não pode estar vazio.");
                return;
            } else {
                generoDoPersonagem = autodescricaoOuOutro;
            }
        } else if (generoDoPersonagemEscolha == 4) {
            generoDoPersonagem = "N.I - Não Informar";
        } else {
            view.mostrarErro("Opção inválida.");
            return;
        }

        view.mostrarMensagem("Informe a vida máxima do(a) personagem: ");
        int vidaMaximaDoPersonagem = view.fazerPerguntaNumeros();

        if (vidaMaximaDoPersonagem <= 0) {
            view.mostrarErro("A vida deve ser maior que zero. Tente novamente.");
            return;
        }

        view.mostrarMensagem("Informe a defesa do(a) personagem: ");
        int defesaDoPersonagem = view.fazerPerguntaNumeros();

        if (defesaDoPersonagem < 0) {
            view.mostrarErro("A defesa  do personagem deve ser maior ou igual a zero. Tente novamente.");
            return;
        }

        view.mostrarMensagem("Informe o ataque do(a) personagem: ");
        int ataqueDoPersonagem = view.fazerPerguntaNumeros();

        if (ataqueDoPersonagem <= 0) {
            view.mostrarErro("O ataque deve ser maior que zero. Tente novamente.");
            return;
        }

        // ao invés de digitar manualmente, o usuário digita 1 para o personagem ser Herói/Heroína, e 2 para Vilão/Vilã, assim
        // dependendo do que for digitado, um personagem da SUBCLASSE específica vai ser criado, entretanto, será tratado como 
        // a classe pai (Personagem)
        view.mostrarMensagem("Por fim, informe a classe do(a) personagem:\n1 - [Herói/Heroína] | 2 - [Vilão/Vilã]");
        int classeDoPersonagemEscolha = view.fazerPerguntaNumeros();

        if (classeDoPersonagemEscolha == 1) {
            /* não preciso que o método seja do tipo heroi ou vilão, eu chamo o objeto cadastroPersonagens e o método dele cadastrarPersonagen(),
            o método recebe um personagem como objeto/parametro, como o heroi é classe filha de personagem, ou seja, herda os atributos, ela e a classe vilão
            tbm serão aceitas, sem o método deixar de ser void */
            cadastroPersonagens.cadastrarPersonagem(new Heroi(nomeDoPersonagem, generoDoPersonagem, vidaMaximaDoPersonagem, ataqueDoPersonagem, defesaDoPersonagem));
            view.mostrarMensagem(nomeDoPersonagem + " cadastrado(a) com sucesso!");
            return;
        } else if (classeDoPersonagemEscolha == 2) {
            cadastroPersonagens.cadastrarPersonagem(new Vilao(nomeDoPersonagem, generoDoPersonagem, vidaMaximaDoPersonagem, ataqueDoPersonagem, defesaDoPersonagem));
            view.mostrarMensagem(nomeDoPersonagem + " cadastrado(a) com sucesso!");
            return;
        } else {
            view.mostrarErro("Opção invalida.");
            return;
        }
    }
}