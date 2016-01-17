package artopia.handler;

import artopia.entitiy.User;
import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import artopia.exception.WrongPassword;
import artopia.service.DatabaseService;
import artopia.service.UserService;
import artopia.service.command.CommandService;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import vehsamrak.helper.NullOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Vehsamrak
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ExceptionHandler.class)
public class ConnectionHandlerTest extends Assert
{
    @Before
    public void setUp() throws Exception
    {
        PowerMockito.mockStatic(ExceptionHandler.class);
        System.setOut(new PrintStream(new NullOutputStream()));
    }

    @Test
    public void disconnect_givenSocketWithCorrectData_returnsNothing() throws Exception
    {
        ConnectionHandler connectionHandler = createConnectionHandler(
                this.createSocketInput(),
                this.createSocketOutput(),
                this.createUser()
        );

        connectionHandler.disconnect();
    }

    @Test
    public void run_connectedUserEntersQuitCommand_disconnectingUser() throws Exception
    {
        BufferedReader socketInput = this.createSocketInput();
        PrintWriter socketOutput = this.createSocketOutput();
        User user = this.createUser();
        ConnectionHandler connectionHandler = this.createConnectionHandler(socketInput, socketOutput, user);
        when(socketInput.readLine()).then(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count == 3) {
                    return "quit";
                } else {
                    count++;
                    return "authors";
                }
            }
        });

        connectionHandler.run();
    }

    @Test
    public void run_connectedUserEntersEmptyLogin_disconnectingUserWithError() throws Exception
    {
        BufferedReader socketInput = this.createSocketInput();
        PrintWriter socketOutput = this.createSocketOutput();
        ConnectionHandler connectionHandler = createConnectionHandlerThrowsException(socketInput, socketOutput, new EmptyUsername());

        connectionHandler.run();
    }

    @Test
    public void run_connectedUserEntersEmptyPassword_disconnectingUserWithError() throws Exception
    {
        BufferedReader socketInput = this.createSocketInput();
        PrintWriter socketOutput = this.createSocketOutput();
        ConnectionHandler connectionHandler = createConnectionHandlerThrowsException(socketInput, socketOutput, new EmptyPassword());

        connectionHandler.run();
    }

    @Test
    public void run_connectedUserEntersWrongPassword_disconnectingUserWithError() throws Exception
    {
        BufferedReader socketInput = this.createSocketInput();
        PrintWriter socketOutput = this.createSocketOutput();
        ConnectionHandler connectionHandler = createConnectionHandlerThrowsException(socketInput, socketOutput, new WrongPassword());

        connectionHandler.run();
    }

    @Test
    public void run_inputStreamThrowsException_handleException() throws Exception
    {
        BufferedReader socketInput = this.createSocketInput();
        PrintWriter socketOutput = this.createSocketOutput();
        ConnectionHandler connectionHandler = createConnectionHandler(socketInput, socketOutput, this.createUser());

        when(socketInput.readLine()).thenThrow(new IOException());

        connectionHandler.run();
    }

    private ConnectionHandler createConnectionHandler(BufferedReader socketInput, PrintWriter socketOutput, User user) throws Exception
    {
        UserService userService = this.createUserServiceWithUser(user);

        return new ConnectionHandler(
                this.createSocket(),
                socketInput,
                socketOutput,
                this.createServiceLocatorWithUserService(userService)
        );
    }

    private ConnectionHandler createConnectionHandlerThrowsException(
            BufferedReader socketInput,
            PrintWriter socketOutput,
            Exception exception
    ) throws Exception
    {
        UserService userService = this.createUserServiceThatThrowsException(exception);
        return new ConnectionHandler(
                this.createSocket(),
                socketInput,
                socketOutput,
                this.createServiceLocatorWithUserService(userService)
        );
    }

    private ServiceLocator createServiceLocatorWithUserService(UserService userService) throws Exception
    {
        DatabaseService databaseService = this.createDatabaseService();

        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        when(serviceLocator.get(Service.USER)).thenReturn(userService);
        when(serviceLocator.get(Service.DATABASE)).thenReturn(databaseService);

        CommandService commandService = new CommandService(serviceLocator);
        when(serviceLocator.get(Service.COMMAND)).thenReturn(commandService);

        return serviceLocator;
    }

    private DatabaseService createDatabaseService()
    {
        Session session = mock(Session.class);
        when(session.beginTransaction()).thenReturn(mock(Transaction.class));

        DatabaseService databaseServiceMock = mock(DatabaseService.class);
        when(databaseServiceMock.openSession()).thenReturn(session);

        return databaseServiceMock;
    }

    private PrintWriter createSocketOutput()
    {
        return mock(PrintWriter.class);
    }

    private BufferedReader createSocketInput()
    {
        return mock(BufferedReader.class);
    }

    private UserService createUserServiceThatThrowsException(Exception exception) throws Exception
    {
        UserService userService = mock(UserService.class);
        when(userService.login(anyString(), anyString())).thenThrow(exception);

        return userService;
    }

    private UserService createUserServiceWithUser(User user) throws Exception
    {
        UserService userService = mock(UserService.class);
        when(userService.login(anyString(), anyString())).thenReturn(user);

        return userService;
    }

    private User createUser()
    {
        User user = mock(User.class);

        when(user.getName()).thenReturn("Tester");

        return user;
    }

    private Socket createSocket() throws Exception
    {
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(new InputStream()
        {
            @Override
            public int read() throws IOException
            {
                return 0;
            }
        });

        InetAddress inetAddress = this.createInetAdress();
        when(socket.getInetAddress()).thenReturn(inetAddress);

        return socket;
    }

    private InetAddress createInetAdress()
    {
        InetAddress inetAddress = mock(InetAddress.class);
        when(inetAddress.getCanonicalHostName()).thenReturn("123.456.789.012");

        return inetAddress;
    }
}
