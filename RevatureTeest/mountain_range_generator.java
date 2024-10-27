import java.util.Random;

public class mountain_range_generator {

    public static void main(String[] args) {

        String[][] mountain_ranges = {
                {"himalayas", "8848", "asia"},
                {"andes", "6962", "south america"},
                {"alps", "4808", "europe"},
                {"rocky mountains", "4401", "north america"},
                {"atlas mountains", "4167", "africa"},
                {"karakoram", "8611", "asia"},
                {"hindu kush", "7708", "asia"},
                {"pamir mountains", "7492", "asia"},
                {"tian shan", "7439", "asia"},
                {"elburz", "5610", "asia"},
                {"pyrenees", "3404", "europe"}
        };

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(mountain_ranges.length);
            String name = mountain_ranges[index][0];
            String height = mountain_ranges[index][1];
            String location = mountain_ranges[index][2];
            boolean is_pyrenees = name.equals("pyrenees");

            System.out.println("name: " + name +
                    ", height (m): " + height +
                    ", location: " + location +
                    ", is_pyrenees: " + is_pyrenees);
        }
    }
}