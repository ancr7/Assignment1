package Assignment1;

public class ItemModel {

  // all member variables
  String name;
  ItemModel.types type;
  double price;
  int quantity;

  enum types {
    RAW,
    MANUFACTURED,
    IMPORTED;

    public static boolean contains(String test) {
      for (ItemModel.types c : ItemModel.types.values()) {
        if (c.name().toLowerCase().equals(test)) return true;
      }
      return false;
    }
  }

  // getter methods
  public double getPrice() {return price; }

  public int getQuantity() { return quantity; }

  public ItemModel.types getType() { return type; }

  // setter methods
  public void setName(String name) { this.name = name; }

  public void setPrice(double price) { this.price = price; }

  public void setQuantity(int quantity) { this.quantity = quantity; }

  public void setType(ItemModel.types type) { this.type = type; }

}
