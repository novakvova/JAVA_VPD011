import dto.CategoryInsert;
import dto.CategoryItem;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    //main
    public static void main(String[] args) {
        //inputData();
        //arrayExample();
        //SortArrayObject();
        String con = "jdbc:mariadb://localhost:3306/java_vpd011";
        String user = "root";
        String password="";
        CategoryService service = new CategoryService(con, user, password);
        //service.insert(new CategoryInsert("Ноутбуки"));
        List<CategoryItem> items = service.getList();
        for(CategoryItem item : items)
        {
            System.out.println(item);
        }
    }

    private static void SortArrayObject()
    {
        Person[] list = {
                new Person("Іван", "Мельник"),
                new Person("Aртур", "Мельник"),
                new Person("Олег", "Мельник"),
                new Person("Оксана", "Рубашка"),
                new Person("Василь", "Юхим"),
                new Person("Діма", "Шлухнок"),
        };
        for(Person item : list)
        {
            System.out.println(item);
        }
        Arrays.sort(list/*, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getLastName().compareTo(p2.getLastName());
            }
        }*/);
        System.out.println("---------Sort list---------");
        for(Person item : list)
        {
            System.out.println(item);
        }
    }
    private static void inputData()
    {
        //int, double, float, short, boolean, byte, char, float, ...
        Scanner input = new Scanner(System.in);
        //sout
        System.out.println("Вказіть значеня a");
        int a = input.nextInt();
        System.out.println("a="+a);
    }

    private static int getRnadomNumber(int min, int max)
    {
        return(int)((Math.random()*(max-min))+min);
    }

    private static void arrayExample()
    {
        final int n=10;
        int [] array=new int[n];
        for(int i=0; i<n; i++)
        {
            array[i]=getRnadomNumber(-10,10);
        }
        for(int item : array)
            System.out.printf("%d\t",item);
        int count =0;
        for(int item : array)
            if(item>=0)
                count++;
        System.out.println("\nCount = "+count);
    }
}
