package src;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Random;

public class JogadorSocket extends Thread{
    
    private static boolean done = false;
    private Socket conexao;
    private Cartas cartas;

    public JogadorSocket(Socket s) {
        conexao = s;
    }
    
    public void run(){
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            
            String texto;

            while (true) {
                texto = entrada.readLine();

                if (texto == null) {
                    System.out.println("Conexao encerrada");
                    break;
                }
                System.out.println("\n" + texto);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    public static <T> T retornaPerso(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(lista.size());
        return lista.get(randomIndex);
    }
    
    public void mostraMenu(BufferedReader entrada, PrintStream saida) {
        try {
            System.out.println("Bem vindo ao menu do jogo!");
            System.out.println("Qual pergunta gostaria de fazer?");
            System.out.println("1. Tipo de cabelo");
            System.out.println("2. Sexo");
            System.out.println("3. Cor da pele");
            System.out.println("4. Cor do cabelo");
            System.out.println("5. Cor olhos");
            System.out.println("6. Assessorios");

            String opcaoEscolhida = entrada.readLine();

            switch (opcaoEscolhida) {
                case "1":
                    System.out.println("Tipos de cabelos: ");
                    System.out.println("1. Cabelo liso");
                    System.out.println("2. Cabelo cacheado");
                    opcaoEscolhida = entrada.readLine();
                    switch (opcaoEscolhida) {
                        case "1":
                            saida.println("Seu personagem tem cabelo liso?");
                            break;
                        case "2":
                            saida.println("Seu personagem tem cabelo cacheado?");
                            break;
                        default:
                            saida.println("Opção inválida");
                            break;
                    }
                    break;
                case "2":
                    System.out.println("Sexualidade: ");
                    System.out.println("1. Feminino");
                    System.out.println("2. Masculino");
                    opcaoEscolhida = entrada.readLine();
                    switch (opcaoEscolhida) {
                        case "1":
                            saida.println("Seu personagem é mulher?");
                            break;
                        case "2":
                            saida.println("Seu personagem é homem?");
                            break;
                        default:
                            saida.println("Opção inválida");
                            break;
                    }
                    break;
                case "3":
                    System.out.println("Cor da pele: ");
                    System.out.println("1. Branca");
                    System.out.println("2. Morena");
                    System.out.println("2. Negra");

                    opcaoEscolhida = entrada.readLine();
                    switch (opcaoEscolhida) {
                        case "1":
                            saida.println("Seu personagem tem pele branca?");
                            break;
                        case "2":
                            saida.println("Seu personagem tem pele morena?");
                            break;
                        case "3":
                            saida.println("Seu personagem tem pele negra?");
                            break;
                        default:
                            saida.println("Opção inválida");
                            break;
                    }
                    break;
                case "4":
                    System.out.println("Cor de cabelo: ");
                    System.out.println("1. Marrom");
                    System.out.println("2. Branco");
                    System.out.println("3. Preto");
                    System.out.println("4. Amarelo");
                    opcaoEscolhida = entrada.readLine();
                    switch (opcaoEscolhida) {
                        case "1":
                            saida.println("Seu personagem tem cabelo marrom?");
                            break;
                        case "2":
                            saida.println("Seu personagem tem cabelo branco?");
                            break;
                        case "3":
                            saida.println("Seu personagem tem cabelo preto?");
                            break;
                        case "4":
                            saida.println("Seu personagem tem cabelo amarelo?");
                            break;
                        default:
                            saida.println("Opção inválida");
                            break;
                    }
                    break;
                case "5":
                    System.out.println("Cor dos olhos: ");
                    System.out.println("1. Azul");
                    System.out.println("2. Castanho");
                    opcaoEscolhida = entrada.readLine();
                    switch (opcaoEscolhida) {
                        case "1":
                            saida.println("Seu personagem tem olho azul?");
                            break;
                        case "2":
                            saida.println("Seu personagem tem olho castanho?");
                            break;
                        default:
                            saida.println("Opção inválida");
                            break;
                    }
                    break;
                case "6":
                    System.out.println("Assessorios: ");
                    System.out.println("1. Chapeu");
                    System.out.println("2. Oculos");
                    opcaoEscolhida = entrada.readLine();
                    switch (opcaoEscolhida) {
                        case "1":
                            saida.println("Seu personagem usa chapeu?");
                            break;
                        case "2":
                            saida.println("Seu personagem usa oculos?");
                            break;
                        default:
                            saida.println("Opção inválida");
                            break;
                    }

                default:
                    saida.println("Opção inválida");
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        try {
            Socket conexao = new Socket("127.0.0.2", 2224);
            PrintStream saida = new PrintStream(conexao.getOutputStream());

            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Digite seu nome: ");

            String nome = teclado.readLine();
            saida.println(nome);

            Thread t = new JogadorSocket(conexao);
            t.start();

            String texto;
            
            List<Personagens> listaPersonagens = new Cartas().todosPersonagens();
            Personagens personagemAleatorio = JogadorSocket.retornaPerso(listaPersonagens);
            System.out.println("Personagem aleatorio: " + personagemAleatorio.getNome());

            //mostraMenu(entrada, saida);
            while (true) {
                System.out.println("> ");
                texto = teclado.readLine();
                if (done) {
                    break;
                }
                saida.println(texto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
