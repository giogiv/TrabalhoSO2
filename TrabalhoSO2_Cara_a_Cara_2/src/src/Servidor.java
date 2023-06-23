package src;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import src.Jogador;

public class Servidor extends Thread{
    
    private static List<Jogador> jogadores;
    //private Cartas jogador;
    private Socket conexao;
    private String nomeJogador;
    private int id_jogador = 0;
    private String[] vetor = null;
    private String texto = null;
    
    int i = 1;

    public Servidor(Cartas c) {
        jogador = c;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(jogadores.getSocket().getInputStream()));
            PrintStream saida = new PrintStream(jogadores.getSocket().getOutputStream());
            jogador(saida);
            nomeJogador = entrada.readLine();
            System.out.println("NOME CLIENTE: " + nomeJogador);

            if (nomeJogador == null) {
                return;
            }

            jogadores.setNome(nomeJogador);

            //String texto = entrada.readLine();
            vetor = entrada.readLine().split(";");
            texto = vetor[0].trim();
            id_jogador = Integer.parseInt(vetor[1].trim());
            
            //System.out.println("TEXTO RECEBIDO" + texto);

            while (texto != null
                    && !(texto.trim().equals(""))) {
                //sendToAll(saida, " disse ", texto);
                sendTo(saida, " disse: ", texto, id_jogador);
                vetor = null;
                vetor = entrada.readLine().split(";");
                texto = vetor[0].trim();
                id_jogador = Integer.parseInt(vetor[1].trim());
            }

            sendToAll(saida, " saiu da conexao", " do chat");
            jogadores.remove(saida);

            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(PrintStream saida, String texto1, String texto2) {
        //System.out.println("entrou n o sendtoall ");
        try {
            //System.out.println("texto 2: "+texto2);
            Iterator<Cartas> iter = jogadores.iterator();
            while (iter.hasNext()) {
                Cartas outroCliente = iter.next();
                PrintStream chat = (PrintStream) outroCliente.getSaida();
                if (chat != saida) {
                    chat.println(jogadores.getNome()
                            + " com IP: "
                            + jogadores.getSocket().
                                    getRemoteSocketAddress()
                            + texto1 + texto2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTo(PrintStream saida, String texto1, String texto2, int id_cliente) {
        try {
            Iterator<Cartas> iter = jogadores.iterator();
            while (iter.hasNext()) {
                System.out.println("id cliente: "+id_cliente);
                Cartas outroCliente = iter.next();
                if (outroCliente.getId()== id_cliente) {
                    PrintStream chat = (PrintStream) outroCliente.getSaida();
                    if (chat != saida) {
                        chat.println(jogadores.getNome()
                            + " com IP: "
                            + jogadores.getSocket().
                                    getRemoteSocketAddress()
                            + texto1 + texto2);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        jogadores = new ArrayList<Cartas>();
            int id_cliente = 1;
        try {
            ServerSocket s = new ServerSocket(2223);
            while (true) {
                System.out.println("Esperando alguem se conectar ");
                Socket conexao = s.accept();

                Cartas cliente = new Cartas();

                cliente.setId(id_cliente);
                cliente.setIp(conexao.getRemoteSocketAddress().toString());

                cliente.setSocket(conexao);

                jogadores.add(cliente);

                System.out.println("Conectou: " + conexao.getRemoteSocketAddress());

                Thread t = new Servidor(cliente);
                t.start();
                id_cliente++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
