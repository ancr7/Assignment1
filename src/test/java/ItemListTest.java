import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemListTest {

    @Test
    @DisplayName("When input is Empty")
    void checkIfInputEmpty() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid(""));
    }
    @Test
    @DisplayName("When name format is wrong")
    void checkInputName() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name 233 -price 10 -quantity 2 -type raw"));
    }

    @Test
    @DisplayName("When price format is wrong")
    void checkInputPrice() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price ww -quantity 2 -type raw"));
    }

    @Test
    @DisplayName("When quantity format is wrong")
    void checkInputQuantity() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price 10 -quantity F -type raw"));
    }

    @Test
    @DisplayName("When type format is wrong")
    void checkInputType() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price 10 -quantity 2 -type 35"));
    }

    @Test
    @DisplayName("check price when -ve")
    void checkPriceNonNegative() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price -2 -quantity 1 -type raw"));
    }

    @Test
    @DisplayName("check quantity when -ve")
    void checkQuantityNonNegative() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price 20 -quantity -1 -type raw"));
    }

    @Test
    @DisplayName("check name appear once")
    void nameAppearOnlyOneTime() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -name def -price 12 -quantity 2 abc -type raw"));
    }

    @Test
    @DisplayName("check price appear once")
    void priceAppearOnlyOneTime() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price 12 -price 12 -quantity 2 abc -type raw"));
    }
    @Test
    @DisplayName("check quantity appear once")
    void quantityAppearOnlyOneTime() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price 12 -quantity 2 -quantity 2 abc -type raw"));
    }
    @Test
    @DisplayName("check type appear once")
    void typeAppearOnlyOneTime() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price 12 -quantity 2 abc -type raw -type raw"));
    }

    @Test
    @DisplayName("check name comes first")
    void checkNameComesFirst() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-type raw -name abc -price 10 -quantity 1"));
    }

    @Test
    @DisplayName("check type value is among (raw/imported/manufactured")
    void checkTypeIsValid() {
        ItemList item = new ItemList();
        assertEquals(false,item.isInputValid("-name abc -price abc -quantity we -type xyz"));
    }


    // Tax Tests

    @Test
    void checkRawTaxInputIsValid() {
        ItemList item = new ItemList();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            item.rawTax(-1);
        });

        String expectedMessage = "Total cant be -ve";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void checkManufacturedTaxInputIsValid() {
        ItemList item = new ItemList();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            item.manufacturedTax(-1);
        });

        String expectedMessage = "Total cant be -ve";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void checkImportedTaxInputIsValid() {
        ItemList item = new ItemList();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            item.importedTax(-1);
        });

        String expectedMessage = "Total cant be -ve";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("check when position is invalid")
    void checkExtractWordEdgeCases() {
        ItemList item = new ItemList();
        Exception exception = assertThrows(RuntimeException.class, () -> {
            item.extractWord("-name abc -type imported -quantity 2 -price 10",-1);
        });

        String expectedMessage = "Invalid input";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void rawTaxSampleTest1() {
        ItemList item = new ItemList();
        assertEquals(4.25,item.rawTax(34));
    }

    @Test
    void manufacturedTaxSampleTest1() {
        ItemList item = new ItemList();
        assertEquals(3.6875,item.manufacturedTax(25));
    }

    @Test
    void importedTaxSampleTest1() {
        ItemList item = new ItemList();
        assertEquals(14,item.importedTax(90));
    }

    @Test
    void importedTaxSampleTest2() {
        ItemList item = new ItemList();
        assertEquals(24.4,item.importedTax(144));
    }
    @Test
    void importedTaxSampleTest3() {
        ItemList item = new ItemList();
        assertEquals(68.665,item.importedTax(443));
    }
    @Test
    void isInputValidSampleTest() {
        ItemList item = new ItemList();
        assertEquals(true,item.isInputValid("-name abc -price 10.2 -quantity 2 -type raw"));
    }

    @Test
    void checkExtractData() {
        ItemList item1 = new ItemList();
        item1._type = "raw";
        item1._name = "abc";
        item1._quantity = 2;
        item1._price = 10;

        ItemList item2 = new ItemList();
        item2.extractData("-name abc -type raw -quantity 2 -price 10");

        assertEquals(item1._name,item2._name);
        assertEquals(item1._price,item2._price);
        assertEquals(item1._quantity,item2._quantity);
        assertEquals(item1._type,item2._type);
    }
    @Test
    void checkCalculateCostTypeRaw() {
        ItemList item = new ItemList();
        item.extractData("-name abc -type raw -quantity 2 -price 10");
        assertEquals(22.5,item.calculateCost());
    }
    @Test
    void checkCalculateCostTypeManufactured() {
        ItemList item = new ItemList();
        item.extractData("-name abc -type manufactured -quantity 2 -price 10");
        assertEquals(22.95,item.calculateCost());
    }

    @Test
    void checkCalculateCostTypeImported() {
        ItemList item = new ItemList();
        item.extractData("-name abc -type imported -quantity 2 -price 10");
        assertEquals(27,item.calculateCost());
    }

}