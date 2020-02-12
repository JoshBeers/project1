import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

public class stateTest {

    State s;

    @Before
    public void setup(){
        s=new State("GA");
    }

    @Test
    public void addSchoolTest(){
        School school=new School(12,"School Name", "Lawrenceville", "GA", "college", 100,100,100);
        School school1=new School(23,"School Name 2", "safdsg", "GA", "collegsafdge", 100,100,100);

        s.add(school);
        Assert.assertEquals("Testing adding one school",s.schools.size(),1);

        s.schools.add(school1);
        Assert.assertEquals("Testing adding a second school",2,s.schools.size());
    }

}
