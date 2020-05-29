package departments.storage;

import departments.VisitedLink;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class LinkStatusFileSaver implements LinkStatusSaver {

    @Override
    public File save(Collection<VisitedLink> links) throws InterruptedException {
        String filePrefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        StringBuilder builder = new StringBuilder();

        links.stream()
                .forEach(link -> builder.append(link.getLink())
                        .append(", ")
                        .append(link.getTitle())
                        .append(", ")
                        .append(link.isAvailable() ? "OK" : "Dead link")
                        .append("\n")
                );

        try {
            String tempPath = System.getProperty("java.io.tmpdir");
            Path path = Files.createFile(Paths.get(tempPath,String.format("%s_results.txt", filePrefix)));
            File file = path.toFile();
            // writing sample data
            Files.write(path, builder.toString().getBytes(StandardCharsets.UTF_8));
            System.out.println(String.format("File saved to path: %s", file.getAbsolutePath()));
            return file;
        } catch (IOException e) {
            System.out.println("Error when saving file");
        }

        return null;
    }
}

