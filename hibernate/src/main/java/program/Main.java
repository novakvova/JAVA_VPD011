package program;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.transaction.internal.TransactionImpl;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Привіт!");
        //insertRole();
        //showRoles();
        //addQuestion();
        //addUserRole();
        //addProductCategory();
        try(var context = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            var p = new Product();
            p.setId(1);
            var u = new User();
            u.setId(1);
            var basket = new Basket();
            basket.setUser(u);
            basket.setProduct(p);
            basket.setCount(2);
            context.save(basket);
            tx.commit();
        }
    }
    private static void addProductCategory() {
        try(var context = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            Category cat = new Category("Ноутбуки","laptop.jpg", new Date(), false);
            context.save(cat);
            Product p = new Product("HP ZBook Power 15 g8", "Для козаків",
                    new Date(),false, cat);
            context.save(p);
            ProductImage pi = new ProductImage("1.jpg", 1, new Date(),false, p);
            ProductImage pi2 = new ProductImage("2.jpg", 2, new Date(),false, p);
            context.save(pi);
            context.save(pi2);
            tx.commit();
        }
    }
    private static void addUserRole() {
        try(var context = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction tx = context.beginTransaction();
            var role = new Role();
            role.setId(1);
            var user = new User("Марко","Шлунок","shlunok@gmail.com",
                    "+38097 98 393 22","123456");
            context.save(user);
            UserRole ur = new UserRole();
            ur.setUser(user);
            ur.setRole(role);
            context.save(ur);
            tx.commit();
        }
    }
    private static void insertRole() {
        Scanner in = new Scanner(System.in);
        Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        System.out.println("Enter role name:");
        String name = in.nextLine();
        Role role = new Role();
        role.setName(name);
        context.save(role);
        context.close();
    }
    private static void showRoles() {
        Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = context.createQuery("FROM Role");
        List<Role> roles = query.list();
        for (Role role : roles)
        {
            System.out.println(role);
        }
        context.close();
    }

    private static void addQuestion() {
        Scanner in = new Scanner(System.in);
        Session context = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = context.beginTransaction();
        System.out.println("Вкажіть питання:");
        String questionText = in.nextLine();
        Question question = new Question();
        question.setName(questionText);
        context.save(question);
        String action="";
        do {
            System.out.println("Введіть варіанти відповіді:");
            String text = in.nextLine();
            System.out.print("Правильність відповіді(0/1)->_");
            boolean isTrue = Byte.parseByte(in.nextLine())==1? true: false;
            QuestionItem qi = new QuestionItem();
            qi.setText(text);
            qi.setTrue(isTrue);
            qi.setQuestion(question);
            context.save(qi);
            System.out.print("Додати наступний варінат(1-так, 0-вийти)->_");
            action=in.nextLine();
        }while(!action.equals("0"));
        tx.commit();
        context.close();
    }
}
