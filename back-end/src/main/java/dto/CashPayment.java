package dto;

import org.bson.Document;

public class CashPayment extends BasePaymentDto {

  private String type = "cash";

  public CashPayment() {
  }

  public CashPayment(String uniqueId, Double amount) {
    super(uniqueId);
    this.amount = amount;
  }

  public CashPayment(Double amount) {
    super();
    this.amount = amount;
  }

  @Override
  public Document toDocument() {
    return new Document("amount", amount)
        .append("type", type);
  }

  public static CashPayment fromDocument(Document document) {
    var payment = new CashPayment(document.getDouble("amount"));
    payment.setUniqueId(document.getObjectId("_id").toHexString());
    return payment;
  }
}
