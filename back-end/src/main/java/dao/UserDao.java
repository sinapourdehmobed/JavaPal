package dao;

import com.mongodb.client.MongoCollection;
import dto.BasePaymentDto;

import dto.UserDto;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao implements BaseDao<BasePaymentDto> {
    private static UserDao instance;
    public MongoCollection<Document> users;

    private UserDao(MongoCollection<Document> users){
        this.users = users;
    }


    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao(MongoConnection.getCollection("users"));
        }
        return instance;
    }

    public static UserDao getInstance(MongoCollection<Document> users) {
        instance = new UserDao(users);
        return instance;
    }


    @Override
    public void put(BasePaymentDto basePaymentDto) {
        users.insertOne(basePaymentDto.toDocument());
    }

    @Override
    public BasePaymentDto get(String id) {
        var doc= users.find(new Document("_id", new ObjectId(id))).first();
        return BasePaymentDto.toDto(doc);
    }

    @Override
    public List<BasePaymentDto> getAll() {
        return users.find().into(new ArrayList<>()).stream()
                .map(UserDto::toDto)
                .collect(Collectors.toList());
    }
}
