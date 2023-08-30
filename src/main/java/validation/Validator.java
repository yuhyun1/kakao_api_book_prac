package validation;

import view.OutputPrinter;

public class Validator {
    OutputPrinter outputPrinter = new OutputPrinter();

    public String modify(String book) {
        return book.replaceAll("\\s", "");
    }

    public void checkSave(int cnt) {
        if (cnt > 0) {
            outputPrinter.printSaveSuccessfully();
        } else {
            outputPrinter.printFailToSave();
        }
    }

    public boolean checkCorrectAnswer(String saveAnswer) {
        if (saveAnswer.equals("Y") || saveAnswer.equals("N")) {
            return true;
        } else {
            return false;
        }
    }

}