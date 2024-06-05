package ResumeMaker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Template2 {
    public static void main() {
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Resume</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "        }\n" +
                "\n" +
                "        .resume-container {\n" +
                "            display: flex;\n" +
                "            margin: 20px auto;\n" +
                "            width: 90%;\n" +
                "            max-width: 900px;\n" +
                "            background-color: #fff;\n" +
                "            border: 1px solid #ccc;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "            position: relative; /* Required for watermark positioning */\n" +
                "        }\n" +
                "\n" +
                "        .left-column {\n" +
                "            width: 30%;\n" +
                "            background-color: #2c3e50;\n" +
                "            color: #fff;\n" +
                "            padding: 20px;\n" +
                "            box-sizing: border-box;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "\n" +
                "        .left-column img {\n" +
                "            border-radius: 50%;\n" +
                "            width: 150px;\n" +
                "            height: 150px;\n" +
                "            object-fit: cover;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .left-column h1 {\n" +
                "            margin: 0;\n" +
                "            font-size: 24px;\n" +
                "        }\n" +
                "\n" +
                "        .left-column p {\n" +
                "            margin: 10px 0;\n" +
                "            font-size: 14px;\n" +
                "        }\n" +
                "\n" +
                "        .right-column {\n" +
                "            width: 70%;\n" +
                "            padding: 20px;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "\n" +
                "        .section-title {\n" +
                "            font-size: 18px;\n" +
                "            color: #2c3e50;\n" +
                "            margin: 20px 0 10px;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .section-title:before {\n" +
                "            content: \"\";\n" +
                "            width: 30px;\n" +
                "            height: 3px;\n" +
                "            background-color: #e67e22;\n" +
                "            position: absolute;\n" +
                "            bottom: -5px;\n" +
                "            left: 0;\n" +
                "        }\n" +
                "\n" +
                "        .section-content ul {\n" +
                "            padding-left: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .section-content ul li {\n" +
                "            list-style-type: disc;\n" +
                "            margin-bottom: 10px;\n" +
                "            font-size: 14px;\n" +
                "            color: #7f8c8d;\n" +
                "        }\n" +
                "\n" +
                "        .right-column .contact-info ul {\n" +
                "            padding-left: 0;\n" +
                "        }\n" +
                "\n" +
                "        .right-column .contact-info ul li {\n" +
                "            list-style-type: none;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .right-column .skills ul li {\n" +
                "            background-color: #34495e;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 3px;\n" +
                "            text-align: center;\n" +
                "            color: #ecf0f1;\n" +
                "        }\n" +
                "\n" +
                "        .watermark {\n" +
                "            position: absolute;\n" +
                "            bottom: 10px;\n" +
                "            right: 10px;\n" +
                "            color: rgba(1, 1, 1, 1);\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"resume-container\">\n" +
                "        <div class=\"left-column\">\n" +
                "            <img src=\"photo-placeholder.png\" alt=\"User Photo\">\n" +
                "            <h1>Name goes here</h1>\n" +
                "            <p>About me goes here</p>\n" +
                "        </div>\n" +
                "        <div class=\"right-column\">\n" +
                "            <div class=\"contact-info\">\n" +
                "                <div class=\"section-title\">Contact Information</div>\n" +
                "                <div class=\"section-content\">\n" +
                "                    <ul>\n" +
                "                        <p>Email: Email goes here</p>\n" +
                "                        <p>Phone: Number goes here</p>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"section\">\n" +
                "                <div class=\"section-title\">Skills</div>\n" +
                "                <div class=\"section-content\">\n" +
                "                    <ul>\n" +
                "                        <li>Skills goes here</li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"section\">\n" +
                "                <div class=\"section-title\">Education</div>\n" +
                "                <div class=\"section-content\">\n" +
                "                    <ul>\n" +
                "                        <li>Education goes here</li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"section\">\n" +
                "                <div class=\"section-title\">Work Experience</div>\n" +
                "                <div class=\"section-content\">\n" +
                "                    <ul>\n" +
                "                        <ul>\n" +
                "                            <li>Experience goes here</li>\n" +
                "                        </ul>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"watermark\">Generated by ResumeHub</div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <div style=\"text-align: center; margin-top: 20px;\">\n" +
                "        <button id=\"download-pdf\" style=\"background-color: #3498db; color: #fff; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;\">Download PDF</button>\n" +
                "    </div>\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.js\"></script>\n" +
                "    <script>\n" +
                "        document.getElementById('download-pdf').addEventListener('click', function(){\nconst content = document.querySelector('.resume-container');\n" +
                "            const opt = {\n" +
                "                margin: 0,\n" +
                "                filename: 'resume_ResumeHub.pdf',\n" +
                "                image: { type: 'png', quality: 1 },\n" +
                "                html2canvas: { scale: 2 },\n" +
                "                jsPDF: { unit: 'pt', format: 'a4', orientation: 'portrait' }\n" +
                "            };\n" +
                "            html2pdf().from(content).set(opt).save().then(function() {\n" +
                "                downloadButton.style.visibility = 'visible';\n" +
                "            });\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Template_2.html"));
            writer.write(htmlContent);
            writer.close();
            //System.out.println("HTML file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
