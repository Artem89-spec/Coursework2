package pro.sky.java.course2.examinerservice.repository;

import pro.sky.java.course2.examinerservice.domain.Question;

import java.util.Collection;

public interface QuestionRepository {
    Question add(Question question);

    Question add(String type, String question, String answer);

    Question remove(Question question);

    Collection<Question> getAll();
}
