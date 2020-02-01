package noah.memo.memodemoservice.controller;

import noah.memo.memodemoservice.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述:
 * demo控制器
 *
 * @author Noah
 * @create 2020-01-29 15:13
 */
@RestController
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping(value = "/hello")
    public String helloworld() {

        System.out.println("hello world print");
        helloService.helloo();

        return "hello world";
    }

}
