package emprestimo_livros;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Biblioteca {

    private List<Livro> livros = new ArrayList<>();
    private List<Autor> autores = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.adicionarLivrosIniciais();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Deseja ver os livros disponíveis? (S/N): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            biblioteca.listarLivrosDisponiveis();

            System.out.println("\nDigite o ID do livro que deseja emprestar: ");
            int idLivro = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            System.out.println("Digite seu nome:");
            String nomeUsuario = scanner.nextLine();

            biblioteca.realizarEmprestimo(idLivro, nomeUsuario);
        } else {
            System.out.println("Obrigado por visitar a biblioteca. Volte sempre!");
        }

        scanner.close();
    }

    public void adicionarLivrosIniciais() {
        Autor autor1 = new Autor(1, "J.K. Rowling", LocalDate.of(1965, 7, 31));
        Autor autor2 = new Autor(2, "George Orwell", LocalDate.of(1903, 6, 25));
        autores.add(autor1);
        autores.add(autor2);

        livros.add(new Livro(1, "Harry Potter e a Pedra Filosofal", autor1));
        livros.add(new Livro(2, "1984", autor2));
        livros.add(new Livro(3, "A Revolução dos Bichos", autor2));
    }

    public void listarLivrosDisponiveis() {
        System.out.println("--- Livros Disponíveis ---");
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                System.out.println("ID: " + livro.getId() + " | Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor().getNome());
            }
        }
    }

    public void realizarEmprestimo(int idLivro, String nomeUsuario) {
        Livro livroParaEmprestar = null;
        for (Livro livro : livros) {
            if (livro.getId() == idLivro && livro.isDisponivel()) {
                livroParaEmprestar = livro;
                break;
            }
        }

        if (livroParaEmprestar != null) {
            livroParaEmprestar.setDisponivel(false);
            emprestimos.add(new Emprestimo(livroParaEmprestar, nomeUsuario));
            System.out.println("\nLivro '" + livroParaEmprestar.getTitulo() + "' emprestado para " + nomeUsuario + " com sucesso!");
        } else {
            System.out.println("\nLivro não encontrado ou indisponível.");
        }
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
