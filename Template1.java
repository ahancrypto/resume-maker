package ResumeMaker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Template1 {
    public static void main() {
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Resume</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: 'Arial', sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "            display: flex;\n" +
                "            justify-content: center;\n" +
                "            align-items: center;\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "        .resume-container {\n" +
                "            width: 210mm;\n" +
                "            max-width: 100%;\n" +
                "            height: 180mm;\n" +
                "            background-color: #fff;\n" +
                "            border: 1px solid #ccc;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "            display: flex;\n" +
                "            flex-direction: row;\n" +
                "            overflow: hidden;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        .left-column {\n" +
                "            width: 30%;\n" +
                "            background-color: #296e9a;\n" +
                "            color: #ecf0f1;\n" +
                "            padding: 20px;\n" +
                "            box-sizing: border-box;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        .left-column img {\n" +
                "            border-radius: 50%;\n" +
                "            width: 120px;\n" +
                "            height: 120px;\n" +
                "            object-fit: cover;\n" +
                "            position: absolute;\n" +
                "            top: 20px;\n" +
                "            left: 50%;\n" +
                "            transform: translateX(-50%);\n" +
                "            border: 5px solid #fff;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "        .left-column h2 {\n" +
                "            margin-top: 150px;\n" +
                "            text-align: center;\n" +
                "            font-size: 24px;\n" +
                "        }\n" +
                "        .left-column .contact-info, .left-column .skills {\n" +
                "            margin: 20px 0;\n" +
                "            padding: 0 20px;\n" +
                "        }\n" +
                "        .left-column .contact-info ul, .left-column .skills ul {\n" +
                "            list-style: none;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .left-column .contact-info ul li, .left-column .skills ul li {\n" +
                "            margin-bottom: 10px;\n" +
                "            font-size: 14px;\n" +
                "        }\n" +
                "        .left-column .skills ul li {\n" +
                "            background-color: #34495e;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 3px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .right-column {\n" +
                "            width: 70%;\n" +
                "            padding: 40px;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "        .right-column h1 {\n" +
                "            margin-top: 0;\n" +
                "            font-size: 28px;\n" +
                "            color: #2c3e50;\n" +
                "        }\n" +
                "        .about-me {\n" +
                "            font-size: 16px;\n" +
                "            margin: 20px 0;\n" +
                "            color: #7f8c8d;\n" +
                "        }\n" +
                "        .section {\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .section-title {\n" +
                "            font-size: 18px;\n" +
                "            color: #2c3e50;\n" +
                "            margin: 10px 0;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        .section-title:before {\n" +
                "            content: \"\";\n" +
                "            width: 30px;\n" +
                "            height: 3px;\n" +
                "            background-color: #2c3e50;\n" +
                "            position: absolute;\n" +
                "            bottom: -5px;\n" +
                "            left: 0;\n" +
                "        }\n" +
                "        .section-content ul {\n" +
                "            padding-left: 20px;\n" +
                "        }\n" +
                "        .section-content ul li {\n" +
                "            list-style-type: disc;\n" +
                "            margin-bottom: 10px;\n" +
                "            font-size: 14px;\n" +
                "            color: #7f8c8d;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"resume-container\">\n" +
                "        <div class=\"left-column\">\n" +
                "            <img src=\"photo-placeholder.png\" alt=\"User Photo\">\n" +
                "<br></br>\n"+
                 "<br></br>\n"+
                  "<br></br>\n"+
                "            <h1>Name goes here</h1>\n" +
                "            <div class=\"contact-info\">\n" +
                "                <h3>Contact Information</h3>\n" +
                "                <ul>\n" +
                "                    <p>Email: Email goes here</p>\n" +
                "                    <p>Phone: Number goes here</p>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <div class=\"skills\">\n" +
                "                <h3>Skills</h3>\n" +
                "                <ul>\n" +
                "                    <li>Skills goes here</li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"right-column\">\n" +
                 "<div class=\"section-title\">About Me</div>\n"+
                
                "            <p>About me goes here</p>\n" +
                "            <div class=\"section education\">\n" +
                "                <div class=\"section-title\">Education</div>\n" +
                "                <div class=\"section-content\">\n" +
                "                    <ul>\n" +
                "                        <li>Education goes here</li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"section work-experience\">\n" +
                "                <div class=\"section-title\">Work Experience</div>\n" +
                "                <div class=\"section-content\">\n" +
                "                    <ul>\n" +
                "                        <li>Experience goes here</li>\n" +
                "                    </ul>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <button id=\"downloadButton\" style=\"position: fixed; bottom: 20px; right: 20px; padding: 10px 20px; background-color: blue; color: white; border: none; cursor: pointer;\">Download PDF</button>\n" +
                "    <script>\n" +
                "        const downloadButton = document.getElementById('downloadButton');\n" +
                "        downloadButton.style.visibility = 'hidden';\n" +
                "        downloadButton.addEventListener('click', function() {\n" +
                "            const content = document.querySelector('.resume-container');\n" +
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
            BufferedWriter writer = new BufferedWriter(new FileWriter("Template_1.html"));
            writer.write(htmlContent);
            writer.close();
            //System.out.println("HTML file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
