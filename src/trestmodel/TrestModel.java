package trestmodel;

import entityProduction.Trest;
import persistence.loader.DataSet;

import java.util.Locale;

/**
 * Created by Max on 19.02.2016.
 */
public class TrestModel {

    private DataSet dataSet;

    private Trest trest;

    public Locale locale;

    public TrestModel() {
        setLocale("ru"); //en
        this.dataSet = new DataSet();
     //   dataSet.openDataSet();						// Считываем данные из XML-файлов в поля DataSet.
        this.trest= dataSet.getTrest(1);					// Инициализируем объект Трест по id=1


    }

    public DataSet getDataSet() {
        return dataSet;
    }

    public void setDataSet(DataSet dataSet) {

        this.dataSet = dataSet;
    }

    public Trest getTrest() {
        return trest;
    }

    public void setTrest(Trest trest) {
        this.trest = trest;
    }


    /**Set the language for the application
     * @param s Language of the application ( new Locale(s))
     */
    private void setLocale(String s) {
        locale = Locale.getDefault();
        if (s != "") {
            locale = new Locale(s);
            Locale.setDefault(locale);
        }
    }

}
