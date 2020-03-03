import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.Scanner;
public class Controller {
    public Controller() {

    }
    public void getInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Username: ");
        String s1 = in.nextLine();
        System.out.print("Password: ");
        String s2 = in.nextLine();
        System.out.println(s1);
        System.out.println(s2);
    }
}
