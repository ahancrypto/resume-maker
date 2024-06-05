package ResumeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Base64;
import java.awt.Desktop;

public class ResumeEditor {
    

    public static void generateResume(String name, String email, String phone, String education, String workExperience, String skills, String aboutMe, BufferedImage image,String templateFileName) {
         
         File templateFile = new File(templateFileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(templateFile))) {
            String line;
            StringBuilder templateContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                // Replace placeholders with actual data
                line = line.replace("<h1>Name goes here</h1>", "<h1>" + name + "</h1>");
                line = line.replace("<p>Email: Email goes here</p>", "<p>Email: " + email + "</p>");
                line = line.replace("<p>Phone: Number goes here</p>", "<p>Phone: " + phone + "</p>");
                line = line.replace("<p>About me goes here</p>", "<p>" + aboutMe + "</p>");
                line = line.replace("<li>Experience goes here</li>", "<p>" + formatAsList(workExperience) + "</p>");
                line = line.replace("<li>Skills goes here</li>", "<p>" + formatAsList(skills) + "</p>");
                line = line.replace("<li>Education goes here</li>", "<p>" + formatAsList(education) + "</p>");

                // Replace the image placeholder
                if (line.contains("<img src=\"photo-placeholder.png\" alt=\"User Photo\">")) {
                    // Encode the image to base64
                    String base64Image = "";
                    if (image != null) {
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        try {
                            ImageIO.write(image, "png", baos);
                            byte[] imageData = baos.toByteArray();
                            base64Image = Base64.getEncoder().encodeToString(imageData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // Replace the placeholder with the base64 encoded image
                    line = line.replace("<img src=\"photo-placeholder.png\" alt=\"User Photo\">", "<img src='data:image/png;base64," + base64Image + "' style='max-width:300px;max-height:300px;'>");
                }

                // Append the modified line to the template content
                templateContent.append(line).append("\n");
            }

            // Write the modified template content to a new HTML file
            String outputFileName = "Resume.html";
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFileName))) {
                writer.println(templateContent.toString());
            }

            // Open the generated HTML file
            Desktop.getDesktop().browse(new File(outputFileName).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatAsList(String text) {
        StringBuilder formattedText = new StringBuilder("<ul>");
        String[] lines = text.split("\\n");
        for (String line : lines) {
            formattedText.append("<li>").append(line).append("</li>");
            
        }
        formattedText.append("</ul>");
        return formattedText.toString();
    }
}
