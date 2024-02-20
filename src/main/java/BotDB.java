import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class BotDB {
    public static void botDB(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        File file = new File("BotDBFile.txt");
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(s);
            printWriter.close();
        } catch (Exception e) {
            System.out.println("Ошибка!");
        }
    }
}