import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


//javac -cp json-simple-1.1.jar: ASPinterpreter.java
//clingo -n 0 timetable.pl --outf=2 | java -cp json-simple-1.1.jar: ASPinterpreter



public class ResultProcessor
{
    private String fullJson="";
    JSONParser parser = new JSONParser();
    ArrayList<ArrayList<String>> allRes = new ArrayList<ArrayList<String>>();
    ArrayList<String> singleRes = new ArrayList<String>();
    private double y=1;//the two coordinates are defined as private (it is not necessary for no, but in larger projects, setting public fields leads to accessibility issues)

    public ResultProcessor(String fullJson)
    {
        this.fullJson=fullJson;
    }


    public void putInArray(String s)
    {

        try{
            Object obj = parser.parse(s);
            JSONArray array1 = (JSONArray)obj;

            //System.out.println();//System.out.println();
            //System.out.println("1111111111111111111111111");
            //System.out.println("1111111111111111111111111");
            String stOb1=array1.get(1).toString();
            //System.out.println(stOb1);
            //System.out.println("1111111111111111111111111");
            //System.out.println("1111111111111111111111111");
            //System.out.println();//System.out.println();

            Object obj2 = parser.parse(stOb1);
            JSONObject array2 = (JSONObject)obj2;

            //System.out.println();//System.out.println();
            //System.out.println("2222222222222222222222222");
            //System.out.println("2222222222222222222222222");
            String stOb2=array2.get("Call").toString();
            //System.out.println(stOb2);
            //System.out.println("2222222222222222222222222");
            //System.out.println("2222222222222222222222222");
            //System.out.println();//System.out.println();


            Object obj3 = parser.parse(stOb2);
            JSONArray array3 = (JSONArray)obj3;

            //System.out.println();//System.out.println();
            //System.out.println("3333333333333333333333333");
            //System.out.println("3333333333333333333333333");
            String stOb3=array3.get(0).toString();
            //System.out.println(stOb3);
            //System.out.println("3333333333333333333333333");
            //System.out.println("3333333333333333333333333");
            //System.out.println();//System.out.println();




            Object obj4 = parser.parse(stOb3);
            JSONObject array4 = (JSONObject)obj4;

            //System.out.println();//System.out.println();
            //System.out.println("4444444444444444444444444");
            //System.out.println("4444444444444444444444444");
            String stOb4="";
            try {
                stOb4=array4.get("Witnesses").toString();
            }
            catch (Exception e)
            {
                System.out.println("UNSATISFIABLE");
                System.exit(0);
            }

            //System.out.println(stOb4);
            //System.out.println("4444444444444444444444444");
            //System.out.println("4444444444444444444444444");
            //System.out.println();//System.out.println();




            int j=0;
            do
            {
                singleRes = new ArrayList<String>();
                Object obj5 = parser.parse(stOb4);
                JSONArray array5 = (JSONArray)obj5;

                //System.out.println();//System.out.println();
                //System.out.println("5555555555555555555555555");
                //System.out.println("5555555555555555555555555");
                String stOb5=null;
                try
                {
                    stOb5=array5.get(j).toString();//////////////////////n from 0 to (nOptimum-1)
                }
                catch(Exception pe)
                {
                    //System.out.println("caught");
                    break;
                }
                //System.out.println(stOb5);
                //System.out.println("5555555555555555555555555");
                //System.out.println("5555555555555555555555555");
                //System.out.println();//System.out.println();
                j++;



                Object obj6 = parser.parse(stOb5);
                JSONObject array6 = (JSONObject)obj6;

                //System.out.println();//System.out.println();
                //System.out.println("6666666666666666666666666");
                //System.out.println("6666666666666666666666666");
                String stOb6=array6.get("Value").toString();
                //System.out.println(stOb6);
                //System.out.println("6666666666666666666666666");
                //System.out.println("6666666666666666666666666");
                //System.out.println();//System.out.println();


                //////////////////////n from nChunks to [nChunks+(nStudents*nTimesteps)]
                Object obj7 = parser.parse(stOb6);
                JSONArray array7 = (JSONArray)obj7;
                String stOb7=null;
                int i=0;
                do
                {
                    try
                    {
                        stOb7=array7.get(i).toString();
                    }
                    catch(Exception pe)
                    {
                        allRes.add(singleRes);
                        //System.out.println("caught");
                        break;
                    }
                    singleRes.add(stOb7);
                    //System.out.println(stOb7);
                    i++;
                }
                while(true);
            }
            while(true);

        }
        catch(ParseException pe)
        {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        }
    }


    public String getFullJson()
    {
        return fullJson;
    }

    public ArrayList<ArrayList<String>> getAllRes()
    {
        return allRes;
    }
}