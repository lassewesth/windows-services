package windows.services.runner;

import static java.lang.String.format;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceRunner
{
    public static void main( String[] args ) throws Exception
    {
        ServerSocket serverSocket = new ServerSocket( 42188 );

        //noinspection InfiniteLoopStatement
        while ( true )
        {
            Socket socket = serverSocket.accept();

            try
            {
                InputStream inputStream = socket.getInputStream();

                byte[] buffer = new byte[10];

                int read = inputStream.read( buffer );

                if ( read != 10 )
                {
                    throw new IllegalStateException( "too short" );
                }

                String serviceMessage = new String( buffer );

                String command = format( "eventcreate /t information /id 1000 /d \"service poke %s\" /so runner /l application", serviceMessage );

                System.out.println(command);

                Runtime.getRuntime().exec( command );
            }
            finally
            {
                socket.close();
            }
        }
    }
}
