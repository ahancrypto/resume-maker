package ResumeMaker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DefaultTemplate {
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
                "            overflow-y: scroll; /* Always show vertical scrollbar */\n" +
                "        }\n" +
                "\n" +
                "        .resume-container {\n" +
                "            display: flex;\n" +
                "            margin: 0 auto;\n" +
                "            width: 20cm; /* A4 width */\n" +
                "            height: 19cm; /* A4 height */\n" +
                "            position: relative; /* Required for watermark positioning */\n" +
                "        }\n" +
                "\n" +
                "        .side-border {\n" +
                "            background-color: yellow;\n" +
                "            width: 2.5cm;\n" +
                "        }\n" +
                "\n" +
                "        .content {\n" +
                "            padding: 1.5cm;\n" +
                "            width: calc(100% - 2.5cm);\n" +
                "            box-sizing: border-box;\n" +
                "            position: relative;\n" +
                "        }\n" +
                "\n" +
                "        .header {\n" +
                "            display: flex;\n" +
                "            justify-content: space-between;\n" +
                "            align-items: center;\n" +
                "        }\n" +
                "\n" +
                "        .name-section {\n" +
                "            max-width: 70%;\n" +
                "        }\n" +
                "\n" +
                "        .name-section h1 {\n" +
                "            margin: 0;\n" +
                "            font-size: 35px;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "\n" +
                "        .name-section p {\n" +
                "            margin: 20px 0;\n" +
                "            font-size: 16px;\n" +
                "            font-weight: bold;\n" +
                "        }\n" +
                "\n" +
                "        .photo-section img {\n" +
                "            width: 300px;\n" +
                "            height: 300px;\n" +
                "            object-fit: cover;\n" +
                "            border: 2px solid #000;\n" +
                "        }\n" +
                "\n" +
                "        .section {\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .section h2 {\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            text-decoration: underline;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .section ul {\n" +
                "            list-style-type: disc;\n" +
                "            margin: 0;\n" +
                "            padding-left: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .section ul li {\n" +
                "            margin-bottom: 20px;\n" +
                "            font-size: 16px;\n" +
                "        }\n" +
                "\n" +
                "        .profile-section {\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "\n" +
                "        .profile-section h2 {\n" +
                "            font-size: 20px;\n" +
                "            font-weight: bold;\n" +
                "            text-decoration: underline;\n" +
                "            margin-bottom: 10px;\n" +
                "        }\n" +
                "\n" +
                "        .profile-section p {\n" +
                "            font-size: 16px;\n" +
                "            margin: 0;\n" +
                "        }\n" +
                "\n" +
                "        .download-pdf {\n" +
                "            position: absolute;\n" +
                "            bottom: 20px;\n" +
                "            left: 50%; /* Shift the button to the middle horizontally */\n" +
                "            transform: translateX(-50%); /* Adjust to center the button */\n" +
                "            background-color: #007bff;\n" +
                "            color: #fff;\n" +
                "            padding: 10px 20px;\n" +
                "            border-radius: 5px;\n" +
                "            text-decoration: none;\n" +
                "        }\n" +
                "\n" +
                "        .watermark {\n" +
                "            position: absolute;\n" +
                "            bottom: 20px;\n" +
                "            right: 20px;\n" +
                "            font-size: 12px;\n" +
                "            color: #aaa;\n" +
                "        }\n" +
                "\n" +
                "        /* Hide the download button in the PDF */\n" +
                "        @media print {\n" +
                "            .download-pdf {\n" +
                "                display: none !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/html2pdf.js/0.10.1/html2pdf.bundle.js\"></script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"resume-container\">\n" +
                "        <div class=\"side-border\"></div>\n" +
                "        <div class=\"content\">\n" +
                "            <!-- Watermark -->\n" +
                "            <div class=\"watermark\">Generated by ResumeHub</div>\n" +
                "            <div class=\"header\">\n" +
                "                <div class=\"name-section\">\n" +
                "                    <h1>Name Goes Here</h1>\n" +
                "                    <p>Email: Email goes here</p>\n" +
                "                    <p>Phone: Number goes here</p>\n" +
                "                </div>\n" +
                "                <div class=\"photo-section\">\n" +
                "                    <img src=\"photo-placeholder.png\" alt=\"User Photo\">\n" +
                "                </div>\n" +
                "            </div>\n" +
                "            <div class=\"profile-section\">\n" +
                "                <h2>Profile</h2>\n" +
                "                <p>About me goes here</p>\n" +
                "            </div>\n" +
                "            <div class=\"section\">\n" +
                "                <h2>Work Experience</h2>\n" +
                "                <ul>\n" +
                "                    <li>Experience goes here</li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <div class=\"section\">\n" +
                "                <h2>Education</h2>\n" +
                "                <ul>\n" +
                "                    <li>Education goes here</li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <div class=\"section\">\n" +
                "                <h2>Skills</h2>\n" +
                "                <ul>\n" +
                "                    <li>Skills goes here</li>\n" +
                "                </ul>\n" +
                "            </div>\n" +
                "            <a href=\"#\" class=\"download-pdf\" id=\"download-pdf\">Download PDF</a>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "    <script>\n" +
                "        document.getElementById('download-pdf').addEventListener('click', function() {\n" +
                "            const content = document.querySelector('.resume-container');\n" +
                "            const downloadButton = document.querySelector('.download-pdf');\n" +
                "            downloadButton.style.visibility = 'hidden';\n" +
                "            var opt = {\n" +
                "                margin:       0,\n" +
                "                filename:     'resume_ResumeHub.pdf',\n" +
                "                image:        { type: 'png', quality: 100 },\n" +
                "                html2canvas:  { scale: 5 },\n" +
                "                jsPDF:        { unit: 'mm', format: 'a4', orientation: 'portrait' }\n" +
                "            };\n" +
                "            html2pdf().from(content).set(opt).save().then(function() {\n" +
                "                downloadButton.style.visibility = 'visible';\n" +
                "            });\n" +
                "        });\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>";


        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Template_default.html"));
            writer.write(htmlContent);
            writer.close();
            //System.out.println("HTML file created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
}
