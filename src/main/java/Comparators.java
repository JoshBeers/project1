import java.io.Serializable;
import java.util.Comparator;

public class Comparators {
    public static class schoolOrder implements Comparator<School>, Serializable {

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

}
