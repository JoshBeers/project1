import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.ObjectNode;

import java.awt.*;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class School {

    String name,city,state,type;
    int numStudents;
    int OPE_ID;
    long totalGrant;
    double averageAmount;



    public School() {
    }

    public School(int OPE_ID,String name, String city, String state, String type, long totalGrant, int numStudents, double averageAmount) {
        this.OPE_ID=OPE_ID;
        this.name = name;
        this.city = city;
        this.state = state;
        this.type = type;
        this.totalGrant = totalGrant;
        this.numStudents = numStudents;
        this.averageAmount = averageAmount;
    }

    public School(LinkedHashMap set){
        this.OPE_ID=(int)set.get("ope_ID");
        this.name = (String)set.get("name");
        this.city = (String)set.get("city");
        this.state = (String)set.get("state");
        this.type = (String)set.get("type");
        this.totalGrant = (int)set.get("totalGrant");
        this.numStudents = (int)set.get("numStudents");
        this.averageAmount =  (double)set.get("averageAmount");
    }

    public int getOPE_ID() {
        return OPE_ID;
    }

    public void setOPE_ID(int OPE_ID) {
        this.OPE_ID = OPE_ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTotalGrant() {
        return totalGrant;
    }

    public void setTotalGrant(long totalGrant) {
        this.totalGrant = totalGrant;
    }

    public int getNumStudents() {
        return numStudents;
    }

    public void setNumStudents(int numStudents) {
        this.numStudents = numStudents;
    }

    public double getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(double averageAmount) {
        this.averageAmount = averageAmount;
    }


    @Override
    public String toString() {
        return "School{" +
                "OPE_ID='" + OPE_ID + '\'' +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", totalGrant=" + totalGrant +
                ", numStudents=" + numStudents +
                ", averageAmount=" + averageAmount +
                '}';
    }

    public static School createSchoolFromTSString(String school){
        String[] split=school.split("\t");

        long totalGrant=convertToInt(split[5]);
        int numOfStudents=(int)convertToInt(split[6]);
        return new School(Integer.parseInt(split[0]),split[1],split[2],split[3],split[4],totalGrant,numOfStudents,(double)totalGrant/(double)numOfStudents);
    }

    private static long convertToInt(String s){
        s=s.replace(",","");
        s=s.replace("$","");
        s=s.replace("\"","");
        if(s.contains(".")){
            s=s.substring(0,s.length()-3);
        }
       // System.out.println(s);
        return Long.parseLong(s);
    }

    /*
    public static School createSchoolFromJSON(String s){
        ObjectMapper m=new ObjectMapper();
        ObjectNode objectNode =m.readValue(s,ObjectNode.class);

    }

     */


}
