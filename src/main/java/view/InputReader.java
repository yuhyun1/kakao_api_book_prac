package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputReader {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public String readLine() throws IOException {
        return br.readLine();
    }
}
