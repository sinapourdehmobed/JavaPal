package dto;

import org.bson.Document;
import org.bson.types.ObjectId;

public class UserDto extends BasePaymentDto{
    private String username;
    private String password;

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public Document toDocument() {
        return new Document("username", username)
            .append("password", password);
    }

    public static BasePaymentDto toDto(Document document){
        UserDto user = new UserDto(document.getString("username"),
                                    document.getString("password"));
        return user;
    }
}
