package loader.tabDataSet;

import loader.DataSet;

/**
 * �������� �������� ������ �������: ����������
 * @author ���
 *
 */
public class RowEmployee   extends RowIdNameDescription
{

	public RowEmployee()    {  super();  }

	/**
	 * �������������� ������ �������: ����������
	 * @param id			Id ����������
	 * @param name			��� ����������
	 * @param description	�������� ����������
	 */
	public RowEmployee(int id, String name, String description)   	 {	super(id, name, description);   }
	public RowEmployee (DataSet dataSet, Class cL)					 {	super( dataSet,  cL);			}

}
