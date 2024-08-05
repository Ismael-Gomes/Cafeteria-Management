package lanchonete;

import java.util.Scanner;

import negocio.AdminNegocio;
import negocio.ProdutoNegocio;
import negocio.VendaNegocio;
import negocio.PessoaNegocio;

import java.util.List;

public class Lanchonete{
  
    static ProdutoNegocio produtoNegocio = new ProdutoNegocio();
    static VendaNegocio vendaNegocio = new VendaNegocio();
    
    private static String categoria;
    
    public static void main(String[] args) throws InterruptedException {

        Scanner sc = new Scanner(System.in);
        
        boolean exit = false;

        while (!exit) {
            System.out.println("#####  Menu Principal  #####");
            System.out.println("============================");
            System.out.println("##### 1 - Cardapio     #####");
            System.out.println("##### 2 - Meu Perfil   #####");
            System.out.println("##### 3 - Suporte      #####");
            System.out.println("##### 4 - Admin        #####");
            System.out.println("##### 0 - Sair         #####");
            System.out.println("============================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    clearScreen();
                    menuOpcao1(sc);
                    break;
                case 2:
                    clearScreen();
                    menuOpcao2(sc);
                    break;
                case 3:
                    clearScreen();
                    menuOpcao3(sc);
                    break;
                case 4:
                    clearScreen();
                    menuOpcao4(sc);
                    break;
                case 0:
                    clearScreen();
                    exit = true;
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    clearScreen();
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();

    }
    
    private static void menuOpcao1(Scanner sc) throws  InterruptedException {
        boolean back = false;

        while (!back) {
            System.out.println("\n######    Cardápio    #######");
            System.out.println("===============================");
            System.out.println("##### 1 - Lanche          #####");
            System.out.println("##### 2 - Acompanhamento  #####");
            System.out.println("##### 3 - Bebida          #####");
            System.out.println("##### 0 - Voltar          #####");
            System.out.println("===============================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();
            
            switch (choice) {
                case 1:
                    categoria = "Lanche";
                    System.out.println("\n##### Esses são nossos lanches: ");
                    GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
                    try {
                        produtoNegocio.searchByCategory(categoria);
                    }catch (Exception e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    gerenciadorLanches.viewProductsSequenceLanche();
                    comprarProduto(sc);

                    
                    break;

                case 2:
                    categoria = "Acompanhamento";
                    System.out.println("\n##### Esses são nossos acompanhamentos: ");
                    GerenciadorDeProdutos gerenciadorAcompanhamentos = new GerenciadorDeProdutos();
                    try {
                        produtoNegocio.searchByCategory(categoria);
                    }catch (Exception e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    gerenciadorAcompanhamentos.viewProductsSequenceAcompanhamento();
                    comprarProduto(sc);
                    break;

                case 3:
                    categoria = "Bebida";
                    System.out.println("\n##### Essas são nossas bebidas: ");
                    GerenciadorDeProdutos gerenciadorProdutos = new GerenciadorDeProdutos();
                    try {
                        produtoNegocio.searchByCategory(categoria);
                    }catch (Exception e) {
                        System.out.println("Erro " + e.getMessage());
                    }
                    gerenciadorProdutos.viewProductsSequenceBebida();
                    comprarProduto(sc);
                    break;

                case 0:
                    back = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    private static void menuOpcao2(Scanner sc) throws  InterruptedException {
        boolean back = false;

        while (!back) {
            System.out.println("\nMenu da Opção 2:");
            System.out.println("1. Sub-opção 2.1");
            System.out.println("2. Sub-opção 2.2");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();
            System.out.println("\nCarregando...");
            Thread.sleep(1500);
            clearScreen();

            switch (choice) {
                case 1:
                    System.out.println("Você escolheu a Sub-opção 2.1.");
                    // Adicionar funcionalidade para Sub-opção 2.1 aqui
                    break;
                case 2:
                    System.out.println("Você escolheu a Sub-opção 2.2.");
                    // Adicionar funcionalidade para Sub-opção 2.2 aqui
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
    
    private static void menuOpcao3(Scanner sc) throws  InterruptedException {
        boolean back = false;

        while (!back) {
            System.out.println("\nMenu da Opção 2:");
            System.out.println("1. Sub-opção 2.1");
            System.out.println("2. Sub-opção 2.2");
            System.out.println("3. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();
            System.out.println("\nCarregando...");
            Thread.sleep(1500);
            clearScreen();

            switch (choice) {
                case 1:
                    System.out.println("Você escolheu a Sub-opção 2.1.");
                    // Adicionar funcionalidade para Sub-opção 2.1 aqui
                    break;
                case 2:
                    System.out.println("Você escolheu a Sub-opção 2.2.");
                    // Adicionar funcionalidade para Sub-opção 2.2 aqui
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        }
    }

    private static void menuOpcao4(Scanner sc) throws InterruptedException {
        boolean back = false;
        boolean run = true;
        AdminNegocio adminNegocio = new AdminNegocio();

        while (!back) {
            System.out.println("1 - Login");
            System.out.println("2 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();


            switch (choice) {
                case 1:
                        System.out.print("Matricula: ");
                        String matricula = sc.next();
                        System.out.print("Senha: ");
                        String senha = sc.next();
                        if (isValidMatricula(matricula) && isValidSenha(senha)) {
                            if (adminNegocio.verificarCredenciais(matricula, senha)) {
                                clearScreen();
                                System.out.println("Login bem-sucedido! Bem-vindo à área administrativa.");
                                // Prossiga para a tela de administração
                            } else {
                                if (adminNegocio.verificarMatricula(matricula)) {
                                    System.out.println("Senha incorreta.");
                                    clearScreen();
                                } else {
                                    System.out.println("Matrícula não encontrada.");
                                    clearScreen();
                                }
                            }
                        } else {
                            if (!isValidMatricula(matricula)) {
                                System.out.println("Matrícula inválida! Deve conter 12 dígitos.");
                                clearScreen();
                            }
                            if (!isValidSenha(senha)) {
                                System.out.println("Senha inválida! Deve ter entre 8 e 20 caracteres.");
                                clearScreen();
                            }
                        }
                    break;
                case 2:
                    back = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static boolean isValidMatricula(String matricula) {
        return matricula.matches("\\d{12}"); // Verifica se a matrícula tem exatamente 12 dígitos
    }

    public static boolean isValidSenha(String senha) {
        return senha.length() >= 8 && senha.length() <= 20; // Verifica se a senha tem entre 8 e 20 caracteres
    }
    
    public static void clearScreen() throws InterruptedException {
        // Limpa o terminal imprimindo 100 linhas em branco
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
        System.out.println("\nCarregando...");
        Thread.sleep(500);
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    public static void comprarProduto(Scanner sc){
        try {
            GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
            System.out.print("\n##### Deseja fazer um pedido | 1 - SIM | 2 - NÃO: ");
            int choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Digite o Código do pedido que deseja: ");
                    int codigo = sc.nextInt();
                    if (gerenciadorLanches.viewProducts(codigo)){
                        System.out.print("\nDigite a quantidade: ");
                        int quantidade = sc.nextInt();
                        int id = produtoNegocio.searchId(codigo);
                        vendaNegocio.insertSale(id, codigo, quantidade);
                    }
                case 2:
                    System.out.println("Voltando...");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

}
