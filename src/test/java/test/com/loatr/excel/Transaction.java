package test.com.loatr.excel;

import java.time.LocalDateTime;

/**
 * 测试使用bean
 */
public class Transaction {

    private String id;
    private String name;
    private int num;
    private float fee;
    private LocalDateTime time;
    private Transaction tran;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Transaction getTran() {
        return tran;
    }

    public void setTran(Transaction tran) {
        this.tran = tran;
    }
}
