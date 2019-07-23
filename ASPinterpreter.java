

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;





class ASPinterpreter
{
    public static void main(String[] args) throws IOException
    {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String a;
        String tot = "";
        do
        {
            a=reader.readLine();
            if(a==null)
            {
                break;
            }
            else
            {
                tot=tot+a;
            }

        }
        while(true);
        String s = "[0,"+tot+"]";



        ResultProcessor r = new ResultProcessor(s);
        s=r.getFullJson();
        //System.out.println(s);
        r.putInArray(s);



        ArrayList<ArrayList<String>> allRes = r.getAllRes();
        UI f=new UI(allRes);

        /*
        System.out.println();
        System.out.println(allRes);
        System.out.println();




        for (int i=0;i<(allRes.size());i++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)
            {
                String exampleOfResultString=allRes.get(i).get(j);
                System.out.println(exampleOfResultString);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                System.out.println(partsOfStr[0]);
                System.out.println(partsOfStr[1]);
                System.out.println(partsOfStr[2]);
                System.out.println(partsOfStr[3]);
                System.out.println(partsOfStr[4]);
                System.out.println(partsOfStr[5]);
                if(partsOfStr[0]=="lec")
                {
                    System.out.println(partsOfStr[6]);
                }
                System.out.println();
                System.out.println();

            }
            System.out.println();
            System.out.println();
            System.out.println();
        }
        */


    }
}











/*


public class GUI  {
    private JButton b1;
    private JPanel p1;

    public GUI()
    {

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public void open()
    {

    }

    public static void main(String[] args)
    {
        JFrame j=new JFrame("GUI");
        j.setContentPane(new GUI().p1);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.pack();
        j.setVisible(true);
    }
}


*/