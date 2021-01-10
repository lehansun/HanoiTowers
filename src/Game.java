import java.io.BufferedReader;
import java.io.IOException;

public class Game {
    private Canvas canvas;
    private BufferedReader reader;

    public Game(BufferedReader reader) throws IOException {
        this.reader = reader;
        init();
    }

    public void printCanvas() {
        System.out.println("A\tC\tB");
        System.out.println("---------");

        for (int i = 0; i < canvas.getCapacity(); i++) {
            System.out.println(canvas.getA()[i] + "\t" + canvas.getC()[i] + "\t" + canvas.getB()[i] + "\t");
        }
        System.out.println();
    }

    private boolean isDigit(String line) {
        return line.matches("\\d");
    }

    private int getValue(String input) throws IOException {
        if (isDigit(input)) {
            int value = Integer.parseInt(input);
            if (value>0 && value <7) {
                return value;
            }
        }
        return 0;
    }

    public void init() throws IOException {
        System.out.println("Введите количество ячеек в башне: ");
        String input = reader.readLine();
        int count = getValue(input);
        if (count>0) {
            canvas = new Canvas(count);
        } else {
            System.out.println("Введите число больше нуля!");
            init();
        }
    }

    public void run() throws IOException {
        while (!canvas.isExit()) {
            printCanvas();

            System.out.println("Выберите ход: 1.А->В; 2.А->C; 3.C->A; 4.C->B; 5.B->A; 6.B->C" +
                    "Или введиде \"Q\" для выхода.");
            String input = reader.readLine();
            if (isExit(input)) {
                System.out.println("Quiting...");
                return;
            }
            int move = getValue(input);
            if (canvas.isCorrectMove(move)) {
                canvas.move(move);
            } else {
                System.out.println("Такой ход невозможен. Выберите другой ход.");
                run();
            }
        }
        printCanvas();
        System.out.println("Победа!");
    }

    private boolean isExit(String line) {
        return line.equalsIgnoreCase("Q");
    }

}
