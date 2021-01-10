import java.util.Arrays;

public class Canvas {
    private int[] a, b, c, answer;
    private int capacity;

    public Canvas(int count) {
        this.capacity = count;
        this.a = new int[capacity];
        this.b = new int[capacity];
        this.c = new int[capacity];
        this.answer = new int[capacity];

        for (int i = 0; i < count; i++) {
            a[i] = answer[i] = i+1;
        }
    }

    private void deleteLastValue(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i]>0) {
                array[i]=0;
                return;
            }
        }
    }

    public int[] getA() {
        return a;
    }

    public int[] getB() {
        return b;
    }

    public int[] getC() {
        return c;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isCorrectMove(int value) {
        switch (value) {
//          1.А->В;
            case 1 :
                return canBeMoved(a, b);

//          2.А->C;
            case 2 :
                return canBeMoved(a, c);

//          3.C->A;
            case 3:
                return canBeMoved(c, a);

//          4.C->B;
            case 4:
                return canBeMoved(c, b);

//          5.B->A;
            case 5 :
                return canBeMoved(b, a);

//          6.B->C
            case 6 :
                return canBeMoved(b, c);

            default :
                return false;
        }
    }

    private int nextValue(int[] array) throws ArrayIndexOutOfBoundsException {
        for (int i = 0; i < array.length; i++) {
            if (array[i]>0) return array[i];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private int lastFreeSpace(int[] array) throws ArrayIndexOutOfBoundsException {
        if (isEmpty(array)) return array.length-1;
        for (int i = 0; i < array.length; i++) {
            if (array[i]>0) return i-1;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private boolean isEmpty(int[] array) {
        for (int j : array) {
            if (j > 0) return false;
        }
        return true;
    }

    private boolean isFool(int[] array) {
        return array[0]>0;
    }


    private boolean canBeMoved(int[] from, int[] to) {
        if (isEmpty(from) || isFool(to)) return false;
        if (isEmpty(to)) return true;
        return nextValue(from)<nextValue(to);
    }

    public void move(int volume) {
        switch (volume) {

//          1.А->В;
            case 1 :
                replace(a, b);
                break;
//          2.А->C;
            case 2 :
                replace(a, c);
            break;
//          3.C->A;
            case 3:
                replace(c, a);
                break;
//          4.C->B;
            case 4:
                replace(c, b);
                break;
//          5.B->A;
            case 5 :
                replace(b, a);
                break;
//          6.B->C
            case 6 :
                replace(b,c);
                break;
            default : ;
        }
    }

    private void replace(int[] from, int[] to) {
        to[lastFreeSpace(to)] = nextValue(from);
        deleteLastValue(from);
    }

    public boolean isExit() {
        for (int i = 0; i < answer.length; i++) {
            if (answer[i]!=b[i]) return false;
        }
        return true;
    }
}
