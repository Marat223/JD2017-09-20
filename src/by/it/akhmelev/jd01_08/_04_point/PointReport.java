package by.it.akhmelev.jd01_08._04_point;

public class PointReport {
    public void printReport(Point1D p) {
        System.out.printf("length=%.2f %s%n", p.length(), p);
        // вызовы out.print(p.toString())
        // и out.print(p) для объекта идентичны !
    }
}