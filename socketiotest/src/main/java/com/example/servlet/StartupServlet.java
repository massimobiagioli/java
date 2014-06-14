package com.example.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class StartupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StartupServlet() {
    }

	public void init(ServletConfig config) throws ServletException {
		Configuration configuration = new Configuration();
	    configuration.setHostname("localhost");
	    configuration.setPort(4000);
	    configuration.setAllowCustomRequests(true);

	    SocketIOServer server = new SocketIOServer(configuration);
	    
	    server.addConnectListener(new ConnectListener() {			
			public void onConnect(SocketIOClient client) {
				System.out.println("NEW CONNECTION: " + client.getRemoteAddress());
				client.sendMessage("response from server");
			}
		});
	    
	    server.addDisconnectListener(new DisconnectListener() {			
			public void onDisconnect(SocketIOClient client) {
				System.out.println("CLIENT DISCONNECTED: " + client.getRemoteAddress());
			}
		});
	    
	    server.start();
	    
	    System.out.println("*** SOCKET IO SERVER READY ***");
	}
	
}
