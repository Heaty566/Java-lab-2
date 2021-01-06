package helper;

/**
 *
 * @author heaty
 */
public class MenuHelper {

    //menu content
    private String[] hints;
    private int length;
    //count menu options
    private int n = 0;
    private static ScannerCus sc = new ScannerCus();

    //constructor with one arg
    public MenuHelper(int size) {

        //prevent negative size of menu
        if (size < 1) {
            size = 10;
        }
        this.length = size;
        //initialized content menu with fixed size
        hints = new String[size];
    }

    //add content for menu
    public void add(String aHint) {
        if (n < hints.length) {
            hints[n++] = aHint;
        }
    }

    //return user choice
    public int getChoice() {
        int result = 0;

        if (n > 0) {
            for (int i = 0; i < n; i++) {
                System.out.println((i + 1) + " - " + hints[i]);
            }
            System.out.print("Please enter your command: ");
            result = sc.getInt(1, this.length, "Please select your option from " + 1 + " to " + this.length + ": ");
        }

        return result;
    }

}
