package assignment1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AssignmentServiceTest {

  @Test
  @DisplayName("When input is Empty")
  void checkIfInputEmpty() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid(""));
  }

  @Test
  @DisplayName("When name format is wrong")
  void checkInputName() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name 233 -price 10 -quantity 2 -type raw"));
  }

  @Test
  @DisplayName("When price format is wrong")
  void checkInputPrice() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price ww -quantity 2 -type raw"));
  }

  @Test
  @DisplayName("When quantity format is wrong")
  void checkInputQuantity() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price 10 -quantity F -type raw"));
  }

  @Test
  @DisplayName("When type format is wrong")
  void checkInputType() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price 10 -quantity 2 -type 35"));
  }

  @Test
  @DisplayName("check price when -ve")
  void checkPriceNonNegative() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price -2 -quantity 1 -type raw"));
  }

  @Test
  @DisplayName("check quantity when -ve")
  void checkQuantityNonNegative() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price 20 -quantity -1 -type raw"));
  }

  @Test
  @DisplayName("check Empty name")
  void checkEmptyName() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name  -price 12 -quantity 2 -type raw"));
  }

  @Test
  @DisplayName("check Empty name")
  void checkEmptyPrice() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price  -quantity 2 -type raw"));
  }

  @Test
  @DisplayName("check Empty quantity")
  void checkEmptyQuantity() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price 12 -quantity  -type raw"));
  }

  @Test
  @DisplayName("check Empty Type")
  void checkEmptyType() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price 12 -quantity 2 -type "));
  }

  @Test
  @DisplayName("check name appear once")
  void nameAppearOnlyOneTime() {
    AssignmentService service = new AssignmentService();
    assertEquals(false,
        service.isInputValid("-name abc -name def -price 12 -quantity 2 abc -type raw"));
  }

  @Test
  @DisplayName("check price appear once")
  void priceAppearOnlyOneTime() {
    AssignmentService service = new AssignmentService();
    assertEquals(false,
        service.isInputValid("-name abc -price 12 -price 12 -quantity 2 abc -type raw"));
  }

  @Test
  @DisplayName("check quantity appear once")
  void quantityAppearOnlyOneTime() {
    AssignmentService service = new AssignmentService();
    assertEquals(false,
        service.isInputValid("-name abc -price 12 -quantity 2 -quantity 2 abc -type raw"));
  }

  @Test
  @DisplayName("check type appear once")
  void typeAppearOnlyOneTime() {
    AssignmentService service = new AssignmentService();
    assertEquals(false,
        service.isInputValid("-name abc -price 12 -quantity 2 abc -type raw -type raw"));
  }

  @Test
  @DisplayName("check name comes first")
  void checkNameComesFirst() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-type raw -name abc -price 10 -quantity 1"));
  }

  @Test
  @DisplayName("check type value is among (raw/imported/manufactured")
  void checkTypeIsValid() {
    AssignmentService service = new AssignmentService();
    assertEquals(false, service.isInputValid("-name abc -price abc -quantity we -type xyz"));
  }

  // Tax Tests

  @Test
  void checkRawTaxInputIsValid() {
    AssignmentService service = new AssignmentService();
    Exception exception = assertThrows(RuntimeException.class, () -> {
      service.rawTax(-1);
    });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void checkManufacturedTaxInputIsValid() {
    AssignmentService service = new AssignmentService();
    Exception exception = assertThrows(RuntimeException.class, () -> {
      service.manufacturedTax(-1);
    });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void checkImportedTaxInputIsValid() {
    AssignmentService service = new AssignmentService();
    Exception exception = assertThrows(RuntimeException.class, () -> {
      service.importedTax(-1);
    });

    String expectedMessage = "Total cant be -ve";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  @DisplayName("check when position is invalid")
  void checkExtractWordEdgeCases() {
    AssignmentService service = new AssignmentService();
    Exception exception = assertThrows(RuntimeException.class, () -> {
      service.extractWord("-name abc -type imported -quantity 2 -price 10", -1);
    });

    String expectedMessage = "Invalid input";
    String actualMessage = exception.getMessage();

    assertTrue(actualMessage.contains(expectedMessage));
  }

  @Test
  void rawTaxWhenTotalIs34() {
    AssignmentService service = new AssignmentService();
    assertEquals(4.25, service.rawTax(34));
  }

  @Test
  void manufacturedTaxWhenTotalIs25() {
    AssignmentService service = new AssignmentService();
    assertEquals(3.6875, service.manufacturedTax(25));
  }

  @Test
  void importedTaxWhenTotalIs90() {
    AssignmentService service = new AssignmentService();
    assertEquals(14, service.importedTax(90));
  }

  @Test
  void importedTaxWhenTotalIs144() {
    AssignmentService service = new AssignmentService();
    assertEquals(24.4, service.importedTax(144));
  }

  @Test
  void importedTaxWhenTotalIs443() {
    AssignmentService service = new AssignmentService();
    assertEquals(68.665, service.importedTax(443));
  }

  @Test
  void isInputValid() {
    AssignmentService service = new AssignmentService();
    assertEquals(true, service.isInputValid("-name abc -price 10.2 -quantity 2 -type raw"));
  }

  @Test
  void checkExtractData() {
    ItemModel model = new ItemModel();
    model.setName("abc");
    model.setPrice(10.0);
    model.setQuantity(2);
    model.setType(ItemModel.types.RAW);

    ItemModel model1 = new ItemModel();
    AssignmentService service = new AssignmentService(model1);
    service.extractData("-name abc -type raw -quantity 2 -price 10");

    assertEquals(model.name, model1.name);
    assertEquals(model.price, model1.price);
    assertEquals(model.quantity, model1.quantity);
    assertEquals(model.type, model1.type);
  }

  @Test
  void checkCalculateCostTypeRaw() {
    ItemModel model = new ItemModel();
    AssignmentService service = new AssignmentService(model);
    service.extractData("-name abc -type raw -quantity 2 -price 10");
    assertEquals(22.5, service.calculateCost());
  }

  @Test
  void checkCalculateCostTypeManufactured() {
    ItemModel model = new ItemModel();
    AssignmentService service = new AssignmentService(model);
    service.extractData("-name abc -type manufactured -quantity 2 -price 10");
    assertEquals(22.95, service.calculateCost());
  }

  @Test
  void checkCalculateCostTypeImported() {
    ItemModel model = new ItemModel();
    AssignmentService service = new AssignmentService(model);
    service.extractData("-name abc -type imported -quantity 2 -price 10");
    assertEquals(27, service.calculateCost());
  }

  @Test
  void processInputInvalid() {
    ItemModel model = new ItemModel();
    AssignmentService service = new AssignmentService(model);
    double total = service.processInput("-name abc -type imported -quantity -2 -price 10");
    assertEquals(-1, total);
  }

  @Test
  void processInputValid() {
    ItemModel model = new ItemModel();
    AssignmentService service = new AssignmentService(model);
    double total = service.processInput("-name abc -type imported -quantity 2 -price 10");
    assertEquals(27, total);
  }
}