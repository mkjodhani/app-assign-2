package example.collections;

import java.util.*;

public class GenericSet <T extends Animal>{
    ArrayList<T> set;
    GenericSet() {
        set = new ArrayList<>();
    }

    public boolean add(T obj){
        boolean alreadyExist=false;

        for(T t : set){
            if(!alreadyExist){
                if(t.getId() == obj.getId()){
                    alreadyExist = true;
                }
            }
        }
        if(!alreadyExist){
            set.add(obj);
            return true;
        }
        return false;

    }
    public T remove(int id){
        T tobj;
        for(T t : set){
            if(t.getId() == id){
                tobj = t;
                set.remove(tobj);
                return tobj;
            }
        }
        return null;
    }
    public boolean peek(int id){
        for(T t : set){
            if(t.getId() == id){
                return true;
            }
        }
        return false;
    }
    public int size(){
        return set.size();
    }
    public void display(){
        for(T t: set){
            System.out.println(t);
        }
    }

    @Override
    public boolean equals(Object newSet) {
        if (this == newSet) return true;
        if (!(newSet instanceof GenericSet)) return false;
        if(set.size() != ((GenericSet<?>) newSet).size()){
            return false;
        }
        boolean notEqual = false;
        for(T t : set){
            if(!((GenericSet<?>) newSet).peek(t.getId())){
                notEqual = true;
            }
        }
        return !notEqual;
    }


}
