package persistence.loader;

import old._util._Date;
import persistence.loader.tabDataSet.*;
import entityProduction.*;
import org.w3c.dom.DOMException;

import javax.xml.parsers.ParserConfigurationException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSet {

    public <cL> Object setTabXML(Object tab, Class cL) {
        FieldClass fc = getFieldName_Class(tab);
        Object o = new Object();
        try {
            o = XmlRW.readTab(pathFile(fc.fieldName), cL);
        } catch (Throwable exp) {
            exp.printStackTrace();
        }
        return o;
    }

    public void writeTab(Object tab) {
        FieldClass fc = getFieldName_Class(tab);
        try {
            XmlRW.writeTab(pathDataDefault + fc.fieldName, tab, fc.fieldName, fc.className);
        } catch (ParserConfigurationException exp) {
            exp.printStackTrace();
        } catch (Throwable exp) {
            exp.printStackTrace();
        }
    }

    static public ArrayList<RowSetting> tSettings;                    // установки и настройки системы. Хранятся в коллекции разные для разных перспектив. Модификатор доступа static , т.к. хранятся гастройки отдельно от объектов

    private ArrayList<RowTrest> tabTrests;                    //
    private ArrayList<RowWork> tabWorks;
    private ArrayList<RowTrestWork> tabTrestsWorks;

    private ArrayList<RowMachine> tabMachines;
    private ArrayList<RowWorkMachine> tabWorksMachines;

    private ArrayList<RowModelmachine> tabModelmachines;

    private ArrayList<RowEmployee> tabEmployees;
    private ArrayList<RowWorkEmployee> tabWorksEmployees;

    private ArrayList<RowSubject_labour> tabSubject_labours;
    private ArrayList<RowWorkSubject_labour> tabWorksSubject_labours;

    private ArrayList<RowOrder> tabOrders;
    private ArrayList<RowWorkOrder> tabWorksOrders;

    private ArrayList<RowLine> tabLines;
    private ArrayList<RowOrderLine> tabOrdersLines;

    private ArrayList<RowLineSubject_labour> tabLinesSubject_labours;
    private ArrayList<RowLineUnit> tabLinesUnits;                    // <RowLineUnit>

    private ArrayList<RowRoute> tabRoutes;
    private ArrayList<RowRouteLineroute> tabRoutesLineroutes;
    private ArrayList<RowResource> tabResources;

    private ArrayList<RowSubject_labourRoute> tabSubject_laboursRoutes;

    private ArrayList<RowUnit> tabUnits;

    private ArrayList<RowLinespec> tabLinespecs;

    private ArrayList<RowLinespecResource> tabLinespecsResources;

    private ArrayList<RowLinespecUnit> tabLinespecsUnits;
    private ArrayList<RowFunctionOEM> tabFunctionOEMs;
    private ArrayList<RowLinespecFunctionOEM> tabLinespecsFunctionOEMs;

    private ArrayList<RowLineroute> tabLineroutes;
    private ArrayList<RowOperation> tabOperations;
    private ArrayList<RowLinerouteOperation> tabLineroutesOperations;
    private ArrayList<RowLinerouteMachine> tabLineroutesMachines;
    private ArrayList<RowLinerouteEmployee> tabLineroutesEmployees;
    private ArrayList<RowLinerouteLinespec> tabLineroutesLinespecs;
    private ArrayList<RowTypemachine> tabTypemachines;
    private ArrayList<RowModelmachineTypemachine> tabModelmachineTypemachines;
    private ArrayList<RowMachineModelmachine> tabMachineModelmachines;


    /* Определяет путь к директории  (папке) с Базой данных ХМL */
    private String pathDataDefault;
    static private String pathConfig = "config/";
    /*_____________________________________________________________________________*/

    /**
     * Инициализация переменных
     *
     * @throws Throwable
     */
    public DataSet() {
        tSettings = new ArrayList<RowSetting>();
        tabTrests = new ArrayList<RowTrest>();
        tabWorks = new ArrayList<RowWork>();
        tabTrestsWorks = new ArrayList<RowTrestWork>();
        tabMachines = new ArrayList<RowMachine>();
        tabWorksMachines = new ArrayList<RowWorkMachine>();
        tabEmployees = new ArrayList<RowEmployee>();
        tabWorksEmployees = new ArrayList<RowWorkEmployee>();
        tabSubject_labours = new ArrayList<RowSubject_labour>();
        tabSubject_laboursRoutes = new ArrayList<RowSubject_labourRoute>();
        tabWorksSubject_labours = new ArrayList<RowWorkSubject_labour>();
        tabOrders = new ArrayList<RowOrder>();
        tabWorksOrders = new ArrayList<RowWorkOrder>();
        tabLines = new ArrayList<RowLine>();
        tabLinesSubject_labours = new ArrayList<RowLineSubject_labour>();
        tabLinesUnits = new ArrayList<RowLineUnit>();    //<RowLineUnit>
        tabUnits = new ArrayList<RowUnit>();
        tabOrdersLines = new ArrayList<RowOrderLine>();
        tabOperations = new ArrayList<RowOperation>();
        tabRoutes = new ArrayList<RowRoute>();
        tabRoutesLineroutes = new ArrayList<RowRouteLineroute>();
        tabLinespecs = new ArrayList<RowLinespec>();

        tabResources = new ArrayList<RowResource>();
        tabLinespecsResources = new ArrayList<RowLinespecResource>();
        tabLinespecsUnits = new ArrayList<RowLinespecUnit>();
        tabFunctionOEMs = new ArrayList<RowFunctionOEM>();
        tabLinespecsFunctionOEMs = new ArrayList<RowLinespecFunctionOEM>();
        tabLineroutes = new ArrayList<RowLineroute>();
        tabLineroutesOperations = new ArrayList<RowLinerouteOperation>();
        tabLineroutesMachines = new ArrayList<RowLinerouteMachine>();
        tabLineroutesEmployees = new ArrayList<RowLinerouteEmployee>();
        tabLineroutesLinespecs = new ArrayList<RowLinerouteLinespec>();
        tabModelmachines = new ArrayList<RowModelmachine>();
        tabTypemachines = new ArrayList<RowTypemachine>();
        tabModelmachineTypemachines = new ArrayList<RowModelmachineTypemachine>();
        tabMachineModelmachines = new ArrayList<RowMachineModelmachine>();
        /*Матрица связей между таблицами в DataSet					*/
        /*						tabLines				tabSubject_labours			tabUnits
         * tabLines										tabLineSubject_labours
         * tabSubject_labours	tabLineSubject_labours
         * tabUnits
         */

        openDataSet();
    }

    /**
     * Чтение данных из XML файла для заполнения DataSet
     *
     * @throws Throwable
     */
    public void openDataSet() {
        pathDataDefault = pathConfig;
        tSettings = (ArrayList<RowSetting>) setTabXML(tSettings, RowSetting.class);
        try {
            showTab(tSettings, RowSetting.class);
        } catch (Throwable exp1) {
            exp1.printStackTrace();
        }
        pathDataDefault = tSettings.get(0).getSystemPath();
        System.out.println("Это путь pathDataDefault=" + pathDataDefault);
        System.out.println("Данные из XML файла считаны");
        tabTrests = (ArrayList<RowTrest>) setTabXML(tabTrests, RowTrest.class);                //showTab(tabTrests,RowTrest.class);
        tabWorks = (ArrayList<RowWork>) setTabXML(tabWorks, RowWork.class);                    //showTab(tabWorks, RowWork.class);
        tabTrestsWorks = (ArrayList<RowTrestWork>) setTabXML(tabTrestsWorks, RowTrestWork.class);        //showTab(tabTrestsWorks, RowTrestWork.class);
        tabMachines = (ArrayList<RowMachine>) setTabXML(tabMachines, RowMachine.class);            //showTab(tabMachines,RowMachine.class);

        tabWorksMachines = (ArrayList<RowWorkMachine>) setTabXML(tabWorksMachines, RowWorkMachine.class);    //showTab(tabWorksMachines,RowWorkMachine.class);

        tabEmployees = (ArrayList<RowEmployee>) setTabXML(tabEmployees, RowEmployee.class);            //showTab(tabEmployees,RowEmployee.class);
        tabWorksEmployees = (ArrayList<RowWorkEmployee>) setTabXML(tabWorksEmployees, RowWorkEmployee.class);    //showTab(tabWorksEmployees,RowWorkEmployee.class);

        tabSubject_labours = (ArrayList<RowSubject_labour>) setTabXML(tabSubject_labours, RowSubject_labour.class);        // 	showTab(tabSubject_labours,RowSubject_labour.class);

        tabSubject_laboursRoutes = (ArrayList<RowSubject_labourRoute>) setTabXML(tabSubject_laboursRoutes, RowSubject_labourRoute.class);        // 	showTab(tabSubject_laboursRoutes,RowSubject_labourRoute.class);

        tabWorksSubject_labours = (ArrayList<RowWorkSubject_labour>) setTabXML(tabWorksSubject_labours, RowWorkSubject_labour.class);    //showTab(tabWorksProducts,RowWorkProduct.class);

        tabOrders = (ArrayList<RowOrder>) setTabXML(tabOrders, RowOrder.class);                //showTab(tabOrders,Order.class);
        tabOrdersLines = (ArrayList<RowOrderLine>) setTabXML(tabOrdersLines, RowOrderLine.class);        //showTab(tabOrdersLines);
        tabWorksOrders = (ArrayList<RowWorkOrder>) setTabXML(tabWorksOrders, RowWorkOrder.class);        //showTab(tabWorksOrders,RowWorkOrder.class);

        tabLines = (ArrayList<RowLine>) setTabXML(tabLines, RowLine.class);                    // showTab(tabLines);
        tabLinesSubject_labours = (ArrayList<RowLineSubject_labour>) setTabXML(tabLinesSubject_labours, RowLineSubject_labour.class);            //		showTab(tabLinesSubject_labours);
        tabLinesUnits = (ArrayList<RowLineUnit>) setTabXML(tabLinesUnits, RowLineUnit.class);            // showTab(tabLinesUnits);			//<RowLineUnit>

        tabUnits = (ArrayList<RowUnit>) setTabXML(tabUnits, RowUnit.class);                    // showTab(tabUnits);
        tabRoutes = (ArrayList<RowRoute>) setTabXML(tabRoutes, RowRoute.class);                // showTab(tabRoutes);
        tabOperations = (ArrayList<RowOperation>) setTabXML(tabOperations, RowOperation.class);        // showTab(tabOperations);
        tabRoutesLineroutes = (ArrayList<RowRouteLineroute>) setTabXML(tabRoutesLineroutes, RowRouteLineroute.class);        // showTab(tabRoutesOperations);

        tabLinespecs = (ArrayList<RowLinespec>) setTabXML(tabLinespecs, RowLinespec.class);     //	 showTab(tabLinespecs);

        tabResources = (ArrayList<RowResource>) setTabXML(tabResources, RowResource.class);     //	 showTab(tabResources);
        tabLinespecsResources = (ArrayList<RowLinespecResource>) setTabXML(tabLinespecsResources, RowLinespecResource.class);        // showTab(tabLinespecsResources);
        tabLinespecsUnits = (ArrayList<RowLinespecUnit>) setTabXML(tabLinespecsUnits, RowLinespecUnit.class);        //  showTab(tabLinespecsUnits);
        tabFunctionOEMs = (ArrayList<RowFunctionOEM>) setTabXML(tabFunctionOEMs, RowFunctionOEM.class);        //   showTab(tabFunctionOEMs);
        tabLinespecsFunctionOEMs = (ArrayList<RowLinespecFunctionOEM>) setTabXML(tabLinespecsFunctionOEMs, RowLinespecFunctionOEM.class);       //showTab(tabLinespecsFunctionOEMs);
        tabLineroutes = (ArrayList<RowLineroute>) setTabXML(tabLineroutes, RowLineroute.class);    //   showTab(tabLineroutes);
        tabLineroutesOperations = (ArrayList<RowLinerouteOperation>) setTabXML(tabLineroutesOperations, RowLinerouteOperation.class);     //  showTab(tabLineroutesOperations);
        tabLineroutesMachines = (ArrayList<RowLinerouteMachine>) setTabXML(tabLineroutesMachines, RowLinerouteMachine.class);      // showTab(tabLineroutesMachines);
        tabLineroutesEmployees = (ArrayList<RowLinerouteEmployee>) setTabXML(tabLineroutesEmployees, RowLinerouteEmployee.class);    //    showTab(tabLineroutesEmployees);
        tabLineroutesLinespecs = (ArrayList<RowLinerouteLinespec>) setTabXML(tabLineroutesLinespecs, RowLinerouteLinespec.class);     //   showTab(tabLineroutesLinespecs);

        tabModelmachines = (ArrayList<RowModelmachine>) setTabXML(tabModelmachines, RowModelmachine.class);   //   showTab(tabModelmachines);
        tabTypemachines = (ArrayList<RowTypemachine>) setTabXML(tabTypemachines, RowTypemachine.class);
       // showTab(tabTypemachines);

        tabModelmachineTypemachines = (ArrayList<RowModelmachineTypemachine>) setTabXML(tabModelmachineTypemachines, RowModelmachineTypemachine.class);
        //showTab(tabModelmachineTypemachines);

        tabMachineModelmachines = (ArrayList<RowMachineModelmachine>) setTabXML(tabMachineModelmachines, RowMachineModelmachine.class);
        //showTab(tabMachineModelmachines);

        System.out.println("Данные из XML файла считаны успешно");
    }

    /**
     * Сохранение данных DataSet в XML файл
     *
     * @throws ParserConfigurationException
     * @throws Throwable
     */
    public void saveDataset() {
        writeTab(tabTrests);
        writeTab(tabTrestsWorks);
        writeTab(tabWorks);
        writeTab(tabWorksMachines);
        writeTab(tabWorksEmployees);
        writeTab(tabWorksSubject_labours);
        writeTab(tabWorksSubject_labours);
        writeTab(tabWorksOrders);
        writeTab(tabMachines);
        writeTab(tabModelmachines);
        writeTab(tabTypemachines);
        writeTab(tabEmployees);
        writeTab(tabSubject_labours);
        writeTab(tabSubject_laboursRoutes);
        writeTab(tabOrders);
        writeTab(tabOrdersLines);
        writeTab(tabUnits);

        writeTab(tabLines);
        writeTab(tabLinesSubject_labours);
        writeTab(tabLinesUnits);
        writeTab(tabRoutes);
        writeTab(tabRoutesLineroutes);
        writeTab(tabOperations);
        writeTab(tabLinespecs);
        writeTab(tabLinespecsResources);
        writeTab(tabLinespecsUnits);
        writeTab(tabLinespecsFunctionOEMs);
        writeTab(tabResources);
        writeTab(tabFunctionOEMs);
        writeTab(tabLineroutes);
        writeTab(tabLineroutesOperations);
        writeTab(tabLineroutesMachines);
        writeTab(tabLineroutesEmployees);
        writeTab(tabLineroutesLinespecs);
        writeTab(tabModelmachineTypemachines);
        writeTab(tabMachineModelmachines);

        for (int i = 50; i < 60; i++) {

            //	tabModelmachines.add(new RowModelmachine (i,"Modelmachine  "+i, "Image/Machine/press_04.png", 1.0,  1.0,  2.0,  2.0,"Modelmachine Описание №"+i) );
            //	tabLineroutes.add(new RowLineroute (i,"Lineroute  "+i, i, i, 100+i, i,0,0,100,0,0,100,"   ") );
            //		tabTypemachines	.add(new RowTypemachine (i,"Typemachine  "+i, "Тип машины - Описание №"+i) );
            //	 tabRoutesLineroutes.add( new RowRouteLineroute (1, it2, "Маршрут-Операция Описание №"+it2)  );
            //   tabModelmachineTypemachines.add( new RowModelmachineTypemachine (i, i, "Строка: Модель Машины- Тип машины   "+i+"-"+i)  );
            // tabMachineModelmachines .add( new RowMachineModelmachine  (i, i-49, "Строка: Машина - Модель Машины "+i+"-"+(i-49) )  );
            for (int i7 = 1; i7 < 6; i7++) {

                //   int it2 = (int) (Math.random() * 19) + 1;
                //		tabLineroutesEmployees.add( new RowLinerouteEmployee (i, it2, "Строка маршрута- Сотрудник №   "+i+"-"+it2)  );
                //		tabLineroutesLinespecs.add( new RowLinerouteLinespec (i, it2, "Строка маршрута- Строка реурса №   "+i+"-"+it2)  );
                //		 tabLineroutesOperations.add( new RowLinerouteOperation (i, it2, "Сторока маршрута-Операция Описание №   "+i+"-"+it2)  );
                //	 tabLinespecsFunctionOEMs.add( new RowLinespecFunctionOEM (i, it , "Строка спецификации - ФР № "+ i +" "+it ));
                //	 tabLinespecsUnits.add( new RowLinespecUnit (i, it , "Строка спецификации - ед.изм № "+ i +" "+it ));
                //	 tabLinespecsResources.add( new RowLinespecResource (i, it , "Строка спецификации - Ресурс № "+ i +" "+it ));
                // 	 tabOperationsLinespecs.add( new RowOperationLinespec (i, it , "Тех.операция - Строка спецификации № "+ i +" "+it ));
                //
            }

            /*
             tabFunctionOEMs.add(new RowFunctionOEM (1,"Равномерный	", "", "Описание строки  ресурса 1") );
             tabFunctionOEMs.add(new RowFunctionOEM (2,"Показательный", "", "Описание строки  ресурса 2") );
             tabFunctionOEMs.add(new RowFunctionOEM (3,"Нормальный	", "", "Описание строки  ресурса 3") );
             tabFunctionOEMs.add(new RowFunctionOEM (4,"Файл №1 тип P", "", "Описание строки  ресурса 4") );
             tabFunctionOEMs.add(new RowFunctionOEM (5,"Файл №1 тип F", "", "Описание строки  ресурса 5") );

             tabResources.add(new RowResource (1,"Время", "Описание строки  ресурса") );
             tabResources.add(new RowResource (2,"Лист 2мм", "Описание строки  ресурса") );
             tabResources.add(new RowResource (3,"Электроэнергия", "Описание строки  ресурса") );
             tabResources.add(new RowResource (4,"Полиэтилен", "Описание строки  ресурса") );
             tabResources.add(new RowResource (5,"Поролон", "Описание строки  ресурса") );
             tabResources.add(new RowResource (6,"Ткань", "Описание строки  ресурса") );
             tabResources.add(new RowResource (7,"Лист 3мм", "Описание строки  ресурса") );
             */
            //	 tabLinespecs.add(new RowLinespec (i,"Строка спецификации №"+i, "Описание строки спецификации №"+i) );
            //	tabOperations.add(new RowOperation(i,"Технологическая операция №"+i, "Описание операции №"+i) );
            //	tabSubject_laboursRoutes.add( new RowSubject_labourRoute (i, (int)(Math.random()*18)+1, "Предмет труда-Технологический маршрут №"+i)  );
            //	tabRoutes.add(new RowRoute(i,"ТехМаршрут №"+i, "Описание теx.маршрута №"+i) );
            //	tabLinesUnits.add( new RowLineUnit (i, (int)(Math.random()*4)+1, "Описание Связи (Строка заказа)-(Единица измерения) №"+i)  );
            //	tabOrderLines.add( new RowOrderLine ((int)(Math.random()*5)+1, i, "Описание Строки № "+i)  );
            //	tabLineSubject_labours.add( new RowLineSubject_labour ((int)(Math.random()*4)+1, i, "Описание Связи (Строка заказа)-(Предмет труда) №"+i)  );
            //	tabSubject_labours.add(new RowSubject_labour(i,"Предмет труда типа №"+i, 20*i,  "Описание предмета труда  №"+i) );
            //    tabLines.add(new RowLine(i,"Строка  №"+i, 20*i, new Date(2015-1900, 3, 1, 12, 0,0),new _Date(2015, 4, i, 12, 0,0),    "Описание строки  №"+i) );
            //	tabOrders.add(new RowOrder(i,"Заказ №"+i, new Date(2015, 3, 1, 12, 0,0),new _Date(2015-1900, 4, i, 12, 0,0),    "Описание заказа №"+i) );
            // 	tabWorksOrders.add( new RowWorkOrder ((int)(Math.random()*2)+1, i, "Описание Связи производство - Заказ №"+i)  );
        }

        //	showTab(tabTypemachines);
        //	System.out.println("Данные в XML файл сохранены успешно"+1/0/0);
        /*
         tabUnits.add(new RowUnit(1,"шт", " 			") );
         tabUnits.add(new RowUnit(1,"кг", " 			") );
         tabUnits.add(new RowUnit(1, "м", " 			") );
         tabUnits.add(new RowUnit(1,"м2", " 			") );
         tabUnits.add(new RowUnit(1,"м3", " 			") );
         */
        /*
         tabOrders			=	new ArrayList<RowOrder>();
         tabWorksOrders		=	new	ArrayList<RowWorkOrder>();
         */
    }

    /**
     * Конкатинирует имя директорию с именем файла
     *
     * @param s Название файла
     * @return Путь к файлу, находящемуся в директории pathDataDefault
     * @see    pathDataDefault
     */
    public String pathFile(String s) {
        return pathDataDefault + s;
    }

    /**
     * Выдает значения полей класса obj. Примитивные типы автоматически,
     * объектные типы objects по выбору
     *
     * @param obj     Анализируемый объект
     * @param cLs     Массив типов элементов (Пример: Object.class), одержащихся в
     *                ArrayList<Object>
     * @param objects Массив объектных типов ArrayList<Object>()
     * @throws ParserConfigurationException
     * @throws Throwable
     */
    static public void showObjTabs(Object obj, Class[] cLs, Object... objects) {
        System.out.println("   ");
        System.out.print(" Данные объекта  " + obj.getClass().getSimpleName() + ":  ");
        Class cLobj = obj.getClass();
        Field[] fields = XmlRW.fieldsCl(cLobj);

        for (Field fd : fields) {
            Object fd_get = new Object();
            fd.setAccessible(true);
            try {
                fd_get = fd.get(obj);
            } catch (IllegalArgumentException exp) {
                exp.printStackTrace();
            } catch (IllegalAccessException exp) {
                exp.printStackTrace();
            }
            if (fd.getType().isPrimitive() || fd.getType() == String.class) {
                System.out.print(fd.getName() + "   " + fd_get + "   ");
            }
            fd.setAccessible(false);
        }
        int i = 0;
        for (Object o : objects) {
            DataSet.showTab(o, cLs[i]);
        }
        i++;
    }

    static public <cL> void showTab(Object tab) {
        if (tab != null) {
            showTab(tab, ((ArrayList<cL>) tab).get(0).getClass());
        }
    }

    /**
     * Показывает данные таблицы tab
     *
     * @param tab коллекция из DataSet { Пример: ArrayList<Object> tabTrests =
     *            new (ArrayList<Object>() }
     * @param cL  Имя строки таблицы. Соответствует типу данных элементов,
     *            которые хранятся в ArrayList (Пример: cL=RowTrest для ArrayList<RowTrest>
     *            ).
     * @throws ParserConfigurationException
     * @throws Throwable
     */
    static public <cL> void showTab(Object tab, Class cL) {
        /*Подготовительная часть: определяем ширину колонок таблицы */
        int[] strLength = new int[200];        // массив, который содержит ширину колонки
        int k = 7;                                // дополнительное увеличение ширины колонки
        boolean flag = true;                        // флаг, который позволяет сначала прочесть имена полей таблицы (flag=true), а затем значение полей таблицы (flag=false)
        for (cL r : (ArrayList<cL>) tab) {
            Field[] fields = XmlRW.fieldsCl(cL);
            strLength = new int[fields.length];                                          // определяем размерность массива для хранения данных о ширине каждой колонки
            int i = 0;
            if (flag) {
                for (Field fd : fields) {
                    strLength[i] = fd.getName().length();
                    i++;
                }     // считываем длину строки заголовка колонки таблицы, которая необходима для заголовка + добавили к-символов
                flag = false;
            }

            i = 0;
            for (Field fd : fields) {
                Object fd_get = new Object();
                fd.setAccessible(true);
                try {
                    fd_get = fd.get(r);
                } catch (IllegalArgumentException exp) {
                    exp.printStackTrace();
                } catch (IllegalAccessException exp) {
                    exp.printStackTrace();
                }
                if (fd_get != null) {
                    if (fd_get.toString().length() > strLength[i]) {
                        if (fd.getType() == Date.class) {
                            strLength[i] = _Date.toStringForDate((Date) fd_get).length();
                        }
                        if (fd.getType() != Date.class) {
                            strLength[i] = fd_get.toString().length(); // 		считываем длину строки значения колонки, которая необходима для заголовка + добавили к-символов
                        }
                    }
                }
                i++;
                fd.setAccessible(false);
            }
        }

        /*Основная часть: зная ширину колонок таблицы, заполняем таблицу */
        System.out.println();
        System.out.println("-- Данные таблицы: " + cL.getSimpleName() + "--");
        int sum = strLength.length;
        for (int j = 0; j < strLength.length; j++) {
            sum = sum + strLength[j] + k + 1;
        }
        for (int j = 0; j < sum; j++) {
            System.out.print("-");
        }
        System.out.println("-");

        flag = true;
        for (cL r : (ArrayList<cL>) tab) {
            Field[] fields = XmlRW.fieldsCl(cL);
            if (flag) {
                int i = 0;
                for (Field fd : fields) {
                    System.out.printf(":%" + Integer.toString(strLength[i] + k) + "s ", fd.getName());
                    i++;
                }
                flag = false;
            }

            int i = 0;
            System.out.println();
            for (Field fd : fields) {
                Object fd_get = new Object();
                fd.setAccessible(true);
                try {
                    fd_get = fd.get(r);
                } catch (IllegalArgumentException exp) {
                    exp.printStackTrace();
                } catch (IllegalAccessException exp) {
                    exp.printStackTrace();
                }
                if (fd.getType() == Date.class) {
                    System.out.printf(":%" + Integer.toString(strLength[i] + k) + "s ", _Date.toStringForDate((Date) fd_get));
                    i++;
                }
                if (fd.getType() != Date.class) {
                    System.out.printf(":%" + Integer.toString(strLength[i] + k) + "s ", fd_get);
                    i++;
                }
                fd.setAccessible(false);
            }
            System.out.print(":");
        }
        System.out.println();
    }

    /**
     * Для коллекции возвращает имя поля этой коллекции в DataSet (DataSet - это
     * this). Это имя служит именем таблицы при записи в XML-файл ( а именно имя
     * XML-файла с таблицей и имя тега root в данном файле.) Проверяет,
     * соответствует ли элементы коллекции заданному типу и в случае
     * соответствия формирует xml-файл
     *
     * @param tab коллекция из DataSet
     * @return Class FieldClass.className	тип данных элементов коллекции. В
     * примере это- tabTrests. Служит именем тега дочернего элемента FieldClass
     * @throws NoSuchFieldException
     * @throws DOMException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public FieldClass getFieldName_Class(Object tab) {
        Class cL = null;
        Class c1 = this.getClass();                        // это DataSet
        Field[] fields_ds = c1.getDeclaredFields();        // возвращаем поля DataSet
        if (tab != null) {
            if (((ArrayList<Object>) tab).isEmpty() == false) {
                cL = (((ArrayList<Object>) tab).get(0)).getClass();
            }
        }
        for (Field field_ds : fields_ds) {
            try {
                if (tab == (field_ds.get(this))) {
                    return new FieldClass(field_ds.getName(), cL);
                }
            } // если Class объект совпадает с именем поля DataSet, то возвращаем его имя. Так мы получим название переменной, которая совпадает с именем файла
            catch (IllegalArgumentException exp) {
                exp.printStackTrace();
            } catch (IllegalAccessException exp) {
                exp.printStackTrace();
            }
        }
        return null;
    }

    private void RowModelmachineTypemachine(int id, int id0, String ___) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Вспомогательный класс, используется для передачи данных из метода
     * FieldClass getFieldName_Class (Object tab)
     *
     * @see    pathDataDefault
     */
    private class FieldClass {

        private String fieldName;
        private Class className;

        public FieldClass(String fieldName, Class className) {
            this.fieldName = fieldName;
            this.className = className;
        }
    }

    /**
     * Инициализация из xml- файла объекта Trest по значению id
     *
     * @param id
     * @return
     * @throws Throwable
     * @throws ParserConfigurationException
     */
    public Trest getTrest(int id) {
        for (RowTrest t : tabTrests) {
            if (id == t.getId()) {

                ArrayList<Work> works = new ArrayList<Work>();

                for (RowTrestWork tw : tabTrestsWorks) {
                    for (RowWork w : tabWorks) {
                        if (id == tw.getId() && tw.getId() == w.getId()) {
                            works.add(getWork(tw.getId2()));
                        }
                    }
                }

                Trest trest = new Trest(t.getId(), t.getName(), t.getDescription(), works);

                return trest;
            }
        }
        return null;
    }

    /**
     * Сохранение в xml- файл объекта Trest по значению id
     *
     * @param id
     * @return
     * @throws Throwable
     * @throws ParserConfigurationException
     */
    public void setTrest(Trest trest) // НАВЕРНО НАДО УБРАТЬ
    {
        for (RowTrest t : tabTrests) {
            if (trest.getId() == t.getId()) // выбираем нужную строку таблицы для изменения
            {
                XmlRW.FieldToField(t, trest);                                // переносим все поля в таблицу
                for (Work w : trest.getWorks()) {
                    for (RowWork r : tabWorks) {
                        if (w.getId() == r.getId()) {
                            XmlRW.FieldToField(r, w);
                        }
                    }
                }
            }
        }
    }

    public <cL> int NextId(Class cL, Object tab) {
        int nextId = 1;
        for (cL t : (ArrayList<cL>) tab) {
            for (Field field : XmlRW.fieldsCl(cL)) {
                field.setAccessible(true);
                int field_getInt = 0;
                try {
                    field_getInt = field.getInt(t);
                } catch (IllegalArgumentException exp) {
                    exp.printStackTrace();
                } catch (IllegalAccessException exp) {
                    exp.printStackTrace();
                }
                if (field.getName() == "id") {
                    if (field_getInt > nextId) {
                        nextId = field_getInt;
                    }
                }
                field.setAccessible(false);
            }
        }
        return nextId;
    }

    /**
     * Инициализация из xml- файла объекта Trest по значению id
     *
     * @param id
     * @return
     * @throws Throwable
     * @throws ParserConfigurationException
     */
    public Work getWork(int id) {

        for (RowWork w : tabWorks) {
            if (id == w.getId()) {
                ArrayList<Machine> machines = select(w, tabMachines, tabWorksMachines, RowMachine.class, RowWorkMachine.class);

                ArrayList<Employee> employees = select(w, tabEmployees, tabWorksEmployees, RowEmployee.class, RowWorkEmployee.class);
                ArrayList<Subject_labour> subject_labours = new ArrayList<Subject_labour>();
                ArrayList<Order> orders = new ArrayList<Order>();


                for (RowWorkSubject_labour wr : tabWorksSubject_labours) {
                    if (w.getId() == wr.getId()) {
                        for (RowSubject_labour m : tabSubject_labours) {
                            if (wr.getId2() == m.getId()) {
                                subject_labours.add((Subject_labour) createObject(m));
                            }
                        }
                    }
                }

                for (RowWorkOrder wr : tabWorksOrders) {
                    if (w.getId() == wr.getId()) {
                        for (RowOrder m : tabOrders) {
                            if (wr.getId2() == m.getId()) {
                                orders.add((Order) createObject(m));
                            }
                        }
                    }
                }

                Work work = new Work(w.getId(), w.getName(), w.getScheme(), w.getOverallSize(), w.getScaleEquipment(),
                        machines, employees, subject_labours, orders, w.getDescription());

                return work;

            }
        }
        return null;
    }

    /**
     * Выбираем из rtTab все элементы RT, для которых row.Id==RMT.Id && RMT.Id
     * == RT.Id
     */
    public <T, RT extends RowIdNameDescription, RMT extends RowIdId2> ArrayList<T> select(Object row, ArrayList<RT> rtTab, ArrayList<RMT> rmtTab, Class cRT, Class cRMT) {
        ArrayList<T> typemachines = new ArrayList<T>();
        for (RMT wr : rmtTab) {  // Выбираем из rtTab все элементы RT, для которых row.Id==RMT.Id && RMT.Id2 == RT.Id
            if (((RowIdNameDescription) row).getId() == wr.getId()) {
                for (RT w : rtTab) {
                    if (wr.getId2() == w.getId()) {
                        typemachines.add((T) createObject(w));
                    }
                }
            }
        }
        if (typemachines.isEmpty() == true) {     //  If the table is empty, create a new element. His type of equipment installed by default.
            try {
                RT rSL = (RT) cRT.getDeclaredConstructor(DataSet.class, Class.class).newInstance(this, cRT);
                boolean flag = false;
                for (RT w : rtTab) {
                    if (rSL.compareTo(w) == 0) {
                        flag = true;
                        rSL = w;
                        System.out.println("rtTab.add(rSL)=" + rSL.getClass() + "rSL.getId()=" + rSL.getId() + "rSL.getId()=" + w.getId());
                    }
                }
                if (!flag) {
                    rtTab.add(rSL);
                }
                RMT rIdId = (RMT) cRMT.getDeclaredConstructor(int.class, int.class, String.class).newInstance(((RT) row).getId(), rSL.getId(), "   ");
                // б)помещаем его в таблицу для RowSubject_labour
                rmtTab.add(rIdId);
                // теперь у нас есть все необходиое для создание отсутствующего предмета труда
                typemachines.add((T) createObject(rSL));
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return typemachines;
    }

    //-------------------------------------------------------------------------------
    public <cL> cL createObject(cL row) {
        cL m = null;

        if (row.getClass() == RowTypemachine.class) {
            m = (cL) new Typemachine(((RowTypemachine) row).getId(), ((RowTypemachine) row).getName(), ((RowTypemachine) row).getDescription());
        }

        //------------------------------------------------------------------------
        if (row.getClass() == RowModelmachine.class) {
            ArrayList<Typemachine> typemachines = select(row, tabTypemachines, tabModelmachineTypemachines, RowTypemachine.class, RowModelmachineTypemachine.class);
            m = (cL) new Modelmachine(((RowModelmachine) row).getId(), ((RowModelmachine) row).getName(),
                    ((RowModelmachine) row).getImg(), typemachines.get(0).getName(), ((RowModelmachine) row).getOverallDimensionX(), ((RowModelmachine) row).getOverallDimensionY(),
                    ((RowModelmachine) row).getWorkSizeX(), ((RowModelmachine) row).getWorkSizeY(), ((RowModelmachine) row).getDescription()
            );
        }

        //------------------------------------------------------------------------
        if (row.getClass() == RowMachine.class) {
            ArrayList<Modelmachine> modelmachines = select(row, tabModelmachines, tabMachineModelmachines, RowModelmachine.class, RowMachineModelmachine.class);
            m = (cL) new Machine(((RowMachine) row).getId(), ((RowMachine) row).getName(), modelmachines.get(0), ((RowMachine) row).getLocationX(), ((RowMachine) row).getLocationY(), ((RowMachine) row).getState(), ((RowMachine) row).getDescription());
        }


        if (row.getClass() == RowEmployee.class) {
            m = (cL) new Employee(((RowEmployee) row).getId(), ((RowEmployee) row).getName(), ((RowEmployee) row).getDescription());
        }
        if (row.getClass() == RowOperation.class) {
            m = (cL) new Operation(((RowOperation) row).getId(), ((RowOperation) row).getName(), ((RowOperation) row).getDescription());
        }
        if (row.getClass() == RowResource.class) {
            m = (cL) new Resource(((RowResource) row).getId(), ((RowResource) row).getName(), ((RowResource) row).getDescription());
        }
        if (row.getClass() == RowFunctionOEM.class) {
            m = (cL) new FunctionOEM(((RowFunctionOEM) row).getId(), ((RowFunctionOEM) row).getName(), ((RowFunctionOEM) row).getPathFileFR(), ((RowFunctionOEM) row).getDescription());
        }
        if (row.getClass() == RowUnit.class) {
            m = (cL) new Unit(((RowUnit) row).getId(), ((RowUnit) row).getName(), ((RowUnit) row).getDescription());
        }

        //B--------Subject_labour----Cоздается Объект-----------------------------------------------------------------------------------------//
        if (row.getClass() == RowSubject_labour.class) {
            ArrayList<Route> routes = new ArrayList<Route>();
            for (RowSubject_labourRoute wr : tabSubject_laboursRoutes) {
                if (((RowSubject_labour) row).getId() == wr.getId()) {
                    for (RowRoute w : tabRoutes) {
                        if (wr.getId2() == w.getId()) {
                            routes.add((Route) createObject(w));
                        }
                    }
                }
            }

            if (routes.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowRoute rSL = new RowRoute(this, RowRoute.class);    // а)создаем отсутствующий RowSubject_labour
                tabRoutes.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowSubject_labourRoute rIdId = new RowSubject_labourRoute(((RowSubject_labour) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и

                tabSubject_laboursRoutes.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                // теперь у нас есть все необходиое для создание отсутствующего предмета труда
                routes.add((Route) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.
            }
            m = (cL) new Subject_labour(((RowSubject_labour) row).getId(), ((RowSubject_labour) row).getName(), ((RowSubject_labour) row).getPrice(), routes, ((RowSubject_labour) row).getDescription());
        }
        //E--------Subject_labour-------------------------------------------------------------------------------------------------------------//

        //B--------Route ------------Cоздается Объект-----------------------------------------------------------------------------------------//
        if (row.getClass() == RowRoute.class) {
            ArrayList<Lineroute> lineroutes = new ArrayList<Lineroute>();
            for (RowRouteLineroute wr : tabRoutesLineroutes) {
                if (((RowRoute) row).getId() == wr.getId()) {
                    for (RowLineroute w : tabLineroutes) {
                        if (wr.getId2() == w.getId()) {
                            lineroutes.add((Lineroute) createObject(w));
                        }
                    }
                }
            }

            if (lineroutes.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowLineroute rSL = new RowLineroute(this, RowLineroute.class);    // а)создаем отсутствующий RowSubject_labour
                tabLineroutes.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowRouteLineroute rIdId = new RowRouteLineroute(((RowRoute) row).getId(),
                        rSL.getId(),
                        "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabRoutesLineroutes.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                // теперь у нас есть все необходиое для создание отсутствующего предмета труда
                //      operations.add((Operation) createObject(rSL));							// д) создаем недостающий предмет труда и помещаем его в коллекцию.
            }
            m = (cL) new Route(((RowRoute) row).getId(), ((RowRoute) row).getName(), lineroutes, ((RowRoute) row).getDescription());
        }
        //E--------Route ------------Cоздается Объект-----------------------------------------------------------------------------------------//

        //B--------Lineroute --------Cоздается Объект-----------------------------------------------------------------------------------------//
        if (row.getClass() == RowLineroute.class) {

            ArrayList<Linespec> linespecs = new ArrayList<Linespec>();
            for (RowLinerouteLinespec wr : tabLineroutesLinespecs) {
                if (((RowLineroute) row).getId() == wr.getId()) {
                    for (RowLinespec w : tabLinespecs) {
                        if (wr.getId2() == w.getId()) {
                            linespecs.add((Linespec) createObject(w));
                        }
                    }
                }
            }

            ArrayList<Operation> operations = new ArrayList<Operation>();
            for (RowLinerouteOperation wr : tabLineroutesOperations) {
                if (((RowLineroute) row).getId() == wr.getId()) {
                    for (RowOperation w : tabOperations) {
                        if (wr.getId2() == w.getId()) {
                            operations.add((Operation) createObject(w));
                        }
                    }
                }
            }

            ArrayList<Machine> machines = new ArrayList<Machine>();
            for (RowLinerouteMachine wr : tabLineroutesMachines) {
                if (((RowLineroute) row).getId() == wr.getId()) {
                    for (RowMachine w : tabMachines) {
                        if (wr.getId2() == w.getId()) {
                            machines.add((Machine) createObject(w));
                        }
                    }
                }
            }

            ArrayList<Employee> employees = new ArrayList<Employee>();
            for (RowLinerouteEmployee wr : tabLineroutesEmployees) {
                if (((RowLineroute) row).getId() == wr.getId()) {
                    for (RowEmployee w : tabEmployees) {
                        if (wr.getId2() == w.getId()) {
                            employees.add((Employee) createObject(w));
                        }
                    }
                }
            }

            if (linespecs.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowLinespec rSL = new RowLinespec(this, RowLinespec.class);    // а)создаем отсутствующий RowSubject_labour
                tabLinespecs.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLinerouteLinespec rIdId = new RowLinerouteLinespec(((RowLineroute) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabLineroutesLinespecs.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                linespecs.add((Linespec) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.  Теперь у нас есть все необходиое для создание отсутствующего предмета труда
            }
            if (operations.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowOperation rSL = new RowOperation(this, RowOperation.class);    // а)создаем отсутствующий RowSubject_labour
                tabOperations.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLinerouteOperation rIdId = new RowLinerouteOperation(((RowLineroute) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabLineroutesOperations.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                operations.add((Operation) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.  Теперь у нас есть все необходиое для создание отсутствующего предмета труда
            }
            if (machines.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowMachine rSL = new RowMachine(this, RowMachine.class);    // а)создаем отсутствующий RowSubject_labour
                tabMachines.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLinerouteMachine rIdId = new RowLinerouteMachine(((RowLineroute) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabLineroutesMachines.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                machines.add((Machine) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.  Теперь у нас есть все необходиое для создание отсутствующего предмета труда
            }
            if (employees.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowEmployee rSL = new RowEmployee(this, RowEmployee.class);    // а)создаем отсутствующий RowSubject_labour
                tabEmployees.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLinerouteEmployee rIdId = new RowLinerouteEmployee(((RowLineroute) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabLineroutesEmployees.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                employees.add((Employee) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.  Теперь у нас есть все необходиое для создание отсутствующего предмета труда
            }

            m = (cL) new Lineroute(
                    ((RowLineroute) row).getId(),
                    ((RowLineroute) row).getName(),
                    operations,
                    ((RowLineroute) row).getNumberWork(),
                    machines,
                    employees,
                    linespecs,
                    ((RowLineroute) row).getInputBufferMin(), ((RowLineroute) row).getInputBuffer(), ((RowLineroute) row).getInputBufferMax(),
                    ((RowLineroute) row).getOutputBufferMin(), ((RowLineroute) row).getOutputBuffer(), ((RowLineroute) row).getOutputBufferMax(),
                    ((RowLineroute) row).getDescription());

        }
        //E--------Lineroute --------Cоздается Объект-----------------------------------------------------------------------------------------//

        //B--------Linespec ---------Cоздается Объект-----------------------------------------------------------------------------------------//
        if (row.getClass() == RowLinespec.class) {
            ArrayList<Resource> resources = new ArrayList<Resource>();

            for (RowLinespecResource wr : tabLinespecsResources) {
                if (((RowLinespec) row).getId() == wr.getId()) {
                    for (RowResource w : tabResources) {
                        if (wr.getId2() == w.getId()) {
                            resources.add((Resource) createObject(w));
                        }
                    }
                }
            }

            ArrayList<Unit> units = new ArrayList<Unit>();
            for (RowLinespecUnit wr : tabLinespecsUnits) {
                if (((RowLinespec) row).getId() == wr.getId()) {
                    for (RowUnit w : tabUnits) {
                        if (wr.getId2() == w.getId()) {
                            units.add((Unit) createObject(w));
                        }
                    }
                }
            }

            ArrayList<FunctionOEM> functionOEMs = new ArrayList<FunctionOEM>();
            for (RowLinespecFunctionOEM wr : tabLinespecsFunctionOEMs) {
                if (((RowLinespec) row).getId() == wr.getId()) {
                    for (RowFunctionOEM w : tabFunctionOEMs) {
                        if (wr.getId2() == w.getId()) {
                            functionOEMs.add((FunctionOEM) createObject(w));
                        }
                    }
                }
            }

            if (units.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowUnit rSL = new RowUnit(this, RowUnit.class);    // а)создаем отсутствующий RowSubject_labour
                tabUnits.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLinespecUnit rIdId = new RowLinespecUnit(((RowLinespec) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabLinespecsUnits.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                // теперь у нас есть все необходиое для создание отсутствующего предмета труда
                units.add((Unit) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.
            }
            if (resources.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowResource rSL = new RowResource(this, RowResource.class);    // а)создаем отсутствующий RowSubject_labour
                tabResources.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLinespecResource rIdId = new RowLinespecResource(((RowLinespec) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabLinespecsResources.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                // теперь у нас есть все необходиое для создание отсутствующего предмета труда
                resources.add((Resource) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.
            }
            if (functionOEMs.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowFunctionOEM rSL = new RowFunctionOEM(this, RowFunctionOEM.class);    // а)создаем отсутствующий RowSubject_labour
                tabFunctionOEMs.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLinespecFunctionOEM rIdId = new RowLinespecFunctionOEM(((RowLinespec) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и
                tabLinespecsFunctionOEMs.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                // теперь у нас есть все необходиое для создание отсутствующего предмета труда
                functionOEMs.add((FunctionOEM) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.
            }

            m = (cL) new Linespec(
                    ((RowLinespec) row).getId(),
                    ((RowLinespec) row).getName(),
                    resources,
                    ((RowLinespec) row).getM(),
                    ((RowLinespec) row).getSigma(),
                    functionOEMs,
                    units,
                    ((RowLinespec) row).getDescription()
            );

        }

        //E--------Linespec --------Cоздается Объект-----------------------------------------------------------------------------------------//		
        if (row.getClass() == RowOrder.class) {
            ArrayList<Line> lines = new ArrayList<Line>();
            for (RowOrderLine wr : tabOrdersLines) {
                if (((RowOrder) row).getId() == wr.getId()) {
                    for (RowLine w : tabLines) {
                        if (wr.getId2() == w.getId()) {
                            lines.add((Line) createObject(w));
                        }
                    }
                }
            }

            m = (cL) new Order(((RowOrder) row).getId(), ((RowOrder) row).getName(), ((RowOrder) row).getDateBegin(), ((RowOrder) row).getDateEnd(), ((RowOrder) row).getDescription(), lines);
        }

        if (row.getClass() == RowLine.class) {
            ArrayList<Subject_labour> subject_labours = new ArrayList<Subject_labour>();
            ArrayList<Unit> units = new ArrayList<Unit>();
            for (RowLineSubject_labour wr : tabLinesSubject_labours) {
                if (((RowLine) row).getId() == wr.getId()) {
                    for (RowSubject_labour w : tabSubject_labours) {
                        if (wr.getId2() == w.getId()) {
                            subject_labours.add((Subject_labour) createObject(w));
                        }
                    }
                }
            }

            for (RowLineUnit wu : tabLinesUnits) {
                if (((RowLine) row).getId() == wu.getId()) {
                    for (RowUnit w : tabUnits) {
                        if (wu.getId2() == w.getId()) {
                            units.add((Unit) createObject(w));
                        }
                    }
                }
            }

            if (subject_labours.isEmpty() == true) {     // Этот  if создан в процессе отладки (в дальнейшем можно убрать или оставить на случай, если кто-то почистить данные в xml-файлах), так как в таблицах были не все данные. Смысл его в том, что он в случае отсутствия записи Предмета труда, которая должна быть в строке, создает в RowSubject_labour и связывает его с RowLine
                // если для строки заказа по IdId не находится предмет труда, то
                RowSubject_labour rSL = new RowSubject_labour(this, RowSubject_labour.class);    // а)создаем отсутствующий RowSubject_labour
                tabSubject_labours.add(rSL);                                                        // б)помещаем его в таблицу для RowSubject_labour
                RowLineSubject_labour rIdId = new RowLineSubject_labour(((RowLine) row).getId(), rSL.getId(), "   ");    //	в)для связи по id строки RowLine и RowSubject_labour  создаем строку реестра    и

                tabLinesSubject_labours.add(rIdId);                                                                //	г) помещаем ее в таблицу DataSet.tabLinesSubject_labours
                // теперь у нас есть все необходиое для создание отсутствующего предмета труда

                subject_labours.add((Subject_labour) createObject(rSL));                            // д) создаем недостающий предмет труда и помещаем его в коллекцию.

            }
            m = (cL) new Line(((RowLine) row).getId(), ((RowLine) row).getName(), subject_labours, units, ((RowLine) row).getQuantity(), ((RowLine) row).getDateBegin(), ((RowLine) row).getDateEnd(), ((RowLine) row).getDescription());
        }

        return m;
    }

    /**
     * Возвращаем максимальное значение Id, которое хранится в таблицах полей
     * Dataset (имеются в виду таблицы вида: tabTrests, tabWorks; ).
     *
     * @param cL тип данных строк, содержащихся в таблице. Каждая строка имеет
     *           id и наследует RowIdNameDescription
     * @return Максимальное значение id, содержащегося в таблицу
     */
    public int IdMax(Class cL) {
        ArrayList<RowIdNameDescription> tab = getTabIND(cL);
        if (tab == null) {
            System.out.println("DataSet.IdMax: Таблица пуста");
            return 1;
        }
        int idMax = tab.get(0).getId();
        for (RowIdNameDescription row : tab) {
            if (idMax < row.getId()) {
                idMax = row.getId();
            }
        }
        return idMax;
    }

    /**
     * Возврает таблицу DataSet, тип данных строк которой соответствует
     * соответствует типу данных объектов Trest (Например типу Work
     * соответствуют данные с типом строк RowWork) Применяется при проверки
     * таблицы по id и возврате максимального элемента в методе public int
     * DataSet.IdMax(Class cL)
     *
     * @param cL
     * @return
     */
    public <T> ArrayList<T> getTabIND(Class cL) {
        // используется для дерева Trest.class а для таблиц RowTrest.class

        if (cL == Employee.class || cL == RowEmployee.class) {
            return (ArrayList<T>) getTabEmployees();
        }
        if (cL == FunctionOEM.class || cL == RowFunctionOEM.class) {
            return (ArrayList<T>) getTabFunctionOEMs();
        }
        if (cL == Line.class || cL == RowLine.class) {
            return (ArrayList<T>) getTabLines();
        }
        if (cL == Lineroute.class || cL == RowLineroute.class) {
            return (ArrayList<T>) getTabLineroutes();
        }
        if (cL == Linespec.class || cL == RowLinespec.class) {
            return (ArrayList<T>) getTabLinespecs();
        }
        if (cL == Machine.class || cL == RowMachine.class) {
            return (ArrayList<T>) getTabMachines();
        }
        if (cL == Operation.class || cL == RowOperation.class) {
            return (ArrayList<T>) getTabOperations();
        }
        if (cL == Order.class || cL == RowOrder.class) {
            return (ArrayList<T>) getTabOrders();
        }
        if (cL == Resource.class || cL == RowResource.class) {
            return (ArrayList<T>) getTabResources();
        }
        if (cL == Route.class || cL == RowRoute.class) {
            return (ArrayList<T>) getTabRoutes();
        }
        if (cL == Subject_labour.class || cL == RowSubject_labour.class) {
            return (ArrayList<T>) getTabProducts();
        }
        if (cL == Trest.class || cL == RowTrest.class) {
            return (ArrayList<T>) getTabTrests();
        }
        if (cL == Unit.class || cL == RowUnit.class) {
            return (ArrayList<T>) getTabUnits();
        }
        if (cL == Work.class || cL == RowWork.class) {
            return (ArrayList<T>) getTabWorks();
        }
        if (cL == Modelmachine.class || cL == RowModelmachine.class) {
            return (ArrayList<T>) getTabModelmachines();
        }
        if (cL == Typemachine.class || cL == RowTypemachine.class) {
            return (ArrayList<T>) getTabTypemachines();
        }

        return null;
    }

    public static void main2(String[] args) {
        int b = 20;                        // Количество предметов труда в таблице
        for (int i = 1; i < 20; i++) {
            int a = ((int) (Math.random() * b));            // генерирование номера предмета труда в таблице
            System.out.println(a);
        }
    }

    public ArrayList<RowLinespec> getTabLinespecs() {
        return tabLinespecs;
    }

    public void setTabLinespecs(ArrayList<RowLinespec> tabLinespecs) {
        this.tabLinespecs = tabLinespecs;
    }

    public ArrayList<RowRouteLineroute> getTabRoutesOperations() {
        return tabRoutesLineroutes;
    }

    public void setTabRoutesOperations(ArrayList<RowRouteLineroute> tabRoutesOperations) {
        this.tabRoutesLineroutes = tabRoutesOperations;
    }

    public ArrayList<RowOperation> getTabOperations() {
        return tabOperations;
    }

    public void setTabOperations(ArrayList<RowOperation> tabOperations) {
        this.tabOperations = tabOperations;
    }

    public ArrayList<RowSubject_labourRoute> getTabSubject_laboursRoutes() {
        return tabSubject_laboursRoutes;
    }

    public void setTabSubject_laboursRoutes(ArrayList<RowSubject_labourRoute> tabSubject_laboursRoutes) {
        this.tabSubject_laboursRoutes = tabSubject_laboursRoutes;
    }

    public ArrayList<RowRoute> getTabRoutes() {
        return tabRoutes;
    }

    public void setTabRoutes(ArrayList<RowRoute> tabRoutes) {
        this.tabRoutes = tabRoutes;
    }

    public ArrayList<RowLineUnit> getTabLinesUnits() {
        return tabLinesUnits;
    }

    public void setTabLinesUnits(ArrayList<RowLineUnit> tabLinesUnits) {
        this.tabLinesUnits = tabLinesUnits;
    }

    public void setTabUnits(ArrayList<RowUnit> tabUnits) {
        this.tabUnits = tabUnits;
    }

    public ArrayList<RowSubject_labour> getTabSubject_labours() {
        return tabSubject_labours;
    }

    public void setTabSubject_labours(
            ArrayList<RowSubject_labour> tabSubject_labours) {
        this.tabSubject_labours = tabSubject_labours;
    }

    public ArrayList<RowWorkSubject_labour> getTabWorksSubject_labours() {
        return tabWorksSubject_labours;
    }

    public void setTabWorksSubject_labours(
            ArrayList<RowWorkSubject_labour> tabWorksSubject_labours) {
        this.tabWorksSubject_labours = tabWorksSubject_labours;
    }

    public ArrayList<RowLine> getTabLines() {
        return tabLines;
    }

    public void setTabLines(ArrayList<RowLine> tabLines) {
        this.tabLines = tabLines;
    }

    public ArrayList<RowLineSubject_labour> getTabLineSubject_labours() {
        return tabLinesSubject_labours;
    }

    public void setTabLineSubject_labours(
            ArrayList<RowLineSubject_labour> tabLineSubject_labours) {
        this.tabLinesSubject_labours = tabLineSubject_labours;
    }

    public ArrayList<RowWorkOrder> getTabWorksOrders() {
        return tabWorksOrders;
    }

    public void setTabWorksOrders(ArrayList<RowWorkOrder> tabWorksOrders) {
        this.tabWorksOrders = tabWorksOrders;
    }

    public ArrayList<RowWorkEmployee> getTabWorksEmployees() {
        return tabWorksEmployees;
    }

    public void setTabWorksEmployees(ArrayList<RowWorkEmployee> tabWorksEmployees) {
        this.tabWorksEmployees = tabWorksEmployees;
    }

    public ArrayList<RowWorkMachine> getTabWorksMachines() {
        return tabWorksMachines;
    }

    public void setTabWorksMachines(ArrayList<RowWorkMachine> tabWorksMachines) {
        this.tabWorksMachines = tabWorksMachines;
    }

    public ArrayList<RowTrestWork> getTabTrestsWorks() {
        return tabTrestsWorks;
    }

    public void setTabTrestsWorks(ArrayList<RowTrestWork> tabTrestsWorks) {
        this.tabTrestsWorks = tabTrestsWorks;
    }

    public ArrayList<RowTrest> getTabTrests() {
        return tabTrests;
    }

    public void setTabTrests(ArrayList<RowTrest> tabTrests) {
        this.tabTrests = tabTrests;
    }

    public ArrayList<RowWork> getTabWorks() {
        return tabWorks;
    }

    public void setTabWorks(ArrayList<RowWork> tabWorks) {
        this.tabWorks = tabWorks;
    }

    public ArrayList<RowMachine> getTabMachines() {
        return tabMachines;
    }

    public void setTabMachines(ArrayList<RowMachine> tabMachines) {
        this.tabMachines = tabMachines;
    }

    public ArrayList<RowModelmachine> getTabModelmachines() {
        return tabModelmachines;
    }

    public void setTabModelmachines(ArrayList<RowModelmachine> tabModelmachines) {
        this.tabModelmachines = tabModelmachines;
    }

    public ArrayList<RowEmployee> getTabEmployees() {
        return tabEmployees;
    }

    public void setTabEmployees(ArrayList<RowEmployee> tabEmployees) {
        this.tabEmployees = tabEmployees;
    }

    public ArrayList<RowSubject_labour> getTabProducts() {
        return tabSubject_labours;
    }

    public void setTabProducts(ArrayList<RowSubject_labour> tabProducts) {
        this.tabSubject_labours = tabSubject_labours;
    }

    public ArrayList<RowOrder> getTabOrders() {
        return tabOrders;
    }

    public void setTabOrders(ArrayList<RowOrder> tabOrders) {
        this.tabOrders = tabOrders;
    }

    public ArrayList<RowUnit> getTabUnits() {
        return tabUnits;
    }

    public void setTabUnit(ArrayList<RowUnit> tabUnits) {
        this.tabUnits = tabUnits;
    }

    public ArrayList<RowOrderLine> getTabOrdersLines() {
        return tabOrdersLines;
    }

    public void setTabOrdersLines(ArrayList<RowOrderLine> tabOrderLines) {
        this.tabOrdersLines = tabOrderLines;
    }

    public ArrayList<RowLinerouteOperation> getTabLineroutesOperations() {
        return tabLineroutesOperations;
    }

    public void setTabLineroutesOperations(ArrayList<RowLinerouteOperation> tabLineroutesOperations) {
        this.tabLineroutesOperations = tabLineroutesOperations;
    }

    public ArrayList<RowLineroute> getTabLineroutes() {
        return tabLineroutes;
    }

    public void setTabLineroutes(ArrayList<RowLineroute> tabLineroutes) {
        this.tabLineroutes = tabLineroutes;
    }

    public ArrayList<RowRouteLineroute> getTabRoutesLineroutes() {
        return tabRoutesLineroutes;
    }

    public void setTabRoutesLineroutes(ArrayList<RowRouteLineroute> tabRoutesLineroutes) {
        this.tabRoutesLineroutes = tabRoutesLineroutes;
    }

    public ArrayList<RowFunctionOEM> getTabFunctionOEMs() {
        return tabFunctionOEMs;
    }

    public void setTabFunctionOEMs(ArrayList<RowFunctionOEM> tabFunctionOEMs) {
        this.tabFunctionOEMs = tabFunctionOEMs;
    }

    public ArrayList<RowResource> getTabResources() {
        return tabResources;
    }

    public void setTabResources(ArrayList<RowResource> tabResources) {
        this.tabResources = tabResources;
    }

    public ArrayList<RowTypemachine> getTabTypemachines() {
        return tabTypemachines;
    }

    public void setTabTypemachines(ArrayList<RowTypemachine> tabTypemachines) {
        this.tabTypemachines = tabTypemachines;
    }

    public ArrayList<RowModelmachineTypemachine> getTabModelmachineTypemachines() {
        return tabModelmachineTypemachines;
    }

    public void setTabModelmachineTypemachines(ArrayList<RowModelmachineTypemachine> tabModelmachineTypemachines) {
        this.tabModelmachineTypemachines = tabModelmachineTypemachines;
    }


    public ArrayList<RowMachineModelmachine> getTabMachineModelmachines() {
        return tabMachineModelmachines;
    }

    public void setTabMachineModelmachines(ArrayList<RowMachineModelmachine> tabMachineModelmachines) {
        this.tabMachineModelmachines = tabMachineModelmachines;
    }

    public static String getPathConfig() {
        return pathConfig;
    }

    public static void setPathConfig(String pathConfig) {
        DataSet.pathConfig = pathConfig;
    }

    public String getPathDataDefault() {
        return pathDataDefault;
    }

    public void setPathDataDefault(String pathDataDefault) {
        this.pathDataDefault = pathDataDefault;
    }

    public DTS select(Field f) {
        DTS dts = new DTS();
        return dts;
    }

}
