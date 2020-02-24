import jdk.nashorn.internal.ir.ObjectNode;

import java.lang.reflect.Array;
import java.util.*;


public class State {

    TreeSet<School> schools;
    String stateInitials;

    public State(String s, LinkedHashMap linkedHashMap) {
        this.stateInitials=s;
        ArrayList<LinkedHashMap> temp=(ArrayList<LinkedHashMap>)linkedHashMap.get("schools");
        this.schools=new TreeSet<>(new Comparators.schoolOrder());
        for(LinkedHashMap m:temp){
            schools.add(new School(m));
        }
    }


    public State(TreeSet<School> schools, String stateInitials) {
        this.schools = schools;
        this.stateInitials = stateInitials;
    }

    public State(String stateInitials) {
        this.stateInitials = stateInitials;
        this.schools = new TreeSet<School>(new Comparators.schoolOrder());
    }

    public TreeSet<School> getSchools() {
        return schools;
    }

    public void setSchools(TreeSet<School> schools) {
        this.schools = schools;
    }

    public String getStateInitials() {
        return stateInitials;
    }

    public void setStateInitials(String stateInitials) {
        this.stateInitials = stateInitials;
    }

    public void add(School s){
        if(schools.add(s)){

        }else {
            System.out.println(s+"   ");
        }

    }

    @Override
    public String toString() {
        return "State{" +
                "schools=" + schools +
                ", stateInitials='" + stateInitials + '\'' +
                '}';
    }
}
