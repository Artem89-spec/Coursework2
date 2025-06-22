package pro.sky.java.course2.examinerservice.service;
/**
 * В классе Question тебе нужно описать саму сущность, по сути два поля question и answer, создать сеттеры, геттеры,
 * конструктор и переопределить стандартные методы.
 * А вот сами вопросы по условию задания создаются в классе JavaQuestionService, который будет работать с вопросами по Java.
 * Он должен хранить список вопросов и обеспечивать работу с ним.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository questionRepository;
    private final Random random = new Random();

    @Autowired
    public JavaQuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question add(String type, String question, String answer) {
        Question newQuestion = new Question(type, question, answer);
        questionRepository.add(newQuestion);
        return newQuestion;
    }

    @Override
    public Question add(Question question) {
        questionRepository.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        questionRepository.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questionRepository.getAll().stream()
                .filter(question -> question.getType() != null && question.getType().toLowerCase().contains("java"))
                .collect(Collectors.toList());
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> questions = new ArrayList<>(getAll());
        if (questions.isEmpty()) {
            return null;
        }
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }
}
