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
public class Inscription {
    private int id;
    private String reponsePr;
    private String reponseDe;
    private String reponseTr;
    private String questionPr;
    private String questionDe;
    private String questionTr;
    private String status;
    private int idClub;
    private int idUser;

    public Inscription(int id, String reponsePr, String reponseDe, String reponseTr, String questionPr, String questionDe, String questionTr, String status, int idClub, int idUser) {
        this.id = id;
        this.reponsePr = reponsePr;
        this.reponseDe = reponseDe;
        this.reponseTr = reponseTr;
        this.questionPr = questionPr;
        this.questionDe = questionDe;
        this.questionTr = questionTr;
        this.status = status;
        this.idClub = idClub;
        this.idUser = idUser;
    }

    public Inscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponsePr() {
        return reponsePr;
    }

    public void setReponsePr(String reponsePr) {
        this.reponsePr = reponsePr;
    }

    public String getReponseDe() {
        return reponseDe;
    }

    public void setReponseDe(String reponseDe) {
        this.reponseDe = reponseDe;
    }

    public String getReponseTr() {
        return reponseTr;
    }

    public void setReponseTr(String reponseTr) {
        this.reponseTr = reponseTr;
    }

    public String getQuestionPr() {
        return questionPr;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}
