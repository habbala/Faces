import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Faces {
    //Read 300 files.
    //Store as 2/3 as learning images and 1/3 as test images.
    //Don't info that's not an image.
    //Read 20x20 pixel file
    //Pixel has 32 grey levels.
    //

    //training-file.txt
    // training-facit.txt
    // test-file.txt
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\temp\\testfile.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
