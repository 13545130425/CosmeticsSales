package mybean.data;

import com.sun.rowset.CachedRowSetImpl;
/**
 * ���ڴ洢��Ʒ�����ݿ��¼
 */
public class DataByPage {
    CachedRowSetImpl rowSet=null;   //�洢����ȫ����¼���м�����
    private int pageSize=1;         //ÿҳ��ʾ�ļ�¼��
    private int totalPages=1;       //��ҳ�����ҳ��
    private int currentPage=1;      //��ǰ��ʾҳ
    public void setRowSet(CachedRowSetImpl set){
    	rowSet=set;
    }
    public CachedRowSetImpl getRowSet(){
    	return rowSet;
    }
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
    
}
