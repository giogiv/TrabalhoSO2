package nova;

import java.io.*;
import java.net.*;

/**
 * Com quem vai conectar
 */
public class Cliente {

    private int id;
    private String ip;
    private String nome;
    private PrintStream saida;
    private Socket socket;

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
}
