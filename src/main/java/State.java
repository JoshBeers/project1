import java.util.Comparator;
import java.util.TreeSet;


public class State {

    TreeSet<School> schools;
    String stateInitials;

    private class schoolOrder implements Comparator<School> {

        public int compare(School o1, School o2) {
            if(o1.getName().compareTo(o2.name)==0) {
                if(o1.city.compareTo(o2.city)==0){
                    return Integer.compare(o1.numStudents,o2.numStudents);
                }



                return o1.city.compareTo(o2.city);
            }
            return o1.getName().compareTo(o2.name);
        }
    }

    public State(String stateInitials) {
        this.stateInitials = stateInitials;
        this.schools = new TreeSet<School>(new schoolOrder());
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
