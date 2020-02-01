package noah.memo.memodemoservice.service;

import noah.memo.memodemoservice.aop.AopTest;
import org.springframework.stereotype.Service;

/**
 * 描述:
 * hello service
 *
 * @author Noah
 * @create 2020-02-01 19:37
 */
@Service
public class HelloService {

    @AopTest
    public void helloo() {
        System.out.println("hello service");
        return;
    }
}
