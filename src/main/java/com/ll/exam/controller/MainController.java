package com.ll.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private int increaseNo = -1;

    @RequestMapping("/sbb")
    @ResponseBody
    public String index() {
        System.out.println("성공");
        return "abc";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showGet() {
        return """
                <h1>안녕하세요</h1>
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이 입력" />
                    <input type="submit" value="page2로 POST이동" />
                </form>
                """;
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPageGet(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이:%d</h1>
                <h1>get방식으로 왔습니다</h1>
                """.formatted(age);
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPagePost(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이:%d</h1>
                <h1>post방식으로 왔습니다</h1>
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(int a, int b) {
        return a + b;
    }

    @GetMapping("/increase")
    @ResponseBody
    public int increaseNumber() {
        increaseNo++;
        return increaseNo;
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(Integer dan, Integer limit) {
        if (dan == null) {
            dan = 9;
        }
        if (limit == null) {
            limit = 9;
        }

        final Integer finalDan = dan;
        //스트림 사용 시 외부 객체 형태 타입은 Final선언이 필요함

        return IntStream.rangeClosed(1, limit)//stream 반복문
                .mapToObj(i -> "%d * %d = %d".formatted(finalDan, i, finalDan * i))
                .collect(Collectors.joining("<br>"));//br기준으로 하나의 스트림으로 생성
    }

}
