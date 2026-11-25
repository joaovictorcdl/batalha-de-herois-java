package br.ufrpe.uast.lp.batalhadeherois.model;
import java.util.ArrayList;
import java.util.List;

public class CadastroPersonagens {

    private List<Personagem> personagensCadastrados;
    private int contadorId = 0;

    public CadastroPersonagens() {
        this.personagensCadastrados = new ArrayList<>();
    }

    /* o ID começa em 0, ao chamar esse método, ele vai atribuir 1 automaticamente e setar o ID no
    personagem cadastrado, além de colocar na lista... -> O controller só tem a responsabilidade de fazer
    as classes conversarem entre si <-, portanto, gerar ID não é obrigação dele, é dessa classe
    
    quando chamo esse método, eu envio um herói ou vilão como parâmetro, mas aqui eu tenho um objeto do tipo
    personagem sendo requisitado... isso é aceito pq Herói e Vião são subclasses, Personagem é classe pai, mesmo
    Herói e Vilão sendo classes diferentes, personagem ainda é o "pai" delas na hierarquia, e por isso é aceito. No caso,
    podem ser tratados como objetos da classe pai*/
    public void cadastrarPersonagem(Personagem novoPersonagem) {
        contadorId++;
        novoPersonagem.setId(contadorId);
        personagensCadastrados.add(novoPersonagem);
    }

    public Personagem buscarPorId(int id) {
        /* percorro os personagens usando o for each, se o ID de algum deles for igual ao id do personagem
        pesquisado (passado como parametro), eu retorno ele, se não achar, retorno null (nada), porque essa
        referência não existe */
       for (Personagem personagem : personagensCadastrados) {
            if (personagem.getId() == id) {
                return personagem;
            } 
       }
       return null;
    }

    public List<Personagem> listarPorCategoria(String categoria) {
        // crio uma lista como controladora, vazia a princípio
        ArrayList<Personagem> controladorPorClasses = new ArrayList<>();
        for (Personagem listadosPorClasse : personagensCadastrados) {
            // percorro a lista e separo os personagens de acordo com a categoria deles,
            // como a variável listadosPorClasse é do método, ou seja, temporária, o
            // resultado anterior não vai influenciar caso eu chame ela n vezes
            // ficando  na controladorPorClasses apenas os personagens que correspondem a classe que
            // estou buscando, sendo uma "cópia" indireta... na verdade, não chamaria de cópia, só estou
            // selecionando quem atende a determinada "característica" e puxando para um novo lugar, uma nova lista
            if (listadosPorClasse.getCategoria().equalsIgnoreCase(categoria)) {
                controladorPorClasses.add(listadosPorClasse);
            }
        }

        return controladorPorClasses;

    }
    
    public List<Personagem> listarTodosOsPersonagens() {
        /*
        eu tinha usado a seguinte sintaxe:
        return personagensCadastrados
        
        AVISO: NÃO PODE, porque se não alguém conseguiria alterar a lista de forma direta...
        fazendo nessa nova sintaxe, ele envia uma nova lista, porém com os dados anteriores,
        acho que posso chamar de cópia */
        return new ArrayList<>(personagensCadastrados);
    }
}