package prodigy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;

public class ECommerceScraper {
    public static void main(String[] args) {
        String url = "https://www.amazon.com/Best-Sellers/zgbs";

        try {
            Document document = Jsoup.connect(url).get();
            Elements productElements = document.select("div.p13n-sc-truncate");

            FileWriter csvWriter = new FileWriter("products.csv");
            csvWriter.append("Product Name,Price,Rating\n");

            for (Element productElement : productElements) {
                String name = productElement.text();
                String price = "";
                String rating = "";

                Element priceElement = productElement.parent().selectFirst("span.a-price");
                if (priceElement != null) {
                    price = priceElement.text();
                }

                Element ratingElement = productElement.parent().selectFirst("span.a-icon-alt");
                if (ratingElement != null) {
                    rating = ratingElement.text();
                }

                csvWriter.append(name).append(",").append(price).append(",").append(rating).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();

            System.out.println("Product information extracted and stored in products.csv");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

