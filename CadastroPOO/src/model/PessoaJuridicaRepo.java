package model;

import java.io.*;
import java.util.ArrayList;

public class PessoaJuridicaRepo {
    private ArrayList<PessoaJuridica> listaPessoaJuridica = new ArrayList<>();

    public void inserir(PessoaJuridica pj) {
        listaPessoaJuridica.add(pj);
    }

    public void alterar(PessoaJuridica pj) {
        for (int i = 0; i < listaPessoaJuridica.size(); i++) {
            if (listaPessoaJuridica.get(i).getId() == pj.getId()) {
                listaPessoaJuridica.set(i, pj);
                break;
            }
        }
    }

    public void excluir(int id) {
        listaPessoaJuridica.removeIf(pj -> pj.getId() == id);
    }

    public PessoaJuridica obter(int id) {
        for (PessoaJuridica pj : listaPessoaJuridica) {
            if (pj.getId() == id) {
                return pj;
            }
        }
        return null;
    }

    public ArrayList<PessoaJuridica> obterTodos() {
        return listaPessoaJuridica;
    }

    public void persistir(String arquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(listaPessoaJuridica);
        }
    }

    @SuppressWarnings("unchecked")
    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            listaPessoaJuridica = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}