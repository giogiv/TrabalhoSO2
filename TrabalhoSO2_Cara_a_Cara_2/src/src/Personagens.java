package src;

public class Personagens {
    
    private String nome;
    private String cabelo;
    private String sexo;
    private String corPele;
    private String corOlhos;
    private String corCabelo;
    private String oculos;
    private String chapeu;

    public Personagens(String nome, String cabelo, String sexo, String corPele, String corOlhos, String corCabelo, String oculos, String chapeu) {
        this.nome = nome;
        this.cabelo = cabelo;
        this.sexo = sexo;
        this.corPele = corPele;
        this.corOlhos = corOlhos;
        this.corCabelo = corCabelo;
        this.oculos = oculos;
        this.chapeu = chapeu;
    }

    Personagens() {
       
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCabelo() {
        return cabelo;
    }

    public void setCabelo(String cabelo) {
        this.cabelo = cabelo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorPele() {
        return corPele;
    }

    public void setCorPele(String corPele) {
        this.corPele = corPele;
    }

    public String getCorOlhos() {
        return corOlhos;
    }

    public void setCorOlhos(String corOlhos) {
        this.corOlhos = corOlhos;
    }

    public String getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(String corCabelo) {
        this.corCabelo = corCabelo;
    }

    public String getOculos() {
        return oculos;
    }

    public void setOculos(String oculos) {
        this.oculos = oculos;
    }

    public String getChapeu() {
        return chapeu;
    }

    public void setChapeu(String chapeu) {
        this.chapeu = chapeu;
    }

}
