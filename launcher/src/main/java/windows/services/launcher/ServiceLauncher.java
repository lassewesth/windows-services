package windows.services.launcher;

import static java.lang.String.format;

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

                String command = format( "eventcreate /t information /id 1000 /d \"installing service %s\" /so launcher /l application", serviceName );

                System.out.println(command);

                Runtime.getRuntime().exec( command );

                command = format( "sc create %s binpath= C:\\Users\\lassewesth\\windows-services\\runner\\runner.cmd obj= lassewesth displayname= ServiceRunner password= u971746", serviceName );

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
