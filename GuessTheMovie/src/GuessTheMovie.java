import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class GuessTheMovie {

    public static void main(String[] args) throws IOException {

        BufferedReader movie_list = new BufferedReader(new FileReader("movies.txt"));
        ArrayList<String> movies  = new ArrayList<String>();

        try {
            while (true) {
                String line = movie_list.readLine();
                if (line == null) {
                    break;
                }
                movies.add(line);

            }
        } finally {
            movie_list.close();
        }

        int randomIndex    = (int) (Math.random() * movies.size());
        String randomMovie = movies.get(randomIndex);
        //String randomMovie = "aaaabbbbcccc";
        boolean winner = false;

        Scanner scanner = new Scanner(System.in);

        String goodGuesses  = ".";
        String wrongGuesses = "";

        for (int i = 10; i > 0; i--) {
            String current_guessed = randomMovie.replaceAll("[^" + goodGuesses + "]", "_");

            if (!current_guessed.contains("_")) {
                winner = true;
                break;
            }

            System.out.println("You are guessing: " + current_guessed);
            System.out.println("You have guessed (" + wrongGuesses.length() + ") wrong letters: " + wrongGuesses);

            String guess = scanner.nextLine();

            if (randomMovie.contains(guess)) {
                goodGuesses = goodGuesses += guess;
            } else {
                wrongGuesses = wrongGuesses += guess;
            }
        }
        if (winner) {
            System.out.println("You are the winner bro!");
        } else {
            System.out.println("You are a loser.");
        }
    }
}
