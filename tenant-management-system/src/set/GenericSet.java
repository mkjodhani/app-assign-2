package set;
import java.util.*;

public class GenericSet <T extends Animal>{
    ArrayList<T> set;
    GenericSet() {
        set = new ArrayList<>();
    }

    // Method to add an object of type T to the set
    public boolean add(T obj){
        boolean alreadyExist=false;
        // Loop through the set to check if the object already exists
        for(T t : set){
            if(!alreadyExist){
                if(t.getId() == obj.getId()){
                    alreadyExist = true;
                }
            }
        }
        // If the object does not already exist in the set, add it to the set
        if(!alreadyExist){
            set.add(obj);
            return true;
        }
        // If the object already exists in the set, return false
        return false;
    }
    // Method to remove an object from the set based on its id
    public T remove(int id){
        T tobj;
        // Loop through the set to find the object to be removed
        for(T t : set){
            if(t.getId() == id){
                tobj = t;
                set.remove(tobj); // Remove the object from the set
                return tobj;
            }
        }
        // If the object is not found, return null
        return null;
    }

    // Method to check if an object exists in the set based on its id
    public boolean peek(int id){
        // Loop through the set to find the object exist in set or not
        for(T t : set){
            if(t.getId() == id){
                return true;
            }
        }
        return false;
    }
    // Method to return the size of the set
    public int size(){
        return set.size();
    }

    // Method to display the contents of the set
    public void display(){
        // Loop through the set and print each object and call toString method of object T
        for(T t: set){
            System.out.println(t);
        }
    }

    // Override the equals method to check if two sets are equal
    @Override
    public boolean equals(Object newSet) {
        if (this == newSet) return true;
        if (!(newSet instanceof GenericSet)) return false;
        // If the sets have different sizes, they are not equal
        if(set.size() != ((GenericSet<?>) newSet).size()){
            return false;
        }
        boolean notEqual = false;
        // Loop through the set to compare each object
        for(T t : set){
            if(!((GenericSet<?>) newSet).peek(t.getId())){
                notEqual = true;
            }
        }
        return !notEqual;
    }

}
