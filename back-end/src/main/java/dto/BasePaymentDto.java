package dto;

import org.bson.Document;

public abstract class BasePaymentDto {

  private String uniqueId;
  public Double amount;
  private String toUser;
  private String fromUser;

  public BasePaymentDto() {
  }

  public BasePaymentDto(String uniqueId, String toUser, String fromUser) {
    this.uniqueId = uniqueId;
    this.toUser = toUser;
    this.fromUser = fromUser;
  }

  public BasePaymentDto(String uniqueId) {
  }

  public String getUniqueId(){
    return uniqueId;
  }

  public String getToUser() {
    return toUser;
  }

  public BasePaymentDto setToUser(String toUser) {
    this.toUser = toUser;
    return this;
  }

  public String getFromUser() {
    return fromUser;
  }

  public BasePaymentDto setFromUser(String fromUser) {
    this.fromUser = fromUser;
    return this;
  }

  public BasePaymentDto setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId;
    return this;
  }

  public BasePaymentDto setAmount(Double amount) {
    this.amount = amount;
    return this;
  }

  public abstract Document toDocument();

  public static BasePaymentDto toDto(Document document){
    if(document.getString("type").equals("cash")){
      return CashPayment.fromDocument(document);
    }else{
      return CreditCardPayment.fromDocument(document);
    }
  }
}
