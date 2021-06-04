package Assignment4;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ServiceTest {

  Service service;

  @BeforeAll
  @Test
  void makeServiceObject() {
    service = new Service();
  }

  @Test
  void checkReadDatabase() {
    service.readDatabase();
    assertEquals(2, service.Data.size());
    assert service.Data.peek() != null;
    assertEquals("Rice", service.Data.peek().getName());
  }

  @Test
  void checkCalculateTax() {
    service.readDatabase();
    service.calculateTax();
    assertEquals(1, service.ProcessedData.size());
    assert service.ProcessedData.peek() != null;
    assertEquals(2.33, service.ProcessedData.peek().getTax());
    assert service.ProcessedData.peek() != null;
    assertEquals("Rice", service.ProcessedData.peek().getName());
  }

  @Test
  void checkWriteDatabase() {
    service.readDatabase();
    service.calculateTax();
    service.writeDatabase();
    assertTrue(service.ProcessedData.isEmpty());
  }
}