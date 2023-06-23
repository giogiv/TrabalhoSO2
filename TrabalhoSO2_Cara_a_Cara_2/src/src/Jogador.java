package src;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Jogador {
    
    private int id;
    private String ip;
    private String nome;
    private PrintStream saida;
    private Socket socket;
    private ArrayList<Cartas> CartaJogador;
    
    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getNome() {
        return nome;
    }

    public PrintStream getSaida() {
        return saida;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaida(PrintStream saida) {
        this.saida = saida;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    
    public ArrayList<Cartas> getCartaJogador() {
        return CartaJogador;
    }

    public void setCartaJogador(ArrayList<Cartas> CartaJogador) {
        this.CartaJogador = CartaJogador;
    }
    
}
