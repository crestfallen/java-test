package test.mymmsc.api.dbm;

import java.io.Serializable;

public class TestObject implements Serializable {
    private static final long serialVersionUID = 127140610913856720L;

    private String name = null;
    private int ago = 0;

    public TestObject(String name, int ago) {
        this.setName(name);
        this.setAgo(ago);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the ago
     */
    public int getAgo() {
        return ago;
    }

    /**
     * @param ago the ago to set
     */
    public void setAgo(int ago) {
        this.ago = ago;
    }
}
