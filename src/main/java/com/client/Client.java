package com.client;

import com.entities.ISOField;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Client {

    int port = 6238;
    InetAddress ip;
    Socket client;

    public Client(){

    }

    public Client(int port){
        this.port = port;
    }

    public Client(InetAddress ip){
        this.ip = ip;
    }

    public Client(InetAddress ip,int port){
        this.ip = ip;
        this.port = port;
    }

    public void communicateServer(){
        try {
            ip = InetAddress.getLocalHost();
            client = new Socket(ip, port);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));


            while(true) {

                //bufferedWriter.write("08003100000000000000852032589634715975315975331970025");
                //bufferedWriter.write("0800A0200000008000100400000000000000000000000001323931313030303100106A504F5320312E392E310301");
                //bufferedWriter.write("080020200100008000009200000000280004454493030303031");
                //bufferedWriter.write("0800460000000000000085203258963471597531597533197002524890713");
                //bufferedWriter.write("005B80380000008100000400000000000000362910102957103110000005031SU20111031102957201110311029573001");
                //bufferedWriter.write("0800823A0000000000000400000000000000042009061390000109061304200420001");
                //bufferedWriter.write("0810823A000002000000048000000000000004200906139000010906130420042000001031128");
                //bufferedWriter.write("0200323A4001084180103800000000000000000420050805011392120805042004225132072000001000000115604080041101251146333156336000299");
                //bufferedWriter.write("0210323A40010A4180103800000000000000000420050805011392120805042004225132072000001000000115604000800411163011511463331563GBAAASDDERRR1300101B54391001000017654350000000000090300000268410000000300000000000000898100009431000000000000000000000000000000000036000299");
                bufferedWriter.write("0400F23A4001084182020000004000000000191111111110000000000180000000000030000090806465100331613451909080909601006000200000000000343000394803808110012000004096565733200000003000001360030003317000394809080646510000000003132020000331609080645190000000020000000000000");
                //bufferedWriter.write("0410F23A40010A418202000000400000000019111111111000000000018000000000003000009080646510033161345190908090860100600020000000000034300000394803808110012000006281100120000040965657332360030003317000394809080646510000000003132020000331609080645190000000020000000000000");
                //bufferedWriter.write("0401F23A4001084182020000004000000000191111111110000000000180000000000030000090806465200331613451909080909601006000200000000000343000394803808110012000004096565733200000003000001360030003318000394809080646520000000003132020000331609080645190000000020000000000000");
                //bufferedWriter.write("0410F23A40010A418202000000400000000019111111111000000000018000000000003000009080646520033161345190908090860100600020000000000034394000394803808110012000004096565733200000003000001360030003318000394809080646520000000003132020000331609080645190000000020000000000000");
                bufferedWriter.newLine();
                bufferedWriter.flush();

                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                Map<Integer, ISOField> data = (Map<Integer, ISOField>)ois.readObject();

                HashMap<String, String> mit = (HashMap<String, String>)ois.readObject();

                printMIT(mit);
                printData(data);
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void printMIT(HashMap<String, String> mit) {
        for (Map.Entry<String, String> field : mit.entrySet()) {
            if(field.getValue() != null)
                System.out.println(field.getKey() + ":      " + field.getValue());
            else
                System.out.println(field.getKey() + ": " + field.getValue());
        }
        System.out.println();
    }

    public void printData(Map<Integer, ISOField> data){
        System.out.println("ID:     "+"Value              "+"Length             "+"Type                "+"Name     ");
        for (Map.Entry<Integer, ISOField> field : data.entrySet()) {
            if(field.getValue() != null)
                System.out.println(field.getKey() + ":      " + field.getValue().getValue()+"           "+field.getValue().getLength()+"            "+field.getValue().getType()+"         "+field.getValue().getName());
            else
                System.out.println(field.getKey() + ": " + field.getValue());
        }
    }
}
