import java.util.Scanner;

class ItemList {
    String _name, _type;
    double _price;
    int _quantity;

    // method to extract word from a sentence from pos till end or '-' appears
    String extractWord(String input_string, int pos) {
        if (input_string.isEmpty() || pos < 0 || pos >= input_string.length())
            throw new RuntimeException("Invalid input");
        StringBuilder name = new StringBuilder();
        while (pos < input_string.length() && input_string.charAt(pos) != '-')
            name.append(input_string.charAt(pos++));
        return name.toString();
    }

    // validation method to check validity of the input string
    boolean isInputValid(String input_string) {

        // input can have only 1 name argument
        if (input_string.split("-name", -1).length - 1 != 1) {
            System.out.println("Invalid name");
            return false;
        }

        // input can have only 1 price argument
        if (input_string.split("-price", -1).length - 1 != 1) {
            System.out.println("Invalid price");
            return false;
        }

        // input can have only 1 quantity argument
        if (input_string.split("-quantity", -1).length - 1 != 1) {
            System.out.println("Invalid quantity");
            return false;
        }

        // input can have only 1 type argument
        if (input_string.split("-type", -1).length - 1 != 1) {
            System.out.println("Invalid type");
            return false;
        }

        // input should begin with name argument
        if (input_string.indexOf("-name") != 0) {
            System.out.println("Invalid name postion");
            return false;
        }

        String name = extractWord(input_string, input_string.indexOf("-name") + 5).trim();
        String quantity = extractWord(input_string, input_string.indexOf("-quantity") + 9).trim();
        String price = extractWord(input_string, input_string.indexOf("-price") + 6).trim();
        String type = extractWord(input_string, input_string.indexOf("-type") + 5).trim();

        // check empty string
        if (name.isEmpty()) {
            System.out.println("Invalid name");
            return false;
        }
        if (price.isEmpty()) {
            System.out.println("Invalid price");
            return false;
        }
        if (quantity.isEmpty()) {
            System.out.println("Invalid quantity");
            return false;
        }
        if (type.isEmpty()) {
            System.out.println("Invalid type");
            return false;
        }
        // name should only have alphabets.
        if (!name.matches("^[ A-Za-z]+$")) {
            System.out.println("Invalid name");
            return false;
        }
        // quantity should only have numbers.
        if (!quantity.matches("[0-9]*") || Integer.parseInt(quantity) <= 0) {
            System.out.println("Invalid quantity");
            return false;
        }
        // price should only have numbers and can have decimal (.) once.
        if (!price.matches("[0-9.]*") || (price.length() - price.replace(".", "").length() > 1)) {
            System.out.println("Invalid price");
            return false;
        }
        // type check.
        if (!type.equals("raw") && !type.equals("manufactured") && !type.equals("imported")) {
            System.out.println("Invalid type");
            return false;
        }
        return true;
    }

    // method to extract data from input to member variables
    void extractData(String input_string) {
        String name = extractWord(input_string, input_string.indexOf("-name") + 5).trim();
        String quantity = extractWord(input_string, input_string.indexOf("-quantity") + 9).trim();
        String price = extractWord(input_string, input_string.indexOf("-price") + 6).trim();
        String type = extractWord(input_string, input_string.indexOf("-type") + 5).trim();
        _name = name;
        _quantity = Integer.parseInt(quantity);
        _price = Double.parseDouble(price);
        _type = type;
    }

    // method to calculate raw tax.
    double rawTax(double total) {
        if (total < 0) throw new RuntimeException("Total cant be -ve");
        double tax = 0;
        tax += total * 12.50 / 100;
        return tax;
    }

    // method to calculate manufactured tax.
    double manufacturedTax(double total) {
        if (total < 0) throw new RuntimeException("Total cant be -ve");
        double tax = 0;
        tax += total * 12.5 / 100;
        tax += (total + tax) * 2.0 / 100;
        return tax;
    }

    // method to calculate imported tax.
    double importedTax(double total) {
        if (total < 0) throw new RuntimeException("Total cant be -ve");
        double tax = total * 0.1;
        if (total <= 100)
            tax += 5;
        else if (total <= 200)
            tax += 10;
        else
            tax += (total + tax) * 0.05;
        return tax;
    }

    // method to calculate total cost.
    double calculateCost() {
        double totalCost = _price * _quantity;
        if (_type.equals("raw"))
            totalCost += rawTax(totalCost);
        if (_type.equals("manufactured"))
            totalCost += manufacturedTax(totalCost);
        if (_type.equals("imported"))
            totalCost += importedTax(totalCost);
        return totalCost;
    }

}

public class Assignment1 {
    public static void main(String[] args) {
        char choice;
        do {
            Scanner input = new Scanner(System.in);
            String input_string = input.nextLine();
            input_string = input_string.trim();

            ItemList item = new ItemList();
            if (item.isInputValid(input_string)) { //validation
                item.extractData(input_string);  // Extracting the data
                System.out.println(item.calculateCost());  // Printing total cost
            }

            System.out.println("Do you want to enter details of any other item (y/n):");
            String input_second = input.nextLine();
            if (input_second.length() > 1 || (input_second.charAt(0) != 'n' && input_second.charAt(0) != 'y')) {
                System.out.println("Invalid Input");
                break;
            } else choice = input_second.charAt(0);

        } while (choice != 'n');
    }
}