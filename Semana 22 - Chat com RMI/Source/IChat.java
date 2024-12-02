package Source;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {
  String getMessage(String message) throws RemoteException;

  String sendMessage(String sentMessage) throws RemoteException;
}
