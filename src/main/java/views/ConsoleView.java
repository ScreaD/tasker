package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleView implements View {

    BufferedReader br = null;

    public ConsoleView() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void showMenu(String menu) {
        System.out.println(menu);
    }

    public String prompt() {
        String input = null;

        try {
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return input;
    }
}