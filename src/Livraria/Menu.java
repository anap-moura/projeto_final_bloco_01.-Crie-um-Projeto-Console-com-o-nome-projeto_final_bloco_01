package Livraria;

import java.util.Scanner;
import java.util.Stack;
import Livraria.util.Cores;
import obras.model.EstoqueLivros;
import obras.model.Obras;

public class Menu {
    public static void main(String[] args) {

        Scanner leia = new Scanner(System.in);
        Stack<Obras> listaLivros = new Stack<>();

        int opcao;

        while (true) {
            System.out.println(Cores.TEXT_CYAN + Cores.ANSI_BLACK_BACKGROUND+"*****************************************************");
            System.out.println("          Seja bem vindo(a) à Livraria APMC!         ");
            System.out.println("            Selecione a opção desejada               ");
            System.out.println("                 no MENU abaixo:                     ");
            System.out.println("*****************************************************");
            System.out.println("                                                     ");
            System.out.println("            1 - Adicionar Livro                      ");
            System.out.println("            2 - Listar todos os livros        	     ");
            System.out.println("            3 - Atualizar livros         			 ");
            System.out.println("            4 - Deletar livro da lista               ");
            System.out.println("            0 - Sair                                 ");
            System.out.println("                                                     ");
            System.out.println("*****************************************************");
            System.out.println("Entre com a opção desejada:                          ");
            System.out.println("                                                     " + Cores.TEXT_RESET);
            
            
            opcao = leia.nextInt();

            try {
                processarOpcao(opcao, listaLivros, leia);
            } catch (Exception e) {
                System.out.println(Cores.TEXT_RED +"Ocorreu um erro: " + e.getMessage());
                leia.nextLine(); 
            }

            if (opcao == 0) {
                System.out.println(Cores.TEXT_CYAN +"\nObrigada por utilizar nossos serviços!");
                sobre();
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
            case 1:
                leia.nextLine();
                System.out.print(Cores.TEXT_CYAN + "Digite o título do livro a ser adicionado: ");
                String tituloLivro = leia.nextLine();
                System.out.print(Cores.TEXT_CYAN + "Digite o autor do livro: ");
                String autorLivro = leia.nextLine();
                System.out.print(Cores.TEXT_CYAN + "Digite a quantidade disponível: ");
                int quantidadeDisponivel = leia.nextInt();

                EstoqueLivros livro = new EstoqueLivros(tituloLivro, autorLivro, quantidadeDisponivel, quantidadeDisponivel);
                listaLivros.push(livro);
                System.out.println(Cores.TEXT_CYAN + "Livro adicionado!\n");
                break;
            case 2:
                if (listaLivros.isEmpty()) {
                    System.out.println(Cores.TEXT_CYAN + "Sua lista está vazia :(\n");
                } else {
                    System.out.println(Cores.TEXT_CYAN + "Lista de Livros:");
                    for (Obras obra : listaLivros) {
                        if (obra instanceof EstoqueLivros) {
                            ((EstoqueLivros) obra).mostrarDetalhes();
                        } else {
                            System.out.println("Tipo de obra desconhecido: " + obra.getClass().getSimpleName());
                        }
                    }
                }
                break;
            case 3:
                leia.nextLine();
                System.out.print(Cores.TEXT_CYAN + "Digite o nome do livro a ser atualizado: ");
                String livroAntigo = leia.nextLine();
                
                // Procurar o livro na pilha
                Obras livroAtualizar = null;
                for (Obras obra : listaLivros) {
                    if (obra.getTitulo().equals(livroAntigo)) {
                        livroAtualizar = obra;
                        break;
                    }
                }

                if (livroAtualizar != null) {
                    System.out.print(Cores.TEXT_CYAN + "Digite o novo título do livro: ");
                    String novoTitulo = leia.nextLine();
                    System.out.print(Cores.TEXT_CYAN + "Digite o novo autor do livro: ");
                    String novoAutor = leia.nextLine();
                    System.out.print(Cores.TEXT_CYAN + "Digite a nova quantidade disponível: ");
                    int novaQuantidade = leia.nextInt();

                    // Atualizar os atributos do livro encontrado
                    livroAtualizar.setTitulo(novoTitulo);
                    livroAtualizar.setAutor(novoAutor);                    

                    if (livroAtualizar instanceof EstoqueLivros) {
                        ((EstoqueLivros) livroAtualizar).setQuantidadeDisponivel(novaQuantidade);
                    }

                    System.out.println(Cores.TEXT_CYAN + "Livro atualizado!\n");
                } else {
                    System.out.println(Cores.TEXT_RED + "Livro não encontrado...\n");
                }
                break;
                case 4:
                    if (listaLivros.isEmpty()) {
                        System.out.println(Cores.TEXT_CYAN + "Lista vazia :( \n");
                    } else {
                        System.out.println(Cores.TEXT_CYAN + "Lista de livros atualizada:");
                        for (int i = 0; i < listaLivros.size(); i++) {
                            System.out.println(i + 1 + ". " + listaLivros.get(i));
                        }

                        boolean entradaValida = false;
                        do {
                            System.out.print(Cores.TEXT_CYAN + "\nDigite o número do livro a ser deletado \n(ou digite 0 para voltar ao Menu principal): ");
                            int numeroLivroDeletado = leia.nextInt();

                            if (numeroLivroDeletado == 0) {
                                entradaValida = true;
                            } else if (numeroLivroDeletado >= 1 && numeroLivroDeletado <= listaLivros.size()) {
                                Obras livroDeletado = listaLivros.remove(numeroLivroDeletado - 1);
                                System.out.println(Cores.TEXT_CYAN + "Livro deletado: " + livroDeletado + "\n");
                                entradaValida = true;
                            } else {
                                System.out.println(Cores.TEXT_RED + "Número inválido... Tente novamente.");
                            }
                        } while (!entradaValida);
                    }
                    break;

                default:
                    System.out.println(Cores.TEXT_RED + "\nOpção Inválida!\n");
                    break;
            }
        }
    }

    private static void processarOpcao(int opcao, Stack<Obras> listaLivros, Scanner leia) {
       
    }

    public static void sobre() {
        System.out.println("Projeto de menu desenvolvido por: Ana Paula Moura");
    }
}
