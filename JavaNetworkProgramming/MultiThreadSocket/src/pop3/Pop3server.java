package pop3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Pop3server {
    private static final int SERVER_PORT = 110;
    private static final int MAX_CLIENT = 500;
    private ServerSocket listenSocket = null;
    private boolean keeprunning = true;
    private InetAddress ip;

    public static void main(String[] args) {
        Pop3server server = new Pop3server();
        server.impserver();
    }

    public void impserver() {
        Socket clientSocket = null;
        try {
            while (keeprunning) {
                clientSocket = listenSocket.accept();
                int numThreads = Thread.activeCount();
                System.out.println(numThreads);
                manageconnection newhandle = new manageconnection(clientSocket);
                Thread newhandlethread = new Thread(newhandle);
                newhandlethread.start();
                System.out.println("hello");

            }
        } catch (IOException excpt) {
            System.err.println("Sorry ,Failed I/O:" + excpt);
        }

    }

    public Pop3server() {
        try {
            listenSocket = new ServerSocket(SERVER_PORT, MAX_CLIENT);
        } catch (IOException excpt) {
            System.err.println("SOrry to open port " + SERVER_PORT + ":" + excpt);
            System.exit(1);
        }
    }
}   

/*follow is manage and process command class*/

class manageconnection implements Runnable {
    private static final boolean AUTOFLUSH = true;
    private Socket mySocket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private boolean checkedpass = false;
    private int msgnum = 0;
    private long[] msgsize = new long[100];
    private long totalsize = 0;
    private String username = null;

    public manageconnection(Socket newSocket) {
        mySocket = newSocket;
    }

    @Override
    public void run() {
        String nextline = null;
        String password;
        int check = 0;
        int strlen;
        String content = "Subject:hello";
        String command = null;
        String auth_user = "user";
        String arg1;
        int arg2 = 0;
        String arg3;
        int i = 0;
        int count = 0;
        int time = 0;
        try {
            Thread.sleep(10);
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            mySocket.setTcpNoDelay(true);
            time = mySocket.getSoTimeout();
        } catch (SocketException excpt) {
            System.out.println(excpt);
        }
        try {
            out = new PrintWriter(mySocket.getOutputStream(), AUTOFLUSH);
            in = new BufferedReader(new InputStreamReader(mySocket.getInputStream())

            );
/*System.out.println("hello thread1");*/
            out.println("+OK" + " TianHua's pop3 server");
            try {
                while (true) {
                    if (check != 3) {
                        try {
                            nextline = in.readLine();
                        } catch (Exception excpt) {
                            System.out.println("sorry:" + excpt);
                            break;
                        }
                        System.out.println(count + " " + nextline);

                        if (nextline.length() < 4) {
                            out.println("-ERR");
                        } else {
                            command = nextline.substring(0, 4);
                            if ("user".equalsIgnoreCase(command)) {
                                i = 1;
                            }
                            if ("pass".equalsIgnoreCase(command)) {
                                i = 2;
                            }
                            if ("stat".equalsIgnoreCase(command)) {
                                i = 3;
                            }
                            if ("quit".equalsIgnoreCase(command)) {
                                i = 4;
                            }
                            if ("list".equalsIgnoreCase(command)) {
                                i = 5;
                            }
                            if ("retr".equalsIgnoreCase(command)) {
                                i = 6;
                            }
                            if ("dele".equalsIgnoreCase(command)) {
                                i = 7;
                            }

                            switch (i) {
                                case 1:
                                    if (check == 0) {
                                        check = 1;
                                        if (nextline.length() < 5) {
                                            out.println("-ERR");
                                        } else {
                                            arg1 = nextline.substring(5);
                                            username = arg1;
                                            out.println("+OK");
                                        }
                                    } else {out.println("-ERR");}
                                    i = 0;
                                    break;
                                case 2:
                                    if (check == 1) {
                                        if (nextline.length() < 5) {
                                            out.println("-ERR");
                                        } else {
                                            arg1 = nextline.substring(5);
                                            password = arg1;
                                            if (check(username, password) == '1') {
                                                check = 2;
                                            /*msgnum=readmail(username);*/
                                                out.println("+OK");
                                            } else {
                                                out.println("-ERR" + " sorry auth failed");
                                                check = 0;
                                            }
                                        }
                                    } else {out.println("-ERR");}
                                    i = 0;
                                    break;
                                case 3:
                                    if (check == 2) {
                                        msgnum = readmail(username);
                                        out.println("+OK" + " " + msgnum + " " + totalsize);
                                    } else {
                                        out.println("-ERR");
                                    }
                                    i = 0;
                                    break;
                                case 4:
                                    out.println("+OK BYE BYE welcome to TianHua's Mail System next time");
                                    check = 3;
                                    i = 0;
                                    break;
                                case 5:
                                    if (check == 2) {
                                        out.println("+OK");
                                        out.println(msgnum + " " + totalsize);
                                        int ii, iii = 0;
                                        for (ii = 1; ii <= msgnum; ii++) {
                                            out.println(ii + " " + msgsize[iii]);
                                            iii++;
                                        }
                                        out.println(".");
                                    } else {
                                        out.println("-ERR");
                                    }
                                    i = 0;
                                    break;
                                case 6:
                                    if (check == 2) {
                                        if (nextline.length() < 5) {
                                            out.println("-ERR");
                                        } else {
                                            arg1 = nextline.substring(5);
                                            out.println("+OK");
                                            System.out.println(arg1);
                                            printmail(Integer.parseInt(arg1), username);
                                            out.print("\r\n");
                                            out.print(".");
                                            out.print("\r\n");
                                            boolean st = out.checkError();
                                            System.out.println(st);
/*if(nextline.length()<6){ 
out.println("-ERR"); 
}else{ 
try{ 
arg2=Integer.parseInt(nextline.substring(5)); 
}catch(NumberFormatException except) 
{ 
out.println("-ERR"); 
break; 
} 
if(msgnum==0){ 
out.println("-ERR no msg retrived"); 
} 
if(arg2<=msgnum){ 
out.println("+OK"); 
out.println(content); 
out.println(""); 
out.print("."); 
out.println(); 
out.println("."); 
System.out.println("msg finished"); 
}else{ 
out.println("no such msg"); 
} 
}*/
                                        }
                                    } else {
                                        out.println("-ERR");
                                    }
                                    i = 0;
                                    System.out.println("retr finished");
                                    break;
                                case 7:
                                    if (check == 2) {
                                        out.println("+OK");
                                    } else {
                                        out.println("-ERR");
                                    }
                                    i = 0;
                                    break;
                                default:
                                    out.println("-ERR");
                                    i = 0;
                                    break;

                            }
                        }
                    } else {
                        out.close();
                        in.close();
                        mySocket.close();
                        break;
                    }

                }
            } catch (NullPointerException excpt) {
                System.out.println("sorry " + excpt);
                in.close();
                out.close();
                mySocket.close();
            }

        } catch (IOException excpt) {
            System.err.println("Failed I/O:" + excpt);
        }

        System.out.println("bye");

    }

