public class Programa02 {
    public static void main(String[] args ) {
        for (int lín = 1; lín <= 3; lín++) {
            for (int k = 0; k < (lín -1); k++) {
                System.out.print(" ");}

            System.out.print("\\");

            for (int m = 0; m < (6 - 2*lín); m++) {
                System.out.print(" ");}

            System.out.print("/");

            System.out.println(); }

        for (int lí = 1; lí <= 3; lí++) {
            for (int e = 0; e < (3 - lí); e++) {
                System.out.print(" ");}

            System.out.print("/");

            for (int i = 0; i < (2*lí - 2); i++) {
                System.out.print(" ");}

            System.out.println("\\");
        }}}
