/**
 * Created by Ivan on 25.03.2015.
 */
import java.io.*;

public class MultiSpaceTabEraser {

    private static String _encoding;

    public static void main(String[] args)
    {
        if (args.length != 3)
        {
            System.out.println("Wrong number of input arguments");
            return;
        }

        File inFile = new File(args[0]);
        _encoding = args[1];
        File outFile = new File(args[2]);

        String contents = GetContents(inFile);
        System.out.print("Input file contents:\r\n" + contents);
        System.out.println("--------------------------------------------");

        contents = EraseSpacesTabs(contents);

        SetContents(outFile, contents);
        System.out.print("Output file contents:\r\n" + contents);
    }

    static public String GetContents(File file)
    {
        StringBuilder contents = new StringBuilder();

        try
        {
            if (file == null)
            {
                throw new IllegalArgumentException("Input file should not be null.");
            }

            if (!file.exists())
            {
                throw new FileNotFoundException();
            }

            if (!file.canRead())
            {
                throw new IllegalArgumentException("Input file cannot be written: " + file);
            }

            if (!file.isFile())
            {
                throw new IllegalArgumentException("Input file should not be a directory: " + file);
            }

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader in = new InputStreamReader(fis, _encoding);
            BufferedReader input =  new BufferedReader(in);
            try
            {
                String line;

                while ((line = input.readLine()) != null)
                {
                    contents.append(line + "\r\n");
                }
            }
            finally
            {
                input.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Input file does not exist: " + file);
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return contents.toString();
    }

    private static String EraseSpacesTabs(String contents)
    {
        return contents.replaceAll("([\\t ]+)", " ");
    }

    static public void SetContents(File file, final String contents)
    {
        try
        {
            if (file == null)
            {
                throw new IllegalArgumentException("Output file should not be null.");
            }

            if (!file.exists())
            {
                throw new FileNotFoundException();
            }

            if (!file.canRead())
            {
                throw new IllegalArgumentException("Output file cannot be written: " + file);
            }

            if (!file.isFile())
            {
                throw new IllegalArgumentException("Output file should not be a directory: " + file);
            }

            FileOutputStream fis = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(fis, _encoding);
            BufferedWriter output =  new BufferedWriter(out);
            try
            {
                output.write(contents);
            }
            finally
            {
                output.close();
            }
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Output file does not exist: " + file);
        }
        catch(IllegalArgumentException ex)
        {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
