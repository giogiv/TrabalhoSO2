package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Servidor2 extends Thread {

    
    private static List<Jogador> jogadores;
    private Socket conexao;
    private String nomeJogador;
    private int id_jogador = 0;
    private String[] vetor = null;
    private String texto = null;

    public Servidor(Socket conexao) {
        this.conexao = conexao;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());

            nomeJogador = entrada.readLine();
            System.out.println("NOME CLIENTE: " + nomeJogador);

            if (nomeJogador == null) {
                return;
            }

            // Cria um novo jogador e adiciona à lista
            Jogador jogadores = new Jogador(nomeJogador);
            jogadores.add(jogador);

            texto = entrada.readLine();
            vetor = texto.split(";");
            texto = vetor[0].trim();
            id_jogador = Integer.parseInt(vetor[1].trim());

            while (texto != null && !(texto.trim().equals(""))) {
                sendToAll(saida, " disse: ", texto, id_jogador);
                vetor = entrada.readLine().split(";");
                texto = vetor[0].trim();
                id_jogador = Integer.parseInt(vetor[1].trim());
            }

            sendToAll(saida, " saiu da conexão", " do chat", 1);
            jogadores.remove(jogador);

            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(PrintStream saida, String texto1, String texto2, int idCliente) {
        try {
            Iterator<Jogador> iter = jogadores.iterator();
            while (iter.hasNext()) {
                Jogador outroJogador = iter.next();
                PrintStream chat = (PrintStream) outroJogador.getOutput();
                if (chat != saida) {
                    chat.println(outroJogador.getNome() + texto1 + texto2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        jogadores = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            int idCliente = 1;

            while (true) {
                System.out.println("Aguardando conexão...");
                Socket conexao = serverSocket.accept();

                System.out.println("Conexão estabelecida: " + conexao.getRemoteSocketAddress());

                Thread thread = new Servidor(conexao);
                thread.start();

                idCliente++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