    private void printmail(int msgnumber, String uname) {
        String msgdir = "/webmail/userinfo/";
        String msgfile = msgdir + uname + "/" + Integer.toString(msgnumber);
        System.out.println(msgfile);
        long filepoint = 0;
        try {
            RandomAccessFile file = new RandomAccessFile(msgfile, "r");
            long msglength = file.length();
            while (filepoint < msglength) {
                String s = file.readLine();
                out.println(s);
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    System.out.println(e);
                }
                filepoint = file.getFilePointer();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    /*check your username and password*/
    private char check(String user, String pass) {
        String[] arg = new String[2];
        int c = 0;
        char value = '0';
        arg[0] = "uname=" + user;
        arg[1] = "pass=" + pass;

        try {

            Process child = Runtime.getRuntime().exec("/disk2/ftp/pub/login"

                , arg);
            InputStream inn = child.getInputStream();
            while ((c = inn.read()) != -1) {
                value = (char)c;
            }
            inn.close();

            try {child.waitFor();} catch (InterruptedException e) {

                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println(e);
        }
        return value;

    }

    private int readmail(String uname) {
        String[] arg = new String[1];
        arg[0] = "username=" + uname;
        int[] msg = new int[3];
        int i = 0, j = 0;
        int msgnum = 0;
        int c;
        try {
            Process child = Runtime.getRuntime().exec("/disk2/ftp/pub/readmail",

                arg);
            InputStream inn = child.getInputStream();
            while ((c = inn.read()) != -1) {
                msg[i] = c - 48;
                i++;
            }
            inn.close();
            try {child.waitFor();} catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        for (j = 0; j <= i; j++) {
            msgnum = msgnum + msg[j];
        }
        System.out.println(msgnum);
        int k = 0;
        try {
            for (j = 1; j <= msgnum; j++) {
                RandomAccessFile file = new RandomAccessFile("/webmail/userinfo/" + uname +

                    "/" + Integer.toString(j), "r");
                msgsize[k] = file.length();
                totalsize = msgsize[k] + totalsize;
                System.out.println(msgsize[k]);
                k++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(totalsize);
        return msgnum;
    }

}  