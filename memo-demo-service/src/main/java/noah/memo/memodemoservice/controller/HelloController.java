package noah.memo.memodemoservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * demo控制器
 *
 * @author Noah
 * @create 2020-01-29 15:13
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping(name = "/world")
    public String helloworld() {
        return "hello world";
    }

}
