import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ToDoListAddMAIN {
    private static JFrame frame = new JFrame("ToDoListAdd");
    private JButton addBtn;
    private JTextField task;
    private JPanel addTask;
    private JPanel jpCald;

    private static String Task;
    private static String deadline;
    private static Date deadlineDate;

    Calendar cld = Calendar.getInstance();
    JDateChooser selectedDate = new JDateChooser(cld.getTime());

    public void run() {
        frame.setContentPane(new ToDoListAddMAIN().addTask);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ToDoListAddMAIN(){
        frame.setContentPane(addTask);
        frame.setLocationRelativeTo(null);

        selectedDate.setDateFormatString("yyyy-MM-dd");
        jpCald.add(selectedDate);

        //버튼 누르면 서버에 요청하고 db에 추가
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Task = task.getText();
                deadline = ((JTextField) selectedDate.getDateEditor().getUiComponent()).getText();
                //이외 db구성 항목들 추가해야함
                int main_index;
                int main_big=0;
                int chat_index =1;


                ArrayList<ListDataMain> datamainlist = ToDoListBring.bringMain();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String today = dateFormat.format(new Date());
                //Date deadlineDate = null;
                try {
                    deadlineDate = new Date(dateFormat.parse(deadline).getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                Date todayDate = null;
                try {
                    todayDate = new Date(dateFormat.parse(today).getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                int compare = deadlineDate.compareTo(todayDate);
                if (Task .isEmpty())
                    JOptionPane.showMessageDialog(null, "일정 내용을 입력하세요");
                else if (compare < 0)
                    JOptionPane.showMessageDialog(null, "이미 지난 날짜입니다.");
                else {
                    //db에 값 넣는 메소드 실행 -> 메소드는 sub용 main용 둘다 있어야함
                    //sub인지 main인지 조건건문 하나 더 들어서 판단해온 다음에 다른 메소드 실행시키기

                    if (datamainlist.size() == 0)
                        main_index = 1;
                    else{
                        for(int i = 0; i< datamainlist.size(); i++) {
                            main_index = datamainlist.get(i).M_idx;

                            if(main_big < main_index){
                                main_big = main_index;
                            }

                        }
                    }
                    main_big +=1;

                    //인덱스를 가져와서 제일 큰 값 가져와서 + 1 = 현재의 추가하려는 인덱스로 지정
                    //메시지,인덱스, 태스크, 기한날짜,채팅방인덱스
                    try {
                        ToDoListAddController.toDoListAddController(main_big,Task,deadlineDate,chat_index);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    frame.dispose();
                    frame.setVisible(false);
                    //클라에서 리턴있을때 종료시킬수있음
                }
            }
        });

    }

    //db에 넘길 리턴값들, 이 메소드들 써서 값 저장해서 db로 넘기면댐, 이외 db구성 항목들 추가해야함 //deadline은 String이랑 Date중 필요한 형식 사용
    //창 하나만 띄운다고 가정하고 static으로 만들어뒀는데 나중에 창 여러개띄워야한다면 수정필요함
    public String getTask(){
        return this.Task;
    }
    public String getDeadline(){
        return this.deadline;
    }
    public Date getDeadlineDate(){
        return this.deadlineDate;
    }



}
