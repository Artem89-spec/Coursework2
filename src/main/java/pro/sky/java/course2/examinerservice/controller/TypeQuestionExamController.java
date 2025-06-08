package pro.sky.java.course2.examinerservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.java.course2.examinerservice.domain.Question;
import pro.sky.java.course2.examinerservice.service.ExaminerService;

import java.util.Collection;

@RestController
@RequestMapping("/exam/type")
public class TypeQuestionExamController {
    private final ExaminerService examinerService;

    @Autowired
    public TypeQuestionExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/get{amount}")
    public Collection<Question> getQuestionsByType(@RequestParam String type, @PathVariable int amount) {
        return examinerService.getQuestionsByType(type, amount);
    }


}
