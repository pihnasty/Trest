package test.trestview.table.tablemodel;

import org.junit.Test;
import persistence.loader.tabDataSet.RowLineroute;
import trestview.table.tablemodel.TableMolelBuilder;

import java.util.ArrayList;

public class TableMolelBuilderTest {

  //  public static <cL> AbstractFactoryTableModel build (ArrayList<cL> tab) {


        @Test
        public void build_test()  {

          ArrayList<RowLineroute> tabLineroutes = new ArrayList<RowLineroute>();

            System.out.println( TableMolelBuilder.build(tabLineroutes, RowLineroute.class) );


        }
}
