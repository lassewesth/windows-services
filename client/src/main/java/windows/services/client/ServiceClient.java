package windows.services.client;

import java.net.InetAddress;
import java.net.Socket;

public class ServiceClient
{
    public static void main( String[] args ) throws Exception
    {
        if (args.length != 2) throw new IllegalArgumentException( "need port, message" );

        int port = Integer.parseInt( args[0] );
        String message = args[1];

        Socket socket = new Socket( InetAddress.getLocalHost(), port );

        try
        {
            socket.getOutputStream().write( message.getBytes() );
        }
        finally
        {
            socket.close();
        }
    }
}
