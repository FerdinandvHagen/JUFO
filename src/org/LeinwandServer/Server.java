/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LeinwandServer;

import java.io.*;
import java.net.*;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class Server
{

    private void handleConnection(LeinwandThread thread) throws IOException, ClassNotFoundException
    {
        System.out.println("Handling connection");
        LeinwandServer leinwand = LeinwandServer.gibLeinwand();
        while (true)
        {
            boolean sync = false;
            while (!sync)
            {
                synchronized ((Object) thread.newData)
                {
                    sync = thread.newData;
                }
            }

            //Process new Data
            if (thread.id.equals("close"))
            {
                leinwand.close();
                leinwand = null;
                synchronized ((Object) thread.newData)
                {
                    thread.newData = false;
                }
                return;
            }
            //Determine, what will be next send
            if (thread.id.equals("OBJECT_2D"))
            {
                //new Object will be send; Register at the Leinwand.
                leinwand.addObject((OBJECT_2D) thread.object);
            }
            else if (thread.id.equals("LeinwandData"))
            {
                leinwand.setLeinwandData((LeinwandData) thread.object);
            }
            else if (thread.id.equals("reopen"))
            {
                leinwand.reopen();
            }
            else if (thread.id.equals("removeOBJECT_2D"))
            {
                leinwand.removeObject((OBJECT_2D) thread.object);
            }
            else if (thread.id.equals("redraw"))
            {
                leinwand.redraw();
            }
            else if (thread.id.equals("FPSLimiter"))
            {
                leinwand.useFPSLimiter((boolean) thread.object);
            }
            else if (thread.id.equals("limitFPS"))
            {
                leinwand.limitFPS((int) thread.object);
            }
            else if (thread.id.equals("changePosition"))
            {
                xy var = (xy) thread.object;
                leinwand.changePosition(var.x, var.y);
            }
            else if (thread.id.equals("relbewegen"))
            {
                xy var = (xy) thread.object;
                leinwand.changePosition(var.x, var.y);
            }
            else if (thread.id.equals("setZoom"))
            {
                leinwand.setZoom((double) thread.object);
            }
            else if (thread.id.equals("getbreite"))
            {
                thread.object = leinwand.getbreite();
            }
            else if (thread.id.equals("getbreite"))
            {
                thread.object = leinwand.gethoehe();
            }
            else if (thread.id.equals("getFPSLimit"))
            {
                thread.object = leinwand.getFPSLimit();
            }
            else if (thread.id.equals("getdelta"))
            {
                thread.object = leinwand.getdelta();
            }
            else if (thread.id.equals("getfps"))
            {
                thread.object = leinwand.getfps();
            }
            else if (thread.id.equals("checkCloseRequest"))
            {
                thread.object = leinwand.checkCloseRequest();
            }
            else if (thread.id.equals("isKeyDown"))
            {
                thread.object = leinwand.isKeyDown();
            }
            else if (thread.id.equals("getMouseX"))
            {
                thread.object = leinwand.getMouseX();
            }
            else if (thread.id.equals("getMouseY"))
            {
                thread.object = leinwand.getMouseY();
            }
            else if (thread.id.equals("getMouseDX"))
            {
                thread.object = leinwand.getMouseDX();
            }
            else if (thread.id.equals("getMouseDY"))
            {
                thread.object = leinwand.getMouseDY();
            }
            else if (thread.id.equals("getDMouseWheel"))
            {
                thread.object = leinwand.getDMouseWheel();
            }
            else
            {
                System.out.println("Not found: " + thread.id);
            }

            synchronized ((Object) thread.newData)
            {
                thread.newData = false;
            }
            //and repeat everything
        }
    }

    public static void main(String[] args)
    {
        new Server();
    }

    public Server()
    {
        LeinwandThread thread;
        thread = new LeinwandThread();
        Runnable rs = (Runnable) thread;
        Thread s = new Thread(rs, "Leinwand");
        s.start();

        while (true)
        {
            while (!thread.getup)
            {
                //Wait for a client to connect
                try
                {
                    Thread.sleep(1);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
            }
            thread.getup = false;
            try
            {
                handleConnection(thread);
            }
            catch (IOException ex)
            {
                System.out.println("Connection closed unexpected");
            }
            catch (ClassNotFoundException ex)
            {
                //ex.printStackTrace();
            }
            finally
            {
                thread.getup = false;
                thread.newData = false;
            }
        }
    }

    private class LeinwandThread implements Runnable
    {

        boolean newData;
        String id;
        boolean stop;
        Object object;
        boolean getup;

        public LeinwandThread()
        {
            this.newData = false;
        }

        public void run()
        {
            //Handle the Server tasks
            try
            {
                ServerSocket server = new ServerSocket(3333);

                System.out.println("Server is online!");
                while (true)
                {
                    Socket client = null;
                    System.out.println("Waiting for Client");
                    try
                    {
                        client = server.accept();
                        //Do the stuff
                        this.getup = true;
                        handleConnection(client);
                    }
                    catch (IOException ex)
                    {
                        System.out.println("Connection closed unexpected");
                    }
                    catch (ClassNotFoundException ex)
                    {
                        //ex.printStackTrace();
                    }
                    finally
                    {
                        if (client != null)
                        {
                            try
                            {
                                client.close();
                            }
                            catch (IOException e)
                            {
                                //e.printStackTrace();
                            }
                        }
                    }

                }
            }
            catch (IOException ex)
            {
                //ex.printStackTrace();
            }
        }

        private void handleConnection(Socket client) throws IOException, ClassNotFoundException
        {
            System.out.println("Connection established");
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());

            while (true)
            {
                boolean sync = true;
                while (sync)
                {
                    synchronized ((Object) newData)
                    {
                        sync = newData;
                    }
                    //Wait for the data to be processed
                }

                Paket paket;

                //Read the next Object
                paket = (Paket) in.readObject();
                this.id = paket.id;
                this.object = paket.object;

                //Determine, what will be next send
                if (id.equals("close"))
                {
                    synchronized ((Object) newData)
                    {
                        this.newData = true;
                    }
                    System.out.println("Connection closed");
                    return;
                }

                if (id.equals("getbreite")
                        || id.equals("gethoehe")
                        || id.equals("getFPSLimit")
                        || id.equals("getdelta")
                        || id.equals("getfps")
                        || id.equals("checkCloseRequest")
                        || id.equals("isKeyDown")
                        || id.equals("getMouseX")
                        || id.equals("getMouseY")
                        || id.equals("getMouseDX")
                        || id.equals("getMouseDY")
                        || id.equals("getDMouseWheel"))
                {
                    //Set the index to new Data
                    synchronized ((Object) newData)
                    {
                        this.newData = true;
                    }
                    sync = true;
                    while (sync)
                    {
                        synchronized ((Object) newData)
                        {
                            sync = newData;
                        }
                    }

                    //and respond to the request
                    out.reset();
                    out.writeObject(object);
                }
                else
                {
                    synchronized ((Object) newData)
                    {
                        this.newData = true;
                    }
                }
            }
        }
    }
}
