package example.collections;

public class Animal {
    String name;
    int id;
    public static int counter=0;
    Animal(String name,int id){
        this.name =name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Animal{ " +
                "id=" + id +
                ", name='" + name + '\'' +
                " }";
    }
}
