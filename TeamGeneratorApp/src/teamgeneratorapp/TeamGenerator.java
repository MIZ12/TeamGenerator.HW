
package teamgeneratorapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author moizb
 */
public class TeamGenerator {
     private static class Person {
        String id;
        String firstName;
        String lastName;
        String email;

        public Person(String id, String firstName, String lastName, String email) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }

        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    private static class Team {
        String name;
        List<Person> members;

        public Team(String name) {
            this.name = name;
            this.members = new ArrayList<>();
        }

        @Override
        public String toString() {
            return name + " : " + members;
        }
    }

    public static void main(String[] args) throws IOException {
        // Step 1: Read the File and Store the Data
        List<Person> allPeople = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/MOCK_DATA.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                allPeople.add(new Person(parts[0], parts[1], parts[2], parts[3]));
            }
        }

        // Step 2: Create Random Teams
        Collections.shuffle(allPeople); // Shuffle the list
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Team team = new Team("Team" + (i + 1));
            for (int j = 0; j < 5; j++) {
                team.members.add(allPeople.remove(0)); // Take the first 5 people for a team, then the next 5 for the next team, and so on
            }
            teams.add(team);
        }

        // Step 3: Display the Teams
        for (Team team : teams) {
            System.out.println(team);
        }
    }
}
    
