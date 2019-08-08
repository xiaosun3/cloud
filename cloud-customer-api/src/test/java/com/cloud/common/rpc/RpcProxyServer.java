package com.cloud.common.rpc;

import com.cloud.common.dynamicproxy.service.TicketService;
import com.cloud.common.dynamicproxy.service.TicketServiceImpl;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @auth Administrator
 */
public class RpcProxyServer {

    private TicketService ticketService = new TicketServiceImpl();

    public void publisherServer(int port) {
        try  {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
//                    try () {
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        String method = ois.readUTF();
                        Object[] objs = (Object[]) ois.readObject();
                        ois.close();
                        Class<?>[] types = new Class[objs.length];
                        for (int i = 0; i < types.length; i++) {
                            types[i] = objs[i].getClass();
                        }
                        Method m = TicketServiceImpl.class.getMethod(method, types);
                        Object obj = m.invoke(ticketService, objs);

//                        try () {
                            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject(obj);
                            oos.flush();
                            oos.close();
//                        }
//                    }
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
