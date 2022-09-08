package br.ufc.quixada.basicscomponents;

public class Sports {
    private String nome, desc;
    private int age;

    @Override
    public String toString() {
        return "Sports{" +
                "nome='" + nome + '\'' +
                ", desc='" + desc + '\'' +
                ", age=" + age +
                '}';
    }

    public Sports(String nome, String desc, int age) {
        this.nome = nome;
        this.desc = desc;
        this.age = age;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
