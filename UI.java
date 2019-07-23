import java.awt.event.*;
import java.io.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.*;



public class UI extends JFrame implements ActionListener
{
    private JPanel pContainer=new JPanel(new GridBagLayout());
    private JPanel pTable=new JPanel(new BorderLayout());
    private JLabel title=new JLabel("Timetable");
    private ArrayList<String> allLecturers = new ArrayList<String>();
    private ArrayList<String> allStudents = new ArrayList<String>();
    private ArrayList<Integer> instancesOfStudents = new ArrayList<Integer>();
    private JButton btnAll=new JButton("- ALL -");
    private ArrayList<JButton> btnLecturers = new ArrayList<JButton>();
    private ArrayList<JButton> btnStudents = new ArrayList<JButton>();
    private GridBagConstraints c = new GridBagConstraints();
    private ArrayList<ArrayList<String>> allRes;


    DefaultTableModel dtm = new DefaultTableModel() {
        // make first cell uneditable
        public boolean isCellEditable(int row, int column)
        {
            return true;
        }
    };
    JTable table = new JTable(dtm);

    public UI(ArrayList<ArrayList<String>> allRes)
    {

        super("Timetable");

        this.allRes=allRes;
        setContentPane(pContainer);

        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.BOTH;
        //c.fill = GridBagConstraints.VERTICAL;
        pContainer.add(title, c);


        c.gridx = 0;
        c.gridy = 1;
        pContainer.add(btnAll, c);



        gatherAllLecturers();
        createButtonsForEachLecturer();

        gatherAllStudents();
        createButtonsForEachStudent();





        table.setGridColor(Color.WHITE);
        table.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane( table );


        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = allLecturers.size()+1;
        //pContainer.add(pTable, c);
        pTable.add(scrollPane,BorderLayout.CENTER);







        dtm.setDataVector(new Object[][]{},
                new Object[]{"Monday","Tuesday","Wednesday","Thursday","Friday"});
        addTimesToGui();

        table.getColumn("Monday").setCellRenderer(new TextAreaRenderer());
        table.getColumn("Monday").setCellEditor(new TextAreaEditor());
        table.getColumn("Tuesday").setCellRenderer(new TextAreaRenderer());
        table.getColumn("Tuesday").setCellEditor(new TextAreaEditor());
        table.getColumn("Wednesday").setCellRenderer(new TextAreaRenderer());
        table.getColumn("Wednesday").setCellEditor(new TextAreaEditor());
        table.getColumn("Thursday").setCellRenderer(new TextAreaRenderer());
        table.getColumn("Thursday").setCellEditor(new TextAreaEditor());
        table.getColumn("Friday").setCellRenderer(new TextAreaRenderer());
        table.getColumn("Friday").setCellEditor(new TextAreaEditor());

        table.setRowHeight(80);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = allLecturers.size()+1;
        JScrollPane scroll = new JScrollPane(table);
        getContentPane().add(scroll,c);












        /*TableColumn column = null;
        for (int i = 0; i < 5; i++) {
            column = table.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(200); //sport column is bigger
            } else {
                column.setPreferredWidth(50);
            }
        }


        for (int k=0;k<5;k++)
        {

        }
        */
        btnAll.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200,700);
        setLocationRelativeTo(null);
    }

    private void gatherAllStudents()
    {


        //System.out.println();
        //System.out.println(allRes);
        //System.out.println();

        int lengthOfDay=4;

        int i=allRes.size()-1;
        //for (int i=0;i<(allRes.size());i++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)
            {
                String exampleOfResultString=allRes.get(i).get(j);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                if(partsOfStr[0].equals("stu"))
                {
                    String readStudent=partsOfStr[1];
                    Boolean alreadyOnList=false;
                    for (int k=0;k<(allStudents.size());k++)
                    {
                        if(partsOfStr[0].equals("stu"))
                        {
                            if (partsOfStr[1].equals(allStudents.get(k)))
                            {
                                alreadyOnList=(alreadyOnList)||(true);
                            }
                            else
                            {
                                alreadyOnList=(alreadyOnList)||(false);
                            }
                        }
                    }
                    if (!alreadyOnList)
                    {
                        allStudents.add(readStudent);
                    }
                }
            }
        }
        System.out.println(allStudents);
        instancesOfStudents.add(2);//list.set( 2, "New" );
        instancesOfStudents.add(1);//list.set( 2, "New" );
        instancesOfStudents.add(1);//list.set( 2, "New" );

        System.out.println(instancesOfStudents);


    }

    private void createButtonsForEachStudent()
    {
        for (int j=0;j<allStudents.size();j++)
        {
            btnStudents.add(new JButton(allStudents.get(j)));
            c.gridx = j;
            c.gridy = 4;
            pContainer.add(btnStudents.get(j), c);
            btnStudents.get(j).addActionListener(this);
        }
    }

    private void gatherAllLecturers()
    {
        //System.out.println();
        //System.out.println(allRes);
        //System.out.println();

        int lengthOfDay=4;

        int i=allRes.size()-1;
        //for (int i=0;i<(allRes.size());i++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)
            {
                String exampleOfResultString=allRes.get(i).get(j);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                if(partsOfStr[0].equals("lec"))
                {
                    String readLecturer=partsOfStr[3];
                    Boolean alreadyOnList=false;
                    for (int k=0;k<(allLecturers.size());k++)
                    {
                        if(partsOfStr[0].equals("lec"))
                        {
                            if (partsOfStr[3].equals(allLecturers.get(k)))
                            {
                                alreadyOnList=(alreadyOnList)||(true);
                            }
                            else
                            {
                                alreadyOnList=(alreadyOnList)||(false);
                            }
                        }
                    }
                    if (!alreadyOnList)
                    {
                        allLecturers.add(readLecturer);
                    }
                }
            }
        }
        System.out.println(allLecturers);
    }

    private void createButtonsForEachLecturer()
    {
        for (int j=0;j<allLecturers.size();j++)
        {
            btnLecturers.add(new JButton(allLecturers.get(j)));
            c.gridx = 1+j;
            c.gridy = 1;
            pContainer.add(btnLecturers.get(j), c);
            btnLecturers.get(j).addActionListener(this);
        }
    }


    private void addTimesToGui()
    {

        int lengthOfDay=4;

        int i=allRes.size()-1;

        for (int j=0;j<lengthOfDay;j++)
        {
            String strValOfJ="TIME: "+String.valueOf(j+1);
            dtm.addRow(new Object[]{strValOfJ,strValOfJ,strValOfJ,strValOfJ,strValOfJ});
        }

        //for (int i=0;i<(allRes.size());i++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)
            {
                String exampleOfResultString=allRes.get(i).get(j);
                //System.out.println(exampleOfResultString);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                //System.out.println("parts: "+partsOfStr[0]);
                String tempSlot="";
                if(partsOfStr[0].equals("lec"))
                {
                    tempSlot="TIME: "+partsOfStr[1]+"    ";
                    tempSlot=tempSlot+"(Pref: "+partsOfStr[6]+")\n\n";

                    tempSlot=tempSlot+"Prof  :     "+partsOfStr[3]+"\n";
                    tempSlot=tempSlot+"Unit  :     "+partsOfStr[2]+"\n";

                    tempSlot=tempSlot+"Room  :     "+partsOfStr[4]+"\n";
                    tempSlot=tempSlot+"Space :     "+partsOfStr[5];

                    String tempSlotpr="\n\n"+(String) table.getValueAt((Integer.parseInt(partsOfStr[1])-1)%lengthOfDay,((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                    if (tempSlotpr.length()<10)
                    {
                        tempSlotpr="";
                    }
                    dtm.setValueAt(tempSlot+tempSlotpr, (Integer.parseInt(partsOfStr[1])-1)%lengthOfDay, ((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                    //dtm.addRow(new Object[]{tempSlot,"Tuesday","Wednesday","Thursday","Fridayyyy"});
                }


            }
        }


    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==btnAll)
        {
            System.out.println("ALLLLL");
            selectAll();
        }
        for (int i=0;i<allLecturers.size();i++)
        {
            if(e.getSource()==btnLecturers.get(i))
            {
                selectIfEqualToLecturerName(allLecturers.get(i));
            }
        }

        for (int i=0;i<allStudents.size();i++)
        {
            if(e.getSource()==btnStudents.get(i))
            {
                ArrayList<String> unitName=getUnitsTakenByStudent(allStudents.get(i));
                selectIfStudentHasToTakeLecture(unitName);
            }
        }

    }

    private ArrayList<String> getUnitsTakenByStudent(String stName)
    {









        ArrayList<String> lecturesOfAStudent = new ArrayList<String>();
        ArrayList<String> alreadyVisited = new ArrayList<String>();
        int lengthOfDay=4;

        int i=allRes.size()-1;
        for (int l=0;l<2;l++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)
            {
                String exampleOfResultString=allRes.get(i).get(j);
                System.out.println(exampleOfResultString);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                if(partsOfStr[0].equals("stu"))
                {
                    if (partsOfStr[1].equals(stName))
                    {
                        boolean addIt=true;
                        for (int k=0;k<(alreadyVisited.size());k++)
                        {
                            if (partsOfStr[3]==alreadyVisited.get(k))
                            {
                                addIt=(addIt)&&(false);
                            }
                        }
                        if (addIt)
                        {
                            alreadyVisited.add(partsOfStr[3]);
                            lecturesOfAStudent.add(partsOfStr[3]);
                            break;
                            //System.out.println(partsOfStr[3]);
                        }


                    }

                }
            }
        }

        System.out.println(stName);












        return lecturesOfAStudent;
    }


    private void selectAll()
    {
        int lengthOfDay=4;

        int i=allRes.size()-1;

        for (int j=0;j<lengthOfDay;j++)//for every slot
        {
            String strValOfJ="TIME: "+String.valueOf(j+1);
            dtm.setValueAt(strValOfJ, j, 0);
            dtm.setValueAt(strValOfJ, j, 1);
            dtm.setValueAt(strValOfJ, j, 2);
            dtm.setValueAt(strValOfJ, j, 3);
            dtm.setValueAt(strValOfJ, j, 4);

        }
        //for (int i=0;i<(allRes.size());i++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)//for every slot
            {
                String exampleOfResultString=allRes.get(i).get(j);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                String tempSlot="";
                if(partsOfStr[0].equals("lec"))
                {
                        tempSlot="TIME: "+partsOfStr[1]+"    ";
                        tempSlot=tempSlot+"(Pref: "+partsOfStr[6]+")\n\n";

                        tempSlot=tempSlot+"Prof  :     "+partsOfStr[3]+"\n";
                        tempSlot=tempSlot+"Unit  :     "+partsOfStr[2]+"\n";

                        tempSlot=tempSlot+"Room  :     "+partsOfStr[4]+"\n";
                        tempSlot=tempSlot+"Space :     "+partsOfStr[5];

                    String tempSlotpr="\n\n"+(String) table.getValueAt((Integer.parseInt(partsOfStr[1])-1)%lengthOfDay,((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                    if (tempSlotpr.length()<10)
                    {
                        tempSlotpr="";
                    }
                    dtm.setValueAt(tempSlot+tempSlotpr, (Integer.parseInt(partsOfStr[1])-1)%lengthOfDay, ((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                }
            }
        }
    }

    private void selectIfStudentHasToTakeLecture(ArrayList<String> unitNames)
    {

        System.out.println(unitNames);

        int lengthOfDay=4;

        int i=allRes.size()-1;

        for (int j=0;j<lengthOfDay;j++)//for every slot
        {
            String strValOfJ="TIME: "+String.valueOf(j+1);
            dtm.setValueAt(strValOfJ, j, 0);
            dtm.setValueAt(strValOfJ, j, 1);
            dtm.setValueAt(strValOfJ, j, 2);
            dtm.setValueAt(strValOfJ, j, 3);
            dtm.setValueAt(strValOfJ, j, 4);

        }
        for (int k=0;k<(unitNames.size());k++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)//for every slot
            {
                String exampleOfResultString=allRes.get(i).get(j);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                String tempSlot="";
                if(partsOfStr[0].equals("lec"))
                {
                    //System.out.println(unitNames.get(k));
                    //System.out.println(partsOfStr[2]);


                    if (partsOfStr[2].equals(unitNames.get(k)))
                    {
                        tempSlot="TIME: "+partsOfStr[1]+"    ";
                        tempSlot=tempSlot+"(Pref: "+partsOfStr[6]+")\n\n";

                        tempSlot=tempSlot+"Prof  :     "+partsOfStr[3]+"\n";
                        tempSlot=tempSlot+"Unit  :     "+partsOfStr[2]+"\n";

                        tempSlot=tempSlot+"Room  :     "+partsOfStr[4]+"\n";
                        tempSlot=tempSlot+"Space :     "+partsOfStr[5];

                        String tempSlotpr="\n\n"+(String) table.getValueAt((Integer.parseInt(partsOfStr[1])-1)%lengthOfDay,((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                        if (tempSlotpr.length()<10)
                        {
                            tempSlotpr="";
                        }
                        dtm.setValueAt(tempSlot+tempSlotpr, (Integer.parseInt(partsOfStr[1])-1)%lengthOfDay, ((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                        //dtm.addRow(new Object[]{tempSlot,"Tuesday","Wednesday","Thursday","Fridayyyy"});
                    }
                }
            }
        }

    }


    public void selectIfEqualToLecturerName(String name)
    {
        System.out.println(name);

        int lengthOfDay=4;

        int i=allRes.size()-1;

        for (int j=0;j<lengthOfDay;j++)//for every slot
        {
            String strValOfJ="TIME: "+String.valueOf(j+1);
            dtm.setValueAt(strValOfJ, j, 0);
            dtm.setValueAt(strValOfJ, j, 1);
            dtm.setValueAt(strValOfJ, j, 2);
            dtm.setValueAt(strValOfJ, j, 3);
            dtm.setValueAt(strValOfJ, j, 4);

        }
        //for (int i=0;i<(allRes.size());i++)
        {
            for (int j=0;j<(allRes.get(i).size());j++)//for every slot
            {
                String exampleOfResultString=allRes.get(i).get(j);
                String [] partsOfStr=exampleOfResultString.split(",|\\(|\\)");
                String tempSlot="";
                if(partsOfStr[0].equals("lec"))
                {
                    if (partsOfStr[3].equals(name))
                    {
                        tempSlot="TIME: "+partsOfStr[1]+"    ";
                        tempSlot=tempSlot+"(Pref: "+partsOfStr[6]+")\n\n";

                        tempSlot=tempSlot+"Prof  :     "+partsOfStr[3]+"\n";
                        tempSlot=tempSlot+"Unit  :     "+partsOfStr[2]+"\n";

                        tempSlot=tempSlot+"Room  :     "+partsOfStr[4]+"\n";
                        tempSlot=tempSlot+"Space :     "+partsOfStr[5];

                        String tempSlotpr="\n\n"+(String) table.getValueAt((Integer.parseInt(partsOfStr[1])-1)%lengthOfDay,((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                        if (tempSlotpr.length()<10)
                        {
                            tempSlotpr="";
                        }
                        dtm.setValueAt(tempSlot+tempSlotpr, (Integer.parseInt(partsOfStr[1])-1)%lengthOfDay, ((int)((Integer.parseInt(partsOfStr[1])-1)/lengthOfDay)));
                        //dtm.addRow(new Object[]{tempSlot,"Tuesday","Wednesday","Thursday","Fridayyyy"});
                    }
                }
            }
        }
    }
}









//IMPORTED LIBRARY!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//I DID NOT WRITE THE CODE BELOW

class TextAreaRenderer extends JScrollPane implements TableCellRenderer
{
    JTextArea textarea;

    public TextAreaRenderer() {
        textarea = new JTextArea();
        textarea.setLineWrap(true);
        textarea.setFont(new Font("Courier", Font.PLAIN, 12));
        textarea.setWrapStyleWord(true);
        getViewport().add(textarea);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column)
    {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
            textarea.setForeground(table.getSelectionForeground());
            textarea.setBackground(table.getSelectionBackground());
        } else {
            setForeground(table.getForeground());
            setBackground(table.getBackground());
            textarea.setForeground(table.getForeground());
            textarea.setBackground(table.getBackground());
        }

        textarea.setText((String) value);
        textarea.setCaretPosition(0);
        return this;
    }
}

class TextAreaEditor extends DefaultCellEditor {
    protected JScrollPane scrollpane;
    protected JTextArea textarea;

    public TextAreaEditor() {
        super(new JCheckBox());
        scrollpane = new JScrollPane();
        textarea = new JTextArea();
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);

        scrollpane.getViewport().add(textarea);
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        textarea.setText((String) value);

        return scrollpane;
    }

    public Object getCellEditorValue() {
        return textarea.getText();
    }
}