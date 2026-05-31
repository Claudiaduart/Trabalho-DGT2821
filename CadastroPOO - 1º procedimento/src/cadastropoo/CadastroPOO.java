package cadastropoo;

import model.PessoaFisica;
import model.PessoaFisicaRepo;
import model.PessoaJuridica;
import model.PessoaJuridicaRepo;

public class CadastroPOO {

    public static void main(String[] args) {
        try {
            // ================= PESSOA FÍSICA =================
            // 1. Instanciar um repositório de pessoas físicas (repo1)
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            
            // 2. Adicionar duas pessoas físicas
            repo1.inserir(new PessoaFisica(1, "João Silva", "11122233344", 30));
            repo1.inserir(new PessoaFisica(2, "Maria Souza", "55566677788", 25));
            
            // 3. Invocar método de persistência em repo1
            String arquivoFisica = "pessoas_fisicas.bin";
            repo1.persistir(arquivoFisica);
            
            // 4. Instanciar outro repositório de pessoas físicas (repo2)
            PessoaFisicaRepo repo2 = new PessoaFisicaRepo();
            
            // 5. Invocar método de recuperação em repo2
            repo2.recuperar(arquivoFisica);
            
            // 6. Exibir os dados
            System.out.println("Dados de Pessoa Física Recuperados:");
            for (PessoaFisica pf : repo2.obterTodos()) {
                pf.exibir();
                System.out.println(); // Quebra de linha para organização
            }

            // ================= PESSOA JURÍDICA =================
            // 1. Instanciar um repositório de pessoas jurídicas (repo3)
            PessoaJuridicaRepo repo3 = new PessoaJuridicaRepo();
            
            // 2. Adicionar duas pessoas jurídicas
            repo3.inserir(new PessoaJuridica(3, "Tech Solutions Ltda", "12345678000199"));
            repo3.inserir(new PessoaJuridica(4, "Comercial Alpha S.A.", "98765432000111"));
            
            // 3. Invocar método de persistência em repo3
            String arquivoJuridica = "pessoas_juridicas.bin";
            repo3.persistir(arquivoJuridica);
            
            // 4. Instanciar outro repositório de pessoas jurídicas (repo4)
            PessoaJuridicaRepo repo4 = new PessoaJuridicaRepo();
            
            // 5. Invocar método de recuperação em repo4
            repo4.recuperar(arquivoJuridica);
            
            // 6. Exibir os dados
            System.out.println("Dados de Pessoa Jurídica Recuperados:");
            for (PessoaJuridica pj : repo4.obterTodos()) {
                pj.exibir();
                System.out.println(); // Quebra de linha para organização
            }

        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}