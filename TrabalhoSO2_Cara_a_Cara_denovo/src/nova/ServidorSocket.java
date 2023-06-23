package nova;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServidorSocket extends Thread {
    
    private static List<Cliente> clientes;
    private Cliente cliente;
    private Socket conexao;
    private String nomeCliente;    
    private  int id_cliente =0;    
    private String[] vetor = null;    
    private String texto = null;
    
    int i = 1;

    public ServidorSocket(Cliente c) {
        cliente = c;
    }

    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getSocket().getInputStream()));

            PrintStream saida = new PrintStream(cliente.getSocket().getOutputStream());

            cliente.setSaida(saida);

            nomeCliente = entrada.readLine();

            System.out.println("NOME CLIENTE: " + nomeCliente);

            if (nomeCliente == null) {
                return;
            }

            cliente.setNome(nomeCliente);

            //String texto = entrada.readLine();
            vetor = entrada.readLine().split(";");
            texto = vetor[0].trim();
            id_cliente = Integer.parseInt(vetor[1].trim());
            
            //System.out.println("TEXTO RECEBIDO" + texto);

            while (texto != null
                    && !(texto.trim().equals(""))) {
                sendTo(saida, " disse: ", texto, id_cliente);
                vetor = null;
                vetor = entrada.readLine().split(";");
                texto = vetor[0].trim();
                id_cliente = Integer.parseInt(vetor[1].trim());
            }

            sendToAll(saida, " saiu da conexao", " do chat");
            clientes.remove(saida);

            conexao.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToAll(PrintStream saida, String texto1, String texto2) {
        //System.out.println("entrou n o sendtoall ");
        try {
            //System.out.println("texto 2: "+texto2);
            Iterator<Cliente> iter = clientes.iterator();
            while (iter.hasNext()) {
                Cliente outroCliente = iter.next();
                PrintStream chat = (PrintStream) outroCliente.getSaida();
                if (chat != saida) {
                    chat.println(cliente.getNome()
                            + " com IP: "
                            + cliente.getSocket().
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
            Iterator<Cliente> iter = clientes.iterator();
            while (iter.hasNext()) {
                System.out.println("id cliente: "+id_cliente);
                Cliente outroCliente = iter.next();
                if (outroCliente.getId() == id_cliente) {
                PrintStream chat = (PrintStream) outroCliente.getSaida();
                if (chat != saida) {
                    chat.println(cliente.getNome()
                            + " com IP: "
                            + cliente.getSocket().
                                    getRemoteSocketAddress()
                            + texto1 + texto2);
                }
            }
        }
    }
    catch (Exception e

    
        ) {
            e.printStackTrace();
    }
}

public static void main(String[] args) {
        clientes = new ArrayList<Cliente>();
            int id_cliente = 1;
        try {
            ServerSocket s = new ServerSocket(2223);
            while (true) {
                System.out.println("Esperando alguem se conectar ");
                Socket conexao = s.accept();

                Cliente cliente = new Cliente();

                cliente.setId(id_cliente);
                cliente.setIp(conexao.getRemoteSocketAddress().toString());

                cliente.setSocket(conexao);

                clientes.add(cliente);

                System.out.println("Conectou: " + conexao.getRemoteSocketAddress());

                Thread t = new ServidorSocket(cliente);
                t.start();
                id_cliente++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}