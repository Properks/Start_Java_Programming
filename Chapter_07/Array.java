package Chapter_07;

/**
 * Array
 */
public class Array {

    public static void main(String[] args) {
        Book[] library = new Book[5];

        library[0] = new Book("It starts with us", "Colleen Hoover", "N001"); // N001 -> novel 1st
        library[1] = new Book("It ends with us", "Colleen Hoover", "N002");
        library[2] = new Book("Never Never : A twist, angsty romance", "Colleen Hoover, Tarryn Fisher", "N003");
        library[3] = new Book("Worthy Opponents", "Danielle Steel", "N004");
        library[4] = new Book("All about love", "Bell Hooks", "V001"); // v -> vision

        searchPage(library, "Colleen Hoover");
    }
    public static void searchPage(Book[] library, String argument) {
        String temp;
        int count = 0;
        System.out.println("Result : ");
        for (int i = 0; i < library.length; i++) {
            temp = library[i].searchBook(argument);
            if (temp != null) {
                System.out.println(temp);
            }
            else {
                count++;
                continue;
            }
        }
        if (count == library.length) {
            System.out.println("Can't find " + argument);
        }
    }
}