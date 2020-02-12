import org.junit.Assert;
import org.junit.Test;

public class SchoolTests {

    @Test
    public void testCreateFromString(){
        String testString= "100200\tALABAMA AGRCLTL & MECHL UNIV\tNORMAL\tAL\tPublic 4yr\t\"$18,323,395\"\t\"3,701\"";
        School s= School.createSchoolFromTSString(testString);

        Assert.assertEquals("name test create from string",s.name,"ALABAMA AGRCLTL & MECHL UNIV");
        Assert.assertEquals("city test create from string",s.city,"NORMAL");
        Assert.assertEquals("state test create from string",s.state,"AL");
        Assert.assertEquals("type test create from string",s.type,"Public 4yr");
        Assert.assertEquals("total grant test create from string",s.totalGrant,18323395);
        Assert.assertEquals("total students test create from string",s.numStudents,3701);
        Assert.assertEquals("average grant test create from string",s.averageAmount,4950,1);
    }
}
