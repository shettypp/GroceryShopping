import java.util.ArrayList;
import java.util.Scanner;

// Abstract class representing basic functionality of a shopping list
abstract class ShoppingList {
    protected ArrayList<GroceryItem> items = new ArrayList<>();
   

    public abstract void addItem(String name);
    public abstract void editItem(int index, String newName);
    public abstract void deleteItem(int index);
    public abstract void markAsPurchased(int index);
    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("The shopping list is empty.");
        } else {
            System.out.println("Shopping List:");
            for (int i = 0; i < items.size(); i++) {
                System.out.println((i + 1) + ". " + items.get(i));
            }
        }
    }
}

// Class representing a single grocery item with encapsulated fields
class GroceryItem {
    private String name;
    private boolean purchased;

    public GroceryItem(String name) {
        this.name = name;
        this.purchased = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void markAsPurchased() {
        this.purchased = true;
    }

    public String toString() {
        return name + (purchased ? " (Purchased)" : "");
    }
}

// Concrete class extending ShoppingList to manage grocery items
class GroceryShoppingList extends ShoppingList {

    // Add item to the list (Abstraction of item details)
    public void addItem(String name) {
        items.add(new GroceryItem(name));
        System.out.println("Item added: " + name);
    }

    // Edit item in the list using polymorphism to update name
    public void editItem(int index, String newName) {
        if (isValidIndex(index)) {
            items.get(index - 1).setName(newName);
            System.out.println("Item updated to: " + newName);
        } else {
            System.out.println("Invalid item number.");
        }
    }

    // Delete item from the list
    public void deleteItem(int index) {
        if (isValidIndex(index)) {
            System.out.println("Removing item: " + items.get(index - 1).getName());
            items.remove(index - 1);
        } else {
            System.out.println("Invalid item number.");
        }
    }

    // Mark item as purchased, demonstrating polymorphism
    public void markAsPurchased(int index) {
        if (isValidIndex(index)) {
            items.get(index - 1).markAsPurchased();
            System.out.println("Item marked as purchased.");
        } else {
            System.out.println("Invalid item number.");
        }
    }

    // Helper method to check index validity
    private boolean isValidIndex(int index) {
        return index > 0 && index <= items.size();
    }
}

// Main class to run the program
public class Main {
    public static void main(String[] args) {
        GroceryShoppingList shoppingList = new GroceryShoppingList();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nGrocery Shopping List:");
            System.out.println("1. Add Item");
            System.out.println("2. Edit Item");
            System.out.println("3. Delete Item");
            System.out.println("4. Mark Item as Purchased");
            System.out.println("5. View Shopping List");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    shoppingList.addItem(scanner.nextLine());
                    break;
                case 2:
                    shoppingList.displayItems();
                    System.out.print("Enter item number to edit: ");
                    int editIndex = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    shoppingList.editItem(editIndex, scanner.nextLine());
                    break;
                case 3:
                    shoppingList.displayItems();
                    System.out.print("Enter item number to delete: ");
                    shoppingList.deleteItem(scanner.nextInt());
                    break;
                case 4:
                    shoppingList.displayItems();
                    System.out.print("Enter item number to mark as purchased: ");
                    shoppingList.markAsPurchased(scanner.nextInt());
                    break;
                case 5:
                    shoppingList.displayItems();
                    break;
                case 6:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
}