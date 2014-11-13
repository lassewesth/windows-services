package windows.services.launcher;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServiceLauncher
{
    public static void main( String[] args ) throws Exception
    {
        ServerSocket serverSocket = new ServerSocket( 42187 );

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

                String serviceName = new String( buffer );

                Runtime.getRuntime().exec( "eventcreate /t information /id 1000 /d \"installing service " + serviceName + "\" /so launcher /l application" );
            }
            finally
            {
                socket.close();
            }
        }
    }
}
