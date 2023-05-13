import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int commands = sc.nextInt();

        Roster roster = new Roster("Roster");
        
        String name;
        String mod;
        String ass;
        String grade;
        for (int i = 0; i < commands; i++) {
            name = sc.next();
            mod = sc.next();
            ass = sc.next();
            grade = sc.next();

            roster = roster.add(name, mod, ass, grade);
        }

        while (sc.hasNext()) {
            name = sc.next();
            mod = sc.next();
            ass = sc.next();


            System.out.println(roster.getGrade(name, mod, ass));
        }

        sc.close();
    }
}

