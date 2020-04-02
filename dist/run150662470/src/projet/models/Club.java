/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

/**
 *
 * @author youssef
 */
public class Club {

    private int id;
    private String nom;
    private String description;
    private int capacite;
    private String path;
    private float moyenneLike;
    private int nbrLike;
    private String nomcategorie;
    private int nbrFoisLike;
    private int categorie_id;
    private String questionPr;
    private String questionDe;
    private String questionTr;

    public String getQuestionPr() {
        return questionPr;
    }

    @Override
    public String toString() {
        return "Club{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", capacite=" + capacite + ", path=" + path + ", moyenneLike=" + moyenneLike + ", nbrLike=" + nbrLike + ", nomcategorie=" + nomcategorie + ", nbrFoisLike=" + nbrFoisLike + ", categorie_id=" + categorie_id + ", questionPr=" + questionPr + ", questionDe=" + questionDe + ", questionTr=" + questionTr + '}';
    }

    public void setQuestionPr(String questionPr) {
        this.questionPr = questionPr;
    }

    public String getQuestionDe() {
        return questionDe;
    }

    public void setQuestionDe(String questionDe) {
        this.questionDe = questionDe;
    }

    public String getQuestionTr() {
        return questionTr;
    }

    public void setQuestionTr(String questionTr) {
        this.questionTr = questionTr;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getNbrFoisLike() {
        return nbrFoisLike;
    }

    public void setNbrFoisLike(int nbrFoisLike) {
        this.nbrFoisLike = nbrFoisLike;
    }

    public Club() {
    }

    public int getNbrLike() {
        return nbrLike;
    }

    public void setNbrLike(int nbrLike) {
        this.nbrLike = nbrLike;
    }

    public Club(int id, String nom, String description, int capacite, String path, float moyenneLike, int nbrLike, String nomcategorie, int nbrFoisLike, int categorie_id, String questionPr, String questionDe, String questionTr) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.capacite = capacite;
        this.path = path;
        this.moyenneLike = moyenneLike;
        this.nbrLike = nbrLike;
        this.nomcategorie = nomcategorie;
        this.nbrFoisLike = nbrFoisLike;
        this.categorie_id = categorie_id;
        this.questionPr = questionPr;
        this.questionDe = questionDe;
        this.questionTr = questionTr;
    }

    public Club(int id, String nom, String description, int capacite, String path, float moyenneLike) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.capacite = capacite;
        this.path = path;
        this.moyenneLike = moyenneLike;
    }

    public Club(int id, String nom, String description, int capacite, float moyenneLike) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.capacite = capacite;
        this.moyenneLike = moyenneLike;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getMoyenneLike() {
        return moyenneLike;
    }

    public void setMoyenneLike(float moyenneLike) {
        this.moyenneLike = moyenneLike;
    }

}
