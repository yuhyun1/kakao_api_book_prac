package view;

import model.Book;

import javax.json.JsonObject;
import java.util.List;

public class OutputPrinter {
    public void bookName() {
        System.out.print("도서를 검색할 제목을 입력하세요: ");
    }

    public void printBookInfo(List<JsonObject> extractedInfoList) {

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("도서 제목 | 가격 | 출판사 | 작가 | 할인 가격 | ISBN");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (JsonObject document : extractedInfoList) {

            System.out.println(document.getString("title")
                    + " | " + document.getInt("price")
                    + " | " + document.getString("publisher")
                    + " | " + document.getJsonArray("authors").getString(0)
                    + " | " + document.getInt("sale_price")
                    + " | " + document.getString("isbn"));

        }

    }

    public void printAskSave() {
        System.out.print("\n" + "데이터베이스에 저장하시겠습니까? (Y/N) ");
    }

    public void printSaveSuccessfully() {
        System.out.println("\n" + "저장 성공");
    }

    public void printFailToSave() {
        System.out.println("\n" + "저장 실패");
    }

    public void printTableList(List<Book> bookList) {

        System.out.println("\n" + "[TABLE LIST]" + "\n");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("도서 제목 | 가격 | 출판사 | 작가 | 할인 가격 | ISBN");
        System.out.println("-----------------------------------------------------------------------------------------");

        if (bookList.size() != 0) {
            for (Book book : bookList) {
                System.out.println(book);
            }
        } else {
            System.out.println("데이터가 없습니다.");
        }

    }

    public void printNotCorrectAnswer() {
        System.out.println("유효하지 않은 입력입니다. 다시 입력해 주세요.");
    }

}
