package view.cli.helper;

import java.util.ArrayList;
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
    private ArrayList<String[]> rows;

    public Table() {
        rows = new ArrayList<>();
    }

    public void show(){
        String horizontalLine = "";
        for (int i=0;i<padding;i++){
            horizontalLine += "-";
        }
        System.out.println(horizontalLine);
        for(int i=0;i<=rows.size() -1;i++){
            String[] row = rows.get(i);
            String key = row[0], value = row[1], position = row[2];
            if(position.equals(String.valueOf(POSITION.LEFT))){
                System.out.print(String.format("|%-"+(padding-2)/2+"s|%"+(padding-2)/2+"s|\n",key,value));
            }
            else{
                System.out.print(String.format("|%"+(padding-2)+"s|\n",value));
            }
        }
        System.out.println(horizontalLine);

    }
    public void addRow(String key,String val,POSITION position){
        rows.add(new String[]{key,val,String.valueOf(position)});
    }
}
