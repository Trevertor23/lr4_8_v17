package ua.bv.vacations;

import java.util.Scanner;

/** Standart console menu
 * @author Trevertor
 */
public class Menu {
    Scanner inp = new Scanner(System.in);

    /** Contains menu items */
    private String[] menuItems = {};

    private String menuName;

    /** Creates a menu with the specified items
     *
     * @param menu Menu items
     */
    public Menu(String name, String[] menu){
        this.menuItems = menu;
        this.menuName = name;
    }
    /** Prints menu to the console
     *
     * @return A number, representing the chosen menu item
     */
    public byte show(){
        byte index = 0;
        System.out.println("\n--------" + menuName + "--------");
        for(String menu : menuItems){
            System.out.println(++index + ". " + menu);
        }

        return inp.nextByte();
    }
}