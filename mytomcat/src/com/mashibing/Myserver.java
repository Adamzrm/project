package com.mashibing;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Myserver {
    public static void startServer(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        //定义客户端套接字
        Socket socket = null;
        while (true) {
            socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            //定义请求对象
            MyRequest request = new MyRequest(inputStream);
            //定义响应对象
            MyResponse response = new MyResponse(outputStream);
            String clazz = new MyMapping().getMapping().get(request.getRequestUrl());

            if (clazz != null) {
                Class<MyServlet> myServletClass = (Class<MyServlet>) Class.forName(clazz);
                //根据myServletClass创建对象
                MyServlet myServlet = myServletClass.newInstance();
                myServlet.service(request, response);
            }
        }
    }

    public static void main(String[] args) {
        try {
            startServer(10086);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
