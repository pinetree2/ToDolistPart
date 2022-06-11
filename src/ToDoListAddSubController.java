import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ToDoListAddSubController extends Thread{


    //소켓연결 코드 있어야댐~ 가져오거나~(클라친구들한테 받아야함)
    // 서브 인덱스 넘는지도 확인
    public static void toDoListAddsubController(int MainIndex, int subIndex,String Task, Date Deadline,int Chatindex) throws IOException {


        String msg = "500"; //메시지
        String task = Task;
        Date deadline = Deadline;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = dateFormat.format(deadline);//날짜 스트링으로 변경
        int mainindex = MainIndex;
        int subindex = subIndex;
        int chatindex = Chatindex;

        //포트연결하는 클라커넥트 메소드 가져옴

        String port = "7777";
        BufferedReader in;
        Socket socket = new Socket();
        OutputStream out;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = socket.getOutputStream();
        socket = new Socket("127.0.0.1", 7777);


            try{
                //메시지,인덱스, 태스크, 기한날짜,채팅방인덱스, 체크여부
                out.write((msg+"|"+String.valueOf(mainindex)+"|"+"|"+String.valueOf(subindex)+"|"+task+"|"+date+"|"+String.valueOf(chatindex)+"|"+"\n").getBytes());
                in.close();
                out.close();
            }catch (IOException e){
            }




    }



}
