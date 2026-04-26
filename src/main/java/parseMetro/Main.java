package parseMetro;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(new DefaultIndenter("  ", "\r\n"));

        Document doc = Jsoup.parse(new File("C:\\Users\\Admin\\IdeaProjects\\nyanya\\src\\main\\java\\parseMetro\\metro.html"));
        Elements lineElements = doc.select(".js-metro-line");

        Map<String, Object> metroData = new LinkedHashMap<>();
        Map<String, List<String>> lines = new LinkedHashMap<>();
        List<Map<String, Object>> connections = new ArrayList<>();

        for (Element lineElement : lineElements) {
            String lineId = lineElement.attr("data-line");
            String lineName = lineElement.text();
            Elements stationElements = lineElement.parent().parent().select("a.listingMetrost_st");
            List<String> stationNames = new ArrayList<>();

            for (Element stationElement : stationElements) {
                Element stationNameElement = stationElement.selectFirst("span.name");
                String stationName = stationNameElement.text();
                stationNames.add(stationName);


                Elements connectionElements = stationElement.select("span.t-icon-metroln");
                if (connectionElements.size() > 1) {
                    Map<String, Object> connection = new LinkedHashMap<>();
                    connection.put("line", lineId);
                    connection.put("station", stationName);
                    List<String> connectedLines = new ArrayList<>();
                    for (int i = 1; i < connectionElements.size(); i++) {
                        String connectedLineClass = connectionElements.get(i).attr("class");
                        String connectedLineId = connectedLineClass.split(" ")[1].substring(13);
                        connectedLines.add(connectedLineId);
                    }
                    connection.put("connected_lines", connectedLines);
                    connections.add(connection);
                }
            }
            lines.put(lineName, stationNames);
        }

        metroData.put("stations", lines);
        metroData.put("connections", connections);

        try (FileWriter fileWriter = new FileWriter("C:\\Users\\Admin\\IdeaProjects\\nyanya\\src\\main\\java\\parseMetro\\metro.json")) {
            mapper.writer(printer).writeValue(fileWriter, metroData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}