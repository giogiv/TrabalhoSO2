package src;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class Tabuleiro extends Personagens {

    private Cartas cartas;

    // criar os jogadores como threads
    // pegar a lista de personagens e entregar um personagem aleatoriamente para cada jogador
    // <T> isso indica que a funcao é generica e pode trabalhar com qualquer tipo de objeto
    // T é o parametro que será substituido por um tipo especifico quando a funcao for chamada
    // ou seja, o retorno será o mesmo tipo generico utilizado na lista
    public static <T> T retornaPerso(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(lista.size());
        return lista.get(randomIndex);
    }

    public static void main(String[] args) {
        List<Personagens> listaPersonagens = new Cartas().todosPersonagens();
        Personagens personagemAleatorio = Tabuleiro.retornaPerso(listaPersonagens);
        System.out.println("Personagem aleatório: " + personagemAleatorio.getNome());
    }

    // criar um bool para verificar as perguntas feitas
    // criar um mini-menu de opcoes
    // classe tabuleiro vai virar a main
    // classe servidor vai estabelecer a conexao
    // de algum jeito fazer o servidor se comunicar com o tabuleiro para a comunicacao das threads
}
