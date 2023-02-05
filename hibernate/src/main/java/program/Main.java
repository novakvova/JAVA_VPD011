package program;

import models.Question;
import models.QuestionItem;
import models.Role;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Привіт!");
        //insertRole();
        //showRoles();
        //addQuestion();
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
