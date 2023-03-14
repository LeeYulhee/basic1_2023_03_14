package com.ll.basic1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private int count;

    public HomeController(){
        count = -1;
        people = new ArrayList<>();
    }

    @GetMapping("/home/main")
    @ResponseBody
    public String showMain(){
        return "안녕하세요.";
    }

    @GetMapping("/home/main2")
    @ResponseBody
    public String showMain2(){
        return "반갑습니다.";
    }

    @GetMapping("/home/main3")
    @ResponseBody
    public String showMain3(){
        return "즐거웠습니다.";
    }

    @GetMapping("/home/increase")
    @ResponseBody
    public String showIncrease () {
        count++;
        return "응답 : " + count;
    }

    @GetMapping("/home/plus")
    @ResponseBody
    public int showPlus (@RequestParam int a, int b) {
        return a+b;
    }

    private List<Person> people;

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(@RequestParam String name, @RequestParam int age){
        Person p = new Person(name, age);
        people.add(p);
        return "%d번 사람이 추가되었습니다.".formatted(p.getId());
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> people(){
        return people;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePerson(int id) {
        boolean remove = people.removeIf(person -> person.getId() == id);

        if (remove == false) {
            return "%번 사람이 존재하지 않습니다.".formatted(id);
        }

        return "%번 사람이 삭제되었습니다.".formatted(id);
    }
}

@AllArgsConstructor
@Getter
@ToString
class Person {
    private static int lastId;
    private final int id;
    private final String name;
    private final int age;


    static {
        lastId = 0;
    }

    Person(String name, int age) {
        this(++lastId, name, age);
    }
}