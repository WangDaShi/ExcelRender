package test.com.loatr.excel;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 测试使用bean
 */
public class ExcelInfo {

    private String message;
    private LocalDateTime date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
