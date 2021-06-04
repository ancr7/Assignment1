package Assignment1;

public class AssignmentService {

  private ItemModel model;

  AssignmentService() {}

  AssignmentService(ItemModel model) {this.model = model;}

  // calculate tax methods
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
    if (total <= 100) tax += 5;
    else if (total <= 200) tax += 10;
    else tax += (total + tax) * 0.05;
    return tax;
  }

  // method to calculate total cost.
  double calculateCost() {
    double totalCost = model.getPrice() * model.getQuantity();
    double tax = 0;
    if (model.getType().equals(ItemModel.types.RAW)) tax += rawTax(totalCost);
    if (model.getType().equals(ItemModel.types.MANUFACTURED))
      tax += manufacturedTax(totalCost);
    if (model.getType().equals(ItemModel.types.IMPORTED)) tax += importedTax(totalCost);
    return totalCost+tax;
  }

  String extractWord(String inputString, int pos) {
    if (inputString.isEmpty() || pos < 0 || pos >= inputString.length())
      throw new RuntimeException("Invalid input");
    StringBuilder name = new StringBuilder();
    while (pos < inputString.length() && inputString.charAt(pos) != '-') {
      name.append(inputString.charAt(pos++));
    }
    return name.toString();
  }

  // validate input method
  boolean isInputValid(String inputString) {

    // input can have only 1 name argument
    if (inputString.split("-name", -1).length - 1 != 1) {
      System.out.println("Invalid name");
      return false;
    }

    // input can have only 1 price argument
    if (inputString.split("-price", -1).length - 1 != 1) {
      System.out.println("Invalid price");
      return false;
    }

    // input can have only 1 quantity argument
    if (inputString.split("-quantity", -1).length - 1 != 1) {
      System.out.println("Invalid quantity");
      return false;
    }

    // input can have only 1 type argument
    if (inputString.split("-type", -1).length - 1 != 1) {
      System.out.println("Invalid type");
      return false;
    }

    // input should begin with name argument
    if (inputString.indexOf("-name") != 0) {
      System.out.println("Invalid name postion");
      return false;
    }

    String name = extractWord(inputString, inputString.indexOf("-name") + 5).trim();
    String quantity = extractWord(inputString, inputString.indexOf("-quantity") + 9).trim();
    String price = extractWord(inputString, inputString.indexOf("-price") + 6).trim();
    String type = extractWord(inputString, inputString.indexOf("-type") + 5).trim();

    // check name empty
    if (name.isEmpty()) {
      System.out.println("Invalid name");
      return false;
    }
    // check price empty
    if (price.isEmpty()) {
      System.out.println("Invalid price");
      return false;
    }
    // check quantity empty
    if (quantity.isEmpty()) {
      System.out.println("Invalid quantity");
      return false;
    }
    // check type empty
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
    if (!ItemModel.types.contains(type)) {
      System.out.println("Invalid type");
      return false;
    }
    return true;
  }

  // extract input method
  void extractData(String inputString) {
    String name = extractWord(inputString, inputString.indexOf("-name") + 5).trim();
    String quantity = extractWord(inputString, inputString.indexOf("-quantity") + 9).trim();
    String price = extractWord(inputString, inputString.indexOf("-price") + 6).trim();
    String type = extractWord(inputString, inputString.indexOf("-type") + 5).trim();
    // store data into model
    model.setName(name);
    model.setPrice(Double.parseDouble(price));
    model.setQuantity(Integer.parseInt(quantity));
    model.setType(ItemModel.types.valueOf(type.toUpperCase()));
  }

  public double processInput(String inputString) {
    // 1. validate input
    if (!isInputValid(inputString)) return -1;
    // 2. if input is valid, extract input then store into model class
    extractData(inputString);
    // 3. calculate tax and return final data to controller
    return calculateCost();
  }
}
