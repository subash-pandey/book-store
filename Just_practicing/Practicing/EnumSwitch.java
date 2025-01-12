enum Level{
    LOW, MEDIUM, HIGH
}

public class EnumSwitch {
    public static void main(String[] args) {
        Level currentLevel = Level.MEDIUM;
        switch (currentLevel){
            case LOW:
                System.out.println("Print Low");
                break;
            case MEDIUM:
                System.out.println("Print Medium");
                break;
            case HIGH:
                System.out.println("Print High");
                break;
            default:
                System.out.println("Print Unknown");
        }
    }
}
