package windows.services.client;

import java.net.InetAddress;
import java.net.Socket;

public class ServiceClient
{
    public static void main( String[] args ) throws Exception
    {
        Socket socket = new Socket( InetAddress.getLocalHost(), 42187 );

        try
        {
            socket.getOutputStream().write( "helloworld".getBytes() );
        }
        finally
        {
            socket.close();
        }
    }
}
