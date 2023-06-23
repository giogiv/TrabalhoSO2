package src;

import java.io.*;
import java.net.*;

public class Jogador {
    
    private int id;
    private String ip;
    private String nome;
    private PrintStream saida;
    private Socket socket;

    public Jogador() {
    }

    public Jogador(int id, String ip, String nome, PrintStream saida, Socket socket) {
        this.id = id;
        this.ip = ip;
        this.nome = nome;
        this.saida = saida;
        this.socket = socket;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public PrintStream getSaida() {
        return saida;
    }

    public void setSaida(PrintStream saida) {
        this.saida = saida;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        try{
            saida = new PrintStream(socket.getOutputStream());
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }    
}
