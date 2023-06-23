package src;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Servidor extends Thread {

    private static List<Jogador> jogadores;
    private Jogador jogador;
    private Socket conexao;
    private Cartas cartas;
    private String nomeJogador;
    private int id_jogador = 0;
    private String[] vetor = null;
    private String texto = null;

    public Servidor(Jogador c) {
        jogador = c;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(jogador.getSocket().getInputStream()));
            PrintStream saida = new PrintStream(jogador.getSocket().getOutputStream());
            jogador.setSaida(saida);
            nomeJogador = entrada.readLine();
            System.out.println("NOME DO JOGADOR: " + nomeJogador);

            if (nomeJogador == null) {
                return;
            }

            jogador.setNome(nomeJogador);

            //Lê uma linha de entrada usando entrada.readLine().
            //Divide a linha lida em várias partes usando o caractere ";" como delimitador
            //utilizando o método split(";"). O resultado é armazenado em um array chamado vetor.
            vetor = entrada.readLine().split(";");
            //O primeiro elemento do array vetor, vetor[0], 
            //é atribuído à variável texto, após remover os espaços em branco do 
            //início e do final usando o método trim().
            texto = vetor[0].trim();

            id_jogador = Integer.parseInt(vetor[1].trim());

            while (texto != null && !(texto.trim().equals(""))) {
                sendTo(saida, " disse: ", texto, id_jogador);
                vetor = null;
                vetor = entrada.readLine().split(";");
                texto = vetor[0].trim();
                id_jogador = Integer.parseInt(vetor[1].trim());
            }

            sendTo(saida, " saiu da conexao", " do chat", id_jogador);
            jogadores.remove(saida);

            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTo(PrintStream saida, String texto1, String texto2, int id_jogador) {
        try {
            Iterator<Jogador> iter = jogadores.iterator();
            while (iter.hasNext()) {
                Jogador outroJogador = iter.next();
                if (outroJogador.getId() == id_jogador) {
                    PrintStream chat = (PrintStream) outroJogador.getSaida();
                    if (chat != saida) {
                        chat.println(jogador.getNome()
                                + " com IP: "
                                + jogador.getSocket().
                                        getRemoteSocketAddress()
                                + texto1 + texto2);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jogadores = new ArrayList<Jogador>();
        int id_jogador = 1;
        try {
            ServerSocket s = new ServerSocket(2224);
            while (true) {
                System.out.println("Esperando alguem se conectar");
                Socket conexao = s.accept();

                Jogador jogador = new Jogador();

                jogador.setId(id_jogador);
                jogador.setIp(conexao.getRemoteSocketAddress().toString());

                jogador.setSocket(conexao);

                jogadores.add(jogador);

                System.out.println("Conectou: " + conexao.getRemoteSocketAddress());

                Thread t = new Servidor(jogador);
                t.start();
                id_jogador++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
