package con.cloud.threadtest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by sunhaidi on 2019-04-10.
 */
@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("de")
public class ThreadTest {

//    @Autowired
//    GoodsServiceImpl service;

    @Test
    public void test1() {
        ThreadService service = new ThreadService();
        ThreadService service2 = new ThreadService();
        new Thread(() -> service.save()).start();
        try {
            System.out.println("sleep 100");
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> service2.select()).start();

    }

    @Test
    public void test2() {
        ThreadService service = new ThreadService();
        new Thread(() -> {
            service.syn2();
        }).start();
        new Thread(() -> {
            service.syn2();
        }).start();

    }


}
