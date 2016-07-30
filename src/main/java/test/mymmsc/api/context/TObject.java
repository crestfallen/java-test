package test.mymmsc.api.context;

import org.mymmsc.api.assembly.BeanAlias;

import java.util.List;

public class TObject {
    private List<Bill> bills;
    @BeanAlias("errorid,errno")
    private long status;
    private Object message;
    private TOrder order;

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * @return the order
     */
    public TOrder getOrder() {
        return order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(TOrder order) {
        this.order = order;
    }
}
