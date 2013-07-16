package org.Leinwand;

/**
 *
 * @author Ferdinand von Hagen
 */
import org.lwjgl.LWJGLException;
import org.lwjgl.util.WaveData;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.AL11.*;

/**
 * Spielt auf Basis der Leinwand .wav-Dateien ab.
 * @author Ferdinand von Hagen
 */
public class AUDIO
{
    private int buffer;
    private int source;
    private WaveData data;
    /**
     * Lädt eine neue Sounddatei (.wav Format)
     * @param file Pfad der .wav Datei
     */
    public AUDIO(String file)
    {
        try
        {
            data = WaveData.create(new BufferedInputStream(new FileInputStream(file)));
        }
        catch (FileNotFoundException e)
        {
            System.err.println(file+" not found");
            System.exit(1);
        }
        buffer = alGenBuffers();
        alBufferData(buffer, data.format, data.data, data.samplerate);
        data.dispose();
        source = alGenSources();
        alSourcei(source, AL_BUFFER, buffer);
    }
    
    /**
     * Startet die Wiedergabe.
     */
    public void playAUDIO()
    {
        alSourcePlay(source);
    }
    
    /**
     * Gibt belegten Speicherplatz frei.
     */
    public void cleanUp()
    {
        alDeleteBuffers(buffer);
    }
    
    /**
     * Pausiert die Wiedergabe.
     */
    public void pauseAUDIO()
    {
        alSourcePause(source);
    }
    
    /**
     * Stoppt die Wiedergabe.
     */
    public void stopAUDIO()
    {
        alSourceStop(source);
    }
}