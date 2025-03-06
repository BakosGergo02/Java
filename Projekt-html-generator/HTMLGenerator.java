import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HTMLGenerator {
    private final List<String> supportedExtensions;
    private final String rootPath;

    public HTMLGenerator(String rootPath, List<String> supportedExtensions) {
        this.rootPath = rootPath;
        this.supportedExtensions = supportedExtensions;
    }

    
    private String getPreviousImageHtml(List<File> images, File current) {
        if (images.indexOf(current) == 0) {
            return fileNameToHtml(current);
        }
    
        return fileNameToHtml(images.get(images.indexOf(current) - 1));
    }
    
    
    private String getNextImageHtml(List<File> images, File current) {
        if (images.indexOf(current) == images.size() - 1) {
            return fileNameToHtml(current);
        }

        return fileNameToHtml(images.get(images.indexOf(current) + 1));
    }
    
    // Csak az adott könyvtárat járja be és Listában visszaadja az összes filet rendezve
    // Ha üres a könyvtár, akkor egy üres listát ad vissza
    // A megfelelő fájlkiterjesztésekre itt történik meg a szűrés, ami case sensitive
    private List<File> getFiles(File file) {
        List<File> files = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isFile() && supportedExtensions.contains(f.getName().substring(f.getName().lastIndexOf('.') + 1))) {
                files.add(f);
            }
        }
        
        Collections.sort(files);
        return files;
    }
    
    // Csak az adott könyvtárat járja be és Listában visszaadja az összes könyvtárat rendezve
    // Ha üres a könyvtár, akkor egy üres listát ad vissza
    private List<File> getDirectories(File file) {
        List<File> directories = new ArrayList<>();
        for (File f : file.listFiles()) {
            if (f.isDirectory()) {
                directories.add(f);
            }
        }

        Collections.sort(directories);
        return directories;
    }



    private String fileNameToHtml(File f) {
        return f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf('.')).concat(".html");
    }
    

    // Megnézi, hogy milyen mélyen van a könyvtár a root-hoz képest
    // Ennek megfelelően generál n*(../)index.html Stringet
    private String generateStartPageReference(String path, boolean isIndexHTML) {
        int depth = 0;
        if (!isIndexHTML) {
            depth = -1;
        }
        String normalizedPath = new File(path).getAbsolutePath();
        String normalizedRootPath = new File(rootPath).getAbsolutePath();

        if (!normalizedPath.startsWith(normalizedRootPath)) {
            throw new IllegalArgumentException("A path nem a root-hoz képest közelíti");
        }
    
        for (char c : normalizedPath.replace(normalizedRootPath, "").toCharArray()) {
            if (c == File.separatorChar) {
                depth++;
            }
        }

        if (depth < 0) {
            depth = 0;
        }

        return ("../".repeat(depth) + "index.html");
    }



    // Képek html fájljainak generálása
    private void generateImageHtmls(File root) {
        List<File> images = getFiles(root);
        Collections.sort(images);
        for (File f : images) {
            try {
                PrintWriter writer = new PrintWriter(fileNameToHtml(f), StandardCharsets.UTF_8);
                writer.printf("<h2><a href=\"%s\">Start Page</a></h2>\n", generateStartPageReference(f.getPath(), false));
                writer.println("<hr>");
                writer.println("<p style=\"font-size: larger\">");
                writer.println("<a href=\"index.html\">^^</a>");
                writer.println("<br><br>");
                writer.printf("<a href=\"%s\"><<</a>\n", getPreviousImageHtml(images, f));
                writer.printf("<b>%s</b>\n", f.getName());
                writer.printf("<a href=\"%s\">>></a>\n", getNextImageHtml(images, f));
                writer.println("</p>");
                writer.printf("<a href=\"%s\"><img src=\"%s\" width=\"500\"></a>\n", getNextImageHtml(images, f), f.getName());

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    // Index html fájlok generálása
    private void generateIndexHtml(File root) {
        List<File> directories = getDirectories(root.getParentFile());
        
        for (File directory : directories) {
            try {
                PrintWriter writer = new PrintWriter(directory.getAbsolutePath() + "/index.html", StandardCharsets.UTF_8);
                writer.printf("<h2><a href=\"%s\">Start Page</a></h2>\n", generateStartPageReference(directory.getPath(), true));
                writer.println("<hr>");
                writer.println("<h2>Directories:</h2>");
                writer.println("<ul>");
                if (!rootPath.equals(directory.getAbsolutePath())) {
                    writer.printf("<li><a href=\"%s\">< <</a></li>\n", "../index.html");
                }
                for (File subDirectory : getDirectories(directory)) {
                    writer.printf("<li><a href=\"%s\">%s</a></li>", subDirectory.getName() + "/index.html", subDirectory.getName());
                }
                writer.println("</ul>");
                writer.println("<hr>");
                writer.println("<h2>Images:</h2>");
                writer.println("<ul>");
                for (File image : getFiles(directory)) {
                    writer.printf("<li><a href=\"%s\">%s</a></li>\n", fileNameToHtml(image), image.getName());
                }
                writer.println("</ul>");
    
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    


    



    // A könyvtárak rekurzív bejárása és a megfelelő html fájlok generálása
    public void generateHtmls(String path) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;

        System.out.printf("%s: index.html generálása\n", path);
        generateIndexHtml(root);
        System.out.printf("%s: kép html-ek generálása\n", path);
        generateImageHtmls(root);

        for (File f : list) {
            if (f.isDirectory()) {
                generateHtmls(f.getAbsolutePath());
            }
        }
    }





    

    
    

}
