package com.mashibing;

public class MyServlet extends MyHttpServlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) throws Exception {
        response.write("mycomcat");
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws Exception
    {
         response.write("posttomcat");
    }
}
