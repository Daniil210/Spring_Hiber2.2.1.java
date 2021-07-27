package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.sql.SQLException;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User vasya = new User("Vasya", "Vasechkin", "vasechkin@mail.io");
      User petya = new User("Petya", "Sidorov", "sidorov@mail.io");
      User olga = new User("Olga", "Petrova", "petrova@mail.io");
      User sveta = new User("Svetlana", "Ivanova", "ivanova@mail.io");

      Car volvo = new Car("Volvo", 9);
      Car bmw = new Car("BMW", 325);
      Car suzuki = new Car("Sisuki", 4);
      Car lada = new Car("Ladaa", 21014);

      vasya.setCar(volvo);

      userService.add(vasya);

      petya.setCar(bmw);

      userService.add(petya);

      olga.setCar(suzuki);

      userService.add(olga);

      sveta.setCar(lada);
      
      userService.add(sveta);

      // пользователи с машинами
      for (User user : userService.listUsers()) {

         System.out.println(user + " " + user.getCar());
      }

      System.out.println();
      // достать юзера, владеющего машиной по ее модели и серии
      System.out.println(userService.getUserByCar("BMW", 325));

      System.out.println();
      // нет такого юзера с такой машиной
      try {
         User notFoundUser = userService.getUserByCar("GAZ", 4211);
      } catch (NoResultException e) {
         System.out.println("User not found");
      }

      context.close();
   }
}
