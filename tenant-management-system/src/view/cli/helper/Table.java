package view.cli.helper;

import java.util.HashMap;

/**
 * @author mkjodhani
 * @version 1.0
 * @project Tenant Management System
 * @since 07/03/23
 */
public class Table {
    private static int padding = 100;
    public enum POSITION{
        LEFT,
        RIGHT
    }
    private HashMap<String,String[]> rows;

    public Table() {
        rows = new HashMap<>();
    }

    public void show(){
        String horizontalLine = "";
        for (int i=0;i<padding;i++){
            horizontalLine += "-";
        }
        String[] keys = rows.keySet().toArray(new String[0]);
        System.out.println(horizontalLine);
        for(int i=keys.length -1;i>=0;i--){
            String key = keys[i];
            String[] values = rows.get(key);
            if(values[0].equals(String.valueOf(POSITION.LEFT))){
                System.out.print(String.format("|%-"+(padding-2)/2+"s|%"+(padding-2)/2+"s|\n",key,values[1]));
            }
            else{
                System.out.print(String.format("|%"+(padding-2)+"s|\n",values[1]));
            }
        }
        System.out.println(horizontalLine);

    }
    public void addRow(String key,String val,POSITION position){
        rows.put(key,new String[]{String.valueOf(position),val});
    }
    public void removeRow(String key){
        rows.remove(key);
    }
}
