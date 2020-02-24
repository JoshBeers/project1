import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.Buffer;

public class OverallThingTests {

    OverallThingy oat;

    @Before
    public void setup(){
        oat= new OverallThingy();
    }

    @Test
    public void testGettingStuff(){
        Assert.assertNotNull(oat.getTSVFromInternets("https://gist.githubusercontent.com/tacksoo/b32d630c2e5c7dcd8ebeb2fc67e9c7ae/raw/72b6bae956dfb3eeb66fa277f63ce8acb784fd01/pell.tsv"));
    }

    @Test
    public void testAddingSchoolWithNewState(){
        School s=new School(0001,"School Name", "Lawrenceville", "GA", "college", 100,100,100);
        oat.addSchool(s);
       // System.out.println(oat.toString());
        Assert.assertEquals(oat.states.get("GA").schools.first(),s);
    }

    @Test
    public void testAddingSchoolWithExistingState(){
        School s=new School(120,"School Name", "Lawrenceville", "GA", "college", 100,100,100);
        School s2=new School(110,"School Name 2", "Lawrenceville", "GA", "college", 100,100,100);
        oat.addSchool(s);
        oat.addSchool(s2);
         System.out.println(oat.states.get("GA").toString());
         Assert.assertEquals("check GA size",2,oat.states.get("GA").schools.size());
        Assert.assertEquals("check first",oat.states.get("GA").schools.first(),s);
        Assert.assertEquals("check second",oat.states.get("GA").schools.last(),s2);
    }


    @Test
    public void testCreatingOverallFromString() throws Exception{
        FileReader f=new FileReader("src/TestFiles/SingleStateTestFile");
        BufferedReader br=new BufferedReader(f);
        String str;
        String hold="";
        while ((str= br.readLine())!=null)
            hold+=str;
        oat=oat.createOverallThingyFromString(hold);

        Assert.assertEquals("reading in length",1,oat.states.size());
        Assert.assertNotNull("created new state", oat.getStates().get("AL"));
    }

    @Test
    public void testCreateOverallFromStringSmallScale() throws Exception{
        FileReader f=new FileReader("src/TestFiles/smallScale");
        BufferedReader br=new BufferedReader(f);
        String str;
        String hold="";
        int numberOfLines=0;
        while ((str= br.readLine())!=null){
            hold+=str+"\n";
            numberOfLines++;
        }
        oat=OverallThingy.createOverallThingyFromString(hold);

        Assert.assertEquals("reading in length",numberOfLines,oat.getTotalSize());
        Assert.assertNotNull("created new state", oat.getStates().get("AL"));
    }

    @Test
    public void testDataWithMultipleStates() throws Exception{
        FileReader f=new FileReader("src/TestFiles/MultiStateTestFile");
        BufferedReader br=new BufferedReader(f);
        String str;
        String hold="";
        int numberOfLines=0;
        while ((str= br.readLine())!=null){
            hold+=str+"\n";
            numberOfLines++;
        }
        oat=OverallThingy.createOverallThingyFromString(hold);

        System.out.println(oat);

        Assert.assertEquals("reading in length",numberOfLines,oat.getTotalSize());
        Assert.assertEquals("number of states",4,oat.states.size());
    }

    @Test
    public void testFullCreatingFromFile() throws Exception{

        FileReader f=new FileReader("src/pell.tsv");
        BufferedReader br=new BufferedReader(f);
        String str;
        String hold="";
        int numberOfLines=0;

        while ((str= br.readLine())!=null){
            hold+=str+"\n";
            numberOfLines++;
        }

        OverallThingy oat=OverallThingy.createOverallThingyFromString(hold);
        Assert.assertEquals("full test based on length",5158,oat.getTotalSize());
    }

    @Test
    public void testCreateFromWeb() {
        OverallThingy oat=OverallThingy.createOverallThingyFromString(OverallThingy.getTSVFromInternets("https://gist.githubusercontent.com/tacksoo/b32d630c2e5c7dcd8ebeb2fc67e9c7ae/raw/72b6bae956dfb3eeb66fa277f63ce8acb784fd01/pell.tsv"));
        Assert.assertEquals("full test based on length",5158,oat.getTotalSize());
    }

    @Test
    public void testWritingJSONFile(){
        OverallThingy oat=OverallThingy.createOverallThingyFromString(OverallThingy.getTSVFromInternets("https://gist.githubusercontent.com/tacksoo/b32d630c2e5c7dcd8ebeb2fc67e9c7ae/raw/72b6bae956dfb3eeb66fa277f63ce8acb784fd01/pell.tsv"));
        System.out.println(oat);
        OverallThingy.createJSONFile("testJSON.json", oat);
    }

    @Test
    public void testReadingJSONFile() throws Exception{
        OverallThingy oat=OverallThingy.loadJSONFile("testJSON.json");
        OverallThingy.createJSONFile("tempJSON.json",oat);
        Assert.assertEquals("testing lenght of deserialized stuff", 5158, oat.getTotalSize());

    }

}
