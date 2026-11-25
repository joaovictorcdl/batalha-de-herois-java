package br.ufrpe.uast.lp.batalhadeherois;
import br.ufrpe.uast.lp.batalhadeherois.controller.ControllerBatalhaDeHerois;

public class Main {
    public static void main(String[] args) {
        /*
        DISCENTE: JOÃO VICTOR CORDEIRO DE LIMA
        DOCENTE: HIDELBERG OLIVEIRA ALBUQUERQUE
        DESCRIÇÃO: Desenvolver um simulador de batalha entre Personagens, com:
                        - cadastro e consulta de de Personagens,
                        - selecionar o personagem para uma batalha
                        - simulação de batalhas
                        - apresentar o resultado
                    O sistema deve ser implementado em Java (versão 21+), utilizando interface textual com entrada
                    via Scanner e saída no terminal. Os projetos devem ser entregues zipados no Classroom.
                    Todos os dados existirão somente durante a execução. Ao encerrar a aplicação, os cadastros
                    serão perdidos. Não haverá arquivos, banco de dados, APIs ou carregamento de fontes
                    externas.
                    O objetivo será utilizar objetos com responsabilidades próprias, herança e troca de mensagens,
                    sem concentrar as regras no Main.
        DATA: 23/11/2026
        */
        ControllerBatalhaDeHerois c = new ControllerBatalhaDeHerois();
        c.menuInicial();
    }
}
