package trestview.table.tablemodel.abstracttablemodel;
/**
 * column settings
 */
public class ParametersColumn  {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getcLs() {
        return cLs;
    }

    public void setcLs(Class cLs) {
        this.cLs = cLs;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    private String name;
    private Class cLs;
    private boolean editable;
    private double  width;
    public ParametersColumn (String name, Class cLs, boolean editable, double  width) {
        this.name=name;
        this.cLs = cLs;
        this.editable = editable;
        this.width = width;
    }
}