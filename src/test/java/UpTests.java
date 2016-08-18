import me.ezapps4.UpTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Created by sheldon on 11/08/2016.
 */


public class UpTests {

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();


    @Test
    public void test1() {


        exit.expectSystemExitWithStatus(100);
        UpTest.main(new String[]{"123.456.789.001", "1521", "xe", null, null, "10"});


    }

    @Test
    public void test2() {


        exit.expectSystemExitWithStatus(100);

        UpTest.main(new String[]{"123.456.789"});


    }

    @Test
    public void test3() {


        exit.expectSystemExitWithStatus(0);

        UpTest.main(new String[]{"192.168.0.213", "11521", "xe"});


    }

    @Test
    public void test4() {


        exit.expectSystemExitWithStatus(0);

        UpTest.main(new String[]{"192.168.0.213", "11521", "xe", "system", "oracle"});


    }

    @Test
    public void test5() {


        exit.expectSystemExitWithStatus(100);

        UpTest.main(new String[]{"192.168.0.213", "11521", "xe", "systemxxxx", "oracle", "10"});


    }

    @Test
    public void test6() {


        exit.expectSystemExitWithStatus(100);

        UpTest.main(new String[]{"192.168.0.213", "11521", "xe", "systemxxxx", "oracle", "10"});


    }

}
