package cadastropoo;

import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PessoaFisicaRepo repoFisica = new PessoaFisicaRepo();
        PessoaJuridicaRepo repoJuridica = new PessoaJuridicaRepo();
        int opcao;

        do {
            System.out.println("==================================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("==================================");
            
            // Lê o número digitado logo abaixo das linhas de igualdade
            opcao = Integer.parseInt(scanner.nextLine());

            if (opcao == 0) {
                break; // Sai do programa
            }

            if (opcao >= 1 && opcao <= 5) {
                System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                String tipo = scanner.nextLine().toUpperCase();

                if (opcao == 1) { // INCLUIR
                    System.out.println("Digite o id da pessoa:");
                    int id = Integer.parseInt(scanner.nextLine());
                    
                    System.out.println("Insira os dados...");
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    
                    if (tipo.equals("F")) {
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        System.out.print("Idade: ");
                        int idade = Integer.parseInt(scanner.nextLine());
                        repoFisica.inserir(new PessoaFisica(id, nome, cpf, idade));
                    } else if (tipo.equals("J")) {
                        System.out.print("CNPJ: ");
                        String cnpj = scanner.nextLine();
                        repoJuridica.inserir(new PessoaJuridica(id, nome, cnpj));
                    }
                } 
                else if (opcao == 2) { // ALTERAR
                    System.out.println("Digite o id da pessoa:");
                    int id = Integer.parseInt(scanner.nextLine());
                    
                    if (tipo.equals("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) {
                            System.out.println("Insira os novos dados...");
                            System.out.print("Nome: "); String nome = scanner.nextLine();
                            System.out.print("CPF: "); String cpf = scanner.nextLine();
                            System.out.print("Idade: "); int idade = Integer.parseInt(scanner.nextLine());
                            repoFisica.alterar(new PessoaFisica(id, nome, cpf, idade));
                        }
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) {
                            System.out.println("Insira os novos dados...");
                            System.out.print("Nome: "); String nome = scanner.nextLine();
                            System.out.print("CNPJ: "); String cnpj = scanner.nextLine();
                            repoJuridica.alterar(new PessoaJuridica(id, nome, cnpj));
                        }
                    }
                } 
                else if (opcao == 3) { // EXCLUIR
                    System.out.println("Digite o id da pessoa:");
                    int id = Integer.parseInt(scanner.nextLine());
                    
                    if (tipo.equals("F")) repoFisica.excluir(id);
                    else if (tipo.equals("J")) repoJuridica.excluir(id);
                } 
                else if (opcao == 4) { // BUSCAR POR ID
                    System.out.println("Digite o id da pessoa:");
                    int id = Integer.parseInt(scanner.nextLine());
                    
                    if (tipo.equals("F")) {
                        PessoaFisica pf = repoFisica.obter(id);
                        if (pf != null) pf.exibir();
                    } else if (tipo.equals("J")) {
                        PessoaJuridica pj = repoJuridica.obter(id);
                        if (pj != null) pj.exibir();
                    }
                } 
                else if (opcao == 5) { // EXIBIR TODOS
                    if (tipo.equals("F")) {
                        for (PessoaFisica pf : repoFisica.obterTodos()) pf.exibir();
                    } else if (tipo.equals("J")) {
                        for (PessoaJuridica pj : repoJuridica.obterTodos()) pj.exibir();
                    }
                }
            } 
            else if (opcao == 6 || opcao == 7) { // SALVAR OU RECUPERAR
                System.out.print("Prefixo dos arquivos: ");
                String prefixo = scanner.nextLine();
                
                try {
                    if (opcao == 6) {
                        repoFisica.persistir(prefixo + ".fisica.bin");
                        repoJuridica.persistir(prefixo + ".juridica.bin");
                        System.out.println("Dados salvos com sucesso.");
                    } else {
                        repoFisica.recuperar(prefixo + ".fisica.bin");
                        repoJuridica.recuperar(prefixo + ".juridica.bin");
                        System.out.println("Dados recuperados com sucesso.");
                    }
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            }

        } while (opcao != 0);

        scanner.close();
    }
}