package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Servidor3 extends Thread {
    private static List<Jogador> jogadores;
    private Socket conexao;

    public Servidor(Socket conexao) {
        this.conexao = conexao;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());

            // Lê o nome do jogador
            String nomeJogador = entrada.readLine();
            System.out.println("NOME CLIENTE: " + nomeJogador);

            if (nomeJogador == null) {
                return;
            }

            // Cria um novo jogador e adiciona à lista
            Jogador jogadores = new Jogador(nomeJogador, saida, conexao);
            jogadores.add(jogador);

            // Implemente a lógica do jogo aqui
            // ...

            // Encerra a conexão
            jogadores.remove(jogador);
            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jogadores = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(12345);

            while (true) {
                System.out.println("Aguardando conexão...");
                Socket conexao = serverSocket.accept();

                System.out.println("Conexão estabelecida: " + conexao.getRemoteSocketAddress());

                Thread thread = new Servidor(conexao);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}