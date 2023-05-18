package example.collections;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GenericSet<Animal> st = new GenericSet<>();
        GenericSet<Animal> st1 = new GenericSet<>();
        Animal a1 = new Animal("animal1",1);
        Animal a2 = new Animal("animal2",2);
        Animal a3 = new Animal("animal3",3);
        Animal a4 = new Animal("animal4",4);
        //INFO: Code for compare two set functionality
        st.add(a1);
        st.add(a2);
        st.add(a3);
        st1.add(a1);
        st1.add(a3);
        st1.add(a2);
        st1.add(a4);
        System.out.println("Set 1:");
        st1.display();
        System.out.println("Set 2:");
        st.display();
        if(st1.equals(st)){
            System.out.println("Both are equal");
        }else {
            System.out.println("Both are not equal");
        }

        int choice=0;
        while(true){
            menu();
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(sc.nextLine());
            if(choice==1){
                System.out.print("Enter id: ");
                int id = Integer.parseInt(sc.nextLine());
                System.out.print("Enter name: ");
                String name = sc.nextLine();
                Animal a= new Animal(name, id);
                if(st1.add(a)){
                    System.out.println("Element added in to set successfully.");
                }else{
                    System.out.println("Element is not added into set.");
                }
            } else if (choice==2) {
                System.out.print("Enter id: ");
                int id = Integer.parseInt(sc.nextLine());
                if(st1.remove(id) !=null){
                    System.out.println("Element with id "+id+" removed successfully.");
                }else{
                    System.out.println("Remove operation failed");
                }
            } else if (choice==3) {
                System.out.print("Enter id: ");
                int id = Integer.parseInt(sc.nextLine());
                if(st1.peek(id)){
                    System.out.println("Element with id "+id+" available in set.");
                }else{
                    System.out.println("Element with id "+id+" not available in set.");
                }
            } else if (choice==4) {
                System.out.println("Size of set: "+st1.size());
            } else if (choice==5) {
                st1.display();
            } else if (choice==6) {
                break;
            } else{
                System.out.println("Enter valid choice.");
            }

        }
    }
    public static void menu(){
        System.out.println("1. Add element in set");
        System.out.println("2. Remove element from set");
        System.out.println("3. Check element with ID available");
        System.out.println("4. Size of set");
        System.out.println("5. Display All elements");
        System.out.println("6. exit");
    }
}