package vehsamrak.helper;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Vehsamrak
 */
public class NullOutputStream extends OutputStream
{
    @Override
    public void write(int b) throws IOException {}
}
