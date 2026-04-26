package parseMetro;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Mainqe {
    public static void main(String[] args) {

        try {
            //This section is not used.  Remove it unless you need to load from a local file.
            //String filePath = "C:\\Users\\Admin\\Downloads\\lenta.html";
            //String htmlContent = parseFile(filePath);

            Document doc = Jsoup.connect("https://lenta.ru").get();
            File outputDir = new File("C:\\Users\\Admin\\IdeaProjects\\nyanya\\src\\links"); // Create a directory for images
            if (!outputDir.exists() && !outputDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + outputDir.getAbsolutePath());
            }


            Elements imgElements = doc.select("img");
            for (Element img : imgElements) {
                String relativeUrl = img.attr("src");
                URL absoluteUrl = new URL(new URL("https://lenta.ru"), relativeUrl); //Correct URL construction.
                String fileName = extractFileName(absoluteUrl); //To prevent errors with invalid characters
                File outputFile = new File(outputDir, fileName);


                BufferedImage image = ImageIO.read(absoluteUrl);
                if (image != null) {
                    ImageIO.write(image, "jpg", outputFile);
                    System.out.println("Downloaded: " + absoluteUrl + " to " + outputFile.getAbsolutePath());
                } else {
                    System.err.println("Failed to download: " + absoluteUrl);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Helper function to get filename (Handles potential issues in filenames.)
    private static String extractFileName(URL url) {
        String path = url.getPath();
        String fileName = path.substring(path.lastIndexOf('/') + 1);
        //Simple sanitation: remove invalid characters. You might need a more robust solution for production code
        fileName = fileName.replaceAll("[\\\\/:*?\"<>|]", "_");
        return fileName;
    }

    //This function is no longer used but included for completeness
    public static String parseFile(String path) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for (String line : lines) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return sb.toString();
    }
}