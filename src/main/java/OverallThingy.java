import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jdk.nashorn.internal.ir.ObjectNode;
import org.apache.commons.io.IOUtils;
import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.net.URL;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class OverallThingy implements Serializable {

    TreeMap<String,State> states;


    public OverallThingy() {
        states= new TreeMap();
    }

    public void addSchool(School school){
        if(states.containsKey(school.state)){
         //   System.out.println("aaaa "+school);
            states.get(school.state).add(school);
        }else{
            //System.out.println("bbbb  "+school);
            State s = new State(school.state);
            s.add(school);
            states.put(s.stateInitials,s);
        }
    }

    public TreeMap<String,State> getStates(){
        return states;
    }

    public int getTotalSize(){
        //System.out.println(states.size());
        int rtn=0;
        for(String s:states.keySet()){
           rtn+= states.get(s).schools.size();
        }
        return rtn;
    }

    public String toString(){
        return states.toString();
    }

    public static String getTSVFromInternets(String urll){
        String s = "";
        try {
            URL url = new URL(urll);
            InputStream is = url.openStream();
            s = IOUtils.toString(is, "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return s;
    }

    public static OverallThingy createOverallThingyFromString(String s){
        OverallThingy rtn= new OverallThingy();
        String[] split=s.split("\n");

        int testCount=0;
        int totalLoop=0;

        for(String str:split){
            totalLoop++;
            try {
                int i=Integer.parseInt(""+str.charAt(0));
                testCount++;
                rtn.addSchool(School.createSchoolFromTSString(str));
            }catch (NumberFormatException e){
          //      System.out.println(e);
            }catch (Exception e){
                System.out.println(e);
            }
        }
      //  System.out.println(totalLoop+"   "+testCount);
        return rtn;
    }

    public static void createJSONFile(String fileLoc,OverallThingy oat){
        File f=new File(fileLoc);
        ObjectMapper om=new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            om.writeValue(f, oat.states);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static OverallThingy loadJSONFile(String filename) throws Exception{
        ObjectMapper mapper=new ObjectMapper();
        File f=new File(filename);


        TreeMap<String, LinkedHashMap> v=(TreeMap<String,LinkedHashMap>)mapper.readValue(f, TreeMap.class);
        TreeMap<String,State> rtn=new TreeMap<>();
        OverallThingy oat=new OverallThingy();

        for(String s:v.keySet()){
            rtn.put(s,new State(s,v.get(s)));

        }
        //System.out.println(rtn);
        oat.states=rtn;
        return oat;
    }



}
