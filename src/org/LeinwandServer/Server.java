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
            while (!thread.newData)
            {
                leinwand.redraw();
                //Wait for a client to connect
                /*try
                {
                    Thread.sleep(1);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }*/
            }

            //Process new Data
            if (thread.id.equals("close"))
            {
                leinwand.close();
                leinwand = null;
                thread.newData = false;
                return;
            }
            //Determine, what will be next send
            if (thread.id.equals("OBJECT_2D"))
            {
                //new Object will be send; Register at the Leinwand.
                leinwand.addObject((Rechteck) thread.object);
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
                thread.newData = false;
            }
            else if (thread.id.equals("getbreite"))
            {
                thread.object = leinwand.gethoehe();
                thread.newData = false;
            }
            else if (thread.id.equals("getFPSLimit"))
            {
                thread.object = leinwand.getFPSLimit();
                thread.newData = false;
            }
            else if (thread.id.equals("getdelta"))
            {
                thread.object = leinwand.getdelta();
                thread.newData = false;
            }
            else if (thread.id.equals("getfps"))
            {
                thread.object = leinwand.getfps();
                thread.newData = false;
            }
            else if (thread.id.equals("checkCloseRequest"))
            {
                thread.object = leinwand.checkCloseRequest();
                thread.newData = false;
            }
            else if (thread.id.equals("isKeyDown"))
            {
                thread.object = leinwand.isKeyDown((int) thread.object);
                thread.newData = false;
            }
            else if (thread.id.equals("getMouseX"))
            {
                thread.object = leinwand.getMouseX();
                thread.newData = false;
            }
            else if (thread.id.equals("getMouseY"))
            {
                thread.object = leinwand.getMouseY();
                thread.newData = false;
            }
            else if (thread.id.equals("getMouseDX"))
            {
                thread.object = leinwand.getMouseDX();
                thread.newData = false;
            }
            else if (thread.id.equals("getMouseDY"))
            {
                thread.object = leinwand.getMouseDY();
                thread.newData = false;
            }
            else if (thread.id.equals("getDMouseWheel"))
            {
                thread.object = leinwand.getDMouseWheel();
                thread.newData = false;
            }
            else
            {
                System.out.println("Not found: " + thread.id);
            }

            thread.newData = false;
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
                while (this.newData)
                {
                    //Wait for the data to be processed
                    try
                    {
                        Thread.sleep(1);
                    }
                    catch (InterruptedException ex)
                    {
                        ex.printStackTrace();
                    }
                }

                //Read the next Object
                id = (String) in.readObject();

                //Determine, what will be next send
                if (id.equals("OBJECT_2D")
                        || id.equals("FPSLimiter")
                        || id.equals("LeinwandData")
                        || id.equals("removeOBJECT_2D")
                        || id.equals("limitFPS")
                        || id.equals("changePosition")
                        || id.equals("relbewegen")
                        || id.equals("setZoom"))
                {
                    //new Object will be send; Register at the Leinwand.
                    this.object = in.readObject();
                }
                else if (id.equals("close"))
                {
                    this.newData = true;
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
                    this.object = in.readObject();
                    //Set the index to new Data
                    this.newData = true;
                    while (this.newData)
                    {
                        //Wait for the data to be processed
                        /*try
                        {
                            Thread.sleep(1);
                        }
                        catch (InterruptedException ex)
                        {
                            ex.printStackTrace();
                        }*/
                    }

                    //and respond to the request
                    out.writeObject(object);
                }
                else
                {
                    this.newData = true;
                }

                //System.out.println("Client requested: " + id);
            }
        }
    }
}
