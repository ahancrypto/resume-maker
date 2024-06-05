package ResumeMaker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Template3 {
    public static void main() {
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Resume </title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #effaf9;\n" +
                "        }\n" +
                "        .resume-container {\n" +
                "            width: 210mm;\n" +
                "            max-width: 100%;\n" +
                "            height: 250mm;\n" +
                "            margin: 20px auto;\n" +
                "            background-color: #fff;\n" +
                "            border: 1px solid #ccc;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "            box-sizing: border-box;\n" +
                "            padding: 20px;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        .header {\n" +
                "            background-color: #296e9a;\n" +
                "            color: #fff;\n" +
                "            padding: 20px;\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            position: relative;\n" +
                "            height: 150px; /* Adjust height to fit the image */\n" +
                "        }\n" +
                "        .header img {\n" +
                "            border-radius: 50%;\n" +
                "            width: 150px;\n" +
                "            height: 150px;\n" +
                "            object-fit: cover;\n" +
                "            position: absolute;\n" +
                "            top: 50%;\n" +
                "            transform: translateY(-50%);\n" +
                "            left: 20px;\n" +
                "            border: 5px solid #fff;\n" +
                "            box-sizing: border-box;\n" +
                "        }\n" +
                "        .header-content {\n" +
                "            margin-left: 180px; /* Adjust based on the image width + margin */\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            justify-content: center;\n" +
                "        }\n" +
                "        .header h1 {\n" +
                "            margin: 0;\n" +
                "            font-size: 24px;\n" +
                "        }\n" +
                "        .header .about-me {\n" +
                "            margin-top: 10px;\n" +
                "            font-size: 14px;\n" +
                "            color: #d1e8f0;\n" +
                "        }\n" +
                "        .section {\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .section-title {\n" +
                "            font-size: 18px;\n" +
                "            color: #296e9a;\n" +
                "            margin: 10px 0;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "        .section-title:before {\n" +
                "            content: \"\";\n" +
                "            width: 30px;\n" +
                "            height: 3px;\n" +
                "            background-color: #296e9a;\n" +
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
                "        .contact-info ul {\n" +
                "            padding-left: 0;\n" +
                "        }\n" +
                "        .contact-info ul li {\n" +
                "            list-style-type: none;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "        .skills ul li {\n" +
                "            background-color: #34495e;\n" +
                "            padding: 5px;\n" +
                "            border-radius: 3px;\n" +
                "            text-align: center;\n" +
                "            color: #ecf0f1;\n" +
                "            margin-bottom: 5px;\n" +
                "        }\n" +
                "        .watermark {\n" +
                "            position: absolute;\n" +
                "            bottom: 10px;\n" +
                "            right: 10px;\n" +
                "            font-size: 16px;\n" +
                "            color: rgba(0.3, 0.3, 0.3, 0.3);\n" +
                "            " +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"resume-container\">\n" +
                "        <div class=\"header\">\n" +
                "           <img src=\"photo-placeholder.png\" alt=\"User Photo\">\n" +
                "            <div class=\"header-content\">\n" +
                "                <h1>Name goes here</h1>\n" +
                "                <p>About me goes here</p>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"section contact-info\">\n" +
                "            <div class=\"section-title\">Contact Information</div>\n" +
                "            <div class=\"section-content\">\n" +
                "                <ul>\n" +
                "                    <p>Email: Email goes here</p>\n" +
                "                    <p>Phone: Number goes here</p>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"section education\">\n" +
                "            <div class=\"section-title\">Education</div>\n" +
                "            <div class=\"section-content\">\n" +
                "                <ul>\n" +
                "                    <li>Education goes here</li>\n" +
                "                   " +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"section work-experience\">\n" +
                "            <div class=\"section-title\">Work Experience</div>\n" +
                "            <div class=\"section-content\">\n" +
                "                <ul>\n" +
                "                    \n" +
                "                       " +
                "                        <ul>\n" +
                "                            <li>Experience goes here</li>\n" +
                "                            " +
                "                            " +
                "                        </ul>\n" +
                "                    </li>\n" +
                "                    " +
                                        
                "                        </ul>\n" +
                "                    </li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"section skills\">\n" +
                "            <div class=\"section-title\">Skills</div>\n" +
                "            <div class=\"section-content\">\n" +
                "                <ul>\n" +
                "                    <li>Skills goes here</li>\n" +
               
                "                </ul>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"watermark\">Generated by ResumeHub</div>\n" +
                "    </div>\n" +
                "    <button id=\"downloadButton\" style=\"position: fixed; bottom: 20px; right: 20px; padding: 10px 20px; background-color: blue; color: white; border: none; cursor: pointer;\">Download PDF</button>\n" +
                 "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.js\"></script>\n"+
                "    <script>\n" +
                "        const downloadButton = document.getElementById('downloadButton');\n" +
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
            BufferedWriter writer = new BufferedWriter(new FileWriter("Template_3.html"));
            writer.write(htmlContent);
            writer.close();
            //System.out.println("HTML file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
