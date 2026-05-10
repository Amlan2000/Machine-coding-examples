package Services.Impl;

import Entities.Card;
import Entities.User;
import Exceptions.GenericException;
import Services.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserServiceImpl implements UserService {

    public HashMap<String, User> userHashMap=new HashMap<>();
//    public HashMap<String, Card> cardHashMap = new HashMap<>();

    @Override
    public void createUser(String userID, String userName){

        if(userHashMap.containsKey(userID)){
            throw new GenericException("User is already present");
        }

        User user = User.builder().userID(userID).userName(userName).build();
        userHashMap.put(userID,user);

        System.out.println("User: "+userName +" successfully added!\n");

    }

    @Override
    public void addCard(String userID, String cardID, double creditLimit) {
        List<Card> cardList = userHashMap.get(userID).getCardList();

        if(cardList==null){
            cardList = new ArrayList<>();
        }

        for (Card card : cardList) {
            if (card.getCardID().equals(cardID)) {
                throw new GenericException("Card is already present");

            }
        }

        cardList.add(new Card(cardID,creditLimit));
        userHashMap.get(userID).setCardList(cardList);

        System.out.println("Card: "+cardID +" successfully added!\n");

    }

    @Override
    public User getUser(String userID){
        return userHashMap.get(userID);
    }
}
