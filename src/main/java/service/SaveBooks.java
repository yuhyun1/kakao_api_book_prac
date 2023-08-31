package service;

import dao.BookDao;
import model.Book;
import validation.Validator;

import javax.json.JsonObject;
import java.util.List;

public class SaveBooks {

    private static final Book book = new Book();
    private static final BookDao dao = new BookDao();
    private static final Validator VALIDATOR = new Validator();
    private static int cnt;

    public static void setBookList(List<JsonObject> extractedInfoList) {

        for (JsonObject document : extractedInfoList) {

            book.setBooktitle(document.getString("title"));
            book.setPrice(document.getInt("price"));
            book.setPublisher(document.getString("publisher"));
            book.setAuthor(document.getJsonArray("authors").getString(0));
            book.setSaleprice(document.getInt("sale_price"));
            book.setIsbn(document.getString("isbn"));

            saveBooks();

        }

        VALIDATOR.checkSave(cnt);
    }

    public static void saveBooks() {

        cnt = dao.bookRegister(book);

    }

}