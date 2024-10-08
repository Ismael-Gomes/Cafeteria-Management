package lanchonete;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import dominio.Cliente;
import dominio.Funcionario;
import dominio.Produto;
import negocio.AdminNegocio;
import negocio.ProdutoNegocio;
import negocio.VendaNegocio;
import negocio.ClienteNegocio;
import negocio.SuporteNegocio;
import negocio.FuncionarioNegocio;

public class Lanchonete {

    static ProdutoNegocio produtoNegocio = new ProdutoNegocio();
    static VendaNegocio vendaNegocio = new VendaNegocio();
    static FuncionarioNegocio funcionarioNegocio = new FuncionarioNegocio();
    static SuporteNegocio suporteNegocio = new SuporteNegocio();
    static AdminNegocio adminNegocio = new AdminNegocio();
    static ClienteNegocio clienteNegocio = new ClienteNegocio();

    private static String categoria, nome, descricao, cpf, cpfVendedor, numero_tele, email, senha, matricula, data_nascimento;
    private static double preco, salario;
    private static int quantidade, codigo;
    private static boolean encontrado = true;

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
                    System.out.print("Digite o CPF do vendedor: ");
                    cpfVendedor = sc.next();
                    if (isValidCPF(cpfVendedor)) {
                        clearScreen();
                        menuOpcao1(sc);
                    }else{
                        System.out.println("CPF Invalido!");
                    }
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

