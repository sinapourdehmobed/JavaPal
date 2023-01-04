import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.client.MongoCollection;
import dao.PaymentDao;
import dto.BasePaymentDto;
import dto.CashPayment;
import dto.UserDto;
import dto.SignUpResponseDto;


import static server.Server.processRequest;
import static spark.Spark.*;
import dao.UserDao;
import org.bson.Document;
import server.Server;

import static com.mongodb.client.model.Filters.*;


public class SparkDemo {

    private static Gson gson = new Gson();
    private static UserDao userDao = UserDao.getInstance();
    public static void main(String[] args) {
        port(1234);

        post("/api/sign-up", (req, res) ->{
            String body = req.body();   //records user object: username+password
            System.out.println(body);
            UserDto userDto = gson.fromJson(body, UserDto.class); // username input
            Document isUsernameTaken = userDao.users.find(eq("username", userDto.getUsername())).first();
            // Finds the first username in collection that matches with user inputted name (userDto)
            if(isUsernameTaken != null){
                var signupRes = new SignUpResponseDto(false, "Username is taken");
                return gson.toJson(signupRes);
            }

            var signupRes = new SignUpResponseDto(true, null);
            userDao.put(userDto);
            System.out.println("Total Users: " + userDao.getAll().size());
            return gson.toJson(signupRes);
        });
        post("/makeCashPayment", (req, res ) -> {
            String payment = req.body();
            System.out.println(payment);
            //BasePaymentDto basePaymentDto = gson.fromJson(payment, CashPayment.class);
            processRequest(payment);
            return gson.toJson(payment);
        });
        post("/makeCreditPayment", (req, res) -> {
            String cardPayment = req.body();
            System.out.println(cardPayment);
            processRequest(cardPayment);
            return gson.toJson(cardPayment);
        });
    }
}