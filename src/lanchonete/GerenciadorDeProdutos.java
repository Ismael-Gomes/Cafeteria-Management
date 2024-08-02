package lanchonete;

import dominio.Produto;
import java.sql.SQLException;
import java.util.List;
import negocio.ProdutoNegocio;

public class GerenciadorDeProdutos {

    public void viewProductsSequence() {
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        List<Produto> produtos;
        
        try {
            produtos = produtoNegocio.searchAll();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os produtos: " + e.getMessage());
            return;
        }

        int sequencia = 1;
        for (Produto produto : produtos) {
            System.out.println("\nProduto " + sequencia);
            System.out.println("=============================");
            System.out.println(produto.toString());
            sequencia++;
        }
    }
    
    public static void main(String[] args) {
        GerenciadorDeProdutos gerenciadorProdutos = new GerenciadorDeProdutos();
        gerenciadorProdutos.viewProductsSequence();
    }

    public void viewProducts(int id) {
        ProdutoNegocio produtoNegocio = new ProdutoNegocio();
        List<Produto> produtos;

        try {
            produtos = produtoNegocio.searchAll();
        } catch (SQLException e) {
            System.out.println("Erro ao buscar os produtos: " + e.getMessage());
            return;
        }

        boolean encontrado = false;
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                System.out.println("\nComprado com Sucesso!");
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Produto não encontrado!");
        }



    }

}
