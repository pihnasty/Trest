package loader;

import loader.tabDataSet.RowTrest;
import loader.tabDataSet.RowTrestWork;
import loader.tabDataSet.RowWork;

import java.util.ArrayList;

public class DTS
{
	public ArrayList<RowTrest> getTabTrests()
	{
		return tabTrests;
	}
	public void setTabTrests(ArrayList<RowTrest> tabTrests)
	{
		this.tabTrests = tabTrests;
	}
	public ArrayList<RowWork> getTabWorks()
	{
		return tabWorks;
	}
	public void setTabWorks(ArrayList<RowWork> tabWorks)
	{
		this.tabWorks = tabWorks;
	}
	public ArrayList<RowTrestWork> getTabTrestsWorks()
	{
		return tabTrestsWorks;
	}
	public void setTabTrestsWorks(ArrayList<RowTrestWork> tabTrestsWorks)
	{
		this.tabTrestsWorks = tabTrestsWorks;
	}
	private ArrayList<RowTrest> tabTrests;
	private ArrayList<RowWork> tabWorks;
	private ArrayList<RowTrestWork> tabTrestsWorks;
}