    //OK
    private static void menuOpcao1(Scanner sc) throws InterruptedException {
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
                        try {
                            categoria = "Lanche";
                            System.out.println("\n##### Esses são nossos lanches: ");
                            GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
                            try {
                                produtoNegocio.searchByCategory(categoria);
                            } catch (Exception e) {
                                System.out.println("Erro " + e.getMessage());
                            }
                            gerenciadorLanches.viewProductsSequenceLanche();
                            comprarProduto(sc);
                            funcionarioNegocio.vendaFuncionario(cpfVendedor);
                        }catch (SQLException e) {
                            System.out.println("Erro " + e.getMessage());
                        }
                        break;
                    case 2:
                        try{
                            categoria = "Acompanhamento";
                            System.out.println("\n##### Esses são nossos acompanhamentos: ");
                            GerenciadorDeProdutos gerenciadorAcompanhamentos = new GerenciadorDeProdutos();
                            try {
                                produtoNegocio.searchByCategory(categoria);
                            } catch (Exception e) {
                                System.out.println("Erro " + e.getMessage());
                            }
                            gerenciadorAcompanhamentos.viewProductsSequenceAcompanhamento();
                            comprarProduto(sc);
                            funcionarioNegocio.vendaFuncionario(cpfVendedor);
                        }catch (SQLException e) {
                            System.out.println("Erro " + e.getMessage());
                        }
                        break;
                    case 3:
                        try{
                            categoria = "Bebida";
                            System.out.println("\n##### Essas são nossas bebidas: ");
                            GerenciadorDeProdutos gerenciadorProdutos = new GerenciadorDeProdutos();
                            try {
                                produtoNegocio.searchByCategory(categoria);
                            } catch (Exception e) {
                                System.out.println("Erro " + e.getMessage());
                            }
                            gerenciadorProdutos.viewProductsSequenceBebida();
                            comprarProduto(sc);
                            funcionarioNegocio.vendaFuncionario(cpfVendedor);
                            break;
                        }catch (SQLException e) {
                            System.out.println("Erro " + e.getMessage());
                        }
                    case 0:
                        back = true;
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
        }
    }

    //A fazer
    private static void menuOpcao2(Scanner sc) throws InterruptedException {
        boolean back = false;

        while (!back) {
            System.out.println("\n#####    Gerenciar Cliente    #####");
            System.out.println("=====================================");
            System.out.println("##### 1 - Login                 #####");
            System.out.println("##### 2 - Criar uma Conta Nova  #####");
            System.out.println("##### 0 - Sair                  #####");
            System.out.println("=====================================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    boolean backClien = false;
                    while (!backClien) {
                        System.out.println("\n#####       Cliente       #####");
                        System.out.println("=================================");
                        System.out.println("##### 1 - Alterar Dados     #####");
                        System.out.println("##### 2 - Apagar Conta      #####");
                        System.out.println("##### 0 - Sair              #####");
                        System.out.println("=================================");
                        System.out.print("##### Escolha uma opção: ");
                        int choiceClien = sc.nextInt();
                        switch (choiceClien) {
                            case 1:
                                System.out.print("Digite o seu CPF: ");
                                if (isValidCPF(cpf)) {
                                    System.out.print("Digite o Novo Nome");
                                    nome = sc.next();
                                    System.out.print("Digite o Novo Nome da Conta: ");
                                    String nomeConta = sc.next();
                                    System.out.print("Digite a Nova Senha: ");
                                    String senha = sc.next();
                                    try {
                                        Cliente cliente = new Cliente(nome, nomeConta, senha, cpf);
                                        clienteNegocio.updateClien(cliente);
                                    }catch (Exception e) {
                                        System.out.println("Erro " + e.getMessage());
                                    }
                                }else{
                                    System.out.println("CPF Invalida!");
                                }
                                break;
                            case 2:
                                System.out.print("Digite o seu CPF: ");
                                if (isValidCPF(cpf)) {
                                    try {
                                        clienteNegocio.deleteClien(cpf);
                                    }catch (Exception e) {
                                        System.out.println("Erro " + e.getMessage());
                                    }
                                }else{
                                    System.out.println("CPF Invalida!");
                                }
                                break;
                            case 0:
                                backClien = true;
                                break;
                        }
                    }
                case 2:
                    System.out.print("Digite o seu Nome");
                    nome = sc.next();
                    System.out.print("Digite o seu CPF: ");
                    cpf = sc.next();
                    if(isValidCPF(cpf)) {
                        System.out.print("Digite sua Data de Nascimento (XX/XX/XXXX): ");
                        data_nascimento = sc.next();
                        String[] dtNascimento = data_nascimento.split("/");
                        if (dtNascimento.length == 3) {
                            String dia = dtNascimento[0];
                            String mes = dtNascimento[1];
                            String ano = dtNascimento[2];
                            if (isValidData(ano, mes, dia)) {
                                data_nascimento = ano + mes + dia;
                                System.out.print("Digite o seu Sexo (M/F): ");
                                String sexo = sc.next();
                                if (sexo.equals("M") || sexo.equals("m") || sexo.equals("F") || sexo.equals("f")) {
                                    System.out.print("Digite o Nome da sua Conta: ");
                                    String nomeConta = sc.next();
                                    System.out.print("Digite seu E-mail: ");
                                    email = sc.next();
                                    System.out.print("Digite sua Senha; ");
                                    senha = sc.next();
                                    try {
                                        clienteNegocio.insertClien(new Cliente(cpf, nome, nomeConta, sexo, data_nascimento, email, senha));
                                    } catch (SQLException e) {
                                        System.out.println("Erro " + e.getMessage());
                                    }
                                }else{
                                    System.out.println("Sexo Invalido!");
                                }
                            } else {
                                System.out.print("Data de nascimento invalida!");
                            }
                        }else{
                            System.out.print("Data invalida!");
                        }
                    }else{
                        System.out.println("CPF Invalido!");
                    }
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    //1 BUG
    private static void menuOpcao3(Scanner sc) throws InterruptedException {
        boolean back = false;
        while (!back) {
            System.out.println("#########      Suporte     ########");
            System.out.println("===================================");
            System.out.println("##### 1 - Falar com o Suporte #####");
            System.out.println("##### 0 - Sair                #####");
            System.out.println("===================================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.print("\nDigite o seu CPF: ");
                    String cpf = sc.next();
                    if (isValidCPF(cpf)) {
                        System.out.print("\nDigite seu nome: ");
                        String nome = sc.next();
                        sc.nextLine();
                        //BUG AO MANDAR PARA O BANCO DE DADOS. ENVIA APENAS A PRIMEIRA PALAVRA.
                        System.out.print("Nos fale sobre o seu problema: ");
                        String descricao = sc.next();
                        sc.nextLine();
                        try {
                            suporteNegocio.insertSuport(cpf, cpf, descricao, nome);
                        } catch (Exception e) {
                            System.out.println("Erro " + e.getMessage());
                        }
                    }
                    break;
                case 0:
                    back = true;
                    break;
            }

        }
    }

    //OK
    private static void menuOpcao4(Scanner sc) throws InterruptedException {
        boolean back = false;
        while (!back) {
            System.out.println("\n#########    ADMIN    ########");
            System.out.println("==============================");
            System.out.println("##### 1 - Login          #####");
            System.out.println("##### 0 - Sair           #####");
            System.out.println("==============================");
            System.out.print("##### Escolha uma opção: ");
            int choice = sc.nextInt();
            clearScreen();
            switch (choice) {
                case 1:
                    System.out.print("Matricula: ");
                    matricula = sc.next();
                    System.out.print("Senha: ");
                    senha = sc.next();
                    if (isValidMatricula(matricula) && isValidSenha(senha)) {
                        if (adminNegocio.verificarCredenciais(matricula, senha)) {
                            boolean backControl = false;
                            while (!backControl) {
                                System.out.println("\nLogin bem-sucedido! Bem-vindo à área administrativa.");
                                System.out.println("\n#########        Controle      ########");
                                System.out.println("=======================================");
                                System.out.println("##### 1 - Gerenciar Produto       #####");
                                System.out.println("##### 2 - Gerenciar Funcionário   #####");
                                System.out.println("##### 3 - Gerenciar Cliente       #####");
                                System.out.println("##### 4 - Gerenciar Vendas        #####");
                                System.out.println("##### 0 - Sair                    #####");
                                System.out.println("=======================================");
                                System.out.print("##### Escolha uma opção: ");
                                int choiceControl = sc.nextInt();
                                clearScreen();
                                switch (choiceControl) {
                                    //OK
                                    case 1:
                                        boolean backProd = false;
                                        while (!backProd){
                                            System.out.println("#########   Gerenciar Produto   ########");
                                            System.out.println("========================================");
                                            System.out.println("##### 1 - Ver Produtos             #####");
                                            System.out.println("##### 2 - Adicionar Produtos       #####");
                                            System.out.println("##### 3 - Modificar Produtos       #####");
                                            System.out.println("##### 0 - Sair                     #####");
                                            System.out.println("========================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceProd = sc.nextInt();
                                            clearScreen();
                                            switch (choiceProd) {
                                                case 1:
                                                    System.out.print("Precisa de alguma categoria especifica? 1 - SIM | 2 - NÃO: ");
                                                    int escolha = sc.nextInt();
                                                    GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
                                                    if (escolha == 1) {
                                                        System.out.println("\n## Escolha uma Categoria ##");
                                                        System.out.println("===========================");
                                                        System.out.println("## 1 = Lanche            ##");
                                                        System.out.println("## 2 = Acompanhamentos   ##");
                                                        System.out.println("## 3 = Bebidas           ##");
                                                        System.out.println("===========================\n");
                                                        boolean encontrado = true;
                                                        do {
                                                            try {
                                                                System.out.print("Digite a Categoria: ");
                                                                int categoriaEscolha = sc.nextInt();
                                                                if (categoriaEscolha == 1) {
                                                                    categoria = "Lanche";
                                                                    produtoNegocio.searchByCategory(categoria);
                                                                    gerenciadorLanches.viewProductsSequenceLanche();
                                                                } else if (categoriaEscolha == 2) {
                                                                    categoria = "Acompanhamento";
                                                                    produtoNegocio.searchByCategory(categoria);
                                                                    gerenciadorLanches.viewProductsSequenceAcompanhamento();
                                                                } else if (categoriaEscolha == 3) {
                                                                    categoria = "Bebida";
                                                                    produtoNegocio.searchByCategory(categoria);
                                                                    gerenciadorLanches.viewProductsSequenceBebida();
                                                                } else {
                                                                    System.out.println("Categoria não encontrada!");
                                                                    encontrado = false;
                                                                }
                                                            } catch (Exception e) {
                                                                System.out.println("Erro " + e.getMessage());
                                                            }
                                                        } while (!encontrado);
                                                    } else if (escolha == 2) {
                                                        try {
                                                            produtoNegocio.searchAll();
                                                            gerenciadorLanches.sequenciaTodos();
                                                        } catch (Exception e) {
                                                            System.out.println("Erro " + e.getMessage());
                                                        }
                                                    } else {
                                                        System.out.println("Opção Inválida!");
                                                    }
                                                    System.out.println("Tecle ENTER para Sair...");
                                                    break;
                                                case 2:
                                                    System.out.println("\n## Escolha uma Categoria ##");
                                                    System.out.println("===========================");
                                                    System.out.println("## 1 = Lanche            ##");
                                                    System.out.println("## 2 = Acompanhamentos   ##");
                                                    System.out.println("## 3 = Bebidas           ##");
                                                    System.out.println("===========================\n");
                                                    int codigo = 0;
                                                    do {
                                                        System.out.print("Digite a Categoria: ");
                                                        int categoriaEscolha = sc.nextInt();
                                                        if (categoriaEscolha == 1) {
                                                            categoria = "Lanche";
                                                            codigo = 1;
                                                        } else if (categoriaEscolha == 2) {
                                                            categoria = "Acompanhamento";
                                                            codigo = 2;
                                                        } else if (categoriaEscolha == 3) {
                                                            categoria = "Bebida";
                                                            codigo = 3;
                                                        } else {
                                                            System.out.println("Categoria não encontrada!");
                                                            encontrado = false;
                                                        }
                                                    } while (!encontrado);
                                                    System.out.print("Digite o Nome: ");
                                                    nome = sc.next();
                                                    System.out.print("Digite a Descrição: ");
                                                    descricao = sc.next();
                                                    System.out.print("Digite o Preço: ");
                                                    preco = sc.nextDouble();
                                                    System.out.print("Digite a Quantidade: ");
                                                    quantidade = sc.nextInt();
                                                    try {
                                                        Produto produto = new Produto(codigo, categoria, nome, descricao, preco, quantidade);
                                                        produtoNegocio.insertProduct(produto);
                                                        System.out.println("\nProduto Adicionado com Sucesso!");
                                                        System.out.print("\nTecle ENTER para sair");
                                                    } catch (Exception e) {
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("\nDigite o ID do produto: ");
                                                    int id = sc.nextInt();
                                                    System.out.print("Digite o Novo Nome: ");
                                                    nome = sc.next();
                                                    System.out.print("Digite a Nova Descrição: ");
                                                    descricao = sc.next();
                                                    System.out.print("Digite o Novo Preço: ");
                                                    preco = sc.nextDouble();
                                                    System.out.print("Digite a Nova Quantidade: ");
                                                    quantidade = sc.nextInt();
                                                    try {
                                                        Produto produto = new Produto(id, nome, descricao, preco, quantidade);
                                                        System.out.println("\nProduto Atualizado com Sucesso!");
                                                        System.out.println("\nTecle ENTER para sair");
                                                        produtoNegocio.updateProduct(produto);
                                                    }catch (Exception e){
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 0:
                                                    backProd = true;
                                                    break;
                                            }
                                        }
                                        break;

                                    //OK
                                    case 2:
                                        boolean backFunc = false;
                                        while (!backFunc) {
                                            System.out.println("\n#######   Gerenciar Funcionário   ######");
                                            System.out.println("========================================");
                                            System.out.println("##### 1 - Cadastrar Funcionário    #####");
                                            System.out.println("##### 2 - Ver Funcionários         #####");
                                            System.out.println("##### 3 - Alterar Funcionários     #####");
                                            System.out.println("##### 4 - Demitir Funcionários     #####");
                                            System.out.println("##### 0 - Sair                     #####");
                                            System.out.println("========================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceFunc = sc.nextInt();
                                            switch (choiceFunc) {
                                                case 1:
                                                    System.out.print("Digite o nome: ");
                                                    nome = sc.next();
                                                    System.out.print("Digite o CPF: ");
                                                    cpf = sc.next();
                                                    if (isValidCPF(cpf)){
                                                        System.out.print("\nDigite o Email: ");
                                                        email = sc.next();
                                                        System.out.print("Digite sua senha: ");
                                                        senha = sc.next();
                                                        System.out.print("Digite o salario: ");
                                                        salario = sc.nextDouble();
                                                        System.out.print("Digite o Telefone: ");
                                                        numero_tele = sc.next();
                                                        Funcionario funcionario = new Funcionario(nome, email, senha, cpf, salario, numero_tele);
                                                        try {
                                                            funcionarioNegocio.insertFuncionario(funcionario);
                                                            System.out.println("\nFuncionario cadastrado com Sucesso!\n");
                                                        }catch (SQLException e){
                                                            System.out.println("Erro " + e.getMessage());
                                                        }
                                                    }else{
                                                        System.out.println("CPF Invalido!");
                                                    }
                                                    break;
                                                case 2:
                                                    System.out.print("Deseja procurar por algum funcionario especifico?\n1 - Sim\n2 - Não\nDigite: ");
                                                    int resp = sc.nextInt();
                                                    if(resp == 1){
                                                        System.out.print("Digite o cpf do funcionario desejado: ");
                                                        cpf = sc.next();
                                                        if (isValidCPF(cpf)){
                                                            try{
                                                                GerenciadorDeFuncionarios gerenciadorDeFuncionarios = new GerenciadorDeFuncionarios();
                                                                funcionarioNegocio.searchById(cpf);
                                                                gerenciadorDeFuncionarios.exibirFuncionariosComSequencia();
                                                            } catch (Exception e){
                                                                System.out.println("Erro " + e.getMessage());
                                                            }
                                                        }else{
                                                            System.out.println("CPF Invalido!");
                                                        }
                                                    }else if (resp == 2){
                                                        try {
                                                            GerenciadorDeFuncionarios gerenciadorFunc = new GerenciadorDeFuncionarios();
                                                            funcionarioNegocio.searchAll();
                                                            gerenciadorFunc.exibirFuncionariosComSequencia();
                                                        } catch (Exception e) {
                                                            System.out.println("Erro " + e.getMessage());
                                                        }
                                                    }else{
                                                        System.out.println("Opção Invalida!");
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.print("Digite o CPF do funcionario: ");
                                                    cpf = sc.next();
                                                    if (isValidCPF(cpf)){
                                                        System.out.print("\nDigite o novo Nome: ");
                                                        nome = sc.next();
                                                        System.out.print("Digite o novo Email: ");
                                                        String email = sc.next();
                                                        System.out.print("Digite sua nova Senha: ");
                                                        senha = sc.next();
                                                        System.out.print("Digite o novo Salario: ");
                                                        double salario = sc.nextDouble();
                                                        System.out.print("Digite o novo Telefone: ");
                                                        String numero_tele = sc.next();
                                                        Funcionario funcionario = new Funcionario(nome, email, senha, cpf, salario, numero_tele);
                                                        try {
                                                            funcionarioNegocio.updateFuncionario(funcionario);
                                                            System.out.println("\nFuncionario atualizado com Sucesso!\n");
                                                        }catch (SQLException e){
                                                            System.out.println("Erro " + e.getMessage());
                                                        }
                                                    }else{
                                                        System.out.println("CPF Invalido!");
                                                    }
                                                    break;
                                                case 4:
                                                    System.out.print("Digite o CPF do funcionario: ");
                                                    cpf = sc.next();
                                                    if (isValidCPF(cpf)){
                                                        try {
                                                            funcionarioNegocio.dropFuncionario(cpf);
                                                            System.out.println("\nFuncionario demitido com Sucesso!\n");
                                                        }catch (SQLException e){
                                                            System.out.println("Erro " + e.getMessage());
                                                        }
                                                    }else{
                                                        System.out.println("CPF Invalido!");
                                                    }
                                                    break;
                                                case 0:
                                                    backFunc = true;
                                                    break;
                                            }
                                        }
                                        break;

                                    //OK
                                    case 3:
                                        boolean backClien = false;
                                        while (!backClien) {
                                            System.out.println("#######    Gerenciar Clientes    ######");
                                            System.out.println("=======================================");
                                            System.out.println("##### 1 - Ver Clientes            #####");
                                            System.out.println("##### 0 - Sair                    #####");
                                            System.out.println("=======================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceClien = sc.nextInt();
                                            switch (choiceClien) {
                                                case 1:
                                                    try {
                                                        GerenciadorDeClientes gerenciadorClien = new GerenciadorDeClientes();
                                                        clienteNegocio.searchAll();
                                                        gerenciadorClien.exibirClientesComSequencia();
                                                    } catch (Exception e) {
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 0:
                                                    backClien = true;
                                                    break;
                                            }
                                        }
                                        break;

                                    //OK
                                    case 4:
                                        boolean backVenda = false;
                                        while (!backVenda) {
                                            System.out.println("########    Gerenciar Vendas    #######");
                                            System.out.println("=======================================");
                                            System.out.println("##### 1 - Relatórios              #####");
                                            System.out.println("##### 0 - Sair                    #####");
                                            System.out.println("=======================================");
                                            System.out.print("##### Escolha uma opção: ");
                                            int choiceVend = sc.nextInt();
                                            GerenciadorDeVendas gerenciadorVendas = new GerenciadorDeVendas();
                                            switch (choiceVend) {
                                                case 1:
                                                    try {
                                                        vendaNegocio.relVenda();
                                                        gerenciadorVendas.sequenciaTodos();
                                                    }catch (SQLException e){
                                                        System.out.println("Erro " + e.getMessage());
                                                    }
                                                    break;
                                                case 0:
                                                    backVenda = true;
                                                    break;
                                            }
                                        }
                                        break;
                                    case 0:
                                        backControl = true;
                                        break;
                                }
                            }
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
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    //OK
    public static boolean isValidMatricula(String matricula) {
        return matricula.matches("\\d{12}"); // Verifica se a matrícula tem exatamente 12 dígitos
    }

    //OK
    public static boolean isValidSenha(String senha) {
        return senha.length() >= 8 && senha.length() <= 20; // Verifica se a senha tem entre 8 e 20 caracteres
    }

    //OK
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

    //OK
    public static void comprarProduto(Scanner sc) {
        try {
            GerenciadorDeProdutos gerenciadorLanches = new GerenciadorDeProdutos();
            System.out.print("\n##### Deseja fazer um pedido | 1 - SIM | 2 - NÃO: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Digite o Código do pedido que deseja: ");
                    codigo = sc.nextInt();
                    if (gerenciadorLanches.viewProducts(codigo)) {
                        System.out.print("\nDigite a quantidade: ");
                        quantidade = sc.nextInt();
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

    //OK
    public static boolean isValidData(String ano, String mes, String dia) {
        Timestamp dataAtual = new Timestamp(System.currentTimeMillis());
        int anoAtual = dataAtual.getDate();
        int mesAtual = dataAtual.getMonth();
        int diaAtual = dataAtual.getDay();
        try {
            int anoInt = Integer.parseInt(ano);
            int mesInt = Integer.parseInt(mes);
            int diaInt = Integer.parseInt(dia);
            if(anoInt <= anoAtual){
                if(mesInt <= mesAtual){
                    if(diaInt <= diaAtual){
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    //OK
    public static boolean isValidCPF(String cpf) {
        Scanner sc = new Scanner(System.in);
        do {
            //Digitar apenas dígitos
            Long cpfLong = Long.valueOf(cpf);
            System.out.print("\nValidando CPF...");
            //O CPF PODE INICIAR COM ZEROS
            if (cpfLong > 0L && cpfLong <= 99999999999L) {
                int vetorCPF[] = new int[11];
                //convertendo a String em um vetor de int
                for (int i = 0; i < cpf.length(); i++) {
                    //Converto cada posição da string em um número
                    vetorCPF[i] = Character.getNumericValue(cpf.charAt(i));
                }
                int sm = (vetorCPF[0] * 10) + (vetorCPF[1] * 9) + (vetorCPF[2] * 8) + (vetorCPF[3] * 7) + (vetorCPF[4] * 6) + (vetorCPF[5] * 5) + (vetorCPF[6] * 4) + (vetorCPF[7] * 3) + (vetorCPF[8] * 2);
                int digito1 = (sm * 10) % 11;
                if (digito1 == 10 || digito1 == 11) {
                    digito1 = 0;
                }
                int sm2 = (vetorCPF[0] * 11) + (vetorCPF[1] * 10) + (vetorCPF[2] * 9) + (vetorCPF[3] * 8) + (vetorCPF[4] * 7) + (vetorCPF[5] * 6) + (vetorCPF[6] * 5) + (vetorCPF[7] * 4) + (vetorCPF[8] * 3) + (digito1 * 2);
                int digito2 = (sm2 * 10) % 11;
                if (digito2 == 10 || digito2 == 11) {
                    digito2 = 0;
                }
                //Verificando se o CPF é válido
                if (digito1 == vetorCPF[9] && digito2 == vetorCPF[10]) {
                    return true;
                } else {
                    System.out.println("\n\n-----------------------------------");
                    System.out.println("\nCPF Inválido!");
                    System.out.println("\n-----------------------------------");
                    return false;
                }
            } else {
                System.out.println("\n\n-----------------------------------");
                System.out.println("\nCPF Inválido!");
                System.out.println("\n-----------------------------------");
                return false;
            }
            //Testa a condição do cpf.
        } while (false);
    }
}
