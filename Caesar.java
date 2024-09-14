package Caesar;

import java.io.*;

public class Caesar {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 3) {
            System.out.println("Usage: java Caesar <key> <infile> <outfile>");
            return;
        }

        int key;
        try {
            key = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("key must be an integer.");
            return;
        }

        File infile = new File(args[1]);
        if (!infile.exists() || !infile.canRead()) {
            System.out.println(args[1] + " does not exist or is not readable.");
            return;
        }

        FileWriter out = null;
        try {
            out = new FileWriter(args[2]);
        } catch (IOException e) {
            System.out.println(args[2] + " could not be created.");
            return;
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(infile));
            String line;
            while ((line = in.readLine()) != null) {
                
                String output = "";
                for (int i=0; i<line.length(); i++) {
                    
                    char charValue = line.charAt(i);
                    if (charValue < 32 || charValue > 126) {
                        output += charValue;
                    } else {
                        charValue += (char)key;
                        if (charValue > 126) {
                            charValue -= 95;
                        }

                        if (charValue < 32) {
                            charValue += 95;
                        }

                        output += charValue;
                    }
                }
                out.write(output + "\n");
                
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            
        } catch (IOException e) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}