package com.ThymeleafDemo.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    String name = "John";
    int number = 42;
    @GetMapping("test")        //www.wiley.com/test.html
    public String testPage(Model model) {

        model.addAttribute("number", number); //the key is always string, value can be anything
        model.addAttribute("firstName", name);

        return "test"; //the html page that should be returned.
    }

    @PostMapping("testForm")
    public String testForm(HttpServletRequest request) {
        name = request.getParameter("formFirstName");
        number = Integer.parseInt(request.getParameter("formNumber"));

        return "redirect:/test";
    }

    @GetMapping("testList")
    public String testList(Model model) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(0);
        numbers.add(10);
        numbers.add(6);
        numbers.add(35);

        model.addAttribute("numberList", numbers);

        // foreach(num in numberList)

        return "testList";
    }

    @GetMapping("testConditional")
    public String testConditional(Model model) {

        model.addAttribute("truth", true);
        model.addAttribute("theNumber", 33);
        model.addAttribute("aString", "testing");

        return "testConditional";
    }
}
