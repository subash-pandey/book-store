public class Main {
    public static void main(String[] args) {


        String working = Main.format("%s %s %s", "this", "code", "works");
        System.out.println(working);

    }

    private static String format(String myRegularExpression, String... args){
        int index = 0;
        while (myRegularExpression.matches("%s")){
            myRegularExpression = myRegularExpression.replaceFirst("%s", args[index++]);
        }
        return myRegularExpression;
    }
}