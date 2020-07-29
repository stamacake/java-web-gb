package task2;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = Persistence
                .createEntityManagerFactory("gb")
                .createEntityManager();
        FinalEntity finalEntity = new FinalEntity(entityManager);
        Scanner scanner = new Scanner(System.in);
        String command ="";
        while(true){
            command = scanner.nextLine();
            String[] subStr = command.split(" ");
            if(command.startsWith("/showProductsByConsumer")){

                System.out.println(finalEntity.showProductsByConsumer(subStr[1]));
            }
            if(command.startsWith("/showConsumersByProductTitle")){
                System.out.println(finalEntity.showConsumersByProductTitle(subStr[1]));
            }
            if(command.startsWith("/deleteConsumer")){
                System.out.println(finalEntity.deleteConsumer(subStr[1]));
            }
            if(command.startsWith("/deleteProduct")){
                System.out.println(finalEntity.deleteProduct(subStr[1]));
            }
            if(command.startsWith("/buy")){
                finalEntity.buy(Integer.parseInt(subStr[1]), Integer.parseInt(subStr[2]));
            }

        }
    }
}
