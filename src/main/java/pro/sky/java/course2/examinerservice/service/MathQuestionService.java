package pro.sky.java.course2.examinerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.repository.QuestionRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MathQuestionService implements QuestionService {
    private QuestionRepository questionRepository;
    private Random random = new Random();

    @Autowired
    public MathQuestionService(QuestionRepository questionRepository) {
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
                .filter(question -> question.getType() != null && question.getType().toLowerCase().contains("math"))
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
