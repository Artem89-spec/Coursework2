package pro.sky.java.course2.examinerservice.domain;

import java.util.Objects;

public class Question {
    private String type;
    private String question;
    private String answer;


    public Question() {
    }

    public Question( String type, String question, String answer) {
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String toString() {
        return "Тип вопроса: " + type +  " " + "Вопрос: " + " " + question + " " + "Ответ: " + answer;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other == null || this.getClass() != other.getClass()) {
            return false;
        }
        Question object = (Question) other;
        return Objects.equals(getType(), object.getType())
                && Objects.equals(getQuestion(), object.getQuestion()) && Objects.equals(getAnswer(), object.getAnswer());
    }

    public int hashCode() {
        return Objects.hash(getType(), getQuestion(), getAnswer());
    }
}
